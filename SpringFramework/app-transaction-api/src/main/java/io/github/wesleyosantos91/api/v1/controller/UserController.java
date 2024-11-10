package io.github.wesleyosantos91.api.v1.controller;

import io.github.wesleyosantos91.api.v1.request.UserQueryRequest;
import io.github.wesleyosantos91.api.v1.request.UserRequest;
import io.github.wesleyosantos91.api.v1.response.UserRespose;
import io.github.wesleyosantos91.core.mapper.UserMapper;
import io.github.wesleyosantos91.core.validation.Groups;
import io.github.wesleyosantos91.domain.exception.ResourceNotFoundException;
import io.github.wesleyosantos91.domain.service.UserService;
import java.util.UUID;
import org.flywaydb.core.internal.util.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public record UserController(UserService service, UserMapper mapper) {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<UserRespose> create(@Validated(Groups.Create.class) @RequestBody UserRequest request) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        LOGGER.debug("Function started 'create user'");
        final var response = mapper.toResponse(service.create(request));
        stopWatch.stop();
        LOGGER.debug("finished function with sucess 'create user {}' in {} ms", response, stopWatch.getTotalTimeMillis());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserRespose> getById(@PathVariable UUID id) throws ResourceNotFoundException {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        LOGGER.debug("Function started 'getById user' with id {}", id);
        final var user = service.findById(id);
        final var response = mapper.toResponse(user);
        stopWatch.stop();
        LOGGER.debug("finished function with sucess 'getById user' {} in {} ms", response, stopWatch.getTotalTimeMillis());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<Page<UserRespose>> search(@ModelAttribute UserQueryRequest query, Pageable page) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        LOGGER.info("Function started 'find user'");
        final var pageEntity = service.search(query, page);
        stopWatch.stop();
        LOGGER.info("finished function with user 'find person' in {} ms", stopWatch.getTotalTimeMillis());
        return ResponseEntity.ok().body(mapper.toPageResponse(pageEntity));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserRespose> update(@PathVariable UUID id,
                                              @Validated(Groups.Update.class)
                                              @RequestBody UserRequest request) throws ResourceNotFoundException {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        LOGGER.debug("Function started 'update user'");
        final var user = service.update(id, request);
        stopWatch.stop();
        LOGGER.debug("finished function with sucess 'update user' {} in {} ms", user, stopWatch.getTotalTimeMillis());
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponse(user));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) throws ResourceNotFoundException {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        LOGGER.debug("Function started 'delete user' with id {}", id);
        service.delete(id);
        stopWatch.stop();
        LOGGER.debug("finished function with sucess 'delete person' in {} ms", stopWatch.getTotalTimeMillis());

        return ResponseEntity.noContent().build();
    }
}