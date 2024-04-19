package ru.yandex.praktikum.page;

import org.openqa.selenium.*;

public class OrderPage {
    private final WebDriver driver;

    //Кнопка для введения имени
    private final By nameInput = By.xpath("//input[@placeholder='* Имя']");
    //Кнопка для введения фамилии
    private final By lastNameInput = By.xpath("//input[@placeholder='* Фамилия']");
    //Кнопка для введения Адреса куда
    private final By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    //Кнопка для введения станции метро
    private final By subwayInput = By.xpath("//input[@placeholder='* Станция метро']");
    //Кнопка для введения номера телефона
    private final By numberInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка Далее
    private final By orderButton = By.xpath("//button[text()='Далее']");

    //Кнопка для введения Адреса куда вести самокат
    private final By whenOrder = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    //Кнопка для списка срока оренды
    private final By rentPeriodInput = By.xpath("//div[text()='* Срок аренды']");
    //Кнопка для оформления заказа, их две, находим по классу
    private final By clickOrderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //Кнопка подтверждения заказа
    private final By clickAgreeOrderButton = By.xpath("//button[text()='Да']");

    //Проверка текста оформленного заказа
    private final By issuedOrder = By.xpath("//*[text()='Заказ оформлен']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillCustomerInfo(String name, String lastName, String adress, String subwayTitle, String phone) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(addressInput).sendKeys(adress);

        //Выбираем из списка метро Арбатская, с прокруткой по списку
        driver.findElement(subwayInput).click();
        WebElement arbatskayaStationMenu = driver.findElement(By.xpath("//button[@value='78'][./div[text()='" + subwayTitle + "']]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", arbatskayaStationMenu);
        arbatskayaStationMenu.click();

        driver.findElement(numberInput).sendKeys(phone);
    }

    public void clickNextButton() {
        driver.findElement(orderButton).click();
    }

    public void orderRent(String dateNumber, String filledDate) {
        driver.findElement(whenOrder).sendKeys(dateNumber, Keys.ENTER);
        driver.findElement(rentPeriodInput).click();
        driver.findElement(By.xpath("//div[text()='" + filledDate  + "']")).click();
    }

    public void clickNextOrderButton() {
        driver.findElement(clickOrderButton).click();
    }

    public void getAgreeOrder() {
        driver.findElement(clickAgreeOrderButton).click();
    }

    public boolean issuedOrderText() {
        return driver.findElement(issuedOrder).isEnabled();
    }
}
