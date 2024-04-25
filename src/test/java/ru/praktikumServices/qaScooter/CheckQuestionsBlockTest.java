package ru.praktikumServices.qaScooter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.praktikumServices.qaScooter.page.CookieOnPage;
import ru.praktikumServices.qaScooter.page.MainPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.praktikumServices.qaScooter.constants.QuestionsConstants.*;

@RunWith(Parameterized.class)
public class CheckQuestionsBlockTest {

  private final String question;
  private final String answer;
  private WebDriver webDriver;
  private final boolean isCorrect;

  public CheckQuestionsBlockTest(String question, String answer, boolean isCorrect) {
    this.question = question;
    this.answer = answer;
    this.isCorrect = isCorrect;
  }

  @Parameterized.Parameters
  public static Object[][] getCredentials() {
    // Тестовые данные
    return new Object[][] {
      {QUESTION_1, ANSWER_1, true},
      {QUESTION_2, ANSWER_2, true},
      {QUESTION_3, ANSWER_3, true},
      {QUESTION_4, ANSWER_4, true},
      {QUESTION_5, ANSWER_5, true},
      {QUESTION_6, ANSWER_6, true},
      {QUESTION_7, ANSWER_7, true},
      {QUESTION_8, ANSWER_8, true},
      {QUESTION_7, ANSWER_7, true}
    };
  }

  @Before
  public void setup(){
    webDriver = WebDriverFactory.getWebDriver(System.getProperty("browser","chrome"));
    webDriver.get("https://qa-scooter.praktikum-services.ru/");
    CookieOnPage cookieOnPage = new CookieOnPage(webDriver);
    cookieOnPage.acceptAllCookies();
  }

  @Test
  public void checkTextTest() {
    MainPage mainPage = new MainPage(webDriver);
    mainPage.setQuestionByTextQuestion(question);
    mainPage.setAnswerElementByTextQuestion(question);
    mainPage.openQuestion();
    assertTrue(mainPage.answerIsDisplayed());

    String data = mainPage.getTextAnswer();
    assertEquals("Данные для вопроса \n \""
      + question
      + "\"\n"
      + "не совпадают", isCorrect, data.equals(answer));
  }

  @After
  public void teardown() {
    webDriver.quit();
  }
}
