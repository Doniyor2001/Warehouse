package ai.ecma.appwarehouseproject.controller;

import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.CategoryAddDTO;
import ai.ecma.appwarehouseproject.payload.CategoryInfoDTO;
import ai.ecma.appwarehouseproject.payload.CategoryUpdateDTO;
import ai.ecma.appwarehouseproject.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CategoryControllerImpl implements CategoryController{

    private final CategoryService categoryService;

    @Override
    public ApiResult<List<CategoryInfoDTO>> getAll(int page, int size) {
        return categoryService.getAll(page,size);
    }

    @Override
    public ApiResult<CategoryInfoDTO> getOne(Long id) {
        return categoryService.getOne(id);
    }

    @Override
    public ApiResult<CategoryInfoDTO> add(CategoryAddDTO categoryAddDTO) {
        return categoryService.add(categoryAddDTO);
    }

    @Override
    public ApiResult<CategoryInfoDTO> update(CategoryUpdateDTO categoryUpdateDTO, Long id) {
        return categoryService.update(categoryUpdateDTO,id);
    }

    @Override
    public String delete(Long id) {
        return categoryService.delete(id);
    }
}
