package ru.mikemind.otus.social_network.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.mikemind.otus.social_network.mapping.UserMapper;
import ru.mikemind.otus.social_network.model.LoginPost200Response;
import ru.mikemind.otus.social_network.model.LoginPostRequest;
import ru.mikemind.otus.social_network.service.LoginService;

@RestController
@RequiredArgsConstructor
public class LoginApiController implements LoginApi {

    private final LoginService loginService;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<LoginPost200Response> loginPost(LoginPostRequest loginPostRequest) {
        String token = loginService.login(userMapper.loginRequestToEntity(loginPostRequest));
        return new ResponseEntity<>(new LoginPost200Response()
                .token(token), HttpStatus.OK);
    }
}
