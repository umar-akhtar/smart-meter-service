package com.scottishpower.smartmeterreadservice.controller;

import com.scottishpower.smartmeterreadservice.model.SmartMeterReadDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("api/smart")
public interface SmartMeterReadController {

    /**
     * Get smart meter readings for a given account
     * @param accountNumber - account readings to lookup
     * @return responseEntity - contains the meter readings
     */
    @GetMapping
    @RequestMapping(value = {"/reads" + "/{accountNumber}"}, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<SmartMeterReadDetail> getSmartMeterReadDetailsByAccountNumber(@PathVariable long accountNumber);
}
