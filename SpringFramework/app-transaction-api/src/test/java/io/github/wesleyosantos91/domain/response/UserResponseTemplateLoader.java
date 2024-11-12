package io.github.wesleyosantos91.domain.response;

import static io.github.wesleyosantos91.api.v1.commons.enums.UserTypeRequest.CUSTOMER;

import io.github.wesleyosantos91.api.v1.response.UserRespose;
import java.util.UUID;

public class UserResponseTemplateLoader {

    public static UserRespose createUserResponse() {
        return new UserRespose(UUID.fromString("f4b3b9b1-5b4f-4b15-8e85-3f946f24b1c3"), "Luís Gustavo Carvalho", "84786287032", "luisgustavocarvalho@email.com.br", "s3K1Y9CgCo", CUSTOMER);
    }
}
