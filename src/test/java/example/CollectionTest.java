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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.*;


public class CollectionTest {
    private WebDriver driver;

    @BeforeEach
    public void setUP() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.bing.com/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName(value = "Проверка поисковой строки")
    public void search(){
        String input = "Selenium";
        By searchFieldCss = By.cssSelector("#sb_form_q");
        WebElement searchField = driver.findElement(searchFieldCss);
        searchField.sendKeys(input);
        searchField.submit();
        WebElement searchPageField = driver.findElement(searchFieldCss);
        assertEquals(input,searchPageField.getAttribute("value"), "Элемент не найден");
    }

    @Test
    @DisplayName(value = "Отображение N-ого элемента в поиске Bing")
    public void bingSearch () {
        String input = "Selenium";
        WebElement searchField = driver.findElement(By.cssSelector("#sb_form_q"));
        searchField.sendKeys(input);
        searchField.submit();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement confirm = driver.findElement(By.xpath("//span[contains(@class, 'cb-i')]"));
        confirm.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        List<WebElement> results = driver.findElements(By.xpath("//a[@class='" +"b_lario' and @aria-label='Selenium']"));
        results.get(0).click();
        for(WebElement el:results) {
            System.out.println(el.getText());
        }
    }

    public void clickElement () {

    }

    @Test
    @DisplayName(value = "Переход по первой ссылке поиска ")
    public void bingListSearch() {
        String input = "Selenium";
        WebElement searchField = driver.findElement(By.cssSelector("sb_form_q"));
        searchField.sendKeys(input);
        searchField.submit();
        List <WebElement> results = driver.findElements(By.cssSelector("//a[contains(@class, 'tilk')][contains(@href, 'selenium.dev')]"));
        results.get(0).click();
        clickElement(results, 0);
        driver.getCurrentUrl();
        assertEquals("https://ya.ru/", driver.getCurrentUrl());
        }

    public void clickElement (List <WebElement> results, int num) {
        System.out.println("Клик по первому элементу");
    }

}