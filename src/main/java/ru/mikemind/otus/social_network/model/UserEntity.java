package ru.mikemind.otus.social_network.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@Table("user_entity")
public class UserEntity {

    @Id
    private Long pkId;
    private String id;
    private String firstName;
    private String secondName;
    private LocalDate birthdate;
    private String biography;
    private String city;
    @MappedCollection(idColumn = "user_pk_id")
    private UserCredentialEntity userCredential;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

}
