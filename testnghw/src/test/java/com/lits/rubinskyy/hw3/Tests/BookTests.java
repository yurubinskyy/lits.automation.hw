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

public class BookTests {
    private HttpClient client = new HttpClient();
    private CommonMethodsLibrary commonMethodsLibrary = new CommonMethodsLibrary();

    @Test (groups = {"bookTest"})
    public void testCreateBook() throws IOException {
        // REQUEST URL
        String url = "https://europe-west2-search-app-263e2.cloudfunctions.net/webapp/api/v1/books";

        // REQUEST BODY
        HashMap<String, String> body = new HashMap<>();
        Random rand = new Random();
        String value = String.valueOf(rand.nextInt(9999999));
        //String value1 = String.valueOf((int)Math.random());
        body.put("isbn", value);
        body.put("name", "BookName" + value);
        body.put("description", "TextDescription");
        body.put("author", "TestAuthor");
        body.put("publishDate", "2020");
        body.put("publisher", "TestPublisher");

        // REQUEST
        Response bookResponse = client.POST(url, Headers.of("Authorization", "Bearer " + commonMethodsLibrary.GetAccessToken()), body);

        final Map<String, String> bookResponseData = convert(bookResponse, Map.class);
        String isbn = bookResponseData.get("isbn");

        // ASSERT
        Assert.assertEquals(isbn, value);
    }

    @Test (groups = {"bookTest"})
    public void testGetBookByIsbn() throws IOException {
        String isbnTest = "3457789";
        // REQUEST URL
        String url = "https://europe-west2-search-app-263e2.cloudfunctions.net/webapp/api/v1/books/"+isbnTest;

        // REQUEST
        Response bookInfoResponse = client.GET(url, Headers.of("Authorization", "Bearer " + commonMethodsLibrary.GetAccessToken()));
        final Map<String, String> bookInfoResponseData = convert(bookInfoResponse, Map.class);

        // ASSERT
        Assert.assertEquals(bookInfoResponseData.get("name"), "Harry potter");
        Assert.assertEquals(bookInfoResponseData.get("publisher"), "Bloomsbury Publishing");
        Assert.assertEquals(bookInfoResponseData.get("publishDate"), "1997");
        Assert.assertEquals(bookInfoResponseData.get("author"), "J. K. Rowling");
        Assert.assertEquals(bookInfoResponseData.get("isbn"), isbnTest);
    }

    @Test (groups = {"bookTest"})
    public void testSearch() throws IOException {
        String searchInput = "harry";
        // REQUEST URL
        String url = "https://europe-west2-search-app-263e2.cloudfunctions.net/webapp/api/v1/search?q="+searchInput;

        // REQUEST
        Response searchResponse = client.GET(url, Headers.of("Authorization", "Bearer " + commonMethodsLibrary.GetAccessToken()));
        final Map<String, Integer> searchResponseData = convert(searchResponse, Map.class);
        int numberOfMatches = searchResponseData.get("nbHits");

        // ASSERT
        Assert.assertEquals(numberOfMatches, 2);
    }
}
