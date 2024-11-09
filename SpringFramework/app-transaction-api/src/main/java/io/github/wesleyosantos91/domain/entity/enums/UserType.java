package io.github.wesleyosantos91.domain.entity.enums;

public enum UserType {
    CUSTOMER("Customer"),
    MERCHANT("Merchant");

    private final String value;

    UserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static UserType fromValue(String value) {
        for (UserType userType : UserType.values()) {
            if (userType.getValue().equalsIgnoreCase(value)) {
                return userType;
            }
        }
        throw new IllegalArgumentException("Unknown user type: " + value);
    }
}
