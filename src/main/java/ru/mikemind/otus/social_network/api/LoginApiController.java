package ru.mikemind.otus.social_network.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.mikemind.otus.social_network.model.LoginPost200Response;
import ru.mikemind.otus.social_network.model.LoginPostRequest;

@RestController
public class LoginApiController implements LoginApi {

    @Override
    public ResponseEntity<LoginPost200Response> loginPost(LoginPostRequest loginPostRequest) {
        return LoginApi.super.loginPost(loginPostRequest);
    }
}
