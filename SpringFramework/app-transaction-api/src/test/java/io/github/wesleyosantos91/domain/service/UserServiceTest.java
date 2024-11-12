package io.github.wesleyosantos91.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.github.wesleyosantos91.api.v1.commons.enums.UserTypeRequest;
import io.github.wesleyosantos91.api.v1.request.UserQueryRequest;
import io.github.wesleyosantos91.api.v1.request.UserRequest;
import io.github.wesleyosantos91.core.mapper.UserMapper;
import io.github.wesleyosantos91.domain.UserEntityTemplateLoader;
import io.github.wesleyosantos91.domain.entity.UserEntity;
import io.github.wesleyosantos91.domain.entity.enums.UserType;
import io.github.wesleyosantos91.domain.exception.ResourceNotFoundException;
import io.github.wesleyosantos91.domain.repository.UserRepository;
import io.github.wesleyosantos91.domain.request.UserQueryRequestTemplateLoader;
import io.github.wesleyosantos91.domain.request.UserRequestTemplateLoader;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    @ParameterizedTest
    @ValueSource(strings = {"CUSTOMER", "MERCHANT"})
    @DisplayName("[domain] - should create a user customer and merchant with success")
    void shouldCreateUserCustomerAndCustomerWithSucess(UserTypeRequest userType) {

        final UserRequest userRequest = userType.name().equals("CUSTOMER") ?
                UserRequestTemplateLoader.createUserCustomerSucess() :
                UserRequestTemplateLoader.createUserMerchantSucess();

        when(mapper.toEntity(any(UserRequest.class))).thenAnswer(invocation -> {
            final UserRequest request = invocation.getArgument(0);
            final UserEntity userEntity = new UserEntity();
            userEntity.setFullName(request.fullName());
            userEntity.setCpfCnpj(request.cpfCnpj());
            userEntity.setEmail(request.email());
            userEntity.setPassword(request.password());
            userEntity.setUserType(UserType.valueOf(request.userType().name()));
            return userEntity;
        });

        when(repository.save(any(UserEntity.class))).thenAnswer(invocation -> {
            final UserEntity userEntity = invocation.getArgument(0);
            userEntity.setId(UUID.randomUUID());
            userEntity.setFullName(userRequest.fullName());
            userEntity.setCpfCnpj(userRequest.cpfCnpj());
            userEntity.setEmail(userRequest.email());
            userEntity.setPassword(userRequest.password());
            userEntity.setUserType(UserType.valueOf(userRequest.userType().name()));
            return userEntity;
        });

        UserEntity userEntity = service.create(userRequest);

        assertThat(userEntity).isNotNull().satisfies(user -> {
            assertThat(user.getId()).isNotNull();
            assertThat(user.getFullName()).isEqualTo(userRequest.fullName());
            assertThat(user.getCpfCnpj()).isEqualTo(userRequest.cpfCnpj());
            assertThat(user.getEmail()).isEqualTo(userRequest.email());
            assertThat(user.getPassword()).isEqualTo(userRequest.password());
            assertThat(user.getUserType().name()).isEqualTo(userRequest.userType().name());
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"CUSTOMER", "MERCHANT"})
    @DisplayName("[domain] - should return a user with success")
    void sholdReturnAUserWithSucsess(UserTypeRequest userType) throws ResourceNotFoundException {
        final UserRequest userRequest = userType.name().equals("CUSTOMER") ?
                UserRequestTemplateLoader.createUserCustomerSucess() :
                UserRequestTemplateLoader.createUserMerchantSucess();

        final UUID uuid = UUID.randomUUID();

        when(repository.findById(uuid)).thenAnswer(invocation -> {
            final UserEntity userEntity = new UserEntity();
            userEntity.setId(uuid);
            userEntity.setFullName(userRequest.fullName());
            userEntity.setCpfCnpj(userRequest.cpfCnpj());
            userEntity.setEmail(userRequest.email());
            userEntity.setPassword(userRequest.password());
            userEntity.setUserType(UserType.valueOf(userRequest.userType().name()));
            return java.util.Optional.of(userEntity);
        });

        UserEntity userEntity = service.findById(uuid);

        assertThat(userEntity).isNotNull().satisfies(user -> {
            assertThat(user.getId()).isNotNull();
            assertThat(user.getId()).isEqualTo(uuid);
            assertThat(user.getFullName()).isEqualTo(userRequest.fullName());
            assertThat(user.getCpfCnpj()).isEqualTo(userRequest.cpfCnpj());
            assertThat(user.getEmail()).isEqualTo(userRequest.email());
            assertThat(user.getPassword()).isEqualTo(userRequest.password());
            assertThat(user.getUserType().name()).isEqualTo(userRequest.userType().name());
        });
    }


    @Test
    @DisplayName("[domain] - should throw exception the type 'ResourceNotFoundException'")
    void shouldThrowExceptionTheTypeResourceNotFoundException() {
        final UUID uuid = UUID.randomUUID();

        final String expectedMessage = "Not found regitstry with code " + uuid;

        final Throwable exception = assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(uuid);
        });

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("[domain] - should return a page is not empty")
    void shouldReturnAListIsNotEmpty() {
        final List<UserEntity> users = List.of(UserEntityTemplateLoader.createUserEntityToSaved());

        final var userEntity = new UserEntity();
        when(mapper.toEntity(any(UserQueryRequest.class))).thenReturn(userEntity);

        final PageRequest pageRequest = PageRequest.of(1, 10);
        when(repository.findAll(Example.of(userEntity), pageRequest)).thenReturn(new PageImpl<>(users, pageRequest, users.size()));
        final Page<UserEntity> result = service.search(UserQueryRequestTemplateLoader.createUserQueryRequestEmpty(), pageRequest);

        assertThat(result.getContent()).isNotEmpty();
        assertThat(result.getTotalElements()).isPositive();
    }


    @Test
    @DisplayName("[domain] - should update a user and return email change")
    void shouldUpdateAUserAndReturnEmailChange() {
        //TODO
    }

    @ParameterizedTest
    @ValueSource(strings = {"CUSTOMER", "MERCHANT"})
    @DisplayName("[domain] - should update a user and return updated user")
    void shouldUpdateAUserAndReturnUpdatedUser(UserTypeRequest userType) throws ResourceNotFoundException {
        final UserRequest userRequest = userType.name().equals("CUSTOMER") ?
                UserRequestTemplateLoader.createUserCustomerSucess() :
                UserRequestTemplateLoader.createUserMerchantSucess();

        final UUID uuid = UUID.randomUUID();

        when(repository.findById(uuid)).thenAnswer(invocation -> {
            final UserEntity userEntity = new UserEntity();
            userEntity.setId(uuid);
            userEntity.setFullName("Old Name");
            userEntity.setCpfCnpj("Old CPF/CNPJ");
            userEntity.setEmail("old.email@example.com");
            userEntity.setPassword("oldpassword");
            userEntity.setUserType(UserType.valueOf(userRequest.userType().name()));
            return java.util.Optional.of(userEntity);
        });

        when(mapper.toEntity(any(UserRequest.class), any(UserEntity.class))).thenAnswer(invocation -> {
            final UserRequest request = invocation.getArgument(0);
            final UserEntity userEntity = invocation.getArgument(1);
            userEntity.setFullName(request.fullName());
            userEntity.setCpfCnpj(request.cpfCnpj());
            userEntity.setEmail(request.email());
            userEntity.setPassword(request.password());
            userEntity.setUserType(UserType.valueOf(request.userType().name()));
            return userEntity;
        });

        when(repository.save(any(UserEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserEntity updatedUser = service.update(uuid, userRequest);

        assertThat(updatedUser).isNotNull().satisfies(user -> {
            assertThat(user.getId()).isEqualTo(uuid);
            assertThat(user.getFullName()).isEqualTo(userRequest.fullName());
            assertThat(user.getCpfCnpj()).isEqualTo(userRequest.cpfCnpj());
            assertThat(user.getEmail()).isEqualTo(userRequest.email());
            assertThat(user.getPassword()).isEqualTo(userRequest.password());
            assertThat(user.getUserType().name()).isEqualTo(userRequest.userType().name());
        });
    }

    @Test
    @DisplayName("[domain] - should throw exception when updating non-existent user")
    void shouldThrowExceptionWhenUpdatingNonExistentUser() {
        final UUID uuid = UUID.randomUUID();
        final UserRequest userRequest = UserRequestTemplateLoader.createUserCustomerSucess();

        when(repository.findById(uuid)).thenReturn(java.util.Optional.empty());

        final Throwable exception = assertThrows(ResourceNotFoundException.class, () -> {
            service.update(uuid, userRequest);
        });

        assertEquals("Not found regitstry with code " + uuid, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"CUSTOMER", "MERCHANT"})
    @DisplayName("[domain] - should delete a user by id")
    void shouldDeleteAUserById(UserTypeRequest userType) throws ResourceNotFoundException {

        final UUID uuid = UUID.randomUUID();

        final UserRequest userRequest = userType.name().equals("CUSTOMER") ?
                UserRequestTemplateLoader.createUserCustomerSucess() :
                UserRequestTemplateLoader.createUserMerchantSucess();

        when(repository.findById(any(UUID.class))).thenAnswer(invocation -> {
            final UserEntity userEntity = new UserEntity();
            userEntity.setId(uuid);
            userEntity.setFullName(userRequest.fullName());
            userEntity.setCpfCnpj(userRequest.cpfCnpj());
            userEntity.setEmail(userRequest.email());
            userEntity.setPassword(userRequest.password());
            userEntity.setUserType(UserType.valueOf(userRequest.userType().name()));
            return java.util.Optional.of(userEntity);
        });

        service.delete(uuid);
        verify(repository, times(1)).delete(any(UserEntity.class));
    }

    @Test
    @DisplayName("[domain] - should throw exception when deleting non-existent user")
    void shouldThrowExceptionWhenDeletingNonExistentUser() {
        final UUID uuid = UUID.randomUUID();

        when(repository.findById(uuid)).thenReturn(java.util.Optional.empty());

        final Throwable exception = assertThrows(ResourceNotFoundException.class, () -> {
            service.delete(uuid);
        });

        assertEquals("Not found regitstry with code " + uuid, exception.getMessage());
    }
}