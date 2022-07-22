package ai.ecma.appwarehouseproject.service.interfaces;

import ai.ecma.appwarehouseproject.entity.Outcome;
import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.OutcomeAddDTO;
import ai.ecma.appwarehouseproject.payload.OutcomeInfoDTO;
import ai.ecma.appwarehouseproject.payload.OutcomeUpdateDTO;

import java.util.List;

public interface OutcomeService {

    ApiResult<OutcomeInfoDTO> getOne(Long id);

    ApiResult<List<OutcomeInfoDTO>> getAll(int page, int size);

    ApiResult<OutcomeInfoDTO> update(OutcomeUpdateDTO outcomeUpdateDTO, Long id);

    ApiResult<OutcomeInfoDTO> add(OutcomeAddDTO outcomeAddDTO);

    Outcome getByIdOrElseThrow(Long outcomeId);
}
