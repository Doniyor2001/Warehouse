package ai.ecma.appwarehouseproject.service.interfaces;

import ai.ecma.appwarehouseproject.entity.Role;
import ai.ecma.appwarehouseproject.enums.PermissionEnums;
import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.RoleAddDTO;
import ai.ecma.appwarehouseproject.payload.RoleInfoDTO;
import ai.ecma.appwarehouseproject.payload.RoleUpdateDTO;

import java.util.List;

public interface RoleService {

    ApiResult<List<RoleInfoDTO>> getAll(int page, int size);

    ApiResult<RoleInfoDTO> add(RoleAddDTO roleAddDTO);

    ApiResult<RoleInfoDTO> update(RoleUpdateDTO roleUpdateDTO, Long id);

    String delete(Long id);

    ApiResult<RoleInfoDTO> deletePermissionsFromRole(List<PermissionEnums> permissionEnums, Long roleId);

    ApiResult<RoleInfoDTO> getOne(Long id);

    Role getByIdOrElseThrow(Long roleId);
}
