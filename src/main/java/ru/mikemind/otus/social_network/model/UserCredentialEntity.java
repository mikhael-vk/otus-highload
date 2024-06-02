package ru.mikemind.otus.social_network.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@Table("user_credential")
public class UserCredentialEntity {

    @Id
    private Long pkId;
    private String password;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

}
