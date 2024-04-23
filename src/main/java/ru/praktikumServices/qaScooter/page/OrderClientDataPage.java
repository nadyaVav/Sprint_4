package ru.praktikumServices.qaScooter.page;

import static java.time.Duration.ofSeconds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderClientDataPage {
  private WebDriver driver;

  private By orderHeaderTextLocator = By.xpath(".//div[text() = 'Для кого самокат']");

  private By nameInputLocator = By.xpath(".//input[@placeholder = '* Имя']");
  private By lastNameInputLocator = By.xpath(".//input[@placeholder = '* Фамилия']");
  private By addressInputLocator = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']");
  private By metroStationInputLocator = By.xpath(".//input[@placeholder = '* Станция метро']");

  private By phoneNumberInputLocator = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']");
  private By nextStepButtonLocator = By.xpath(".//button[text() = 'Далее']");
  private By metroStationRowLocator;

  public OrderClientDataPage(WebDriver driver) {
    this.driver = driver;
  }

  public By getOrderHeaderTextLocator() {
    return orderHeaderTextLocator;
  }

  public void fillClientData (String name,String lastName, String phoneNumber, String address, String metroStation, int codeItem) {
    driver.findElement(nameInputLocator).sendKeys(name);
    driver.findElement(lastNameInputLocator).sendKeys(lastName);
    driver.findElement(phoneNumberInputLocator).sendKeys(phoneNumber);
    driver.findElement(addressInputLocator).sendKeys(address);

    setElementForAddress(codeItem,metroStation);
    fillDropdown();
  }

  public void setElementForAddress(int codeElement, String nameElement) {
    this.metroStationRowLocator = By.xpath("//button[@value='" + codeElement + "'][./div[text()='" + nameElement + "']]");
  }

  public void fillDropdown(){
    driver.findElement(metroStationInputLocator).click();
    driver.findElement(metroStationRowLocator).click();
  }

  public void clickOnNextStepButton(By element) {
    driver.findElement(nextStepButtonLocator).click();
    new WebDriverWait(driver, ofSeconds(3))
      .until(ExpectedConditions.visibilityOfElementLocated(element));
  }
}
