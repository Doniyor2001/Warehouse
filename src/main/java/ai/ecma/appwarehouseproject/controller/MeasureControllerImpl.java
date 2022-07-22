package ai.ecma.appwarehouseproject.controller;


import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.MeasureAddDTO;
import ai.ecma.appwarehouseproject.payload.MeasureInfoDTO;
import ai.ecma.appwarehouseproject.payload.MeasureUpdateDTO;
import ai.ecma.appwarehouseproject.service.interfaces.MeasureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MeasureControllerImpl implements MeasureController {

    private final MeasureService measureService;

    @Override
    public ApiResult<List<MeasureInfoDTO>> getAll(int page, int size) {
        return measureService.getAll(page,size);
    }

    @Override
    public ApiResult<MeasureInfoDTO> getOne(Long id) {
        return measureService.getOne(id);
    }

    @Override
    public ApiResult<MeasureInfoDTO> add(MeasureAddDTO measureAddDTO) {
        return measureService.add(measureAddDTO);
    }

    @Override
    public ApiResult<MeasureInfoDTO> update(MeasureUpdateDTO measureUpdateDTO, Long id) {
        return measureService.update(measureUpdateDTO,id);
    }

    @Override
    public String delete(Long id) {
        return measureService.delete(id);
    }
}
