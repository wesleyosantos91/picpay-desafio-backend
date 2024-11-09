package io.github.wesleyosantos91.domain.entity.enums;

public enum TransactionAuthorizationStatus {
    AUTHORIZED("Authorized"),
    DENIED("Denied");

    private final String value;

    TransactionAuthorizationStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TransactionAuthorizationStatus fromValue(String value) {
        for (TransactionAuthorizationStatus userType : TransactionAuthorizationStatus.values()) {
            if (userType.getValue().equalsIgnoreCase(value)) {
                return userType;
            }
        }
        throw new IllegalArgumentException("Unknown transaction authorization status: " + value);
    }
}
