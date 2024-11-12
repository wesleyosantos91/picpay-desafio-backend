package io.github.wesleyosantos91.api.v1.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.wesleyosantos91.api.v1.commons.enums.UserTypeRequest;
import io.github.wesleyosantos91.api.v1.request.UserRequest;
import io.github.wesleyosantos91.domain.UserEntityTemplateLoader;
import io.github.wesleyosantos91.domain.entity.UserEntity;
import io.github.wesleyosantos91.domain.exception.ResourceNotFoundException;
import io.github.wesleyosantos91.domain.request.UserQueryRequestTemplateLoader;
import io.github.wesleyosantos91.domain.request.UserRequestTemplateLoader;
import io.github.wesleyosantos91.domain.response.UserResponseTemplateLoader;
import io.github.wesleyosantos91.domain.service.UserService;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@WebMvcTest(UserController.class)
@ExtendWith(SpringExtension.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("[controller] - create user successfully")
    void createUserSuccessfully() throws Exception {
        final var uuid = UUID.randomUUID();
        final var userRequest = UserRequestTemplateLoader.createUserCustomerSucess();
        final var userResponse = UserResponseTemplateLoader.createUserResponse();

        when(service.create(any(UserRequest.class))).thenReturn(UserEntityTemplateLoader.createUserEntityToSaved());

        ResultActions result = mockMvc.perform(post("/v1/users")
                .header("x-correlationID", uuid.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest))
                .accept(MediaType.APPLICATION_JSON));

        result.andDo(MockMvcResultHandlers.print());
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.id").value(userResponse.id().toString()));
        verify(service, times(1)).create(any(UserRequest.class));
    }

    @Test
    @DisplayName("[controller] - create user with invalid correlation ID")
    void createUserWithInvalidCorrelationId() throws Exception {
        final var userRequest = UserRequestTemplateLoader.createUserCustomerSucess();

        ResultActions result = mockMvc.perform(post("/v1/users")
                .header("x-correlationID", "invalid-correlation-id")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest))
                .accept(MediaType.APPLICATION_JSON));

        result.andDo(MockMvcResultHandlers.print());
        result.andExpect(status().isBadRequest());
        verify(service, times(0)).create(any(UserRequest.class));
    }

    @Test
    @DisplayName("[controller] - create user with invalid request")
    void createUserWithInvalidRequest() throws Exception {
        final var uuid = UUID.randomUUID();
        final var invalidRequest = new UserRequest(null, "invalid-email", "short", "", UserTypeRequest.CUSTOMER);

        ResultActions result = mockMvc.perform(post("/v1/users")
                .header("x-correlationID", uuid.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest))
                .accept(MediaType.APPLICATION_JSON));

        result.andDo(MockMvcResultHandlers.print());
        result.andExpect(status().isBadRequest());
        verify(service, times(0)).create(any(UserRequest.class));
    }

    @Test
    @DisplayName("[controller] - get user by ID successfully")
    void getUserByIdSuccessfully() throws Exception {
        final var uuid = UUID.randomUUID();
        final var userResponse = UserResponseTemplateLoader.createUserResponse();

        when(service.findById(uuid)).thenReturn(UserEntityTemplateLoader.createUserEntityToSaved());

        ResultActions result = mockMvc.perform(get("/v1/users/{id}", uuid)
                .header("x-correlationID", uuid.toString())
                .accept(MediaType.APPLICATION_JSON));

        result.andDo(MockMvcResultHandlers.print());
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").value(userResponse.id().toString()));
        verify(service, times(1)).findById(uuid);
    }

    @Test
    @DisplayName("[controller] - get user by ID with invalid correlation ID")
    void getUserByIdWithInvalidCorrelationId() throws Exception {
        final var uuid = UUID.randomUUID();

        ResultActions result = mockMvc.perform(get("/v1/users/{id}", uuid)
                .header("x-correlationID", "invalid-correlation-id")
                .accept(MediaType.APPLICATION_JSON));

        result.andDo(MockMvcResultHandlers.print());
        result.andExpect(status().isBadRequest());
        verify(service, times(0)).findById(uuid);
    }

    @Test
    @DisplayName("[controller] - get user by non-existent ID")
    void getUserByNonExistentId() throws Exception {
        final var uuid = UUID.randomUUID();

        doThrow(new ResourceNotFoundException("User not found")).when(service).findById(uuid);

        ResultActions result = mockMvc.perform(get("/v1/users/{id}", uuid)
                .header("x-correlationID", uuid.toString())
                .accept(MediaType.APPLICATION_JSON));

        result.andDo(MockMvcResultHandlers.print());
        result.andExpect(status().isNotFound());
        verify(service, times(1)).findById(uuid);
    }

    @Test
    @DisplayName("[controller] - search users successfully")
    void searchUsersSuccessfully() throws Exception {
        final var uuid = UUID.randomUUID();
        final var queryRequest = UserQueryRequestTemplateLoader.createUserQueryRequestEmpty();
        final var pageRequest = PageRequest.of(0, 10);
        final var userEntities = List.of(UserEntityTemplateLoader.createUserEntityToSaved());
        final var pageEntity = new PageImpl<>(userEntities, pageRequest, userEntities.size());

        when(service.search(queryRequest, pageRequest)).thenReturn(pageEntity);

        ResultActions result = mockMvc.perform(get("/v1/users")
                .header("x-correlationID", uuid.toString())
                .param("page", "0")
                .param("size", "10")
                .accept(MediaType.APPLICATION_JSON));

        result.andDo(MockMvcResultHandlers.print());
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.content").isNotEmpty());
        verify(service, times(1)).search(queryRequest, pageRequest);
    }

    @Test
    @DisplayName("[controller] - search users with invalid correlation ID")
    void searchUsersWithInvalidCorrelationId() throws Exception {
        final var queryRequest = UserQueryRequestTemplateLoader.createUserQueryRequestEmpty();
        final var pageRequest = PageRequest.of(0, 10);

        ResultActions result = mockMvc.perform(get("/v1/users")
                .header("x-correlationID", "invalid-correlation-id")
                .param("page", "0")
                .param("size", "10")
                .accept(MediaType.APPLICATION_JSON));

        result.andDo(MockMvcResultHandlers.print());
        result.andExpect(status().isBadRequest());
        verify(service, times(0)).search(queryRequest, pageRequest);
    }

    @Test
    @DisplayName("[controller] - search users with empty result")
    void searchUsersWithEmptyResult() throws Exception {
        final var uuid = UUID.randomUUID();
        final var queryRequest = UserQueryRequestTemplateLoader.createUserQueryRequestEmpty();
        final var pageRequest = PageRequest.of(0, 10);
        final var pageEntity = new PageImpl<UserEntity>(List.of(), pageRequest, List.of().size());

        when(service.search(queryRequest, pageRequest)).thenReturn(pageEntity);

        ResultActions result = mockMvc.perform(get("/v1/users")
                .header("x-correlationID", uuid.toString())
                .param("page", "0")
                .param("size", "10")
                .accept(MediaType.APPLICATION_JSON));

        result.andDo(MockMvcResultHandlers.print());
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.content").isEmpty());
        verify(service, times(1)).search(queryRequest, pageRequest);
    }

    @Test
    @DisplayName("[controller] - update user successfully")
    void updateUserSuccessfully() throws Exception {
        final var uuid = UUID.fromString("f4b3b9b1-5b4f-4b15-8e85-3f946f24b1c3");
        final var userRequest = UserRequestTemplateLoader.createUserCustomerSucess();
        final var userEntity = UserEntityTemplateLoader.createUserEntityToSaved();

        when(service.update(uuid, userRequest)).thenReturn(userEntity);

        ResultActions result = mockMvc.perform(put("/v1/users/{id}", uuid)
                .header("x-correlationID", uuid.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest))
                .accept(MediaType.APPLICATION_JSON));

        result.andDo(MockMvcResultHandlers.print());
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").isNotEmpty());
        verify(service, times(1)).update(uuid, userRequest);
    }

    @Test
    @DisplayName("[controller] - update user with invalid correlation ID")
    void updateUserWithInvalidCorrelationId() throws Exception {
        final var uuid = UUID.randomUUID();
        final var userRequest = UserRequestTemplateLoader.createUserCustomerSucess();

        ResultActions result = mockMvc.perform(put("/v1/users/{id}", uuid)
                .header("x-correlationID", "invalid-correlation-id")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest))
                .accept(MediaType.APPLICATION_JSON));

        result.andDo(MockMvcResultHandlers.print());
        result.andExpect(status().isBadRequest());
        verify(service, times(0)).update(uuid, userRequest);
    }

    @Test
    @DisplayName("[controller] - update user with non-existent ID")
    void updateUserWithNonExistentId() throws Exception {
        final var uuid = UUID.randomUUID();
        final var userRequest = UserRequestTemplateLoader.createUserCustomerSucess();

        doThrow(new ResourceNotFoundException("User not found")).when(service).update(uuid, userRequest);

        ResultActions result = mockMvc.perform(put("/v1/users/{id}", uuid)
                .header("x-correlationID", uuid.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest))
                .accept(MediaType.APPLICATION_JSON));

        result.andDo(MockMvcResultHandlers.print());
        result.andExpect(status().isNotFound());
        verify(service, times(1)).update(uuid, userRequest);
    }

    @Test
    @DisplayName("[controller] - delete user successfully")
    void deleteUserSuccessfully() throws Exception {

        final var uuid = UUID.randomUUID();
        doNothing().when(service).delete(uuid);

        ResultActions result = mockMvc.perform(delete("/v1/users/{id}", uuid)
                .header("x-correlationID", uuid.toString())
                .accept(MediaType.APPLICATION_JSON));

        result.andDo(MockMvcResultHandlers.print());
        result.andExpect(status().isNoContent());
        verify(service, times(1)).delete(uuid);
    }

    @Test
    @DisplayName("[controller] - delete user with invalid correlation ID")
    void deleteUserWithInvalidCorrelationId() throws Exception {

        final var uuid = UUID.randomUUID();
        ResultActions result = mockMvc.perform(delete("/v1/users/{id}", uuid)
                .header("x-correlationID", "invalid-correlation-id")
                .accept(MediaType.APPLICATION_JSON));

        result.andDo(MockMvcResultHandlers.print());
        result.andExpect(status().isBadRequest());
        verify(service, times(0)).delete(uuid);
    }
}