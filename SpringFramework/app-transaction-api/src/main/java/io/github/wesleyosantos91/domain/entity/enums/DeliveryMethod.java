package io.github.wesleyosantos91.domain.entity.enums;

public enum DeliveryMethod {
    EMAIL("Email"),
    SMS("SMS");

    private final String value;

    DeliveryMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DeliveryMethod fromValue(String value) {
        for (DeliveryMethod userType : DeliveryMethod.values()) {
            if (userType.getValue().equalsIgnoreCase(value)) {
                return userType;
            }
        }
        throw new IllegalArgumentException("Unknown delivery method: " + value);
    }
}
