package ai.ecma.appwarehouseproject.service;

import ai.ecma.appwarehouseproject.entity.Measure;
import ai.ecma.appwarehouseproject.exception.RestException;
import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.MeasureAddDTO;
import ai.ecma.appwarehouseproject.payload.MeasureInfoDTO;
import ai.ecma.appwarehouseproject.payload.MeasureUpdateDTO;
import ai.ecma.appwarehouseproject.repository.MeasureRepository;
import ai.ecma.appwarehouseproject.service.interfaces.MeasureService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeasureServiceImpl implements MeasureService {
    private final MeasureRepository measureRepository;

    @Override
    public ApiResult<List<MeasureInfoDTO>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Measure> infoDTOPage = measureRepository.findAll(pageable);
        List<Measure> measureList = infoDTOPage.getContent();
        List<MeasureInfoDTO> measureInfoDTOS = measureList
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(measureInfoDTOS);
    }

    @Override
    public ApiResult<MeasureInfoDTO> add(MeasureAddDTO measureAddDTO) {
        checkName(measureAddDTO.getName());
        Measure measure = new Measure(
                measureAddDTO.getName());
        measureRepository.save(measure);
        return returnApiResult(measure,true,"Success");
    }

    @Override
    public ApiResult<MeasureInfoDTO> update(MeasureUpdateDTO measureUpdateDTO, Long id) {
        checkName(measureUpdateDTO.getName(),id);
        Measure measure = getByIdOrElseThrow(id);
        measure.setName(measureUpdateDTO.getName());
        measureRepository.save(measure);
        return returnApiResult(measure,true,"Success");
    }

    @Override
    public String delete(Long id) {
        Measure measure =getByIdOrElseThrow(id);
        measureRepository.delete(measure);
        return "success";
    }

    @Override
    public Measure getByIdOrElseThrow(Long id) {
        return measureRepository.findById(id).orElseThrow(
                () -> RestException.notFound("Measure"));
    }

    @Override
    public ApiResult<MeasureInfoDTO> getOne(Long id) {
        Measure measure = getByIdOrElseThrow(id);
        MeasureInfoDTO measureInfoDTO = entityToInfoDTO(measure);
        return ApiResult.successResponse(measureInfoDTO);
    }

    private void checkName(String name) {
        boolean exists = measureRepository.existsByNameAndActiveTrue(name);
        if (exists) throw RestException.alreadyExist("Currency");
    }

    private void checkName(String name, Long id) {
        boolean exists = measureRepository.existsByNameAndIdNot(name,id);
        if (exists) throw RestException.alreadyExist("Currency");
    }

    private MeasureInfoDTO entityToInfoDTO(Measure measure) {
        return new MeasureInfoDTO(
                measure.getName());
    }

    private ApiResult<MeasureInfoDTO> returnApiResult(Measure measure, boolean success, String msg) {
        MeasureInfoDTO measureInfoDTO = entityToInfoDTO(measure);
        return new ApiResult<>(measureInfoDTO,success,msg);
    }
}
