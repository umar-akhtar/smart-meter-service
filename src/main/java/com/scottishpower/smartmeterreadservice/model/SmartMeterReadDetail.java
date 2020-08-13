package com.scottishpower.smartmeterreadservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SMART_METER_READ_DETAILS")
public class SmartMeterReadDetail {

    @Id
    @Column(name = "ACCOUNT_NUMBER")
    @JsonIgnore
    private long accountNumber;

    @Column(name = "ELEC_SMART_READ")
    private BigDecimal electricityRead;

    @Column(name = "GAS_SMART_READ")
    private BigDecimal gasRead;

}
