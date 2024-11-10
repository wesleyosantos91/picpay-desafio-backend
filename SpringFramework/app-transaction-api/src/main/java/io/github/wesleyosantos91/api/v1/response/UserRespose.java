package io.github.wesleyosantos91.api.v1.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.wesleyosantos91.api.v1.commons.enums.UserTypeRequest;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record UserRespose(
        UUID id,
        String fullName,
        String cpfCnpj,
        String email,
        String password,
        UserTypeRequest userType) {
}
