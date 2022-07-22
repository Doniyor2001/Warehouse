package ai.ecma.appwarehouseproject.controller;

import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.IncomeAddDTO;
import ai.ecma.appwarehouseproject.payload.IncomeInfoDTO;
import ai.ecma.appwarehouseproject.payload.IncomeUpdateDTO;
import ai.ecma.appwarehouseproject.utils.AppConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(IncomeController.INCOME_CONTROLLER_PATH)
public interface IncomeController {

    String INCOME_CONTROLLER_PATH = AppConstant.BASE_PATH + "/income/";

    String ADD = "add";
    String UPDATE = "update";
    String VIEW = "view";

    @PreAuthorize("hasAuthority('VIEW_INCOME')")
    @GetMapping(VIEW + "/{id}")
    ApiResult<IncomeInfoDTO> getOne(@PathVariable Long id);

    @PreAuthorize("hasAuthority('VIEW_INCOMES')")
    @GetMapping(VIEW)
    ApiResult<List<IncomeInfoDTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size);

    @PreAuthorize("hasAuthority('ADD_INCOME')")
    @PostMapping(ADD)
    ApiResult<IncomeInfoDTO> add(@RequestBody IncomeAddDTO incomeAddDTO);

    @PreAuthorize("hasAuthority('UPDATE_INCOME')")
    @PutMapping(UPDATE + "/{id}")
    ApiResult<IncomeInfoDTO> update(IncomeUpdateDTO incomeUpdateDTO, @PathVariable Long id);


}
