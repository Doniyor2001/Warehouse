package ai.ecma.appwarehouseproject.controller;

import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.RegisterDTO;
import ai.ecma.appwarehouseproject.payload.SignInDTO;
import ai.ecma.appwarehouseproject.service.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;

    @Override
    public ApiResult<?> signUp(RegisterDTO registerDTO) {
        return authService.signUp(registerDTO);
    }

    @Override
    public ApiResult<?> signIn(SignInDTO signInDTO) {
        return authService.signIn(signInDTO);
    }

    @Override
    public ApiResult<?> confirmAccount(Long userId, String verificationCode) {
        return authService.confirmAccount(userId,verificationCode);
    }
}
