package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class HomePage {

    Page page;

    private String searchField = "input[name='search']";
    private String searchButton = "div#search button";
    private String searchPageHeader = "div#content h1";
    private String loginLink = "a:text('Login')";
    private String myAccountLink = "a[title='My Account']";

    public HomePage(Page page) {
        this.page = page;
    }

    public String getHomePageTitle() {
        return page.title();
    }

    public String doSearch(String productName) {
        System.out.println("Searching for product: " + productName);
        page.fill(searchField, productName);
        page.click(searchButton);
        return page.textContent(searchPageHeader);
    }

    public String getHomePageURL() {
        return page.url();
    }

    public LoginPage navigateToLoginPage() {
        page.waitForTimeout(2000);
        page.click(myAccountLink);
        page.click(loginLink);
        return new LoginPage(page);
    }
}
