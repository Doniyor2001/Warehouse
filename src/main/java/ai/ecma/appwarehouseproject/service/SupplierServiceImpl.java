package ai.ecma.appwarehouseproject.service;

import ai.ecma.appwarehouseproject.entity.Supplier;
import ai.ecma.appwarehouseproject.exception.RestException;
import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.SupplierAddDTO;
import ai.ecma.appwarehouseproject.payload.SupplierInfoDTO;
import ai.ecma.appwarehouseproject.payload.SupplierUpdateDTO;
import ai.ecma.appwarehouseproject.repository.SupplierRepository;
import ai.ecma.appwarehouseproject.service.interfaces.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    @Override
    public ApiResult<List<SupplierInfoDTO>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Supplier> infoDTOPage = supplierRepository.findAll(pageable);
        List<Supplier> supplierList = infoDTOPage.getContent();
        List<SupplierInfoDTO> supplierInfoDTOS = supplierList
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(supplierInfoDTOS);
    }

    @Override
    public ApiResult<SupplierInfoDTO> add(SupplierAddDTO supplierAddDTO) {
        checkPhoneNumber(supplierAddDTO.getName());
        Supplier supplier = new Supplier(
                supplierAddDTO.getName(),
                supplierAddDTO.getPhoneNumber()
        );
        supplierRepository.save(supplier);
        return returnApiResult(supplier,true,"Success");
    }

    @Override
    public ApiResult<SupplierInfoDTO> update(SupplierUpdateDTO supplierUpdateDTO, Long id) {
        checkPhoneNumber(supplierUpdateDTO.getName(), id);
        Supplier supplier = getByIdOrElseThrow(id);
        supplier.setName(supplierUpdateDTO.getName());
        supplierRepository.save(supplier);
        return returnApiResult(supplier,true,"Success");
    }

    @Override
    public String delete(Long id) {
        Supplier supplier = getByIdOrElseThrow(id);
        supplierRepository.delete(supplier);
        return "success";
    }

    @Override
    public Supplier getByIdOrElseThrow(Long id) {
        return supplierRepository.findById(id).orElseThrow(
                () -> RestException.notFound("Supplier"));
    }

    @Override
    public ApiResult<SupplierInfoDTO> getOne(Long id) {
        Supplier supplier = getByIdOrElseThrow(id);
        SupplierInfoDTO supplierInfoDTO = entityToInfoDTO(supplier);
        return ApiResult.successResponse(supplierInfoDTO);
    }

    private void checkPhoneNumber(String phoneNumber) {
        boolean exists = supplierRepository.existsByPhoneNumber(phoneNumber);
        if (exists) throw RestException.alreadyExist("Supplier");
    }

    private void checkPhoneNumber(String phoneNumber, Long id) {
        boolean exists = supplierRepository.existsByPhoneNumberAndIdNot(phoneNumber, id);
        if (exists) throw RestException.alreadyExist("Supplier");
    }

    private SupplierInfoDTO entityToInfoDTO(Supplier supplier) {
        return new SupplierInfoDTO(
                supplier.getName(),
                supplier.getPhoneNumber());
    }


    private ApiResult<SupplierInfoDTO> returnApiResult(Supplier supplier, boolean success, String msg) {
        SupplierInfoDTO supplierInfoDTO = entityToInfoDTO(supplier);
        return new ApiResult<>(supplierInfoDTO, success, msg);
    }
}
