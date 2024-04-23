package ru.praktikumServices.qaScooter.page;

import static java.time.Duration.ofSeconds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConfirmationOrderPage {

  private WebDriver driver;

  public ConfirmationOrderPage(WebDriver driver) {
    this.driver = driver;
  }

  private By confirmationHeaderLocator = By.xpath(".//div[text() = 'Хотите оформить заказ?']");
  private By yesButtonLocator = By.xpath(".//button[text() = 'Да']");
  private By noButtonLocator = By.xpath(".//button[text() = 'Нет']");

  public void clickSendOrderButton(By element){
    driver.findElement(yesButtonLocator).click();
    new WebDriverWait(driver, ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(element));
  }

  public void clickPrevStepButton(){
    driver.findElement(noButtonLocator).click();
  }

  public By getConfirmationHeaderLocator() {
    return confirmationHeaderLocator;
  }
}
