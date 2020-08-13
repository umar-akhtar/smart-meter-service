package com.scottishpower.smartmeterreadservice.cucumber.steps;

import com.scottishpower.smartmeterreadservice.cucumber.SpringCucumberBaseTests;
import com.scottishpower.smartmeterreadservice.exception.AccountNotFoundException;
import com.scottishpower.smartmeterreadservice.model.SmartMeterReadDetail;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class ReadingsStepDefs extends SpringCucumberBaseTests {

    @Given("the application is running")
    public void theApplicationIsRunning() {
        assertThat(super.isAppUp()).isTrue();
    }

    @Then("a meter reading request is made with the account in the database and the customer can successfully see the readings")
    public void getMeterReadingsForAccountSuccess() {
        ResponseEntity<String> responseEntity = super.getMeterReadings("866318");
        String expectedResponse = "{\"electricityRead\":73002.91,\"gasRead\":97681.01}";
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo(expectedResponse);
    }

    @Then("a meter reading request is made with the account {string} and the customer can see a message stating account not found")
    public void getMeterReadingsForAccountResultsInAccountNotFound(String accountNumber) {
        String accountNotFoundMessage = super.getMeterReadingsForAccountNotInDatabase(accountNumber);
        String expectedResponse = "404: Account " + accountNumber + " not found in database.";
        assertThat(accountNotFoundMessage).isEqualTo(expectedResponse);
    }
}
