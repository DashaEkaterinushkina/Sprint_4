package ru.yandex.praktikum;

//Если бразуер не запускает удалить этот input и добавить зановоrmdir
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

    public static WebDriver getWebDriver(String browserType) {
        if (browserType.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }  else {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
    }
}
