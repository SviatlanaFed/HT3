package com.epam.ta.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditProfile extends AbstractPage {

    @FindBy(id = "user_profile_name")
    private WebElement inputName;
    @FindBy(xpath = "//p/button[@type='submit']")
    private WebElement submit;
    @FindBy(xpath = "//a[@href='/settings/profile']")
    private WebElement linkSettings;

    @FindBy(xpath = "(//ul[@id='user-links']/li)[last()]")
    private WebElement dropDown;

    public EditProfile(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void openPage() {
        dropDown.click();
        linkSettings.click();
    }

    public void setName(String newName) {
        inputName.clear();
        inputName.sendKeys(newName);
        submit.click();
    }

    public String getName() {
        return inputName.getAttribute("value");
    }
}
