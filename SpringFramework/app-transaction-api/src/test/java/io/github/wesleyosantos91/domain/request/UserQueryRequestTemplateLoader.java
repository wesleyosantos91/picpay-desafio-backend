package io.github.wesleyosantos91.domain.request;

import io.github.wesleyosantos91.api.v1.request.UserQueryRequest;

public class UserQueryRequestTemplateLoader {

    public static UserQueryRequest createUserQueryRequestEmpty() {
        return new UserQueryRequest(null, null, null, null, null, null);
    }
}
