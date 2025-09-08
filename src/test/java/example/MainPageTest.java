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


public class MainPageTest {
    private WebDriver driver;

    @BeforeEach
    public void setUP() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://www.habr.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body")));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName(value = "Первый тест, на отображение кнопки Suggest")
    public void suggestTest() {
        WebElement allStreamsButton = driver.findElement(By.xpath("//a[contains(text(), 'All streams') and contains(@href, '/articles')]"));
        allStreamsButton.click();

        WebElement hubsButton = driver.findElement(By.xpath("//a[text()='Hubs' and contains(@href, '/hubs')]"));
        hubsButton.click();

        WebElement suggest = driver.findElement(By.cssSelector(".tm-suggest-button"));
        assertTrue(suggest.isDisplayed(), "Suggest new не найдена");

    }

    @Test
    @DisplayName(value = "Второй тест на отображение пагинации")
    public void secondTest() {
        WebElement startupButoon = driver.findElement(By.xpath("//a[@class='footer-menu__item-link' and @href='/en/sandbox/']"));
        startupButoon.click();

        WebElement paginationButton = driver.findElement(By.xpath("//a[@class='tm-pagination__page' and @href='/en/sandbox/page4/']\n"));
        paginationButton.click();

        WebElement returnButton = driver.findElement(By.xpath("//span[@class='tm-pagination__navigation-link-title' and text()='Here']\n"));
        assertTrue(returnButton.isDisplayed(), "Кнопка назад отсутствует");
    }
}