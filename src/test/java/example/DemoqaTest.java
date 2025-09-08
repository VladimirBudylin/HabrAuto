package example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    @DisplayName(value = "Отображение кнопки с отложенной видимостью")
    public void enable() {
        driver.get("https://demoqa.com/dynamic-properties");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        WebElement disabledButton = driver.findElement(By.cssSelector("#enableAfter"));
        wait.untill(ExpectedConditions.elementToBeClickable(disabledButton));
        assertTrue(disabledButton.isEnabled(), "кнопка не стала активной");
    }
}