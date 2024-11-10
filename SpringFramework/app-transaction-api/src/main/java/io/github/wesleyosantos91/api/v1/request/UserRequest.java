package io.github.wesleyosantos91.api.v1.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.wesleyosantos91.api.v1.commons.enums.UserTypeRequest;
import io.github.wesleyosantos91.core.validation.Groups;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record UserRequest(
        @NotBlank(groups = Groups.Create.class)
        @Size(min = 2, max = 120, groups = Groups.Create.class)
        String fullName,

        @Pattern(groups = Groups.Create.class,
                regexp = "^(?!0{11})(?!1{11})(?!2{11})(?!3{11})(?!4{11})(?!5{11})(?!6{11})(?!7{11})(?!8{11})(?!9{11})([0-9]{11})"
                        + "|(?!0{14})(?!1{14})(?!2{14})(?!3{14})(?!4{14})(?!5{14})(?!6{14})(?!7{14})(?!8{14})(?!9{14})([0-9]{14})$",
                message = "O CPF ou CNPJ fornecido é inválido"
        )
        @NotBlank(groups = Groups.Create.class)
        String cpfCnpj,
        @Email(groups = Groups.Create.class)
        @NotBlank(groups = Groups.Create.class)
        String email,
        @NotBlank(groups = Groups.Create.class)
        String password,
        @NotNull(groups = Groups.Create.class)
        UserTypeRequest userType) {
}