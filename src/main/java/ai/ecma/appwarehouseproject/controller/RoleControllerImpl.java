package ai.ecma.appwarehouseproject.controller;

import ai.ecma.appwarehouseproject.entity.Role;
import ai.ecma.appwarehouseproject.enums.PermissionEnums;
import ai.ecma.appwarehouseproject.payload.*;
import ai.ecma.appwarehouseproject.service.interfaces.RoleService;
import ai.ecma.appwarehouseproject.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoleControllerImpl implements RoleController{

    private final RoleService roleService;
    private final UserService userService;

    @Override
    public ApiResult<List<RoleInfoDTO>> getAll(int page, int size) {
        return roleService.getAll(page,size);
    }

    @Override
    public ApiResult<RoleInfoDTO> getOne(Long id) {
        return roleService.getOne(id);
    }

    @Override
    public ApiResult<RoleInfoDTO> add(RoleAddDTO roleAddDTO) {
        return roleService.add(roleAddDTO);
    }

    @Override
    public ApiResult<RoleInfoDTO> update(RoleUpdateDTO roleUpdateDTO, Long id) {
        return roleService.update(roleUpdateDTO,id);
    }

    @Override
    public String delete(Long id) {
        return roleService.delete(id);
    }

    @Override
    public ApiResult<RoleInfoDTO> deletePermissionsFromRole(List<PermissionEnums> permissionEnums, Long id) {
        return roleService.deletePermissionsFromRole(permissionEnums,id);
    }

    @Override
    public ApiResult<UserInfoDTO> manageRole(Long id, ManageRoleInfoDTO manageRoleInfoDTO) {
        Role role = roleService.getByIdOrElseThrow(manageRoleInfoDTO.getRoleId());
        return userService.manageRoleToUser(id,role);
    }
}
