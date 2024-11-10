package io.github.wesleyosantos91.domain.entity.enums;

import java.util.HashMap;
import java.util.Map;

public enum TransactionAuthorizationStatus {

    AUTHORIZED("Authorized"),
    DENIED("Denied");

    private final String value;

    private static final Map<String, TransactionAuthorizationStatus> VALUE_MAP = new HashMap<>();

    static {
        for (TransactionAuthorizationStatus status : TransactionAuthorizationStatus.values()) {
            VALUE_MAP.put(status.value.toLowerCase(), status);
        }
    }

    TransactionAuthorizationStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TransactionAuthorizationStatus fromValue(String value) {
        final TransactionAuthorizationStatus status = VALUE_MAP.get(value.toLowerCase());
        if (status == null) {
            throw new IllegalArgumentException("Unknown transaction authorization status: " + value);
        }
        return status;
    }
}
