package ai.ecma.appwarehouseproject.service;

import ai.ecma.appwarehouseproject.entity.Role;
import ai.ecma.appwarehouseproject.entity.User;
import ai.ecma.appwarehouseproject.exception.RestException;
import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.UserInfoDTO;
import ai.ecma.appwarehouseproject.repository.UserRepository;
import ai.ecma.appwarehouseproject.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ApiResult<UserInfoDTO> manageRoleToUser(Long id, Role role) {
        User user = getByIdOrElseThrow(id);
        user.setRole(role);
        userRepository.save(user);
        return ApiResult.successResponse();
    }

    public void setEnabledFalse(Long id) {
        User user = getByIdOrElseThrow(id);
        user.setEnabled(false);
        userRepository.save(user);
    }

    private User getByIdOrElseThrow(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> RestException.notFound("User")
        );
    }

    private UserInfoDTO entityToInfoDTO(User user) {
        return new UserInfoDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getRole().getId(),
                user.getWarehouse().getId()
        );
    }

}
