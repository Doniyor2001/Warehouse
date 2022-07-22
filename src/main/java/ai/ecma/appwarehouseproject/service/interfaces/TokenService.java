package ai.ecma.appwarehouseproject.service.interfaces;

import ai.ecma.appwarehouseproject.payload.TokenDTO;

public interface TokenService {

    TokenDTO verifyExpiration(TokenDTO tokenDTO);

}
