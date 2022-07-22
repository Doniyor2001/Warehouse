package ai.ecma.appwarehouseproject.service;

import ai.ecma.appwarehouseproject.entity.Attachment;
import ai.ecma.appwarehouseproject.entity.Category;
import ai.ecma.appwarehouseproject.entity.Measure;
import ai.ecma.appwarehouseproject.entity.Product;
import ai.ecma.appwarehouseproject.exception.RestException;
import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.ProductAddDTO;
import ai.ecma.appwarehouseproject.payload.ProductInfoDTO;
import ai.ecma.appwarehouseproject.payload.ProductUpdateDTO;
import ai.ecma.appwarehouseproject.repository.ProductRepository;
import ai.ecma.appwarehouseproject.service.interfaces.AttachmentService;
import ai.ecma.appwarehouseproject.service.interfaces.CategoryService;
import ai.ecma.appwarehouseproject.service.interfaces.MeasureService;
import ai.ecma.appwarehouseproject.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final MeasureService measureService;
    private final AttachmentService attachmentService;

    @Override
    public ApiResult<List<ProductInfoDTO>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Product> productPage = productRepository.findAll(pageable);
        List<Product> products = productPage.getContent();
        List<ProductInfoDTO> productInfoDTOS = products
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(productInfoDTOS);
    }

    @Override
    public ApiResult<ProductInfoDTO> add(ProductAddDTO productAddDTO) {
        checkName(productAddDTO.getName());
        Category category = categoryService.getByIdOrElseThrow(productAddDTO.getCategoryId());
        Measure measure = measureService.getByIdOrElseThrow(productAddDTO.getMeasureId());
        Attachment attachment = attachmentService.getByOrElseThrow(productAddDTO.getAttachmentId());
        Product product = new Product(
                productAddDTO.getName(),
                category,
                measure,
                attachment
        );
        productRepository.save(product);
        return returnApiResult(product);
    }

    @Override
    public ApiResult<ProductInfoDTO> update(ProductUpdateDTO productUpdateDTO, Long id) {
        checkName(productUpdateDTO.getName(),id);
        Product product = getByIdOrElseThrow(id);
        Attachment attachment = attachmentService.getByOrElseThrow(productUpdateDTO.getAttachmentId());
        Measure measure = measureService.getByIdOrElseThrow(productUpdateDTO.getMeasureId());
        Category category = categoryService.getByIdOrElseThrow(productUpdateDTO.getCategoryId());
        product.setAttachment(attachment);
        product.setCategory(category);
        product.setMeasure(measure);
        product.setName(productUpdateDTO.getName());
        productRepository.save(product);
        return returnApiResult(product);
    }

    @Override
    public String delete(Long id) {
        Product product = getByIdOrElseThrow(id);
        productRepository.delete(product);
        return "SUCCESS";
    }

    private void checkName(String name) {
        boolean exists = productRepository.existsByName(name);
        if (exists) throw RestException.alreadyExist("Product");
    }

    private void checkName(String name, Long id) {
        boolean exists = productRepository.existsByNameAndIdNot(name,id);
        if (exists) throw RestException.alreadyExist("Product");
    }

    @Override
    public Product getByIdOrElseThrow(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> RestException.notFound("Warehouse")
        );
    }

    @Override
    public ApiResult<ProductInfoDTO> getOne(Long id) {
        Product product = getByIdOrElseThrow(id);
        ProductInfoDTO productInfoDTO = entityToInfoDTO(product);
        return ApiResult.successResponse(productInfoDTO);
    }

    private ApiResult<ProductInfoDTO> returnApiResult(Product product) {
        ProductInfoDTO productInfoDTO = new ProductInfoDTO(
                product.getName(),
                product.getCategory().getId(),
                product.getAttachment().getId(),
                product.getMeasure().getId()
        );
        return new ApiResult<>(productInfoDTO,true);
    }

    private ProductInfoDTO entityToInfoDTO(Product product) {
        return new ProductInfoDTO(
                product.getName(),
                product.getCategory().getId(),
                product.getAttachment().getId(),
                product.getMeasure().getId()
        );
    }

}
