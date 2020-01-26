package com.lits.rubinskyy.hw2;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;


public class TestNbu {
    @Test
    public void testCheckUsdRate() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request getNbuRequest = new Request.Builder()
                .url("https://bank.gov.ua/NBU_Exchange/exchange?date=20.01.2020&json")
                .build();

        Response getNbuResponse = okHttpClient
                .newCall(getNbuRequest)
                .execute();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String string = getNbuResponse.body().string();

        CollectionType collectionType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, CurrencyRate.class);

        List<CurrencyRate> currencyRates = objectMapper
                .readValue(string, collectionType);

        for (CurrencyRate currencyRate : currencyRates) {
            if (currencyRate.getCurrencyCodeL().equals("USD")) {
                Assert.assertEquals (currencyRate.getAmount(), BigDecimal.valueOf(24.2527));
            }
        }
    }
}
