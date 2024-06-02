package ru.mikemind.otus.social_network.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.mikemind.otus.social_network.exception.ResourceNotFoundException;
import ru.mikemind.otus.social_network.mapping.UserMapper;
import ru.mikemind.otus.social_network.model.User;
import ru.mikemind.otus.social_network.model.UserEntity;
import ru.mikemind.otus.social_network.model.UserRegisterPost200Response;
import ru.mikemind.otus.social_network.model.UserRegisterPostRequest;
import ru.mikemind.otus.social_network.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserApiController implements UserApi {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<UserRegisterPost200Response> userRegisterPost(UserRegisterPostRequest userRegisterPostRequest) {
        UserEntity userEntity = userService.register(userMapper.registerRequestToEntity(userRegisterPostRequest));
        return new ResponseEntity<>(new UserRegisterPost200Response()
                .userId(userEntity.getId()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> userGetIdGet(String id) {
        return userService.getById(id)
                .map(us -> new ResponseEntity<>(userMapper.entityToUserResponse(us), HttpStatus.OK))
                .orElseThrow(() -> new ResourceNotFoundException("User with id=%s not found".formatted(id)));
    }

    @Override
    public ResponseEntity<List<User>> userSearchGet(String firstName, String lastName) {
        List<User> responseUsers = userService.findByFirstNameAndSecondName(firstName, lastName).stream()
                .map(userMapper::entityToUserResponse).collect(Collectors.toList());
        return new ResponseEntity<>(responseUsers, HttpStatus.OK);
    }
}
