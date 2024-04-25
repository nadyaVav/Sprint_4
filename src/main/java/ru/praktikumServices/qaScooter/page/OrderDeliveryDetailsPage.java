package ru.praktikumServices.qaScooter.page;

import static java.time.Duration.ofSeconds;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderDeliveryDetailsPage {

  private WebDriver driver;

  private By deliveryDetailsHeaderLocator = By.xpath(".//div[text() = 'Про аренду']");

  private By orderDateInput = By.xpath(".//input[@placeholder = '* Когда привезти самокат']");
  private By periodRentInput = By.xpath(".//div[text() = '* Срок аренды']");
  private By periodRentInputLocator;

  private By colorOfScooterLocator;

  private By commentInputLocator = By.xpath(".//input[@placeholder = 'Комментарий для курьера']");
  private By prevStepButtonLocator = By.xpath(".//div[contains(@class, 'Order')]/button[text() = 'Назад']");
  private By sendOrderButtonLocator = By.xpath(".//div[contains(@class, 'Order')]/button[text()='Заказать']");

  public OrderDeliveryDetailsPage(WebDriver driver) {
    this.driver = driver;
  }

  public By getOrderHeaderText() {
    return deliveryDetailsHeaderLocator;
  }

  public void fillDataOfDelivery(String deliveryDate, String rentPeriod, String colorOfScooter, String commentOrder){
    driver.findElement(orderDateInput).sendKeys(deliveryDate, Keys.ENTER);
    setPeriodRentInputLocator(rentPeriod);
    driver.findElement(periodRentInput).click();
    driver.findElement(periodRentInputLocator).click();

    if(colorOfScooter != null){
      setColorOfScooterLocator(colorOfScooter);
      driver.findElement(colorOfScooterLocator).click();
    }

    if(commentOrder != null) {
      driver.findElement(commentInputLocator).sendKeys(commentOrder);
    }
  }

  public void setColorOfScooterLocator(String colorOfScooter) {
    colorOfScooterLocator = By.id(colorOfScooter);
  }
  public void setPeriodRentInputLocator(String rentPeriod) {
    periodRentInputLocator = By.xpath(".//div[text() = '" + rentPeriod + "']");
  }

  public void clickOnNextStepButton(By element) {
    driver.findElement(sendOrderButtonLocator).click();
    new WebDriverWait(driver, ofSeconds(3))
      .until(ExpectedConditions.visibilityOfElementLocated(element));
  }

  public void clickOnPrevStepButton(By element) {
    driver.findElement(prevStepButtonLocator).click();
    new WebDriverWait(driver, ofSeconds(3))
      .until(ExpectedConditions.visibilityOfElementLocated(element));
  }
}
