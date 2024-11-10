package io.github.wesleyosantos91.api.v1.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.wesleyosantos91.api.v1.commons.enums.UserTypeRequest;
import java.util.UUID;
import org.springframework.web.bind.annotation.BindParam;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record UserQueryRequest(
        UUID id,
        @BindParam("full_name")
        String fullName,
        @BindParam("cpf_cnpj")
        String cpfCnpj,
        String email,
        String password,
        @BindParam("user_type")
        UserTypeRequest userType) {
}
