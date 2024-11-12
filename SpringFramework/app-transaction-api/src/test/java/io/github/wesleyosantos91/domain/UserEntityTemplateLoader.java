package io.github.wesleyosantos91.domain;

import static io.github.wesleyosantos91.domain.entity.enums.UserType.CUSTOMER;

import io.github.wesleyosantos91.domain.entity.UserEntity;

public class UserEntityTemplateLoader {

    public static UserEntity createUserEntityToSave() {

        UserEntity userEntity = new UserEntity();
        userEntity.setFullName("Luís Gustavo Carvalho");
        userEntity.setCpfCnpj("84786287032");
        userEntity.setEmail("luisgustavocarvalho@email.com.br");
        userEntity.setPassword("s3K1Y9CgCo");
        userEntity.setUserType(CUSTOMER);
        return userEntity;
    }

    public static UserEntity createUserEntityToSaved() {
       UserEntity userEntity = createUserEntityToSave();
       userEntity.setId(java.util.UUID.fromString("f4b3b9b1-5b4f-4b15-8e85-3f946f24b1c3"));
       return userEntity;
    }
}
