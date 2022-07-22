package ai.ecma.appwarehouseproject.controller;

import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.OutcomeAddDTO;
import ai.ecma.appwarehouseproject.payload.OutcomeInfoDTO;
import ai.ecma.appwarehouseproject.payload.OutcomeUpdateDTO;
import ai.ecma.appwarehouseproject.utils.AppConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(OutcomeController.OUTCOME_CONTROLLER_PATH)
public interface OutcomeController {

    String OUTCOME_CONTROLLER_PATH = AppConstant.BASE_PATH + "/outcome/";

    String ADD = "add";
    String UPDATE = "update";
    String VIEW = "view";

    @PreAuthorize("hasAuthority('VIEW_OUTCOME')")
    @GetMapping(VIEW + "/{id}")
    ApiResult<OutcomeInfoDTO> getOne(@PathVariable Long id);

    @PreAuthorize("hasAuthority('VIEW_OUTCOMES')")
    @GetMapping(VIEW)
    ApiResult<List<OutcomeInfoDTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size);

    @PreAuthorize("hasAuthority('ADD_OUTCOME')")
    @PostMapping(ADD)
    ApiResult<OutcomeInfoDTO> add(@RequestBody OutcomeAddDTO outcomeAddDTO);

    @PreAuthorize("hasAuthority('UPDATE_OUTCOME')")
    @PutMapping(UPDATE + "/{id}")
    ApiResult<OutcomeInfoDTO> update(OutcomeUpdateDTO outcomeUpdateDTO, @PathVariable Long id);


}
