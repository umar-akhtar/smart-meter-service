package com.scottishpower.smartmeterreadservice.controller.impl;


import com.scottishpower.smartmeterreadservice.exception.AccountNotFoundException;
import com.scottishpower.smartmeterreadservice.model.SmartMeterReadDetail;
import com.scottishpower.smartmeterreadservice.repository.SmartMeterReadRepository;
import com.scottishpower.smartmeterreadservice.service.SmartMeterReadService;
import com.scottishpower.smartmeterreadservice.service.impl.SmartMeterReadServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SmartMeterReadControllerImplTest {

    private SmartMeterReadControllerImpl smartMeterReadController;
    @Mock
    private SmartMeterReadRepository smartMeterReadRepository;
    SmartMeterReadDetail expectedReadDetail;

    private static final long ACCOUNT_NUMBER = 77787L;
    private static final double ELEC_READ = 97000.34;
    private static final double GAS_READ = 89899.12;

    @Before
    public void setup() {
        SmartMeterReadService smartMeterReadServiceSpy = spy(new SmartMeterReadServiceImpl(smartMeterReadRepository));
        smartMeterReadController = new SmartMeterReadControllerImpl(smartMeterReadServiceSpy);
        expectedReadDetail = new SmartMeterReadDetail(ACCOUNT_NUMBER, BigDecimal.valueOf(ELEC_READ), BigDecimal.valueOf(GAS_READ));
    }

    @Test
    public void getSmartMeterReadingsByAccountNumberWhereAccountExists() {
        when(smartMeterReadRepository.findById(ACCOUNT_NUMBER)).thenReturn(Optional.of(expectedReadDetail));
        ResponseEntity<SmartMeterReadDetail> actualReadDetail = smartMeterReadController.getSmartMeterReadDetailsByAccountNumber(ACCOUNT_NUMBER);

        Assert.assertEquals(expectedReadDetail, actualReadDetail.getBody());
    }

    @Test(expected = AccountNotFoundException.class)
    public void getSmartMeterReadDetailsByAccountNumberWhereAccountDoesNotExist() {
        when(smartMeterReadRepository.findById(any())).thenReturn(Optional.empty());
        smartMeterReadController.getSmartMeterReadDetailsByAccountNumber(ACCOUNT_NUMBER);
    }

    @Test(expected = NullPointerException.class)
    public void getSmartMeterReadDetailsByAccountNumberThrowNullPointer() {
        when(smartMeterReadRepository.findById(any())).thenThrow(new NullPointerException());
        smartMeterReadController.getSmartMeterReadDetailsByAccountNumber(ACCOUNT_NUMBER);
    }

    @Test(expected = SQLException.class)
    public void getSmartMeterReadDetailsByAccountNumberThrowSQLException() {
        when(smartMeterReadRepository.findById(any())).thenThrow(new SQLException());
        smartMeterReadController.getSmartMeterReadDetailsByAccountNumber(ACCOUNT_NUMBER);
    }

    @Test(expected = NumberFormatException.class)
    public void getSmartMeterReadDetailsByAccountNumberThrowNumberFormatException() {
        when(smartMeterReadRepository.findById(any())).thenThrow(new NumberFormatException());
        smartMeterReadController.getSmartMeterReadDetailsByAccountNumber(ACCOUNT_NUMBER);
    }
}