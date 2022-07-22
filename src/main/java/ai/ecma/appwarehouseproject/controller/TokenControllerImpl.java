package ai.ecma.appwarehouseproject.controller;

import ai.ecma.appwarehouseproject.payload.TokenDTO;
import ai.ecma.appwarehouseproject.service.interfaces.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenControllerImpl implements TokenController{

    private final TokenService tokenService;

    @Override
    public TokenDTO verifyExpiration(TokenDTO tokenDTO) {
        return null;
    }
}
