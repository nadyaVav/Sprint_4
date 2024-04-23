package ru.praktikumServices.qaScooter.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CookieOnPage {
  private WebDriver driver;

  // Кнопка "принять куки"
  private By orderInHowToButtonLocator = By.id("rcc-confirm-button");

   //driver.findElement(By.id("rcc-confirm-button")).click();

  public CookieOnPage(WebDriver driver) {
    this.driver = driver;
  }

  public void acceptAllCookies() {
    driver.findElement(orderInHowToButtonLocator).click();
  }
}
