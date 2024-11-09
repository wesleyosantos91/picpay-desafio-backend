package io.github.wesleyosantos91.domain.entity.enums;

public enum NotificationStatus {
    PENDING("Pending"),
    SENT("Sent"),
    FAILED("Failed");

    private final String value;

    NotificationStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static NotificationStatus fromValue(String value) {
        for (NotificationStatus userType : NotificationStatus.values()) {
            if (userType.getValue().equalsIgnoreCase(value)) {
                return userType;
            }
        }
        throw new IllegalArgumentException("Unknown notification status: " + value);
    }
}
