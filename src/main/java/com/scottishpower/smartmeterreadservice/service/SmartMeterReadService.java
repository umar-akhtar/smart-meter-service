package com.scottishpower.smartmeterreadservice.service;

import com.scottishpower.smartmeterreadservice.model.SmartMeterReadDetail;

public interface SmartMeterReadService {

    SmartMeterReadDetail getSmartMeterReadDetailsByAccountNumber(final long accountNumber);
}
