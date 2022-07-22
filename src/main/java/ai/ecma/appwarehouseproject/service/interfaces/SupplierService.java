package ai.ecma.appwarehouseproject.service.interfaces;

import ai.ecma.appwarehouseproject.entity.Supplier;
import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.SupplierAddDTO;
import ai.ecma.appwarehouseproject.payload.SupplierInfoDTO;
import ai.ecma.appwarehouseproject.payload.SupplierUpdateDTO;

import java.util.List;

public interface SupplierService {

    ApiResult<List<SupplierInfoDTO>> getAll(int page, int size);

    ApiResult<SupplierInfoDTO> add(SupplierAddDTO supplierAddDTO);

    ApiResult<SupplierInfoDTO> update(SupplierUpdateDTO supplierUpdateDTO, Long id);

    String delete(Long id);

    Supplier getByIdOrElseThrow(Long id);

    ApiResult<SupplierInfoDTO> getOne(Long id);
}
