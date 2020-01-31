package com.lits.rubinskyy.hw3.Tests;

import com.lits.rubinskyy.hw3.CommonMethodsLibrary;
import com.lits.rubinskyy.hw3.HttpClient;
import okhttp3.Headers;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.lits.rubinskyy.hw3.HttpClient.convert;

public class LoginTests {
    private HttpClient client = new HttpClient();
    private CommonMethodsLibrary commonMethodsLibrary = new CommonMethodsLibrary();

    @Test (groups = {"loginTest"})
    public void testLogin() throws IOException {
        // REQUEST
        Response loginResponse = commonMethodsLibrary.GetLoginResponse();

        // ASSERT
        Assert.assertEquals(loginResponse.code(), 200);
    }

    @Test (groups = {"loginTest"})
    public void testGetUserInfo() throws IOException {
        String userId = "3HvulRdUfWgWM8mz6JJ6";
        // REQUEST URL
        String url = "https://europe-west2-search-app-263e2.cloudfunctions.net/webapp/api/v1/users/"+userId;

        // REQUEST
        Response infoResponse = client.GET(url, Headers.of("Authorization", "Bearer " + commonMethodsLibrary.GetAccessToken()));
        final Map<String, String> infoResponseData = convert(infoResponse, Map.class);

        // ASSERT
        Assert.assertEquals(infoResponseData.get("firstName"), "Yuriy");
        Assert.assertEquals(infoResponseData.get("lastName"), "Rubins");
        Assert.assertEquals(infoResponseData.get("email"), "rubins.yurko.test@gmail.com");
    }
}
