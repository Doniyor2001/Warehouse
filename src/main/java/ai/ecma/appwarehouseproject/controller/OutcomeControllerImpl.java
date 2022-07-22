package ai.ecma.appwarehouseproject.controller;

import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.OutcomeAddDTO;
import ai.ecma.appwarehouseproject.payload.OutcomeInfoDTO;
import ai.ecma.appwarehouseproject.payload.OutcomeUpdateDTO;
import ai.ecma.appwarehouseproject.service.interfaces.OutcomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OutcomeControllerImpl implements OutcomeController{

    private final OutcomeService outcomeService;

    @Override
    public ApiResult<List<OutcomeInfoDTO>> getAll(int page, int size) {
        return outcomeService.getAll(page, size);
    }

    @Override
    public ApiResult<OutcomeInfoDTO> getOne(Long id) {
        return outcomeService.getOne(id);
    }

    @Override
    public ApiResult<OutcomeInfoDTO> add(OutcomeAddDTO outcomeAddDTO) {
        return outcomeService.add(outcomeAddDTO);
    }

    @Override
    public ApiResult<OutcomeInfoDTO> update(OutcomeUpdateDTO outcomeUpdateDTO, Long id) {
        return outcomeService.update(outcomeUpdateDTO, id);
    }
}
