package com.scottishpower.smartmeterreadservice.repository;

import com.scottishpower.smartmeterreadservice.model.SmartMeterReadDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmartMeterReadRepository extends CrudRepository<SmartMeterReadDetail, Long> {
}
