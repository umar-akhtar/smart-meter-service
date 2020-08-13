package com.scottishpower.smartmeterreadservice.service.impl;

import com.scottishpower.smartmeterreadservice.exception.AccountNotFoundException;
import com.scottishpower.smartmeterreadservice.model.SmartMeterReadDetail;
import com.scottishpower.smartmeterreadservice.repository.SmartMeterReadRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SmartMeterReadServiceImplTest {

    private SmartMeterReadServiceImpl smartMeterReadService;
    @Mock
    private SmartMeterReadRepository smartMeterReadRepository;
    private SmartMeterReadDetail expectedReadDetail;

    private static final long ACCOUNT_NUMBER = 77787L;
    private static final double ELEC_READ = 97000.34;
    private static final double GAS_READ = 89899.12;

    @Before
    public void setup() {
        smartMeterReadService = new SmartMeterReadServiceImpl(smartMeterReadRepository);
        expectedReadDetail = new SmartMeterReadDetail(ACCOUNT_NUMBER, BigDecimal.valueOf(ELEC_READ), BigDecimal.valueOf(GAS_READ));
    }

    @Test
    public void getSmartMeterReadDetailsByAccountNumberWhereAccountExists() {

        when(smartMeterReadRepository.findById(ACCOUNT_NUMBER)).thenReturn(Optional.of(expectedReadDetail));
        SmartMeterReadDetail actualReadDetail = smartMeterReadService.getSmartMeterReadDetailsByAccountNumber(ACCOUNT_NUMBER);

        Assert.assertEquals(expectedReadDetail, actualReadDetail);
    }

    @Test(expected = AccountNotFoundException.class)
    public void getSmartMeterReadDetailsByAccountNumberWhereAccountDoesNotExist() {
        when(smartMeterReadRepository.findById(any())).thenReturn(Optional.empty());
        smartMeterReadService.getSmartMeterReadDetailsByAccountNumber(ACCOUNT_NUMBER);
    }
}