package ai.ecma.appwarehouseproject.service.interfaces;


import ai.ecma.appwarehouseproject.entity.InputProduct;
import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.InputProductAddDTO;
import ai.ecma.appwarehouseproject.payload.InputProductInfoDTO;
import ai.ecma.appwarehouseproject.payload.InputProductUpdateDTO;

public interface InputProductService {

    ApiResult<?> getAll(int page, int size);

    ApiResult<InputProductInfoDTO> add(InputProductAddDTO inputProductAddDTO);

    ApiResult<InputProductInfoDTO> update(InputProductUpdateDTO inputProductUpdateDTO, Long id);

    InputProduct getByIdOrElseThrow(Long id);

    ApiResult<InputProductInfoDTO> getOne(Long id);
}
