package ai.ecma.appwarehouseproject.controller;

import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.InputProductAddDTO;
import ai.ecma.appwarehouseproject.payload.InputProductInfoDTO;
import ai.ecma.appwarehouseproject.payload.InputProductUpdateDTO;
import ai.ecma.appwarehouseproject.service.interfaces.InputProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InputProductControllerImpl implements InputProductController{

    private final InputProductService inputProductService;

    @Override
    public ApiResult<InputProductInfoDTO> getOne(Long id) {
        return inputProductService.getOne(id);
    }

    @Override
    public ApiResult<?> getAll(int page, int size) {
        return inputProductService.getAll(page, size);
    }

    @Override
    public ApiResult<InputProductInfoDTO> add(InputProductAddDTO inputProductAddDTO) {
        return inputProductService.add(inputProductAddDTO);
    }

    @Override
    public ApiResult<InputProductInfoDTO> update(InputProductUpdateDTO inputProductUpdateDTO, Long id) {
        return inputProductService.update(inputProductUpdateDTO,id);
    }
}


