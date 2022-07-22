package ai.ecma.appwarehouseproject.service;

import ai.ecma.appwarehouseproject.entity.Warehouse;
import ai.ecma.appwarehouseproject.exception.RestException;
import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.WarehouseAddDTO;
import ai.ecma.appwarehouseproject.payload.WarehouseInfoDTO;
import ai.ecma.appwarehouseproject.payload.WarehouseUpdateDTO;
import ai.ecma.appwarehouseproject.repository.WarehouseRepository;
import ai.ecma.appwarehouseproject.service.interfaces.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;

    @Override
    public ApiResult<List<WarehouseInfoDTO>> getAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Warehouse> warehousePage = warehouseRepository.findAll(pageable);

        List<Warehouse> warehouses = warehousePage.getContent();

        List<WarehouseInfoDTO> warehouseInfoDTOS = warehouses
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());


        return ApiResult.successResponse(warehouseInfoDTOS);
    }

    @Override
    public ApiResult<WarehouseInfoDTO> add(WarehouseAddDTO warehouseAddDTO) {
        checkName(warehouseAddDTO.getName());
        Warehouse warehouse = new Warehouse(
                warehouseAddDTO.getName(),
                warehouseAddDTO.getDescription()
        );
        warehouseRepository.save(warehouse);
        return returnApiResult(warehouse, true, "Success");
    }

    @Override
    public ApiResult<WarehouseInfoDTO> update(WarehouseUpdateDTO warehouseUpdateDTO, Long id) {
        checkName(warehouseUpdateDTO.getName(), id);
        Warehouse warehouse = getByIdOrElseThrow(id);
        warehouse.setName(warehouseUpdateDTO.getName());
        warehouse.setDescription(warehouse.getDescription());
        warehouseRepository.save(warehouse);
        return returnApiResult(warehouse, true, "Success");
    }

    @Override
    public String delete(Long id) {
        Warehouse warehouse = getByIdOrElseThrow(id);
        warehouseRepository.delete(warehouse);
        return "Success";
    }

    private WarehouseInfoDTO entityToInfoDTO(Warehouse warehouse) {
        return new WarehouseInfoDTO(
                warehouse.getName(),
                warehouse.getDescription()
        );
    }

    private ApiResult<WarehouseInfoDTO> returnApiResult(Warehouse warehouse, boolean success, String msg) {
        WarehouseInfoDTO warehouseInfoDTO = entityToInfoDTO(warehouse);
        return new ApiResult<>(warehouseInfoDTO, true, msg);
    }

    private void checkName(String name) {
        boolean exists = warehouseRepository.existsByNameAndActiveTrue(name);
        if (exists) throw RestException.alreadyExist("Warehouse");
    }

    @Override
    public Warehouse getByIdOrElseThrow(Long id) {
        return warehouseRepository.findById(id).orElseThrow(
                () -> RestException.notFound("Warehouse")
        );
    }

    @Override
    public ApiResult<WarehouseInfoDTO> getOne(Long id) {
        Warehouse warehouse = getByIdOrElseThrow(id);
        WarehouseInfoDTO warehouseInfoDTO = entityToInfoDTO(warehouse);
        return ApiResult.successResponse(warehouseInfoDTO);
    }

    private void checkName(String name, Long id) {
        boolean exists = warehouseRepository.existsByNameAndIdNotAndActiveTrue(name, id);
        if (exists) throw RestException.alreadyExist("Warehouse");
    }

}
