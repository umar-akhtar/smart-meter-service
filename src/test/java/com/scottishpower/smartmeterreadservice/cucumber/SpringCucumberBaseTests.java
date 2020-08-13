package com.scottishpower.smartmeterreadservice.cucumber;

import com.scottishpower.smartmeterreadservice.exception.AccountNotFoundException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringCucumberBaseTests {

    private final String SERVER_URL = "http://localhost";
    private final String READINGS_ENDPOINT = "/api/smart/reads/";
    private RestTemplate restTemplate;
    @LocalServerPort
    protected int port;

    public SpringCucumberBaseTests() {
        this.restTemplate = new RestTemplate();
    }

    private String getMeterReadingsEndPoint() {
        return SERVER_URL + ":" + port + READINGS_ENDPOINT;
    }

    public ResponseEntity<String> getMeterReadings(String accountNumber) throws AccountNotFoundException{
        return restTemplate.getForEntity(getMeterReadingsEndPoint() + accountNumber, String.class);
    }

    public String getMeterReadingsForAccountNotInDatabase(String accountNumber) {
        try {
            restTemplate.getForEntity(getMeterReadingsEndPoint() + accountNumber, String.class);
        } catch (HttpClientErrorException.NotFound e){
            return e.getMessage();
        }
        return null;
    }

    public boolean isAppUp() {
        final String healthUrl = "http://localhost:8080/actuator/health";
        final ResponseEntity<String> response = restTemplate.getForEntity(healthUrl, String.class);

        return response.getStatusCode().equals(HttpStatus.OK) && response.getStatusCodeValue() == 200;
    }
}