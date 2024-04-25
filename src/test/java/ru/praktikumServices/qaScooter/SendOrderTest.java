package ru.praktikumServices.qaScooter;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.praktikumServices.qaScooter.constants.DataConstants.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.praktikumServices.qaScooter.page.ApprovedOrderPage;
import ru.praktikumServices.qaScooter.page.ConfirmationOrderPage;
import ru.praktikumServices.qaScooter.page.CookieOnPage;
import ru.praktikumServices.qaScooter.page.HeaderPage;
import ru.praktikumServices.qaScooter.page.OrderClientDataPage;
import ru.praktikumServices.qaScooter.page.OrderDeliveryDetailsPage;

@RunWith(Parameterized.class)
public class SendOrderTest {

  private WebDriver webDriver;

  private final String name;
  private final String lastName;
  private final String phoneNumber;
  private final String deliveryAddress;
  private final String subwayStation;
  private final int codeSubwayStation;
  private final String deliveryDate;
  private final String rentPeriod;
  private final String colorOfScooter;
  private final String commentOrder;
  private final boolean isCorrect;

  public SendOrderTest(String name, String lastName, String phoneNumber,
    String deliveryAddress, String subwayStation, int codeSubwayStation,
    String deliveryDate, String rentPeriod,
    String colorOfScooter, String commentOrder,
    boolean isCorrect) {
    this.name = name;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.deliveryAddress = deliveryAddress;
    this.subwayStation = subwayStation;
    this.codeSubwayStation = codeSubwayStation;
    this.deliveryDate = deliveryDate;
    this.rentPeriod = rentPeriod;
    this.colorOfScooter = colorOfScooter;
    this.commentOrder = commentOrder;
    this.isCorrect = isCorrect;
  }

  @Parameterized.Parameters
  public static Object[][] getTestData() {
    // Тестовые данные
    return new Object[][] {
      /*   {
        "name", "lastName", "phoneNumber",
        "deliveryAddress", "subwayStation", "codeSubwayStation",
        "deliveryDate", "rentPeriod",
        "colorOfScooter", "commentOrder",
        "result"
      } */
     {
        "Пупкин", "Мага", "89808563636",
        "Москва Папанина 15-56", "Арбатская", 78,
        "01.01.2025", PERIOD_1_DAY,
        COLOR_BLACK, "Позвонить за 10 минут",
        true
      },
      {
        "Рюмочкин", "Вафля", "89808563637",
        "Шоколадная Вафля 18-32", "Бульвар Рокоссовского", 1,
        "01.05.2024", PERIOD_7_DAY,
        COLOR_GREY, null,
        true
      },
      {
        "Шолохов", "Лука", "89808563631",
        "Шоколадная Вафля 18-32", "Юго-Западная", 19,
        "23.04.2024", PERIOD_3_DAY,
        null, null,
        true
      }
    };
  }

  @Before
  public void setup() {
    webDriver = WebDriverFactory.getWebDriver(System.getProperty("browser", "chrome"));
    webDriver.get("https://qa-scooter.praktikum-services.ru/");
    CookieOnPage cookieOnPage = new CookieOnPage(webDriver);
    cookieOnPage.acceptAllCookies();
  }

  @Test
  public void sendOrder() {
    // 1я страница заказа
    OrderClientDataPage orderClientDataPage = new OrderClientDataPage(webDriver);
    // 2я страница заказа
    OrderDeliveryDetailsPage deliveryDetailsPage = new OrderDeliveryDetailsPage(webDriver);
    //Поп-ап подтверждения заказа
    ConfirmationOrderPage confirmationOrderPage = new ConfirmationOrderPage(webDriver);
    //Поп-ап Заказ оформлен
    ApprovedOrderPage approvedOrderPage = new ApprovedOrderPage(webDriver);
    //Хедер
    HeaderPage headerPage = new HeaderPage(webDriver);

    headerPage.clickOrderButton(orderClientDataPage.getOrderHeaderTextLocator());
    orderClientDataPage.fillClientData(name, lastName, phoneNumber, deliveryAddress, subwayStation, codeSubwayStation);
    orderClientDataPage.clickOnNextStepButton(deliveryDetailsPage.getOrderHeaderText());
    deliveryDetailsPage.fillDataOfDelivery(deliveryDate, rentPeriod, colorOfScooter, commentOrder);
    deliveryDetailsPage.clickOnNextStepButton(confirmationOrderPage.getConfirmationHeaderLocator());
    confirmationOrderPage.clickSendOrderButton(approvedOrderPage.getApprovedOrderHeaderLocator());
    boolean result = approvedOrderPage.isApprovedOrderPage();
    assertEquals("Page is not correct", isCorrect, result);
  }

  @After
  public void teardown() {
    webDriver.quit();
  }
}
