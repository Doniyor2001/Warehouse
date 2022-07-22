package ai.ecma.appwarehouseproject.controller;

import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.RegisterDTO;
import ai.ecma.appwarehouseproject.payload.SignInDTO;
import ai.ecma.appwarehouseproject.utils.AppConstant;
import org.springframework.web.bind.annotation.*;

@RequestMapping(AuthController.AUTH_CONTROLLER_PATH)
public interface AuthController {
    String AUTH_CONTROLLER_PATH = AppConstant.BASE_PATH + "/auth/";
    String SIGN_UP = "sign-up";
    String SIGN_IN = "sign-in";
    String CONFIRM_ACCOUNT = "confirm-account";

    @PostMapping(SIGN_UP)
    ApiResult<?> signUp(@RequestBody RegisterDTO registerDTO);

    @PostMapping(SIGN_IN)
    ApiResult<?> signIn(@RequestBody SignInDTO signInDTO);

    @GetMapping(CONFIRM_ACCOUNT)
    ApiResult<?> confirmAccount(@RequestParam Long userId,
                                @RequestParam String verificationCode);


}
