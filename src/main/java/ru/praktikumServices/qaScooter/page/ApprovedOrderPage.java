package ru.praktikumServices.qaScooter.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ApprovedOrderPage {

  private WebDriver driver;
  private By approvedOrderHeaderLocator = By.xpath(".//div[text() = 'Заказ оформлен']");


  public ApprovedOrderPage(WebDriver driver) {
    this.driver = driver;
  }

  public By getApprovedOrderHeaderLocator() {
    return approvedOrderHeaderLocator;
  }

}
