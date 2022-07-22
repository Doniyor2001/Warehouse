package ai.ecma.appwarehouseproject.service;

import ai.ecma.appwarehouseproject.entity.Category;
import ai.ecma.appwarehouseproject.exception.RestException;
import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.CategoryAddDTO;
import ai.ecma.appwarehouseproject.payload.CategoryInfoDTO;
import ai.ecma.appwarehouseproject.payload.CategoryUpdateDTO;
import ai.ecma.appwarehouseproject.repository.CategoryRepository;
import ai.ecma.appwarehouseproject.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public ApiResult<List<CategoryInfoDTO>> getAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Category> categoryPage = categoryRepository.findAll(pageable);

        List<Category> categories = categoryPage.getContent();

        List<CategoryInfoDTO> categoryInfoList = categories
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());

        return ApiResult.successResponse(categoryInfoList);
    }

    @Override
    public ApiResult<CategoryInfoDTO> add(CategoryAddDTO categoryAddDTO) {
        checkName(categoryAddDTO.getName());
        Category category = new Category(
                categoryAddDTO.getName(),
                categoryAddDTO.getDescription()
        );
        categoryRepository.save(category);
        return returnApiResult(category, true, "success");
    }

    @Override
    public ApiResult<CategoryInfoDTO> update(CategoryUpdateDTO categoryUpdateDTO, Long id) {
        checkName(categoryUpdateDTO.getName(), id);
        Category category = getByIdOrElseThrow(id);
        category.setName(categoryUpdateDTO.getName());
        category.setDescription(categoryUpdateDTO.getDescription());
        categoryRepository.save(category);
        return returnApiResult(category, true, "success");
    }

    @Override
    public String delete(Long id) {
        Category category = getByIdOrElseThrow(id);
        categoryRepository.delete(category);
        return "Successfully deleted";
    }

    private CategoryInfoDTO entityToInfoDTO(Category category) {
        return new CategoryInfoDTO(
                category.getName(),
                category.getDescription()
        );
    }

    private void checkName(String name) {
        boolean exists = categoryRepository.existsByNameAndActiveTrue(name);
        if (exists) throw RestException.alreadyExist("Category");
    }

    private void checkName(String name, Long id) {
        boolean exists = categoryRepository.existsByNameAndIdNot(name, id);
        if (exists) throw RestException.alreadyExist("Category");
    }

    private ApiResult<CategoryInfoDTO> returnApiResult(Category category, boolean success, String msg) {
        CategoryInfoDTO categoryInfoDTO = entityToInfoDTO(category);
        return new ApiResult<>(categoryInfoDTO, success, msg);
    }

    @Override
    public Category getByIdOrElseThrow(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> RestException.notFound("Category"));
    }

    @Override
    public ApiResult<CategoryInfoDTO> getOne(Long id) {
        Category category = getByIdOrElseThrow(id);
        CategoryInfoDTO categoryInfoDTO = entityToInfoDTO(category);
        return ApiResult.successResponse(categoryInfoDTO);
    }

}
