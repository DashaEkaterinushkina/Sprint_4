package ru.yandex.praktikum;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.yandex.praktikum.page.MainPage;
import ru.yandex.praktikum.page.OrderPage;

public class OrderTest {
    private WebDriver driver ;

    //Открыть браузер
    @Before
    public void setup(){
        ChromeOptions options = new ChromeOptions(); // Драйвер для браузера
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = WebDriverFactory.getWebDriver(System.getProperty("browser", "firefox"));
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void orderNotFount(){
        //Создаем объект класса
        MainPage mainPage = new MainPage(driver);

        //Вызываем метод нажатия книпки Статус заказа
        mainPage.clickOrderStutusButtun();

        //Ввводим номер заказа, который не существует
        mainPage.enterOrderNumber("9376546285716");
        mainPage.clickGoButton();

        //Проверяем наличие картинки
        Assert.assertTrue(mainPage.notFoundImgeIsDisplaed());
    }

    @Test
    public void createOrder(){
        //Создаем объект класса главного окна и вызываем метод нажатия верхней кнопки Заказать
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCreateOrder();
        //Закрываем окно с куки
        mainPage.closeCookiesWindows();

        //Создаем объект класса оформления заказа
        OrderPage orderPage = new OrderPage(driver);
        //Вводим данные пользователя
        orderPage.fillCustomerInfo("Имя", "Фамилия", "Адрес", "Арбатская", "89523652147");
        orderPage.clickNextButton();

        //Вводим срок аренды
        orderPage.orderRent("21.04.2024", "трое суток");
        orderPage.clickNextOrderButton();

        //Подтверждаем аренду
        orderPage.getAgreeOrder();

        //Проверяем окно об успешном создании заказа
        Assert.assertTrue(orderPage.issuedOrderText());

        }

   //  Закрыть браузер
    @After
    public void tearDown() {
        driver.quit();
    }
}
