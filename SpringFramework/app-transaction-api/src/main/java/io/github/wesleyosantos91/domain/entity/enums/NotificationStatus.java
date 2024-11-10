package io.github.wesleyosantos91.domain.entity.enums;

import java.util.HashMap;
import java.util.Map;

public enum NotificationStatus {

    PENDING("Pending"),
    SENT("Sent"),
    FAILED("Failed");

    private final String value;

    private static final Map<String, NotificationStatus> VALUE_MAP = new HashMap<>();

    static {
        for (NotificationStatus status : NotificationStatus.values()) {
            VALUE_MAP.put(status.value.toLowerCase(), status);
        }
    }

    NotificationStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static NotificationStatus fromValue(String value) {
        final NotificationStatus status = VALUE_MAP.get(value.toLowerCase());
        if (status == null) {
            throw new IllegalArgumentException("Unknown notification status: " + value);
        }
        return status;
    }
}
