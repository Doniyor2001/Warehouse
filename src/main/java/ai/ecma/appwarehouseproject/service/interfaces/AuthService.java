package ai.ecma.appwarehouseproject.service.interfaces;

import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.RegisterDTO;
import ai.ecma.appwarehouseproject.payload.SignInDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    ApiResult<?> signUp(RegisterDTO registerDTO);
    ApiResult<?> signIn(SignInDTO signInDTO);

    ApiResult<?> confirmAccount(Long userId, String verificationCode);

}
