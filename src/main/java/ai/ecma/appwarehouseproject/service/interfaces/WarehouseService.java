package ai.ecma.appwarehouseproject.service.interfaces;

import ai.ecma.appwarehouseproject.entity.Warehouse;
import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.WarehouseAddDTO;
import ai.ecma.appwarehouseproject.payload.WarehouseInfoDTO;
import ai.ecma.appwarehouseproject.payload.WarehouseUpdateDTO;

import java.util.List;

public interface WarehouseService {

    ApiResult<List<WarehouseInfoDTO>> getAll(int page, int size);

    ApiResult<WarehouseInfoDTO> add(WarehouseAddDTO warehouseAddDTO);

    ApiResult<WarehouseInfoDTO> update(WarehouseUpdateDTO warehouseUpdateDTO, Long id);

    String delete(Long id);

    Warehouse getByIdOrElseThrow(Long id);

    ApiResult<WarehouseInfoDTO> getOne(Long id);
}
