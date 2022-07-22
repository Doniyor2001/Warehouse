package ai.ecma.appwarehouseproject.service.interfaces;


import ai.ecma.appwarehouseproject.entity.OutputProduct;
import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.OutputProductAddDTO;
import ai.ecma.appwarehouseproject.payload.OutputProductInfoDTO;
import ai.ecma.appwarehouseproject.payload.OutputProductUpdateDTO;

public interface OutputProductService {

    ApiResult<?> getAll(int page, int size);

    ApiResult<OutputProductInfoDTO> add(OutputProductAddDTO outputProductAddDTO);

    ApiResult<OutputProductInfoDTO> update(OutputProductUpdateDTO outputProductUpdateDTO, Long id);

    OutputProduct getByIdOrElseThrow(Long id);

    ApiResult<OutputProductInfoDTO> getOne(Long id);
}
