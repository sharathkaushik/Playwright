package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

    private Page page;

    private String emailField = "input#input-email";
    private String passwordField = "input#input-password";
    private String loginButton = "input[value='Login']";
    private String forgotPwdLink = "text=Forgotten Password";

    public LoginPage(Page page) {
        this.page = page;
    }

    public String getLoginPageTitle(){
        return page.title();
    }

    public boolean isForgotPwdLinkExist(){
        return page.isVisible(forgotPwdLink);
    }

    public void doLogin(String username, String password) {
        System.out.println("Logging in with username: " + username);
        page.fill(emailField, username);
        page.fill(passwordField, password);
        page.click(loginButton);
    }





}
