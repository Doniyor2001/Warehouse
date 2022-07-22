package ai.ecma.appwarehouseproject.controller;

import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.OutputProductAddDTO;
import ai.ecma.appwarehouseproject.payload.OutputProductInfoDTO;
import ai.ecma.appwarehouseproject.payload.OutputProductUpdateDTO;
import ai.ecma.appwarehouseproject.service.interfaces.OutputProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OutputProductControllerImpl implements OutputProductController{

    private final OutputProductService outputProductService;

    @Override
    public ApiResult<OutputProductInfoDTO> getOne(Long id) {
        return outputProductService.getOne(id);
    }

    @Override
    public ApiResult<?> getAll(int page, int size) {
        return outputProductService.getAll(page, size);
    }

    @Override
    public ApiResult<OutputProductInfoDTO> add(OutputProductAddDTO outputProductAddDTO) {
        return outputProductService.add(outputProductAddDTO);
    }

    @Override
    public ApiResult<OutputProductInfoDTO> update(OutputProductUpdateDTO outputProductUpdateDTO, Long id) {
        return outputProductService.update(outputProductUpdateDTO,id);
    }
}


