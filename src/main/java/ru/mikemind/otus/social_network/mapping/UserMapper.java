package ru.mikemind.otus.social_network.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import ru.mikemind.otus.social_network.model.LoginPostRequest;
import ru.mikemind.otus.social_network.model.User;
import ru.mikemind.otus.social_network.model.UserEntity;
import ru.mikemind.otus.social_network.model.UserRegisterPostRequest;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper {

    @Mapping(target = "userCredential.password", source = "password")
    UserEntity registerRequestToEntity(UserRegisterPostRequest request);

    @Mapping(target = "userCredential.password", source = "password")
    UserEntity loginRequestToEntity(LoginPostRequest request);

    User entityToUserResponse(UserEntity userEntity);

}
