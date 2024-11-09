package io.github.wesleyosantos91.domain.entity.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserType {
    CUSTOMER("Customer"),
    MERCHANT("Merchant");

    private final String value;

    private static final Map<String, UserType> VALUE_MAP = new HashMap<>();

    static {
        for (UserType status : UserType.values()) {
            VALUE_MAP.put(status.value.toLowerCase(), status);
        }
    }

    UserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static UserType fromValue(String value) {
        UserType status = VALUE_MAP.get(value.toLowerCase());
        if (status == null) {
            throw new IllegalArgumentException("Unknown user type: " + value);
        }
        return status;
    }
}
