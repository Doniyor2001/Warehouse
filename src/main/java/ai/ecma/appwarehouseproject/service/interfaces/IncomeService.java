package ai.ecma.appwarehouseproject.service.interfaces;

import ai.ecma.appwarehouseproject.entity.Income;
import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.IncomeAddDTO;
import ai.ecma.appwarehouseproject.payload.IncomeInfoDTO;
import ai.ecma.appwarehouseproject.payload.IncomeUpdateDTO;

import java.util.List;

public interface IncomeService {

    ApiResult<IncomeInfoDTO> getOne(Long id);

    ApiResult<List<IncomeInfoDTO>> getAll(int page, int size);

    ApiResult<IncomeInfoDTO> update(IncomeUpdateDTO incomeUpdateDTO, Long id);

    ApiResult<IncomeInfoDTO> add(IncomeAddDTO incomeAddDTO);

    Income getByIdOrElseThrow(Long incomeId);
}
