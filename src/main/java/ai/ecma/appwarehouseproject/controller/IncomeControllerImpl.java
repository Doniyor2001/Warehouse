package ai.ecma.appwarehouseproject.controller;

import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.IncomeAddDTO;
import ai.ecma.appwarehouseproject.payload.IncomeInfoDTO;
import ai.ecma.appwarehouseproject.payload.IncomeUpdateDTO;
import ai.ecma.appwarehouseproject.service.interfaces.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class IncomeControllerImpl implements IncomeController{

    private final IncomeService incomeService;

    @Override
    public ApiResult<List<IncomeInfoDTO>> getAll(int page, int size) {
        return incomeService.getAll(page, size);
    }

    @Override
    public ApiResult<IncomeInfoDTO> getOne(Long id) {
        return incomeService.getOne(id);
    }

    @Override
    public ApiResult<IncomeInfoDTO> add(IncomeAddDTO incomeAddDTO) {
        return incomeService.add(incomeAddDTO);
    }

    @Override
    public ApiResult<IncomeInfoDTO> update(IncomeUpdateDTO incomeUpdateDTO, Long id) {
        return incomeService.update(incomeUpdateDTO, id);
    }
}
