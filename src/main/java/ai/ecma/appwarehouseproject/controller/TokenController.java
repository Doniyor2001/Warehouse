package ai.ecma.appwarehouseproject.controller;

import ai.ecma.appwarehouseproject.payload.TokenDTO;
import ai.ecma.appwarehouseproject.utils.AppConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(TokenController.TOKEN_CONTROLLER_PATH)
public interface TokenController {

    String TOKEN_CONTROLLER_PATH = AppConstant.BASE_PATH + "/token/";

    String VERIFY_TOKEN = "verify-and-refresh";

    @GetMapping(VERIFY_TOKEN)
    TokenDTO verifyExpiration(TokenDTO tokenDTO);

}
