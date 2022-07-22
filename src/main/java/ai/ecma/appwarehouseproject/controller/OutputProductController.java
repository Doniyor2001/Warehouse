package ai.ecma.appwarehouseproject.controller;

import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.OutputProductAddDTO;
import ai.ecma.appwarehouseproject.payload.OutputProductInfoDTO;
import ai.ecma.appwarehouseproject.payload.OutputProductUpdateDTO;
import ai.ecma.appwarehouseproject.utils.AppConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping(OutputProductController.OUTPUT_PRODUCT_CONTROLLER_PATH)
public interface OutputProductController {

    String OUTPUT_PRODUCT_CONTROLLER_PATH = AppConstant.BASE_PATH + "/output-product";

    String ADD = "add";
    String VIEW = "view";
    String UPDATE = "update";


    @PreAuthorize(value = "hasAuthority('VIEW_OUTPUT_PRODUCT')")
    @GetMapping(VIEW + "/{id}")
    ApiResult<OutputProductInfoDTO> getOne(@PathVariable Long id);

    @PreAuthorize(value = "hasAuthority('VIEWS_OUTPUT_PRODUCTS')")
    @GetMapping(VIEW)
    ApiResult<?> getAll(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size);

    @PreAuthorize(value = "hasAuthority('ADD_OUTPUT_PRODUCT')")
    @PostMapping(ADD)
    ApiResult<OutputProductInfoDTO> add(@RequestBody OutputProductAddDTO outputProductAddDTO);

    @PreAuthorize(value = "hasAuthority('UPDATE_OUTPUT_PRODUCT')")
    @PutMapping(UPDATE + "/{id}")
    ApiResult<OutputProductInfoDTO> update(OutputProductUpdateDTO outputProductUpdateDTO, @PathVariable Long id);



}
