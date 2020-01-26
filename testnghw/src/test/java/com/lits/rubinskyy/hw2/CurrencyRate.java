package com.lits.rubinskyy.hw2;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CurrencyRate {
    @JsonProperty("StartDate")
    private String StartDate;
    @JsonProperty("TimeSign")
    private String TimeSign;
    @JsonProperty("CurrencyCode")
    private String CurrencyCode;
    @JsonProperty("CurrencyCodeL")
    private String CurrencyCodeL;
    @JsonProperty("Units")
    private String Units;
    @JsonProperty("Amount")
    private BigDecimal Amount;

    public String getStartDate() {
        return StartDate;
    }
    public void setStartDate(String startDate) {
        StartDate = startDate;
    }
    public String getTimeSign() {
        return TimeSign;
    }
    public void setTimeSign(String timeSign) {
        TimeSign = timeSign;
    }
    public String getCurrencyCode() {
        return CurrencyCode;
    }
    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }
    public String getCurrencyCodeL() {
        return CurrencyCodeL;
    }
    public void setCurrencyCodeL(String currencyCodeL) {
        CurrencyCodeL = currencyCodeL;
    }
    public String getUnits() {
        return Units;
    }
    public void setUnits(String units) {
        Units = units;
    }
    public BigDecimal getAmount() {
        return Amount;
    }
    public void setAmount(BigDecimal amount) {
        Amount = amount;
    }

    @Override
    public String toString() {
        return "CurrencyRate{" +
                "StartDate='" + StartDate + '\'' +
                ", TimeSign='" + TimeSign + '\'' +
                ", CurrencyCode='" + CurrencyCode + '\'' +
                ", CurrencyCodeL='" + CurrencyCodeL + '\'' +
                ", Units='" + Units + '\'' +
                ", Amount='" + Amount + '\'' +
                '}';
    }
}
