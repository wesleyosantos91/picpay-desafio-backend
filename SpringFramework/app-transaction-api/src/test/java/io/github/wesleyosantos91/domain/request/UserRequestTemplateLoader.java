package io.github.wesleyosantos91.domain.request;

import static io.github.wesleyosantos91.api.v1.commons.enums.UserTypeRequest.CUSTOMER;
import static io.github.wesleyosantos91.api.v1.commons.enums.UserTypeRequest.MERCHANT;

import io.github.wesleyosantos91.api.v1.request.UserRequest;

public class UserRequestTemplateLoader {

    public static UserRequest createUserCustomerSucess() {
        return new UserRequest("Luís Gustavo Carvalho", "84786287032", "luisgustavocarvalho@email.com.br", "s3K1Y9CgCo", CUSTOMER);
    }

    public static UserRequest createUserMerchantSucess() {
        return new UserRequest("Epsilon Systems", "99900011122333", "contato@epsilon-systems.com", "password345", MERCHANT);
    }
}
