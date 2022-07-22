package ai.ecma.appwarehouseproject.service.interfaces;

import ai.ecma.appwarehouseproject.entity.Currency;
import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.CurrencyAddDTO;
import ai.ecma.appwarehouseproject.payload.CurrencyInfoDTO;
import ai.ecma.appwarehouseproject.payload.CurrencyUpdateDTO;

import java.util.List;

public interface CurrencyService {

    ApiResult<List<CurrencyInfoDTO>> getAll(int page, int size);

    ApiResult<CurrencyInfoDTO> add(CurrencyAddDTO currencyAddDTO);

    ApiResult<CurrencyInfoDTO> update(CurrencyUpdateDTO currencyUpdateDTO, Long id);

    String delete(Long id);

    Currency getByIdOrElseThrow(Long id);

    ApiResult<CurrencyInfoDTO> getOne(Long id);
}
