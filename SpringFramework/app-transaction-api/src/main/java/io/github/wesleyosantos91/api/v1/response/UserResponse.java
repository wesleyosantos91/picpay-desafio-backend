package io.github.wesleyosantos91.api.v1.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.wesleyosantos91.api.v1.commons.enums.UserTypeRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

@Schema(name = "PersonResponse")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record UserResponse(
        @Schema(example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
        UUID id,
        @Schema(example = "John Doe")
        String fullName,
        @Schema(example = "12345678901")
        String cpfCnpj,
        @Schema(example = "johndoe@example.com")
        String email,
        @Schema(example = "password123")
        String password,
        @Schema(example = "CUSTOMER")
        UserTypeRequest userType) {
}
