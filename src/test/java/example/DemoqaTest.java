package example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class DemoqaTest {
    private WebDriver driver;

    @BeforeEach
    public void setUP() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName(value = "Доступность кнопки спустя время")
    public void enable() {
        driver.get("https://demoqa.com/dynamic-properties");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        WebElement disabledButton = driver.findElement(By.cssSelector("#enableAfter"));
        wait.until(ExpectedConditions.elementToBeClickable(disabledButton));
        assertTrue(disabledButton.isEnabled(), "кнопка не стала активной");
    }

    @Test
    @DisplayName(value = "Кнопка меняющая цвет")
    public void colorChange () {
        driver.get("https://demoqa.com/dynamic-properties");
        WebElement colorizedButton = driver.findElement(By.cssSelector("#colorChange"));
        wait.until(ExpectedConditions.attributeContains(colorizedButton, "class",
                ".mt-4 text-danger btn btn-primary"));
        assertTrue(colorizedButton.isDisplayed(), "Кнопка отображается");
    }

    @Test
    @DisplayName(value = "Кнопка появляющаяся спустя время")
    public void visisbleAfter () {
        driver.get("https://demoqa.com/dynamic-properties");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        WebElement visibleAfterButton = driver.findElement(By.cssSelector("#visibleAfter"));
        wait.until(ExpectedConditions.elementToBeClickable(visibleAfterButton));
        assertTrue(visibleAfterButton.isDisplayed(), "Кнопка не кликабельна");
    }











    @Test
    @DisplayName(value = "Текст спрятанный до выполнения условия")
    public void hidden() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        driver.findElement(By.cssSelector("#start button")).click();
        WebElement helloTest = driver.findElement(By.cssSelector("#finish h4"));
        wait.until(ExpectedConditions.visibilityOf(helloTest));
        assertTrue(helloTest.isDisplayed(), "Нет нужного текста");
    }

    @Test
    @DisplayName(value = "Элемент появляется в dom после ожидания")
    public void notLoaded() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        driver.findElement(By.cssSelector("#start button")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#finish h4")));
        WebElement helloText = driver.findElement(By.cssSelector("#finish h4"));
        assertTrue(driver.findElement(By.cssSelector("#finish h4")).isDisplayed(), "Нет нужного текста");
    }
}