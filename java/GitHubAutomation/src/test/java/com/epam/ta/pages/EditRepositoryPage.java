package com.epam.ta.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditRepositoryPage extends AbstractPage {

    @FindBy(xpath = "(//a[@class='js-selected-navigation-item reponav-item'])[last()]")
    private WebElement repoSettings;
    @FindBy(id = "rename_field")
    private WebElement inputName;
    @FindBy(xpath = "//button[contains(@class,'js-rename-repository-button')]")
    private WebElement submit;

    public EditRepositoryPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void openPage() {
        repoSettings.click();
    }

    public void setName(String newName) {

        inputName.clear();
        inputName.sendKeys(newName);
        submit.click();
    }
}
