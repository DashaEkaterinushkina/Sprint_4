package ru.yandex.praktikum;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static ru.yandex.praktikum.Constant.URLCONST;

public class StartEnd {

    private WebDriver driver ;

    //Открыть браузер
    public WebDriver setup(String browser){
        ChromeOptions options = new ChromeOptions(); // Драйвер для браузера
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = WebDriverFactory.getWebDriver(System.getProperty("browser", browser));
        driver.get(URLCONST);
        return driver;
    }

    //  Закрыть браузер
    public void tearDown() {
        driver.quit();
    }
}
