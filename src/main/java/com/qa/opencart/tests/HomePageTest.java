package com.qa.opencart.tests;

import com.qa.opencart.contants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void HomePageTitleTest() {
        String title = homePage.getHomePageTitle();
        System.out.println("Verifying Home Page Title: " + title);
        Assert.assertEquals(title, AppConstants.HOME_PAGE_TITLE);
    }
}
