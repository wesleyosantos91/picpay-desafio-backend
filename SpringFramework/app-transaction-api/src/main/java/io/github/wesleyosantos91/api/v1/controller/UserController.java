package io.github.wesleyosantos91.api.v1.controller;

import static io.github.wesleyosantos91.api.v1.openapi.UserOpenApi.REGEX_UUID;
import static io.github.wesleyosantos91.core.mapper.UserMapper.MAPPER;

import io.github.wesleyosantos91.api.v1.request.UserQueryRequest;
import io.github.wesleyosantos91.api.v1.request.UserRequest;
import io.github.wesleyosantos91.api.v1.response.UserResponse;
import io.github.wesleyosantos91.core.validation.Groups;
import io.github.wesleyosantos91.domain.exception.ResourceNotFoundException;
import io.github.wesleyosantos91.domain.service.UserService;
import jakarta.validation.constraints.Pattern;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public record UserController(UserService service) {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestHeader("x-correlationID") @Pattern(regexp = REGEX_UUID) String correlationId,
                                               @Validated(Groups.Create.class) @RequestBody UserRequest request) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        LOGGER.debug("Function started 'create user'");
        final var response = MAPPER.toResponse(service.create(request));
        stopWatch.stop();
        LOGGER.debug("finished function with sucess 'create user {}' in {} ms", response, stopWatch.getTotalTimeMillis());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse> getById(@RequestHeader("x-correlationID") @Pattern(regexp = REGEX_UUID) String correlationId,
                                                @PathVariable UUID id) throws ResourceNotFoundException {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        LOGGER.debug("Function started 'getById user' with id {}", id);
        final var user = service.findById(id);
        final var response = MAPPER.toResponse(user);
        stopWatch.stop();
        LOGGER.debug("finished function with sucess 'getById user' {} in {} ms", response, stopWatch.getTotalTimeMillis());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<PagedModel<UserResponse>> search(@RequestHeader("x-correlationID") @Pattern(regexp = REGEX_UUID) String correlationId,
                                                           @ModelAttribute UserQueryRequest query, Pageable page) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        LOGGER.info("Function started 'find user'");
        final var pageEntity = service.search(query, page);
        stopWatch.stop();
        LOGGER.info("finished function with user 'find person' in {} ms", stopWatch.getTotalTimeMillis());

        return ResponseEntity.ok().body(new PagedModel<>(MAPPER.toPageResponse(pageEntity)));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponse> update(@RequestHeader("x-correlationID") @Pattern(regexp = REGEX_UUID) String correlationId,
                                               @PathVariable UUID id,
                                               @Validated(Groups.Update.class)
                                              @RequestBody UserRequest request) throws ResourceNotFoundException {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        LOGGER.debug("Function started 'update user'");
        final var user = service.update(id, request);
        stopWatch.stop();
        LOGGER.debug("finished function with sucess 'update user' {} in {} ms", user, stopWatch.getTotalTimeMillis());
        return ResponseEntity.status(HttpStatus.OK).body(MAPPER.toResponse(user));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@RequestHeader("x-correlationID") @Pattern(regexp = REGEX_UUID) String correlationId,
                                       @PathVariable UUID id) throws ResourceNotFoundException {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        LOGGER.debug("Function started 'delete user' with id {}", id);
        service.delete(id);
        stopWatch.stop();
        LOGGER.debug("finished function with sucess 'delete person' in {} ms", stopWatch.getTotalTimeMillis());

        return ResponseEntity.noContent().build();
    }
}
