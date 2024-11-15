Feature: Valitadate heath check
  Scenario: Get API heatlth check
    When I request the endpoint "/actuator/health"
    Then the response should be "UP"
    And the status code is 200