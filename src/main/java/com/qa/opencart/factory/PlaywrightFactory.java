package com.qa.opencart.factory;

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class PlaywrightFactory {

    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    Properties prop;

    private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
    private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
    private static ThreadLocal<Page> tlPage = new ThreadLocal<>();

    public static synchronized Playwright getPlaywright(){
        return tlPlaywright.get();
    }

    public static synchronized Browser getBrowser(){
        return tlBrowser.get();
    }

    public static synchronized BrowserContext getBrowserContext(){
        return tlBrowserContext.get();
    }

    public static synchronized Page getPage(){
        return tlPage.get();
    }

    public Page intiBrowser(Properties prop) {

        String browserName = prop.getProperty("browser");
        System.out.println("Browser name is: " + browserName);
        //playwright = Playwright.create();
        tlPlaywright.set(Playwright.create());

        switch (browserName){
            case "chromium":
                //browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "firefox":
                //browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "webkit":
                //browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "chrome":
                //browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
                tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)));
                break;
            default:
                System.out.println("Please pass the right browser: " + browserName);
                break;
        }

        //browserContext = browser.newContext();
        tlBrowserContext.set(getBrowser().newContext());
        //page = browserContext.newPage();
        tlPage.set(getBrowserContext().newPage());
        getPage().navigate(prop.getProperty("url"));
        return getPage();

    }


    public Properties init_prop(){
        try {
            FileInputStream fs = new FileInputStream("./src/test/resources/config/config.properties");
            prop = new Properties();
            prop.load(fs);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e1) {
            throw new RuntimeException(e1);
        }

        return prop;
    }

    public static String takeScreenshot(){
        String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
        getPage().screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(path))
                .setFullPage(true));
        return path;
    }

    public static void main(String[] args) {

    }
}
