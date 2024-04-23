package ru.praktikumServices.qaScooter;

import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

  private WebDriver webDriver;

  public static WebDriver getWebDriver(String browserType) {
    if (browserType.equalsIgnoreCase("firefox")) {
      WebDriverManager.firefoxdriver().clearDriverCache().setup();
      return new FirefoxDriver();
    }
    else {
      WebDriverManager.chromedriver().setup();
      return new ChromeDriver();
    }
  }
}
