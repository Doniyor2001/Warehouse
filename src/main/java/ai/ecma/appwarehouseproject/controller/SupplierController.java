package ai.ecma.appwarehouseproject.controller;

import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.SupplierAddDTO;
import ai.ecma.appwarehouseproject.payload.SupplierInfoDTO;
import ai.ecma.appwarehouseproject.payload.SupplierUpdateDTO;
import ai.ecma.appwarehouseproject.utils.AppConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(SupplierController.SUPPLIER_CONTROLLER_PATH)
public interface SupplierController {

    String SUPPLIER_CONTROLLER_PATH = AppConstant.BASE_PATH + "/supplier/";

    String ADD = "add";
    String UPDATE = "update";
    String VIEW = "view";
    String DELETE = "delete";

    @PreAuthorize(value = "hasAuthority('VIEW_SUPPLIERS')")
    @GetMapping(VIEW)
    ApiResult<List<SupplierInfoDTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size);

    @PreAuthorize(value = "hasAuthority('VIEW_SUPPLIER')")
    @GetMapping(VIEW + "/{id}")
    ApiResult<SupplierInfoDTO> getOne(@PathVariable Long id);

    @PreAuthorize(value = "hasAuthority('ADD_SUPPLIER')")
    @PostMapping(ADD)
    ApiResult<SupplierInfoDTO> add(@RequestBody SupplierAddDTO supplierAddDTO);

    @PreAuthorize(value = "hasAuthority('UPDATE_SUPPLIER')")
    @PutMapping(UPDATE + "/{id}")
    ApiResult<SupplierInfoDTO> update(@RequestBody SupplierUpdateDTO supplierUpdateDTO, @PathVariable Long id);

    @PreAuthorize(value = "hasAuthority('DELETE_SUPPLIER')")
    @DeleteMapping(DELETE + "/{id}")
    String delete(@PathVariable Long id);

}
