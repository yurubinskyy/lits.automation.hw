package com.lits.rubinskyy.hw5.Tests;

import com.lits.rubinskyy.hw5.Pages.YahooHomePage;
import com.lits.rubinskyy.hw5.Pages.YahooLoginPage;
import com.lits.rubinskyy.hw5.Pages.YahooSentSection;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class YahooTests {
    WebDriver driver;
    YahooLoginPage yahooLoginPage;
    YahooHomePage yahooHomePage;
    YahooSentSection yahooSentSection;

    @BeforeMethod
    private void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @DataProvider(parallel = true)
    public Object[][] data() {
        return new Object[][]{
                {"Test 999"}
        };
    }

    @Test(dataProvider = "data", description = "checkSendingMail")
    public void TC01(String subjectTitle) throws InterruptedException {
        String login = "yuriy.rubinstest@yahoo.com";
        String password = "Test123!";
        String loginTo = "fireandriu@yahoo.com";


        yahooLoginPage = new YahooLoginPage(driver);

        yahooHomePage = yahooLoginPage.Login(login, password)
                .Tap_ComposeButton()
                .SendMail(loginTo, subjectTitle);
        yahooSentSection = yahooHomePage.Tap_SentItem()
                .Refresh_SentSection()
                .Check_Subject(subjectTitle);

        Thread.sleep(3000);
        driver.quit();
    }

    @Test(dataProvider = "data", description = "checkReceivedMail")
    public void TC02(String subjectTitle) throws InterruptedException {
        String login = "fireandriu@yahoo.com";
        String password = "192837465mason";
        //String subjectTitle = "Test 123";

        yahooLoginPage = new YahooLoginPage(driver);

        yahooHomePage = yahooLoginPage.Login(login, password);
        yahooHomePage.Tap_InboxItem()
                .Check_Subject(subjectTitle);

        Thread.sleep(3000);
        driver.quit();
    }
}
