package io.github.wesleyosantos91.cucumber.step;

import static org.junit.Assert.assertEquals;

import com.jayway.jsonpath.JsonPath;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.wesleyosantos91.cucumber.utils.FeatureUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class HealthCheckStep {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> response;

    @LocalServerPort
    private int randomServerPort;

    @When("I request the endpoint {string}")
    public void iRequestTheEndpoint(String path) {
        response  = this.restTemplate.getForEntity(FeatureUtils.getHost(randomServerPort) + path, String.class);
    }

    @Then("the response should be {string}")
    public void theResponseShouldBe(String status) {
        final String statusResponse = JsonPath.parse(response.getBody()).read("$.status");
        assertEquals(status, statusResponse);
    }

    @And("the status code is {int}")
    public void theStatusCodeIs(int statusCode) {
        assertEquals(HttpStatusCode.valueOf(statusCode), response.getStatusCode());
    }
}
