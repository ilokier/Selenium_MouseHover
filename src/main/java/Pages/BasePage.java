package Pages;

import WebListener.WebListener;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.internal.EventFiringMouse;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BasePage {

    private static Logger log = LoggerFactory.getLogger("BasePage.class");
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    private EventFiringMouse eventFiringMouse;
    private WebListener webListener;


    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        actions = new Actions(driver);
        webListener = new WebListener();
        eventFiringMouse = new EventFiringMouse(driver, webListener);

    }

    public void clickOnElement(WebElement element) {
        waitToBeVisible(element);
        highLightenerMethod(element);
        element.click();
    }

    public void moveToElement(WebElement element) {
        waitToBeVisible(element);
        actions.moveToElement(element);
    }

    public void mouseHover(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        Locatable item = (Locatable) element;
        Coordinates coordinates = item.getCoordinates();
        eventFiringMouse.mouseMove(coordinates);

    }

    public void scrollToElement(WebElement element) {
        actions.moveToElement(element);
        actions.perform();
        waitToBeVisible(element);
    }

    public void waitToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void highLightenerMethod(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: lightgreen; border: 5px solid green;')", element);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

