package ai.ecma.appwarehouseproject.service;

import ai.ecma.appwarehouseproject.entity.Income;
import ai.ecma.appwarehouseproject.entity.InputProduct;
import ai.ecma.appwarehouseproject.entity.Measure;
import ai.ecma.appwarehouseproject.entity.Product;
import ai.ecma.appwarehouseproject.exception.RestException;
import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.InputProductAddDTO;
import ai.ecma.appwarehouseproject.payload.InputProductInfoDTO;
import ai.ecma.appwarehouseproject.payload.InputProductUpdateDTO;
import ai.ecma.appwarehouseproject.repository.InputProductRepository;
import ai.ecma.appwarehouseproject.service.interfaces.IncomeService;
import ai.ecma.appwarehouseproject.service.interfaces.InputProductService;
import ai.ecma.appwarehouseproject.service.interfaces.MeasureService;
import ai.ecma.appwarehouseproject.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InputProductServiceImpl implements InputProductService {

    private final InputProductRepository inputProductRepository;
    private final ProductService productService;
    private final MeasureService measureService;

    private final IncomeService incomeService;

    @Override
    public ApiResult<?> getAll(int page, int size) {

        Sort sort = Sort.by(Sort.Direction.ASC, InputProduct.Fields.product);

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<InputProduct> currentProductPage = inputProductRepository.findAll(pageable);

        List<InputProduct> inputProductList = currentProductPage.getContent();

        List<InputProductInfoDTO> inputProductInfoDTOList = inputProductList
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());
        Page<InputProductInfoDTO> currentProductInfoDTOPage = new PageImpl<>(
                inputProductInfoDTOList,
                currentProductPage.getPageable(),
                currentProductPage.getTotalElements()
        );

        return ApiResult.successResponse(currentProductInfoDTOPage);
    }

    @Override
    public ApiResult<InputProductInfoDTO> add(InputProductAddDTO inputProductAddDTO) {
        Product product = productService.getByIdOrElseThrow(inputProductAddDTO.getProductId());
        Measure measure = measureService.getByIdOrElseThrow(inputProductAddDTO.getMeasureId());
        Income income = incomeService.getByIdOrElseThrow(inputProductAddDTO.getIncomeId());
        Double finalPrice = (inputProductAddDTO.getAmount() * inputProductAddDTO.getPrice());
        InputProduct inputProduct = new InputProduct(
                product,
                measure,
                inputProductAddDTO.getAmount(),
                inputProductAddDTO.getPrice(),
                inputProductAddDTO.getExpiredDate(),
                income,
                finalPrice
        );
        inputProductRepository.save(inputProduct);
        return ApiResult.successResponse(entityToInfoDTO(inputProduct));
    }

    @Override
    public ApiResult<InputProductInfoDTO> update(InputProductUpdateDTO inputProductUpdateDTO, Long id) {
        InputProduct inputProduct = getByIdOrElseThrow(id);
        Product product = productService.getByIdOrElseThrow(inputProductUpdateDTO.getProductId());
        Measure measure = measureService.getByIdOrElseThrow(inputProductUpdateDTO.getMeasureId());
        Income income = incomeService.getByIdOrElseThrow(inputProductUpdateDTO.getIncomeId());
        inputProduct.setProduct(product);
        inputProduct.setMeasure(measure);
        inputProduct.setAmount(inputProductUpdateDTO.getAmount());
        inputProduct.setPrice(inputProductUpdateDTO.getPrice());
        inputProduct.setExpiredDate(inputProduct.getExpiredDate());
        inputProduct.setIncome(income);
        inputProduct.setFinalPrice(inputProductUpdateDTO.getAmount() * inputProductUpdateDTO.getPrice());
        inputProductRepository.save(inputProduct);
        return ApiResult.successResponse(entityToInfoDTO(inputProduct));
    }


    public InputProduct getByIdOrElseThrow(Long currentProductId) {
        return inputProductRepository.findById(currentProductId).orElseThrow(
                () -> RestException.notFound("InputProduct"));
    }

    @Override
    public ApiResult<InputProductInfoDTO> getOne(Long id) {
        return ApiResult.successResponse(entityToInfoDTO(getByIdOrElseThrow(id)));
    }

    private InputProductInfoDTO entityToInfoDTO(InputProduct inputProduct) {
        return new InputProductInfoDTO(
                inputProduct.getProduct().getId(),
                inputProduct.getMeasure().getId(),
                inputProduct.getAmount(),
                inputProduct.getPrice(),
                inputProduct.getExpiredDate(),
                inputProduct.getIncome().getId(),
                inputProduct.getFinalPrice()
        );
    }
}
