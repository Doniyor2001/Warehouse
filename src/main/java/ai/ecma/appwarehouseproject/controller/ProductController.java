package ai.ecma.appwarehouseproject.controller;

import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.ProductAddDTO;
import ai.ecma.appwarehouseproject.payload.ProductInfoDTO;
import ai.ecma.appwarehouseproject.payload.ProductUpdateDTO;
import ai.ecma.appwarehouseproject.utils.AppConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ProductController.PRODUCT_CONTROLLER_PATH)
public interface ProductController {

    String PRODUCT_CONTROLLER_PATH = AppConstant.BASE_PATH + "/product/";
    String ADD = "add";
    String UPDATE = "update";
    String DELETE = "delete";
    String VIEW = "view";

    @PreAuthorize(value = "hasAuthority('VIEW_PRODUCTS')")
    @GetMapping(VIEW)
    ApiResult<List<ProductInfoDTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size);

    @PreAuthorize(value = "hasAuthority('VIEW_PRODUCT')")
    @GetMapping(VIEW + "/{id}")
    ApiResult<ProductInfoDTO> getOne(@PathVariable Long id);

    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping(ADD)
    ApiResult<ProductInfoDTO> add(@RequestBody ProductAddDTO productAddDTO);

    @PreAuthorize(value = "hasAuthority('UPDATE_PRODUCT')")
    @PutMapping(UPDATE + "/{id}")
    ApiResult<ProductInfoDTO> update(@RequestBody ProductUpdateDTO productUpdateDTO, @PathVariable Long id);

    @PreAuthorize(value = "hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping(DELETE + "/{id}")
    String delete(@PathVariable Long id);

}
