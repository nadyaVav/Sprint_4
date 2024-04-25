package ru.praktikumServices.qaScooter.page;
import static java.time.Duration.ofSeconds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

  private WebDriver driver;

  // Кнопка "Заказать" в середине страницы
  private By createOrderButtonLocator = By.xpath(".//div[contains(@class, 'Home')]/button[text()='Заказать']");
  // Раздел Вопросы о важном
  //Вопрос из блока [Вопросы о важном]. Определяется в методе setQuestionByTextQuestion по вопросу.
  private By questionLocator;
  //Ответ из блока [Вопросы о важном]. Определяется в методе setAnswerElementByTextQuestion по вопросу.
  private By answerLocator;

  private By sloganScooterLocator = By.xpath(".//div[contains(text(),'Привезём его прямо к вашей двери')]");

  public MainPage(WebDriver driver) {
    this.driver = driver;
  }

  public void setQuestionByTextQuestion(String textQuestion) {
    this.questionLocator = By.xpath(".//div[text()='" + textQuestion + "']");
  }

  public void setAnswerElementByTextQuestion(String textQuestion) {
    this.answerLocator = By.xpath(".//div[text()='" + textQuestion + "']/../../div[@role='region']/p");
  }

  public void openQuestion() {
    driver.findElement(questionLocator).click();
  }

  public String getTextAnswer() {

    return driver.findElement(answerLocator).getText();
  }

  public void clickOrderButton(By element) {
    driver.findElement(createOrderButtonLocator).click();
    new WebDriverWait(driver, ofSeconds(3))
      .until(ExpectedConditions.visibilityOfElementLocated(element));
  }

  public boolean answerIsDisplayed() {
    return  driver.findElement(answerLocator).isDisplayed();
  }

  public boolean isMainPage() {
    return driver.findElement(sloganScooterLocator).isDisplayed();
  }
}
