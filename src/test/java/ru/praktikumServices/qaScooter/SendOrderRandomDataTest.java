package ru.praktikumServices.qaScooter;


import static org.junit.Assert.assertEquals;
import static ru.praktikumServices.qaScooter.constants.DataConstants.*;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
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
public class SendOrderRandomDataTest {

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

  public SendOrderRandomDataTest(String subwayStation, int codeSubwayStation, boolean isCorrect) {
    Faker faker = new Faker(new Locale("ru"));
    FakeValuesService fakeValuesService = new FakeValuesService(
      new Locale("ru"), new RandomService());

    this.name = faker.name().firstName();
    this.lastName = faker.name().lastName();
    this.phoneNumber = fakeValuesService.numerify("8##########");
    this.deliveryAddress = faker.address().streetAddress();
    this.subwayStation = subwayStation;
    this.codeSubwayStation = codeSubwayStation;
    this.colorOfScooter = COLOR_NAMES[faker.random().nextInt(COLOR_NAMES.length)];
    this.rentPeriod = PERIOD_NAMES[faker.random().nextInt(PERIOD_NAMES.length)];
    this.deliveryDate = faker.date().future(100, TimeUnit.DAYS).toString();
    this.commentOrder = fakeValuesService.bothify("??? ### ?? ## ");
    this.isCorrect = isCorrect;
  }

  @Parameterized.Parameters
  public static Object[][] getTestData() {
    // Тестовые данные
    return new Object[][] {
      {"Юго-Западная", 19, true},
      {"Арбатская", 78, true},
      {"Бульвар Рокоссовского", 1, true}
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
  public void sendOrderRandomData() {
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
