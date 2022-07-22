package ai.ecma.appwarehouseproject.controller;

import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.ProductAddDTO;
import ai.ecma.appwarehouseproject.payload.ProductInfoDTO;
import ai.ecma.appwarehouseproject.payload.ProductUpdateDTO;
import ai.ecma.appwarehouseproject.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController{

    private final ProductService productService;

    @Override
    public ApiResult<List<ProductInfoDTO>> getAll(int page, int size) {
        return productService.getAll(page, size);
    }

    @Override
    public ApiResult<ProductInfoDTO> getOne(Long id) {
        return productService.getOne(id);
    }

    @Override
    public ApiResult<ProductInfoDTO> add(ProductAddDTO productAddDTO) {
        return productService.add(productAddDTO);
    }

    @Override
    public ApiResult<ProductInfoDTO> update(ProductUpdateDTO productUpdateDTO, Long id) {
        return productService.update(productUpdateDTO, id);
    }

    @Override
    public String delete(Long id) {
        return productService.delete(id);
    }
}
