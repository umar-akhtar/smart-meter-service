package com.scottishpower.smartmeterreadservice.controller.impl;

import com.scottishpower.smartmeterreadservice.controller.SmartMeterReadController;
import com.scottishpower.smartmeterreadservice.exception.AccountNotFoundException;
import com.scottishpower.smartmeterreadservice.model.SmartMeterReadDetail;
import com.scottishpower.smartmeterreadservice.service.SmartMeterReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class SmartMeterReadControllerImpl implements SmartMeterReadController {

    SmartMeterReadService smartMeterReadService;

    @Autowired
    public SmartMeterReadControllerImpl(SmartMeterReadService smartMeterReadService) {
        this.smartMeterReadService = smartMeterReadService;
    }

    @Override
    public ResponseEntity<SmartMeterReadDetail> getSmartMeterReadDetailsByAccountNumber(@PathVariable long accountNumber) {
        SmartMeterReadDetail smartMeterReadDetail = smartMeterReadService.getSmartMeterReadDetailsByAccountNumber(accountNumber);
        return new ResponseEntity<>(smartMeterReadDetail, HttpStatus.OK);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity handleAccountNotFoundException(AccountNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({SQLException.class, NullPointerException.class, NumberFormatException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity handleSQLNullNumberException(Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }




}
