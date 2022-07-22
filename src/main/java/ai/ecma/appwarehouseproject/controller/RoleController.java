package ai.ecma.appwarehouseproject.controller;

import ai.ecma.appwarehouseproject.enums.PermissionEnums;
import ai.ecma.appwarehouseproject.payload.*;
import ai.ecma.appwarehouseproject.utils.AppConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(RoleController.ROLE_CONTROLLER_PATH)
public interface RoleController {

    String ROLE_CONTROLLER_PATH = AppConstant.BASE_PATH + "/role/";
    String ADD = "add";
    String UPDATE = "update";
    String DELETE = "delete";
    String VIEW = "view";
    String DELETE_PERMISSIONS = "delete-permission";
    String ADD_ROLE = "add-role";

    @PreAuthorize(value = "hasAuthority('VIEW_ROLES')")
    @GetMapping(VIEW)
    ApiResult<List<RoleInfoDTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size);

    @PreAuthorize(value = "hasAuthority('VIEW_ROLE')")
    @GetMapping(VIEW + "/{id}")
    ApiResult<RoleInfoDTO> getOne(@PathVariable Long id);

    @PreAuthorize(value = "hasAuthority('ADD_ROLE')")
    @PostMapping(ADD)
    ApiResult<RoleInfoDTO> add(@RequestBody RoleAddDTO roleAddDTO);

    @PreAuthorize(value = "hasAuthority('UPDATE_ROLE')")
    @PutMapping(UPDATE + "/{id}")
    ApiResult<RoleInfoDTO> update(RoleUpdateDTO roleUpdateDTO, @PathVariable Long id);

    @PreAuthorize(value = "hasAuthority('DELETE_ROLE')")
    @DeleteMapping(DELETE + "/{id}")
    String delete(@PathVariable Long id);

    @PreAuthorize(value = "hasAuthority('DELETE_PERMISSIONS_ROLE')")
    @DeleteMapping(DELETE_PERMISSIONS + "/{id}")
    ApiResult<RoleInfoDTO> deletePermissionsFromRole(List<PermissionEnums> permissionEnums, @PathVariable Long id);

    @PreAuthorize(value = "hasAuthority('MANAGE_ROLE_TO_USER')")
    @PostMapping(ADD_ROLE + "/{id}")
    ApiResult<UserInfoDTO> manageRole(@PathVariable Long id, @RequestBody ManageRoleInfoDTO manageRoleInfoDTO);


}
