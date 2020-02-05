package com.lits.rubinskyy.hw5.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YahooHomePage {
    @FindBy(xpath = "//*[@data-test-id='compose-button']")
    private WebElement composeButton;
    @FindBy(id = "message-to-field")
    private WebElement mailToField;
    @FindBy(xpath = "//*[@data-test-id='compose-subject']")
    private WebElement subjectField;
    @FindBy(xpath = "//*[@id=\"mail-app-component\"]/div/div/div[2]/div[2]/div/button")
    private WebElement sendButton;
    @FindBy(xpath = "//*[@data-test-folder-name='Sent']")
    private WebElement sentItem;
    @FindBy(xpath = "//*[@data-test-folder-name='Inbox']")
    private WebElement inboxItem;

    private WebDriver driver;

    public YahooHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public YahooHomePage Tap_ComposeButton() {
        composeButton.click();
        return this;
    }

    public YahooHomePage SendMail(String mail, String subject) {
        mailToField.sendKeys(mail);
        subjectField.sendKeys(subject);
        sendButton.click();
        return this;
    }

    public YahooSentSection Tap_SentItem() {
        sentItem.click();
        return new YahooSentSection(driver);
    }

    public YahooInboxSection Tap_InboxItem() {
        inboxItem.click();
        return new YahooInboxSection(driver);
    }




}
