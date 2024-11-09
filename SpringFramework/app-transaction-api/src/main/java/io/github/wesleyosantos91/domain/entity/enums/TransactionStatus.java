package io.github.wesleyosantos91.domain.entity.enums;

public enum TransactionStatus {
    PENDING("Pending"),
    COMPLETED("Completed"),
    CANCELED("Canceled");

    private final String value;

    TransactionStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TransactionStatus fromValue(String value) {
        for (TransactionStatus userType : TransactionStatus.values()) {
            if (userType.getValue().equalsIgnoreCase(value)) {
                return userType;
            }
        }
        throw new IllegalArgumentException("Unknown transaction status: " + value);
    }
}
