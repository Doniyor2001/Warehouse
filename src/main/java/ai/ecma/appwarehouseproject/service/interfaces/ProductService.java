package ai.ecma.appwarehouseproject.service.interfaces;

import ai.ecma.appwarehouseproject.entity.Product;
import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.ProductAddDTO;
import ai.ecma.appwarehouseproject.payload.ProductInfoDTO;
import ai.ecma.appwarehouseproject.payload.ProductUpdateDTO;

import java.util.List;

public interface ProductService {

    ApiResult<List<ProductInfoDTO>> getAll(int page, int size);

    ApiResult<ProductInfoDTO> add(ProductAddDTO productAddDTO);

    ApiResult<ProductInfoDTO> update(ProductUpdateDTO productUpdateDTO, Long id);

    String delete(Long id);

    Product getByIdOrElseThrow(Long id);

    ApiResult<ProductInfoDTO> getOne(Long id);
}
