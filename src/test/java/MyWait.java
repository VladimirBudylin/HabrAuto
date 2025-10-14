import example.DemoqaTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;




public class MyWait {
    private static final Logger LOG = LoggerFactory.getLogger(MyWait.class);
    private WebDriverWait wait;
    private int secondsToWait;

    public static MyWait myWait(int seconds)
    {
        return new MyWait(seconds);
    }

    public MyWait(int seconds)
    {
        secondsToWait = seconds;
        wait = new WebDriverWait(DemoqaTest.getDriver(),
                Duration.ofSeconds(seconds));
    }

    public WebElement clicable(WebElement element) {
        LOG.info("Ждём " + secondsToWait + " секунд, пока элемент не станет кликабельным: " + element.toString());
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement visible(WebElement element) {
        LOG.info("Ждём " + secondsToWait + " секунд, пока элемент не станет видимым: " + element.toString());
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement invisible(WebElement element) {
        LOG.info("Ждём " + secondsToWait + " секунд, пока элемент не станет видимым: " + element.toString());
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }
}
