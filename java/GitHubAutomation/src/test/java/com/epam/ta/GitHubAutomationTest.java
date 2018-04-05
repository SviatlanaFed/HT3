package com.epam.ta;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.ta.steps.Steps;
import com.epam.ta.utils.Utils;

public class GitHubAutomationTest
{
	private Steps steps;
	private final String USERNAME = "testautomationuser";
	private final String PASSWORD = "Time4Death!";

	@BeforeMethod(description = "Init browser")
	public void setUp()
	{
		steps = new Steps();
		steps.initBrowser();
	}

	@Test
	public void updateRepoName()
        {
            steps.loginGithub(USERNAME, PASSWORD);
            String name = "rename";
            boolean result = steps.renameRepository(name + Utils.getRandomString(6), name + Utils.getRandomString(6));
            Assert.assertTrue(result);
        }

	@Test
        public void updateProfile()
        {
            steps.loginGithub(USERNAME, PASSWORD);
            Assert.assertTrue(steps.updateProfile("my new name" + Utils.getRandomString(6)));
        }

	@Test
	public void oneCanCreateProject()
	{
		steps.loginGithub(USERNAME, PASSWORD);
		Assert.assertTrue(steps.createNewRepository("testRepo", "auto-generated test repo"));
		Assert.assertTrue(steps.currentRepositoryIsEmpty());
		// do not use lots of asserts
	}

	@Test(description = "Login to Github")
	public void oneCanLoginGithub()
	{
		steps.loginGithub(USERNAME, PASSWORD);
		Assert.assertTrue(steps.isLoggedIn(USERNAME));
	}

	@AfterMethod(description = "Stop Browser")
	public void stopBrowser()
	{
		steps.closeDriver();
	}

}
