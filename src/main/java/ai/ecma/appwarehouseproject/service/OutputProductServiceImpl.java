package ai.ecma.appwarehouseproject.service;

import ai.ecma.appwarehouseproject.entity.*;
import ai.ecma.appwarehouseproject.exception.RestException;
import ai.ecma.appwarehouseproject.payload.*;
import ai.ecma.appwarehouseproject.repository.OutputProductRepository;
import ai.ecma.appwarehouseproject.service.interfaces.OutcomeService;
import ai.ecma.appwarehouseproject.service.interfaces.OutputProductService;
import ai.ecma.appwarehouseproject.service.interfaces.MeasureService;
import ai.ecma.appwarehouseproject.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OutputProductServiceImpl implements OutputProductService {

    private final OutputProductRepository outputProductRepository;
    private final ProductService productService;
    private final MeasureService measureService;

    private final OutcomeService outcomeService;

    @Override
    public ApiResult<?> getAll(int page, int size) {

        Sort sort = Sort.by(Sort.Direction.ASC, OutputProduct.Fields.product);

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<OutputProduct> currentProductPage = outputProductRepository.findAll(pageable);

        List<OutputProduct> outputProductList = currentProductPage.getContent();

        List<OutputProductInfoDTO> outputProductInfoDTOList = outputProductList
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());
        Page<OutputProductInfoDTO> currentProductInfoDTOPage = new PageImpl<>(
                outputProductInfoDTOList,
                currentProductPage.getPageable(),
                currentProductPage.getTotalElements()
        );

        return ApiResult.successResponse(currentProductInfoDTOPage);
    }

    @Override
    public ApiResult<OutputProductInfoDTO> add(OutputProductAddDTO outputProductAddDTO) {
        Product product = productService.getByIdOrElseThrow(outputProductAddDTO.getProductId());
        Measure measure = measureService.getByIdOrElseThrow(outputProductAddDTO.getMeasureId());
        Outcome outcome = outcomeService.getByIdOrElseThrow(outputProductAddDTO.getOutcomeId());
        Double finalPrice = (outputProductAddDTO.getAmount() * outputProductAddDTO.getPrice());
        OutputProduct outputProduct = new OutputProduct(
                product,
                measure,
                outputProductAddDTO.getAmount(),
                outputProductAddDTO.getPrice(),
                outcome,
                finalPrice
        );
        outputProductRepository.save(outputProduct);
        return ApiResult.successResponse(entityToInfoDTO(outputProduct));
    }

    @Override
    public ApiResult<OutputProductInfoDTO> update(OutputProductUpdateDTO outputProductUpdateDTO, Long id) {
        OutputProduct outputProduct = getByIdOrElseThrow(id);
        Product product = productService.getByIdOrElseThrow(outputProductUpdateDTO.getProductId());
        Measure measure = measureService.getByIdOrElseThrow(outputProductUpdateDTO.getMeasureId());
        Outcome outcome = outcomeService.getByIdOrElseThrow(outputProductUpdateDTO.getOutcomeId());
        outputProduct.setProduct(product);
        outputProduct.setMeasure(measure);
        outputProduct.setAmount(outputProductUpdateDTO.getAmount());
        outputProduct.setPrice(outputProductUpdateDTO.getPrice());
        outputProduct.setOutcome(outcome);
        outputProduct.setFinalPrice(outputProductUpdateDTO.getAmount() * outputProductUpdateDTO.getPrice());
        outputProductRepository.save(outputProduct);
        return ApiResult.successResponse(entityToInfoDTO(outputProduct));
    }


    public OutputProduct getByIdOrElseThrow(Long currentProductId) {
        return outputProductRepository.findById(currentProductId).orElseThrow(
                () -> RestException.notFound("OutputProduct"));
    }

    @Override
    public ApiResult<OutputProductInfoDTO> getOne(Long id) {
        return ApiResult.successResponse(entityToInfoDTO(getByIdOrElseThrow(id)));
    }

    private OutputProductInfoDTO entityToInfoDTO(OutputProduct outputProduct) {
        return new OutputProductInfoDTO(
                outputProduct.getProduct().getId(),
                outputProduct.getMeasure().getId(),
                outputProduct.getAmount(),
                outputProduct.getPrice(),
                outputProduct.getOutcome().getId(),
                outputProduct.getFinalPrice()
        );
    }
}
