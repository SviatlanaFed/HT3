package com.epam.ta;

import com.epam.ta.driver.DriverSingleton;
import com.epam.ta.pages.MainPage;
import com.epam.ta.steps.Steps;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GitHubLogin {
        private Steps steps;
        private final String USERNAME = "testautomationuser";
        private final String PASSWORD = "Time4Death!";

        @BeforeMethod(description = "Init browser")
        public void setUp()
        {
            steps = new Steps();
            steps.initBrowser();
        }

        @Test(description = "Login to Github")
        public void oneCanCreateProject()
        {
            steps.loginGithub(USERNAME, PASSWORD);

            Assert.assertTrue(steps.createNewRepository("testRepo", "auto-generated test repo"));
            Assert.assertTrue(steps.currentRepositoryIsEmpty());
            // do not use lots of asserts
        }
        @AfterMethod(description = "Stop Browser")
        public void stopBrowser()
        {
            steps.closeDriver();
        }

    }

