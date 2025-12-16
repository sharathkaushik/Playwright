package com.qa.opencart.tests;

import com.qa.opencart.contants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest{


    @Test
    public void loginPageNavigationTest(){
        loginPage = homePage.navigateToLoginPage();
        String actualTitle = loginPage.getLoginPageTitle();
        System.out.println("Login Page Title: " + actualTitle);
        Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);

    }

    @Test
    public void forgotPwdLinkExistTest(){
        loginPage = homePage.navigateToLoginPage();
        Assert.assertTrue( loginPage.isForgotPwdLinkExist());
    }

}
