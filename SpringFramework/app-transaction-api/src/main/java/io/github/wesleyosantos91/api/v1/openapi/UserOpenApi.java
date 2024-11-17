package io.github.wesleyosantos91.api.v1.openapi;

import io.github.wesleyosantos91.api.v1.request.UserQueryRequest;
import io.github.wesleyosantos91.api.v1.request.UserRequest;
import io.github.wesleyosantos91.api.v1.response.CustomProblemDetail;
import io.github.wesleyosantos91.api.v1.response.UserResponse;
import io.github.wesleyosantos91.domain.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "User", description = "API for managing users")
public interface UserOpenApi {

    String REGEX_UUID = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$";

    @Operation(
            summary = "Registers a new user",
            description = "Creates a new user with the provided details.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            headers = {
                                    @Header(
                                            name = "x-correlationID",
                                            description = "Unique identifier for the request",
                                            required = true,
                                            schema = @Schema(type = "string", format = "uuid", pattern = REGEX_UUID)
                                    )
                            },
                            description = "User successfully registered",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid user payload provided.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomProblemDetail.class))
                    )
            }
    )
    ResponseEntity<UserResponse> create(String correlationId,
                                        @RequestBody(
                                                description = "Representation of the new user to be registered",
                                                required = true,
                                                content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserRequest.class))
                                        ) UserRequest request);

    @Operation(
            summary = "Find user by ID",
            description = "Creates a new user with the provided details.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            headers = {
                                    @Header(
                                            name = "x-correlationID",
                                            description = "Unique identifier for the request",
                                            required = true,
                                            schema = @Schema(type = "string", format = "uuid", pattern = REGEX_UUID)
                                    )
                            },
                            description = "User successfully registered",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid user ID provided.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomProblemDetail.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User with the given ID not found.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomProblemDetail.class))
                    )
            }
    )
    ResponseEntity<UserResponse> getById(String correlationId,
                                         @Parameter(description = "Unique identifier of the person",
                                                 example = "fbacbce6-2129-11ef-a5d8-0242ac120002",
                                                 required = true) UUID id) throws ResourceNotFoundException;

    @Operation(
            summary = "Search user with filters",
            description = "Searches for users with the provided filters.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            headers = {
                                    @Header(
                                            name = "x-correlationID",
                                            description = "Unique identifier for the request",
                                            required = true,
                                            schema = @Schema(type = "string", format = "uuid", pattern = REGEX_UUID)
                                    )
                            },
                            description = "User successfully registered",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PagedModel.class)))
                    )
            }
    )
    ResponseEntity<PagedModel<UserResponse>> search(String correlationId,
                                                    @Parameter(
                                                            description = "Filters to apply when searching for users",
                                                            required = false,
                                                            schema = @Schema(implementation = UserQueryRequest.class)
                                                    ) UserQueryRequest query,
                                                    @Parameter(
                                                            description = "Pagination details",
                                                            required = false,
                                                            schema = @Schema(implementation = Pageable.class)
                                                    ) Pageable page);

    @Operation(
            summary = "Update user by ID",
            description = "Updates the user with the provided by Id and details.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            headers = {
                                    @Header(
                                            name = "x-correlationID",
                                            description = "Unique identifier for the request",
                                            required = true,
                                            schema = @Schema(type = "string", format = "uuid", pattern = REGEX_UUID)
                                    )
                            },
                            description = "User successfully registered",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid user payload provided.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomProblemDetail.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User with the given ID not found.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomProblemDetail.class))
                    )
            }
    )
    ResponseEntity<UserResponse> update(String correlationId,
                                        @Parameter(description = "Unique identifier of the person",
                                                example = "fbacbce6-2129-11ef-a5d8-0242ac120002",
                                                required = true) UUID id,
                                        @RequestBody(
                                                description = "Representation of the user to be updated",
                                                required = true,
                                                content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserRequest.class))
                                        ) UserRequest request) throws ResourceNotFoundException;

    @Operation(
            summary = "Delete user by ID",
            description = "Deletes a user with the provided ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            headers = {
                                    @Header(
                                            name = "x-correlationID",
                                            description = "Unique identifier for the request",
                                            required = true,
                                            schema = @Schema(type = "string", format = "uuid", pattern = REGEX_UUID)
                                    )
                            },
                            description = "User successfully deleted",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User with the given ID not found.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomProblemDetail.class))
                    )
            }
    )
    ResponseEntity<Void> delete(String correlationId,
                                @Parameter(description = "Unique identifier of the person",
                                        example = "fbacbce6-2129-11ef-a5d8-0242ac120002",
                                        required = true) UUID id) throws ResourceNotFoundException;
}
