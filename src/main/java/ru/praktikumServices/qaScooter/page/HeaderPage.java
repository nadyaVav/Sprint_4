package ru.praktikumServices.qaScooter.page;

import static java.time.Duration.ofSeconds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HeaderPage {

  private WebDriver driver;
  // Кнопка "Заказать" в шапке страницы
  private By createOrderButtonLocator = By.xpath(".//div[contains(@class, 'Home')]/button[text()='Заказать']");
  private By scooterButtonLocator = By.xpath(".//img[@alt='Scooter']");

  public HeaderPage(WebDriver driver) {
    this.driver = driver;
  }

  public void clickOrderButton(By element){
    driver.findElement(createOrderButtonLocator).click();
    new WebDriverWait(driver, ofSeconds(3))
      .until(ExpectedConditions.visibilityOfElementLocated(element));
  }

  public void clickScooterButtonLocator() {
    driver.findElement(scooterButtonLocator).click();
  }
}
