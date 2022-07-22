package ai.ecma.appwarehouseproject.controller;

import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.CategoryAddDTO;
import ai.ecma.appwarehouseproject.payload.CategoryInfoDTO;
import ai.ecma.appwarehouseproject.payload.CategoryUpdateDTO;
import ai.ecma.appwarehouseproject.utils.AppConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(CategoryController.CATEGORY_CONTROLLER_PATH)
public interface CategoryController {

    String CATEGORY_CONTROLLER_PATH = AppConstant.BASE_PATH + "/category/";

    String ADD = "add";
    String UPDATE = "update";
    String VIEW = "view";
    String DELETE = "delete";

    @PreAuthorize(value = "hasAuthority('VIEW_CATEGORIES')")
    @GetMapping(VIEW)
    ApiResult<List<CategoryInfoDTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size);

    @PreAuthorize(value = "hasAuthority('VIEW_CATEGORY')")
    @GetMapping(VIEW + "/{id}")
    ApiResult<CategoryInfoDTO> getOne(@PathVariable Long id);

    @PreAuthorize(value = "hasAuthority('ADD_CATEGORY')")
    @PostMapping(ADD)
    ApiResult<CategoryInfoDTO> add(@RequestBody CategoryAddDTO categoryAddDTO);

    @PreAuthorize(value = "hasAuthority('UPDATE_CATEGORY')")
    @PutMapping(UPDATE + "/{id}")
    ApiResult<CategoryInfoDTO> update(@RequestBody CategoryUpdateDTO categoryUpdateDTO, @PathVariable Long id);

    @PreAuthorize(value = "hasAuthority('DELETE_CATEGORY')")
    @DeleteMapping(DELETE + "/{id}")
    String delete(@PathVariable Long id);

}
