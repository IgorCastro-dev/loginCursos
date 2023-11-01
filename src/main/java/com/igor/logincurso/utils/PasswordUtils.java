package com.igor.logincurso.utils;

import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NoArgsConstructor
public class PasswordUtils {

    public static String encode(String password){
        return new BCryptPasswordEncoder().encode(password);
    }
}
