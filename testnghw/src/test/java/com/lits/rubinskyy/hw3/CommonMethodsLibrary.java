package com.lits.rubinskyy.hw3;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.lits.rubinskyy.hw3.Models.AccessInfo;
import com.lits.rubinskyy.hw3.Models.R;
import okhttp3.Headers;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.lits.rubinskyy.hw3.HttpClient.convert;

public class CommonMethodsLibrary {
    private HttpClient client = new HttpClient();

    public Response GetLoginResponse() throws IOException{
        // REQUEST URL
        String url = "https://europe-west2-search-app-263e2.cloudfunctions.net/webapp/api/auth/login";

        // REQUEST BODY
        HashMap<String, String> body = new HashMap<>();
        body.put("email", "rubins.yurko.test@gmail.com");
        body.put("password", "Test1234!");

        // POST and receive response
        Response loginResponse = client.POST(url, Headers.of(), body);
        return loginResponse;
    }

    public String GetAccessToken() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String loginResponseBody = GetLoginResponse().body().string();


        AccessInfo user = objectMapper.readValue(loginResponseBody, AccessInfo.class);
        System.out.println(user.getR().getAccess_token());

        final Map<String, Map<String, String>> loginResponseData = convert(GetLoginResponse(), Map.class);
        String accessToken = loginResponseData.get("r").get("access_token");
        return accessToken;
    }
}
