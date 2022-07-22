package ai.ecma.appwarehouseproject.service.interfaces;

import ai.ecma.appwarehouseproject.entity.Measure;
import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.MeasureAddDTO;
import ai.ecma.appwarehouseproject.payload.MeasureInfoDTO;
import ai.ecma.appwarehouseproject.payload.MeasureUpdateDTO;

import java.util.List;

public interface MeasureService {

    ApiResult<List<MeasureInfoDTO>> getAll(int page, int size);

    ApiResult<MeasureInfoDTO> add(MeasureAddDTO measureAddDTO);

    ApiResult<MeasureInfoDTO> update(MeasureUpdateDTO measureUpdateDTO, Long id);

    String delete(Long id);

    Measure getByIdOrElseThrow(Long id);

    ApiResult<MeasureInfoDTO> getOne(Long id);
}
