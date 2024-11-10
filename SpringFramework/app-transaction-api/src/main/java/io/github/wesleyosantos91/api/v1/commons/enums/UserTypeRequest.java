package io.github.wesleyosantos91.api.v1.commons.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserTypeRequest {

    CUSTOMER("Customer"),
    MERCHANT("Merchant");

    private final String value;

    private static final Map<String, UserTypeRequest> VALUE_MAP = new HashMap<>();

    static {
        for (UserTypeRequest status : UserTypeRequest.values()) {
            VALUE_MAP.put(status.value.toLowerCase(), status);
        }
    }

    UserTypeRequest(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static UserTypeRequest fromValue(String value) {
        final UserTypeRequest status = VALUE_MAP.get(value.toLowerCase());
        if (status == null) {
            throw new IllegalArgumentException("Unknown user type: " + value);
        }
        return status;
    }
}
