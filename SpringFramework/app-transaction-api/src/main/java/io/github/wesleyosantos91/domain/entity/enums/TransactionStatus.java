package io.github.wesleyosantos91.domain.entity.enums;

import java.util.HashMap;
import java.util.Map;

public enum TransactionStatus {

    PENDING("Pending"),
    COMPLETED("Completed"),
    CANCELED("Canceled");

    private final String value;

    private static final Map<String, TransactionStatus> VALUE_MAP = new HashMap<>();

    static {
        for (TransactionStatus status : TransactionStatus.values()) {
            VALUE_MAP.put(status.value.toLowerCase(), status);
        }
    }

    TransactionStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TransactionStatus fromValue(String value) {
        final TransactionStatus status = VALUE_MAP.get(value.toLowerCase());
        if (status == null) {
            throw new IllegalArgumentException("Unknown transaction status: " + value);
        }
        return status;
    }
}
