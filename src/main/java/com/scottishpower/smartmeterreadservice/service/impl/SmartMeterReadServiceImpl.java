package com.scottishpower.smartmeterreadservice.service.impl;

import com.scottishpower.smartmeterreadservice.exception.AccountNotFoundException;
import com.scottishpower.smartmeterreadservice.model.SmartMeterReadDetail;
import com.scottishpower.smartmeterreadservice.repository.SmartMeterReadRepository;
import com.scottishpower.smartmeterreadservice.service.SmartMeterReadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SmartMeterReadServiceImpl implements SmartMeterReadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmartMeterReadServiceImpl.class);
    private SmartMeterReadRepository smartMeterReadRepository;

    @Autowired
    public SmartMeterReadServiceImpl(SmartMeterReadRepository smartMeterReadRepository) {
        this.smartMeterReadRepository = smartMeterReadRepository;
    }

    @Override
    public SmartMeterReadDetail getSmartMeterReadDetailsByAccountNumber(final long accountNumber) {
        LOGGER.info("Retrieving meter details from database for account: {}", accountNumber);
        Optional<SmartMeterReadDetail> smartMeterReadDetails = smartMeterReadRepository.findById(accountNumber);
        if (smartMeterReadDetails.isPresent()) {
            LOGGER.info("-- Account meter details retrieved --");
            return smartMeterReadDetails.get();
        } else {
            LOGGER.error("-- No account found --");
            throw new AccountNotFoundException("Account " + accountNumber + " not found in database.");
        }
    }
}
