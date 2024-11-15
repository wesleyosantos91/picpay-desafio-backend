package io.github.wesleyosantos91.cucumber.utils;

public class FeatureUtils {

    private static final String URL = "http://localhost";

    public static String getHost(int randomServerPort) {

        return URL + ":" + randomServerPort;
    }
}
