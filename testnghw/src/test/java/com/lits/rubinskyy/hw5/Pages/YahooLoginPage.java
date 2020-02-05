package com.lits.rubinskyy.hw5.Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YahooLoginPage {
    @FindBy(id = "login-username")
    private WebElement emailInput;
    @FindBy(id = "login-passwd")
    private WebElement passwordInput;
    @FindBy(id = "header-mail-button")
    private WebElement mailBoxButton;

    private WebDriver driver;

    public YahooLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public YahooHomePage Login(String email, String password) {
        //Go to Gamil
        driver.get("https://login.yahoo.com/?.src=ym&.lang=en-US&.intl=us&.done=https%3A%2F%2Fmail.yahoo.com%2Fd%3F.src%3Dfp");
        //Fill email
        emailInput.sendKeys(email, Keys.ENTER);
        //Fill password
        passwordInput.sendKeys(password, Keys.ENTER);

        return new YahooHomePage(driver);
    }
}
