package ru.yandex.praktikum;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static ru.yandex.praktikum.Constant.URL_CONST;

public class BaseTest {

    private WebDriver driver ;

    //Открыть браузер
    public WebDriver setup(String browser){
        ChromeOptions options = new ChromeOptions(); // Драйвер для браузера
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = WebDriverFactory.getWebDriver(System.getProperty("browser", browser));
        driver.get(URL_CONST);
        return driver;
    }
}