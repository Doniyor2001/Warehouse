package ai.ecma.appwarehouseproject.controller;

import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.InputProductAddDTO;
import ai.ecma.appwarehouseproject.payload.InputProductInfoDTO;
import ai.ecma.appwarehouseproject.payload.InputProductUpdateDTO;
import ai.ecma.appwarehouseproject.utils.AppConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(InputProductController.INPUT_PRODUCT_CONTROLLER_PATH)
public interface InputProductController {

    String INPUT_PRODUCT_CONTROLLER_PATH = AppConstant.BASE_PATH + "/input-product";

    String ADD = "add";
    String VIEW = "view";
    String UPDATE = "update";


    @PreAuthorize(value = "hasAuthority('VIEW_INPUT_PRODUCT')")
    @GetMapping(VIEW + "/{id}")
    ApiResult<InputProductInfoDTO> getOne(@PathVariable Long id);

    @PreAuthorize(value = "hasAuthority('VIEWS_INPUT_PRODUCTS')")
    @GetMapping(VIEW)
    ApiResult<?> getAll(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size);

    @PreAuthorize(value = "hasAuthority('ADD_INPUT_PRODUCT')")
    @PostMapping(ADD)
    ApiResult<InputProductInfoDTO> add(@RequestBody InputProductAddDTO inputProductAddDTO);

    @PreAuthorize(value = "hasAuthority('UPDATE_INPUT_PRODUCT')")
    @PutMapping(UPDATE + "/{id}")
    ApiResult<InputProductInfoDTO> update(InputProductUpdateDTO inputProductUpdateDTO, @PathVariable Long id);



}
