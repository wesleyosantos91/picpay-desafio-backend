//package io.github.wesleyosantos91.api.v1.controller;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.doThrow;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.github.wesleyosantos91.api.v1.commons.enums.UserTypeRequest;
//import io.github.wesleyosantos91.api.v1.request.UserRequest;
//import io.github.wesleyosantos91.domain.UserEntityTemplateLoader;
//import io.github.wesleyosantos91.domain.entity.UserEntity;
//import io.github.wesleyosantos91.domain.exception.ResourceNotFoundException;
//import io.github.wesleyosantos91.domain.request.UserQueryRequestTemplateLoader;
//import io.github.wesleyosantos91.domain.request.UserRequestTemplateLoader;
//import io.github.wesleyosantos91.domain.response.UserResponseTemplateLoader;
//import io.github.wesleyosantos91.domain.service.UserService;
//import java.util.List;
//import java.util.UUID;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//
//
//class UserControllerIT {
//
//    @Test
//    @DisplayName("Test IT")
//    void name() {
//        System.out.println("Test IT");
//    }
//}