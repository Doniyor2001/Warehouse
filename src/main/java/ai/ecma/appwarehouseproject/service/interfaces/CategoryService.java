package ai.ecma.appwarehouseproject.service.interfaces;

import ai.ecma.appwarehouseproject.entity.Category;
import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.CategoryAddDTO;
import ai.ecma.appwarehouseproject.payload.CategoryInfoDTO;
import ai.ecma.appwarehouseproject.payload.CategoryUpdateDTO;

import java.util.List;

public interface CategoryService {

    ApiResult<List<CategoryInfoDTO>> getAll(int page, int size);

    ApiResult<CategoryInfoDTO> add(CategoryAddDTO categoryAddDTO);

    ApiResult<CategoryInfoDTO> update(CategoryUpdateDTO categoryUpdateDTO, Long id);

    String delete(Long id);

    Category getByIdOrElseThrow(Long categoryId);

    ApiResult<CategoryInfoDTO> getOne(Long id);
}
