package ai.ecma.appwarehouseproject.controller;

import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.CurrencyAddDTO;
import ai.ecma.appwarehouseproject.payload.CurrencyInfoDTO;
import ai.ecma.appwarehouseproject.payload.CurrencyUpdateDTO;
import ai.ecma.appwarehouseproject.utils.AppConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(CurrencyController.CURRENCY_CONTROLLER_PATH)
public interface CurrencyController {

    String CURRENCY_CONTROLLER_PATH = AppConstant.BASE_PATH + "/currency/";

    String ADD = "add";
    String UPDATE = "update";
    String VIEW = "view";
    String DELETE = "delete";

    @PreAuthorize(value = "hasAuthority('VIEW_CURRENCIES')")
    @GetMapping(VIEW)
    ApiResult<List<CurrencyInfoDTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size);

    @PreAuthorize(value = "hasAuthority('VIEW_CURRENCY')")
    @GetMapping(VIEW + "/{id}")
    ApiResult<CurrencyInfoDTO> getOne(@PathVariable Long id);

    @PreAuthorize(value = "hasAuthority('ADD_CURRENCY')")
    @PostMapping(ADD)
    ApiResult<CurrencyInfoDTO> add(@RequestBody CurrencyAddDTO currencyAddDTO);

    @PreAuthorize(value = "hasAuthority('UPDATE_CURRENCY')")
    @PutMapping(UPDATE + "/{id}")
    ApiResult<CurrencyInfoDTO> update(@RequestBody CurrencyUpdateDTO currencyUpdateDTO, @PathVariable Long id);

    @PreAuthorize(value = "hasAuthority('DELETE_CURRENCY')")
    @DeleteMapping(DELETE + "/{id}")
    String delete(@PathVariable Long id);

}
