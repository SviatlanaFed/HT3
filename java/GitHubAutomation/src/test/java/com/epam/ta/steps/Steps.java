package com.epam.ta.steps;

import com.epam.ta.driver.DriverSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.epam.ta.pages.CreateNewRepositoryPage;
import com.epam.ta.pages.EditProfile;
import com.epam.ta.pages.EditRepositoryPage;
import com.epam.ta.pages.LoginPage;
import com.epam.ta.pages.MainPage;

public class Steps
{
	private WebDriver driver;

	private final Logger logger = LogManager.getRootLogger();

	public void initBrowser()
	{
		driver = DriverSingleton.getDriver();
	}

	public void closeDriver()
	{
		DriverSingleton.closeDriver();
	}

	public void loginGithub(String username, String password)
	{
		LoginPage loginPage = new LoginPage(driver);
		loginPage.openPage();
		loginPage.login(username, password);
	}

	public boolean isLoggedIn(String username)
	{
		LoginPage loginPage = new LoginPage(driver);
		String actualUsername = loginPage.getLoggedInUserName().trim().toLowerCase();
		logger.info("Actual username: " + actualUsername);
		return actualUsername.equals(username);
	}

	public boolean createNewRepository(String repositoryName, String repositoryDescription)
	{
		MainPage mainPage = new MainPage(driver);
		mainPage.clickOnCreateNewRepositoryButton();
		CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
		String expectedRepoName = createNewRepositoryPage.createNewRepository(repositoryName, repositoryDescription);
		return expectedRepoName.equals(createNewRepositoryPage.getCurrentRepositoryName());
	}

	public boolean renameRepository(String name, String newName)
        {
            createNewRepository(name, name);
            EditRepositoryPage editRepositoryPage = new EditRepositoryPage(driver);
            editRepositoryPage.openPage();
            editRepositoryPage.setName(newName);
            CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
            return createNewRepositoryPage.getCurrentRepositoryName().equals(newName);
        }

        public boolean updateProfile(String newName)
        {
            EditProfile profile = new EditProfile(driver);
            profile.openPage();
            profile.setName(newName);
            profile.openPage();
            return profile.getName().equals(newName);
        }

	public boolean currentRepositoryIsEmpty()
	{
		CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
		return createNewRepositoryPage.isCurrentRepositoryEmpty();
	}

}
