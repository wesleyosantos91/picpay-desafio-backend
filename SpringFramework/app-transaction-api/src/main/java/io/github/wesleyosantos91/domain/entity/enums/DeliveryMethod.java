package io.github.wesleyosantos91.domain.entity.enums;

import java.util.HashMap;
import java.util.Map;

public enum DeliveryMethod {
    EMAIL("Email"),
    SMS("SMS");

    private final String value;
    private static final Map<String, DeliveryMethod> VALUE_MAP = new HashMap<>();

    static {
        for (DeliveryMethod deliveryMethod : DeliveryMethod.values()) {
            VALUE_MAP.put(deliveryMethod.value.toLowerCase(), deliveryMethod);
        }
    }

    DeliveryMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DeliveryMethod fromValue(String value) {

        DeliveryMethod deliveryMethod = VALUE_MAP.get(value.toLowerCase());

        if (deliveryMethod == null) {
            throw new IllegalArgumentException("Unknown delivery method: " + value);
        }

        return deliveryMethod;
    }
}
