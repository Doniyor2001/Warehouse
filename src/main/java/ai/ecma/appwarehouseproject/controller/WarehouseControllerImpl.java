package ai.ecma.appwarehouseproject.controller;

import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.WarehouseAddDTO;
import ai.ecma.appwarehouseproject.payload.WarehouseInfoDTO;
import ai.ecma.appwarehouseproject.payload.WarehouseUpdateDTO;
import ai.ecma.appwarehouseproject.service.interfaces.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class WarehouseControllerImpl implements WarehouseController{

    private final WarehouseService warehouseService;

    @Override
    public ApiResult<List<WarehouseInfoDTO>> getAll(int page, int size) {
        return warehouseService.getAll(page,size);
    }

    @Override
    public ApiResult<WarehouseInfoDTO> getOne(Long id) {
        return warehouseService.getOne(id);
    }

    @Override
    public ApiResult<WarehouseInfoDTO> add(WarehouseAddDTO warehouseAddDTO) {
        return warehouseService.add(warehouseAddDTO);
    }

    @Override
    public ApiResult<WarehouseInfoDTO> update(WarehouseUpdateDTO warehouseUpdateDTO, Long id) {
        return warehouseService.update(warehouseUpdateDTO,id);
    }

    @Override
    public String delete(Long id) {
        return warehouseService.delete(id);
    }
}
