package ru.mikemind.otus.social_network.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import ru.mikemind.otus.social_network.model.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends ListCrudRepository<UserEntity, Long> {

    @Query("select * from user_entity where id = :id")
    Optional<UserEntity> getById(String id);

}
