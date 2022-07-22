package ai.ecma.appwarehouseproject.controller;

import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.CurrencyAddDTO;
import ai.ecma.appwarehouseproject.payload.CurrencyInfoDTO;
import ai.ecma.appwarehouseproject.payload.CurrencyUpdateDTO;
import ai.ecma.appwarehouseproject.service.interfaces.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CurrencyControllerImpl implements CurrencyController{

    private final CurrencyService currencyService;

    @Override
    public ApiResult<List<CurrencyInfoDTO>> getAll(int page, int size) {
        return currencyService.getAll(page,size);
    }

    @Override
    public ApiResult<CurrencyInfoDTO> getOne(Long id) {
        return currencyService.getOne(id);
    }

    @Override
    public ApiResult<CurrencyInfoDTO> add(CurrencyAddDTO currencyAddDTO) {
        return currencyService.add(currencyAddDTO);
    }

    @Override
    public ApiResult<CurrencyInfoDTO> update(CurrencyUpdateDTO currencyUpdateDTO, Long id) {
        return currencyService.update(currencyUpdateDTO,id);
    }

    @Override
    public String delete(Long id) {
        return currencyService.delete(id);
    }
}
