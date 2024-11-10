package io.github.wesleyosantos91.domain.service;

import static java.text.MessageFormat.format;

import io.github.wesleyosantos91.api.v1.request.UserQueryRequest;
import io.github.wesleyosantos91.api.v1.request.UserRequest;
import io.github.wesleyosantos91.core.mapper.UserMapper;
import io.github.wesleyosantos91.domain.entity.UserEntity;
import io.github.wesleyosantos91.domain.exception.ResourceNotFoundException;
import io.github.wesleyosantos91.domain.repository.UserRepository;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository respository;
    private final UserMapper mapper;

    public UserService(UserRepository respository, UserMapper mapper) {
        this.respository = respository;
        this.mapper = mapper;
    }

    @Transactional
    public UserEntity create(UserRequest userRequest) {
        return respository.save(mapper.toEntity(userRequest));
    }

    public UserEntity findById(UUID id) throws ResourceNotFoundException {
        return respository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Not found regitstry with code {0}", id)));
    }

    public Page<UserEntity> search(UserQueryRequest queryRequest, Pageable pageable) {

        final var userEntityExample = Example.of(mapper.toEntity(queryRequest));
        return respository.findAll(userEntityExample, pageable);
    }

    @Transactional
    public UserEntity update(UUID id, UserRequest request) throws ResourceNotFoundException {
        final var user = mapper.toEntity(request, findById(id));
        return respository.save(user);
    }

    @Transactional
    public void delete(UUID id) throws ResourceNotFoundException {
        final var user = findById(id);
        respository.delete(user);
    }
}
