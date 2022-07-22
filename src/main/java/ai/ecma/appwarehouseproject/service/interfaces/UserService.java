package ai.ecma.appwarehouseproject.service.interfaces;


import ai.ecma.appwarehouseproject.entity.Role;
import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.UserInfoDTO;

public interface UserService {

    ApiResult<UserInfoDTO> manageRoleToUser(Long id, Role role);

    void setEnabledFalse(Long id);



}
