package com.lits.rubinskyy.hw5.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class YahooSentSection {
    String mailSubjectXpath = "//*[@data-test-id='message-subject']";

    private WebDriver driver;

    public YahooSentSection(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public YahooSentSection Check_Subject(String subject) {
        List<String> sentSubjects = driver.findElements(By.xpath(mailSubjectXpath))
                .stream()
                .map(WebElement::getText).collect(Collectors.toList());
        Assert.assertTrue(sentSubjects.contains(subject));
        return this;
    }
    public YahooSentSection Refresh_SentSection() {
        driver.navigate().refresh();
        return this;
    }
}
