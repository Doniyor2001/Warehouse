package ai.ecma.appwarehouseproject.controller;

import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.SupplierAddDTO;
import ai.ecma.appwarehouseproject.payload.SupplierInfoDTO;
import ai.ecma.appwarehouseproject.payload.SupplierUpdateDTO;
import ai.ecma.appwarehouseproject.service.interfaces.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SupplierControllerImpl implements SupplierController{

    private final SupplierService supplierService;

    @Override
    public ApiResult<List<SupplierInfoDTO>> getAll(int page, int size) {
        return supplierService.getAll(page,size);
    }

    @Override
    public ApiResult<SupplierInfoDTO> getOne(Long id) {
        return supplierService.getOne(id);
    }

    @Override
    public ApiResult<SupplierInfoDTO> add(SupplierAddDTO supplierAddDTO) {
        return supplierService.add(supplierAddDTO);
    }

    @Override
    public ApiResult<SupplierInfoDTO> update(SupplierUpdateDTO supplierUpdateDTO, Long id) {
        return supplierService.update(supplierUpdateDTO,id);
    }

    @Override
    public String delete(Long id) {
        return supplierService.delete(id);
    }
}
