package ai.ecma.appwarehouseproject.service;

import ai.ecma.appwarehouseproject.controller.AuthController;
import ai.ecma.appwarehouseproject.entity.Role;
import ai.ecma.appwarehouseproject.entity.User;
import ai.ecma.appwarehouseproject.exception.RestException;
import ai.ecma.appwarehouseproject.payload.*;
import ai.ecma.appwarehouseproject.repository.RoleRepository;
import ai.ecma.appwarehouseproject.repository.UserRepository;
import ai.ecma.appwarehouseproject.security.JWTProvider;
import ai.ecma.appwarehouseproject.service.interfaces.AuthService;
import ai.ecma.appwarehouseproject.service.interfaces.MailService;
import ai.ecma.appwarehouseproject.utils.AppConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;

    @Value("${spring.mail.username}")
    private String myEmail;


    @Override
    public ApiResult<?> signUp(RegisterDTO registerDTO) {

        checkPhoneNUmber(registerDTO.getPhoneNumber());

        checkPasswordAndPrePassword(registerDTO.getPassword(), registerDTO.getPrePassword());

        Role role = roleRepository.findByName(AppConstant.USER_ROLE).orElseThrow(() -> RestException.notFound("Role"));

        User user = new User(
                registerDTO.getFirstName(),
                registerDTO.getLastName(),
                registerDTO.getPhoneNumber(),
                registerDTO.getEmail(),
                generateVerificationCode(),
                role,
                passwordEncoder.encode(registerDTO.getPassword()));


        userRepository.save(user);

        sendMail(user);

        return ApiResult.successResponse("Successfully registered. Please confirm by email");
    }

    @Override
    public ApiResult<?> signIn(SignInDTO signInDTO) {

        try {

            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signInDTO.getUsername(),
                            signInDTO.getPassword()
                    )
            );
            String accessToken = jwtProvider.generateAccessToken(signInDTO.getUsername());
            String refreshToken = jwtProvider.generateRefreshToken(signInDTO.getUsername());
            TokenDTO tokenDTO = new TokenDTO(accessToken,refreshToken);

            return ApiResult.successResponse(tokenDTO);
        }catch (Exception e){
            e.printStackTrace();
            throw RestException.restThrow("password or username is wrong");
        }
    }

    @Override
    public ApiResult<?> confirmAccount(Long userId, String verificationCode) {

        User user = getUserByIdOrElseThrow(userId);

        if (!Objects.equals(verificationCode,user.getVerificationCode()))
            throw RestException.restThrow("wrong code");

        user.setEnabled(true);
        user.setVerificationCode(null);
        userRepository.save(user);

        return ApiResult.successResponse("Successfully confirmed");
    }

    private User getUserByIdOrElseThrow(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> RestException.notFound("User"));
    }

    private void checkPasswordAndPrePassword(String password, String prePassword) {
        if (!Objects.equals(password, prePassword))
            throw RestException.restThrow("");
    }

    private void checkPhoneNUmber(String phoneNumber) {
        boolean exist = userRepository.existsByPhoneNumber(phoneNumber);
        if (exist) throw RestException.alreadyExist("User");
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByPhoneNumber(username).orElseThrow(() -> RestException.notFound("User"));
    }


    private String generateVerificationCode() {
        String code = String.valueOf((int) (Math.random() * 1_000_000_000));
        return code.substring(0, 6);
    }

    private void sendMail(User user) {
        String url = "http://localhost" + AuthController.AUTH_CONTROLLER_PATH + AuthController.CONFIRM_ACCOUNT +
                "?userId=" + user.getId() + "&verificationCode=" + user.getVerificationCode();
        String text = "Account confirmation. Please click this link!\n" + url;
        SendMessageDTO sendMessageDTO = new SendMessageDTO(
                user.getEmail(),
                myEmail,
                AppConstant.CONFIRMATION,
                text
        );

        mailService.sendMessage(sendMessageDTO);
    }

}
