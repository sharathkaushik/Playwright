package com.qa.opencart.tests;

import com.microsoft.playwright.Page;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Properties;

public class BaseTest {

    PlaywrightFactory playwrightfactory;
    Page page;
    protected HomePage homePage;
    protected Properties prop;
    protected LoginPage loginPage;

    @BeforeTest
    public void setup() {
        System.out.println("Setting up HomePage tests...");
        playwrightfactory = new PlaywrightFactory();
        prop = playwrightfactory.init_prop();
        page = playwrightfactory.intiBrowser(prop);

        homePage = new HomePage(page);
        String title = homePage.getHomePageTitle();
        System.out.println("Home Page Title: " + title);
    }

    @AfterTest
    public void tearDown() {
        System.out.println("Tearing down HomePage tests...");
        page.context().browser().close();
    }

}
