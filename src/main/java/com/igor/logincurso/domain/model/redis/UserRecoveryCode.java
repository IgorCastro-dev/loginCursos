package com.igor.logincurso.domain.model.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("userRecoveryCode")
public class UserRecoveryCode {

    @Id
    private String id;

    @Indexed
    private String email;

    private String code;

    private LocalDateTime dateTime = LocalDateTime.now();
}
