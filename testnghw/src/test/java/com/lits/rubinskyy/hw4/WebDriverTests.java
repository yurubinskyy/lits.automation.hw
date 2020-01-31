package com.lits.rubinskyy.hw4;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class WebDriverTests {

    @Test
    public void checkRandomCoin() throws InterruptedException {
        String trueRandomLableXpath = "//*[@id=\"invisible\"]/h1/span";
        String coinFlipperLableXpath = "//*[@id=\"invisible\"]/h2";
        String flipDropDownXpath = "//*[@id=\"invisible\"]/form/p[1]/select[1]";
        String coinTypeDropDownXpath = "//*[@id=\"invisible\"]/form/p[1]/select[2]";
        String flipCoinsButtonXpath = "//*[@id=\"invisible\"]/form/p[2]/input[1]";
        String reverseCoinXpath = "//img[@title=\"reverse\"]";
        String observeCoinXpath = "//img[@title=\"obverse\"]";
        String goBackButtonXpath = "//*[@id=\"invisible\"]/form/input[4]";
        String resetButtonXpath = "//*[@id=\"invisible\"]/form/p[2]/input[2]";


        System.setProperty("webdriver.chrome.driver", "./src/test/java/resources/chromedriver.exe");

        // BROWSER == DRIVER
        WebDriver driver = new ChromeDriver();

        // Go to
        driver.get("https://www.random.org/coins/");

        // Check ‘True Random Number Service’ label exists and present, check text
        WebElement trueRandomLable = driver.findElement(By.xpath(trueRandomLableXpath));
        Assert.assertEquals(trueRandomLable.getText(), "True Random Number Service");

        // Check ‘Coin Flipper’ label exists and present, check text
        WebElement coinFlipperLable = driver.findElement(By.xpath(coinFlipperLableXpath));
        Assert.assertEquals(coinFlipperLable.getText(), "Coin Flipper");

        // Select value from flip dropdown
        Select flipDropDown = new Select(driver.findElement(By.xpath(flipDropDownXpath)));
        flipDropDown.selectByValue("200");

        // Select value from coin type drop down
        Select coinTypeDropDown = new Select(driver.findElement(By.xpath(coinTypeDropDownXpath)));
        coinTypeDropDown.selectByValue("40-antique.maximinus");

        // Press flip
        driver.findElement(By.xpath(flipCoinsButtonXpath)).click();

        // Print count of reverse coins
        List<WebElement> listofReverseCoin = driver.findElements(By.xpath(reverseCoinXpath));
        int countOfReverseCoin = listofReverseCoin.size();
        System.out.println(countOfReverseCoin);

        // Print count of obverse coins
        List<WebElement> listofObserveCoin = driver.findElements(By.xpath(observeCoinXpath));
        int countOfObserveCoin =listofObserveCoin.size();
        System.out.println(countOfObserveCoin);

        boolean result;
        if (countOfObserveCoin / countOfReverseCoin < 0.1){
            System.out.println("Correct ratio");
        } else {
            System.out.println("Incorrect ratio");
        }

        // Scroll to element
        WebElement goBackButton = driver.findElement(By.xpath(goBackButtonXpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", goBackButton);

        // Reset form
        goBackButton.click();
        driver.findElement(By.xpath(resetButtonXpath)).click();

        Thread.sleep(3000);

        driver.quit();
    }

    @Test
    public void checkMailLogin() throws InterruptedException {
        String mailInputFieldId = "identifierId";
        String nextButtonId = "identifierNext";
        String passwordInputFieldXpath = "//*[@id=\"password\"]/div[1]/div/div[1]/input";
        String passwordNextButtonId = "passwordNext";
        String verificationId = "gb";

        String url = "https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin";
        String login = "rubins.test@gmail.com";
        String password = "Rubinstest";

        System.setProperty("webdriver.chrome.driver", "./src/test/java/resources/chromedriver.exe");

        // BROWSER == DRIVER
        WebDriver driver = new ChromeDriver();

        // Go to
        driver.get(url);

        // Check ‘True Random Number Service’ label exists and present, check text
        WebElement inputField = driver.findElement(By.id(mailInputFieldId));
        inputField.sendKeys(login);
        driver.findElement(By.id(nextButtonId)).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(passwordInputFieldXpath)));
        driver.findElement(By.xpath(passwordInputFieldXpath)).sendKeys(password);
        driver.findElement(By.id(passwordNextButtonId)).click();
        System.out.println(driver.findElement(By.id(verificationId)).getAttribute("aria-label"));

        Thread.sleep(3000);

        driver.quit();
    }

    @Test
    public void checkLibraryLogin() throws InterruptedException {
        String loginButtonId = "h.p_Mn9dCRJZ_dHM";
        String emailInputId = "email";
        String passwordInputId = "password";
        String logInId = "login-text";
        String owlImageXpath = "/html/body/app-root/div/div/a/img";

        String url1 = "https://sites.google.com/view/library-automation-lits/home";String login = "rubins.yurko.test@gmail.com";
        String password = "Test1234!";

        System.setProperty("webdriver.chrome.driver", "./src/test/java/resources/chromedriver.exe");

        // BROWSER == DRIVER
        WebDriver driver = new ChromeDriver();

        // Go to
        driver.get(url1);

        // Check ‘True Random Number Service’ label exists and present, check text
        WebElement loginButton = driver.findElement(By.id(loginButtonId));
        loginButton.click();

        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        driver.findElement(By.id(emailInputId)).sendKeys(login);
        driver.findElement(By.id(passwordInputId)).sendKeys(password);
        driver.findElement(By.id(logInId)).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(owlImageXpath)));

        Thread.sleep(3000);

        driver.quit();
    }
}
