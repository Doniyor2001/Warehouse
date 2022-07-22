package ai.ecma.appwarehouseproject.service;

import ai.ecma.appwarehouseproject.entity.Role;
import ai.ecma.appwarehouseproject.enums.PermissionEnums;
import ai.ecma.appwarehouseproject.exception.RestException;
import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.RoleAddDTO;
import ai.ecma.appwarehouseproject.payload.RoleInfoDTO;
import ai.ecma.appwarehouseproject.payload.RoleUpdateDTO;
import ai.ecma.appwarehouseproject.repository.RoleRepository;
import ai.ecma.appwarehouseproject.service.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public ApiResult<List<RoleInfoDTO>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Role> rolePage = roleRepository.findAll(pageable);
        List<Role> roles = rolePage.getContent();
        List<RoleInfoDTO> roleInfoDTO = roles
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(roleInfoDTO);
    }

    @Override
    public ApiResult<RoleInfoDTO> add(RoleAddDTO roleAddDTO) {
        checkName(roleAddDTO.getName());
        Role role = new Role(
                roleAddDTO.getName(),
                roleAddDTO.getDescription(),
                roleAddDTO.getPermissions()
        );
        roleRepository.save(role);
        return returnApiResult(role,true,"Success");
    }

    @Override
    public ApiResult<RoleInfoDTO> update(RoleUpdateDTO roleUpdateDTO, Long id) {
        checkName(roleUpdateDTO.getName(),id);
        Role role = getByIdOrElseThrow(id);
        role.setName(roleUpdateDTO.getName());
        role.setDescription(roleUpdateDTO.getDescription());
        role.setPermissions(roleUpdateDTO.getPermissions());
        roleRepository.save(role);
        return returnApiResult(role,true,"Success - 404");
    }

    @Override
    public String delete(Long id) {
        Role role = getByIdOrElseThrow(id);
        roleRepository.delete(role);
        return "Successfully deleted!";
    }

    @Override
    public ApiResult<RoleInfoDTO> deletePermissionsFromRole(List<PermissionEnums> permissionEnums, Long roleId) {
        for (PermissionEnums permissionEnum: permissionEnums) {
            roleRepository.deletePermissions(String.valueOf(permissionEnum),roleId);
        }
        return ApiResult.successResponse();
    }

    @Override
    public ApiResult<RoleInfoDTO> getOne(Long id) {
        Role role = getByIdOrElseThrow(id);
        RoleInfoDTO roleInfoDTO = entityToInfoDTO(role);
        return ApiResult.successResponse(roleInfoDTO);
    }


    private RoleInfoDTO entityToInfoDTO(Role role) {
        return new RoleInfoDTO(
                role.getName(),
                role.getDescription(),
                role.getPermissions()
        );
    }

    private void checkName(String name) {
        boolean exists = roleRepository.existsByName(name);
        if (exists) throw RestException.alreadyExist("Role");
    }

    private void checkName(String name, Long id) {
        boolean exists = roleRepository.existsByNameAndIdNot(name,id);
        if (exists) throw RestException.alreadyExist("Role");
    }

    public Role getByIdOrElseThrow(Long id) {
        return roleRepository.findById(id).orElseThrow(
                () -> RestException.notFound("Warehouse")
        );
    }

    private ApiResult<RoleInfoDTO> returnApiResult(Role role, boolean success, String msg) {
        RoleInfoDTO roleInfoDTO = entityToInfoDTO(role);
        return new ApiResult<>(roleInfoDTO,success,msg);
    }

}
