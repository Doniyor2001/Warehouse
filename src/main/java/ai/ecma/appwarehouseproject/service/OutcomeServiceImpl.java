package ai.ecma.appwarehouseproject.service;

import ai.ecma.appwarehouseproject.entity.Currency;
import ai.ecma.appwarehouseproject.entity.Outcome;
import ai.ecma.appwarehouseproject.entity.Warehouse;
import ai.ecma.appwarehouseproject.exception.RestException;
import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.OutcomeAddDTO;
import ai.ecma.appwarehouseproject.payload.OutcomeInfoDTO;
import ai.ecma.appwarehouseproject.payload.OutcomeUpdateDTO;
import ai.ecma.appwarehouseproject.repository.OutcomeRepository;
import ai.ecma.appwarehouseproject.service.interfaces.CurrencyService;
import ai.ecma.appwarehouseproject.service.interfaces.OutputProductService;
import ai.ecma.appwarehouseproject.service.interfaces.OutcomeService;
import ai.ecma.appwarehouseproject.service.interfaces.WarehouseService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OutcomeServiceImpl implements OutcomeService {

    private final OutcomeRepository outcomeRepository;

    private final OutputProductService outputProductService;

    private final WarehouseService warehouseService;

    private final CurrencyService currencyService;

    public OutcomeServiceImpl(OutcomeRepository outcomeRepository,
                              @Lazy OutputProductService outputProductService,
                              WarehouseService warehouseService,
                              CurrencyService currencyService) {
        this.outcomeRepository = outcomeRepository;
        this.outputProductService = outputProductService;
        this.warehouseService = warehouseService;
        this.currencyService = currencyService;
    }


    @Override
    public ApiResult<OutcomeInfoDTO> getOne(Long id) {
        Outcome outcome = getByIdOrElseThrow(id);
        OutcomeInfoDTO outcomeInfoDTO = entityToInfoDTO(outcome);
        return ApiResult.successResponse(outcomeInfoDTO);
    }

    @Override
    public ApiResult<List<OutcomeInfoDTO>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Outcome> outcomePage = outcomeRepository.findAll(pageable);
        List<Outcome> outcomeList = outcomePage.getContent();
        List<OutcomeInfoDTO> outcomeInfoDTOS = outcomeList
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(outcomeInfoDTOS);
    }

    @Override
    public ApiResult<OutcomeInfoDTO> update(OutcomeUpdateDTO outcomeUpdateDTO, Long id) {
        Outcome outcome = getByIdOrElseThrow(id);
        Currency currency = currencyService.getByIdOrElseThrow(outcomeUpdateDTO.getCurrencyId());
        Warehouse warehouse = warehouseService.getByIdOrElseThrow(outcomeUpdateDTO.getWarehouseId());
        outcome.setWarehouse(warehouse);
        outcome.setCurrency(currency);
        outcomeRepository.save(outcome);
        return ApiResult.successResponse(entityToInfoDTO(outcome));
    }

    @Override
    public ApiResult<OutcomeInfoDTO> add(OutcomeAddDTO outcomeAddDTO) {
        Warehouse warehouse = warehouseService.getByIdOrElseThrow(outcomeAddDTO.getWarehouseId());
        Currency currency = currencyService.getByIdOrElseThrow(outcomeAddDTO.getCurrencyId());
        String factureNumber = generateSerialNumber();
        Outcome outcome = new Outcome(
                warehouse,
                currency,
                factureNumber
        );
        outcomeRepository.save(outcome);
        return ApiResult.successResponse(entityToInfoDTO(outcome));
    }


    private String generateSerialNumber() {
        String code = String.valueOf((int) (Math.random() * 1_000_000_000));
        return code.substring(0, 6);
    }

    public Outcome getByIdOrElseThrow(Long id) {
        return outcomeRepository.findById(id).orElseThrow(
                () -> RestException.notFound("Outcome")
        );
    }

    public OutcomeInfoDTO entityToInfoDTO(Outcome outcome) {
        return new OutcomeInfoDTO(
                outcome.getWarehouse().getId(),
                outcome.getCurrency().getId(),
                outcome.getFactureNumber()
        );
    }

}
