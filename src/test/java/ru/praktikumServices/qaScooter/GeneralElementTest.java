package ru.praktikumServices.qaScooter;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.praktikumServices.qaScooter.page.CookieOnPage;
import ru.praktikumServices.qaScooter.page.HeaderPage;
import ru.praktikumServices.qaScooter.page.MainPage;
import ru.praktikumServices.qaScooter.page.OrderClientDataPage;

public class GeneralElementTest {

  private WebDriver webDriver;

  @Before
  public void setup(){
    webDriver = WebDriverFactory.getWebDriver(System.getProperty("browser","chrome"));
    webDriver.get("https://qa-scooter.praktikum-services.ru/");
    CookieOnPage cookieOnPage = new CookieOnPage(webDriver);
    cookieOnPage.acceptAllCookies();
  }

  //переход по кнопке Заказать в теле главной страницы
  @Test
  public void checkStartOrderFromMainPage(){
    OrderClientDataPage orderClientDataPage = new OrderClientDataPage(webDriver);
    MainPage mainPage = new MainPage(webDriver);
    mainPage.clickOrderButton(orderClientDataPage.getOrderHeaderTextLocator());
    boolean result = orderClientDataPage.isOrderStartPage();
    assertTrue("Page is not correct", result);
  }


  //переход по кнопке Заказать в шапке
  @Test
  public void checkStartOrderFromHeader(){
    OrderClientDataPage orderClientDataPage = new OrderClientDataPage(webDriver);
    new HeaderPage(webDriver)
      .clickOrderButton(orderClientDataPage.getOrderHeaderTextLocator());
    boolean result = orderClientDataPage.isOrderStartPage();
    assertTrue("Page is not correct", result);
  }

  // Проверить: если нажать на логотип «Самоката», попадёшь на главную страницу «Самоката».
  @Test
  public void checkClickLogoScooter(){
    HeaderPage headerPage = new HeaderPage(webDriver);
    MainPage mainPage = new  MainPage(webDriver);
    OrderClientDataPage orderClientDataPage = new OrderClientDataPage(webDriver);

    headerPage.clickScooterButtonLocator();
    boolean isMainPage = mainPage.isMainPage();
    assertTrue(isMainPage);

    headerPage.clickOrderButton(orderClientDataPage.getOrderHeaderTextLocator());
    headerPage.clickScooterButtonLocator();
    isMainPage = mainPage.isMainPage();
    assertTrue(isMainPage);
  }


  @After
  public void teardown() {
    webDriver.quit();
  }
}
