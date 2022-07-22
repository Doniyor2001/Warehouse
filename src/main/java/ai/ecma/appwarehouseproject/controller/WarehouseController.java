package ai.ecma.appwarehouseproject.controller;

import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.WarehouseAddDTO;
import ai.ecma.appwarehouseproject.payload.WarehouseInfoDTO;
import ai.ecma.appwarehouseproject.payload.WarehouseUpdateDTO;
import ai.ecma.appwarehouseproject.utils.AppConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(WarehouseController.WARE_HOUSE_CONTROLLER_PATH)
public interface WarehouseController {

    String WARE_HOUSE_CONTROLLER_PATH = AppConstant.BASE_PATH + "/ware-house/";
    String ADD = "add";
    String DELETE = "delete";
    String UPDATE = "update";
    String VIEW = "view";

    @PreAuthorize(value = "hasAuthority('VIEW_WAREHOUSES')")
    @GetMapping(VIEW)
    ApiResult<List<WarehouseInfoDTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size);

    @PreAuthorize(value = "hasAuthority('VIEW_WAREHOUSE')")
    @GetMapping(VIEW + "/{id}")
    ApiResult<WarehouseInfoDTO> getOne(@PathVariable Long id);

    @PreAuthorize(value = "hasAuthority('ADD_WAREHOUSE')")
    @PostMapping(ADD)
    ApiResult<WarehouseInfoDTO> add(@RequestBody WarehouseAddDTO warehouseAddDTO);

    @PreAuthorize(value = "hasAuthority('UPDATE_WAREHOUSE')")
    @PutMapping(UPDATE + "/{id}")
    ApiResult<WarehouseInfoDTO> update(@RequestBody WarehouseUpdateDTO warehouseUpdateDTO, @PathVariable Long id);

    @PreAuthorize(value = "hasAuthority('DELETE_WAREHOUSE')")
    @DeleteMapping(DELETE + "/{id}")
    String delete(@PathVariable Long id);

}
