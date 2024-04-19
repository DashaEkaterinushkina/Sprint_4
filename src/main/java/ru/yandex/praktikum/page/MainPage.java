package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private final WebDriver driver;

    //Кнопка статуса заказа на главной странице
    private final By orderStatusButton = By.xpath("//button[text()='Статус заказа']");
    //Поле для номера заказа на главной странице
    private final By orderInputPlaceholder = By.xpath("//input[@placeholder='Введите номер заказа']");
    //Кнопка для поиска заказа на главной странице
    private final By orderClickGoButton = By.xpath("//button[text()='Go!']");
    //Картинка: Такого заказа нет
    private final By notFoundImg = By.xpath("//img[@alt='Not found']");
    //Кнопка заказа
    private final By createOrderButton = By.xpath("//div[contains(@class, 'Header')]/button[text()='Заказать']");

    //Кнопку куки
    private final By clickCookie = By.className("App_CookieButton__3cvqF");

    //Вопрос на главной странице
    private final String questionLocator = "accordion__heading-%s";
    //Ответ на главной странице
    private final String answerLocator = "//div[contains(@id, 'accordion__panel')][.='%s']";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOrderStutusButtun() {
        WebElement orderStatus = driver.findElement(orderStatusButton);
        orderStatus.click();
    }
    public void closeCookiesWindows(){
        //Без клика куки, не видно кнопку Далее, сначала кликаем: да все привыкли
        driver.findElement(clickCookie).click();

    }

    public void enterOrderNumber(String orderNumber) {
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(orderInputPlaceholder));
        WebElement orderInput = driver.findElement(orderInputPlaceholder);
        orderInput.click();
        orderInput.sendKeys(orderNumber);
    }

    public void clickGoButton() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(orderClickGoButton));
        driver.findElement(orderClickGoButton).click();
    }

    public boolean notFoundImgeIsDisplaed() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(notFoundImg));
        return driver.findElement(notFoundImg).isDisplayed();
    }

    public void clickCreateOrder(){
        WebElement createOrder = driver.findElement(createOrderButton);
        createOrder.click();
    }

    public void expandQuestion(int index) {
        WebElement element = driver.findElement(By.id(String.format(questionLocator,index)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    public boolean answerQuestion(String answer) {
        WebElement element = driver.findElement(By.xpath(String.format(answerLocator,answer)));
        return element.isDisplayed();
    }
}
