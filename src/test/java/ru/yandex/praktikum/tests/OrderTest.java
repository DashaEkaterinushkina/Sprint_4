package ru.yandex.praktikum.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.BaseTest;
import ru.yandex.praktikum.page.MainPage;
import ru.yandex.praktikum.page.OrderPage;


@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {

    private WebDriver driver;
    private String index;
    private String button;
    public OrderTest(String index, String button) {
        this.index = index;
        this.button = button;
    }

    @Parameterized.Parameters
    public static Object[][] data(){
        return new Object[][]{
                {"Верхняя","Header"},
                {"Нижняя","Home"}
        };
    }

//Тест orderNotFound исключён, т.к. по сценарию нет проверки статуса заказа

    @Test
    public void createOrderOne(){

        //Запускам браузер
        BaseTest baseTest = new BaseTest();
        driver = baseTest.setup("firefox");

        //Создаем объект класса главного окна и вызываем метод нажатия кнопки Заказать
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCreateOrder(index, button);
        //Закрываем окно с куки
        mainPage.closeCookiesWindows();

        //Создаем объект класса оформления заказа
        OrderPage orderPage = new OrderPage(driver);
        //Вводим данные пользователя
        orderPage.fillCustomerInfo("Павел", "Панфилов", "Павловск", "Арбатская", "89652358963");
        orderPage.clickNextButton();

        //Вводим срок аренды
        orderPage.orderRent("01.05.2024", "трое суток");
        orderPage.clickNextOrderButton();

        //Подтверждаем аренду
        orderPage.getAgreeOrder();

        //Проверяем окно об успешном создании заказа
        Assert.assertTrue(orderPage.issuedOrderText());

        //Закрываем браузер
        // По заданию: Метод driver.quit(); нужно использовать в конце каждого теста.
        driver.quit();
    }

    @Test
    public void createOrderTwo(){

        //Запускам браузер
        BaseTest baseTest = new BaseTest();
        driver = baseTest.setup("firefox");

        //Создаем объект класса главного окна и вызываем метод нажатия кнопки Заказать
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCreateOrder(index, button);
        //Закрываем окно с куки
        mainPage.closeCookiesWindows();

        //Создаем объект класса оформления заказа
        OrderPage orderPage = new OrderPage(driver);
        //Вводим данные пользователя
        orderPage.fillCustomerInfo("Мария", "Маркова", "Магнитогорск", "Арбатская", "89523652147");
        orderPage.clickNextButton();

        //Вводим срок аренды
        orderPage.orderRent("25.04.2024", "трое суток");
        orderPage.clickNextOrderButton();

        //Подтверждаем аренду
        orderPage.getAgreeOrder();

        //Проверяем окно об успешном создании заказа
        Assert.assertTrue(orderPage.issuedOrderText());

        //Закрываем браузер
        // По заданию: Метод driver.quit(); нужно использовать в конце каждого теста.
        driver.quit();
    }
}