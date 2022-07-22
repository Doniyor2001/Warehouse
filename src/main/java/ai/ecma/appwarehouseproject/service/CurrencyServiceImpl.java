package ai.ecma.appwarehouseproject.service;

import ai.ecma.appwarehouseproject.entity.Currency;
import ai.ecma.appwarehouseproject.exception.RestException;
import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.CurrencyAddDTO;
import ai.ecma.appwarehouseproject.payload.CurrencyInfoDTO;
import ai.ecma.appwarehouseproject.payload.CurrencyUpdateDTO;
import ai.ecma.appwarehouseproject.repository.CurrencyRepository;
import ai.ecma.appwarehouseproject.service.interfaces.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository currencyRepository;

    @Override
    public ApiResult<List<CurrencyInfoDTO>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Currency> infoDTOPage = currencyRepository.findAll(pageable);
        List<Currency> currencyList = infoDTOPage.getContent();
        List<CurrencyInfoDTO> currencyInfoDTOS = currencyList
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(currencyInfoDTOS);
    }

    @Override
    public ApiResult<CurrencyInfoDTO> add(CurrencyAddDTO currencyAddDTO) {
        checkName(currencyAddDTO.getName());
        Currency currency = new Currency(
                currencyAddDTO.getName(),
                currencyAddDTO.getDescription()
        );
        currencyRepository.save(currency);
        return returnApiResult(currency,true,"Success");
    }

    @Override
    public ApiResult<CurrencyInfoDTO> update(CurrencyUpdateDTO currencyUpdateDTO, Long id) {
        checkName(currencyUpdateDTO.getName(), id);
        Currency currency = getByIdOrElseThrow(id);
        currency.setName(currencyUpdateDTO.getName());
        currencyRepository.save(currency);
        return returnApiResult(currency,true,"Success");
    }

    @Override
    public String delete(Long id) {
        Currency currency = getByIdOrElseThrow(id);
        currencyRepository.delete(currency);
        return "success";
    }

    @Override
    public Currency getByIdOrElseThrow(Long id) {
        return currencyRepository.findById(id).orElseThrow(
                () -> RestException.notFound("Currency"));
    }

    @Override
    public ApiResult<CurrencyInfoDTO> getOne(Long id) {
        Currency currency = getByIdOrElseThrow(id);
        CurrencyInfoDTO currencyInfoDTO = entityToInfoDTO(currency);
        return ApiResult.successResponse(currencyInfoDTO);
    }

    private void checkName(String name) {
        boolean exists = currencyRepository.existsByNameAndActiveTrue(name);
        if (exists) throw RestException.alreadyExist("Currency");
    }

    private void checkName(String name, Long id) {
        boolean exists = currencyRepository.existsByNameAndIdNot(name, id);
        if (exists) throw RestException.alreadyExist("Currency");
    }

    private CurrencyInfoDTO entityToInfoDTO(Currency currency) {
        return new CurrencyInfoDTO(
                currency.getName(),
                currency.getDescription());
    }


    private ApiResult<CurrencyInfoDTO> returnApiResult(Currency currency, boolean success, String msg) {
        CurrencyInfoDTO currencyInfoDTO = entityToInfoDTO(currency);
        return new ApiResult<>(currencyInfoDTO, success, msg);
    }
}
