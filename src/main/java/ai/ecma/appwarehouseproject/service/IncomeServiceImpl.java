package ai.ecma.appwarehouseproject.service;

import ai.ecma.appwarehouseproject.entity.Currency;
import ai.ecma.appwarehouseproject.entity.Income;
import ai.ecma.appwarehouseproject.entity.Supplier;
import ai.ecma.appwarehouseproject.entity.Warehouse;
import ai.ecma.appwarehouseproject.exception.RestException;
import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.IncomeAddDTO;
import ai.ecma.appwarehouseproject.payload.IncomeInfoDTO;
import ai.ecma.appwarehouseproject.payload.IncomeUpdateDTO;
import ai.ecma.appwarehouseproject.repository.IncomeRepository;
import ai.ecma.appwarehouseproject.service.interfaces.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;

    private final InputProductService inputProductService;

    private final WarehouseService warehouseService;

    private final CurrencyService currencyService;

    private final SupplierService supplierService;

    public IncomeServiceImpl(IncomeRepository incomeRepository,
                             @Lazy InputProductService inputProductService,
                             WarehouseService warehouseService,
                             CurrencyService currencyService,
                             SupplierService supplierService) {
        this.incomeRepository = incomeRepository;
        this.inputProductService = inputProductService;
        this.warehouseService = warehouseService;
        this.currencyService = currencyService;
        this.supplierService = supplierService;
    }


    @Override
    public ApiResult<IncomeInfoDTO> getOne(Long id) {
        Income income = getByIdOrElseThrow(id);
        IncomeInfoDTO incomeInfoDTO = entityToInfoDTO(income);
        return ApiResult.successResponse(incomeInfoDTO);
    }

    @Override
    public ApiResult<List<IncomeInfoDTO>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Income> incomePage = incomeRepository.findAll(pageable);
        List<Income> incomeList = incomePage.getContent();
        List<IncomeInfoDTO> incomeInfoDTOS = incomeList
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(incomeInfoDTOS);
    }

    @Override
    public ApiResult<IncomeInfoDTO> update(IncomeUpdateDTO incomeUpdateDTO, Long id) {
        Income income = getByIdOrElseThrow(id);
        Supplier supplier = supplierService.getByIdOrElseThrow(incomeUpdateDTO.getSupplierId());
        Currency currency = currencyService.getByIdOrElseThrow(incomeUpdateDTO.getCurrencyId());
        Warehouse warehouse = warehouseService.getByIdOrElseThrow(incomeUpdateDTO.getWarehouseId());
        income.setWarehouse(warehouse);
        income.setSupplier(supplier);
        income.setCurrency(currency);
        incomeRepository.save(income);
        return ApiResult.successResponse(entityToInfoDTO(income));
    }

    @Override
    public ApiResult<IncomeInfoDTO> add(IncomeAddDTO incomeAddDTO) {
        Warehouse warehouse = warehouseService.getByIdOrElseThrow(incomeAddDTO.getWarehouseId());
        Supplier supplier = supplierService.getByIdOrElseThrow(incomeAddDTO.getSupplierId());
        Currency currency = currencyService.getByIdOrElseThrow(incomeAddDTO.getCurrencyId());
        String factureNumber = generateSerialNumber();
        Income income = new Income(
                warehouse,
                supplier,
                currency,
                factureNumber
        );
        incomeRepository.save(income);
        return ApiResult.successResponse(entityToInfoDTO(income));
    }


    private String generateSerialNumber(){
        String code = String.valueOf((int)(Math.random()*1_000_000_000));
        return code.substring(0,6);
    }

    public Income getByIdOrElseThrow(Long id) {
        return incomeRepository.findById(id).orElseThrow(
                () -> RestException.notFound("Income")
        );
    }

    public IncomeInfoDTO entityToInfoDTO(Income income) {
        return new IncomeInfoDTO(
                income.getWarehouse().getId(),
                income.getSupplier().getId(),
                income.getCurrency().getId(),
                income.getFactureNumber()
        );
    }

}
