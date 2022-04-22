import Pages.HomePage;
import WebListener.WebListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    private static Logger log = LoggerFactory.getLogger("BaseTest.class");
    public WebDriver webdriver;
    public EventFiringWebDriver driver;
    public WebListener webListener;
    protected HomePage homePage;
    String url = "http://automationpractice.com/index.php";

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        log.info("Driver initialized");
    }

    @BeforeEach
    void setup() {
        webdriver = new ChromeDriver();
        webdriver.manage().window().maximize();
        log.info("Window maximized");
        driver = new EventFiringWebDriver(webdriver);
        webListener = new WebListener();
        driver.register(webListener);
        homePage = new HomePage(driver);
        driver.get(url);
    }

    @AfterAll
    void quit() {
        webdriver.quit();
        log.debug("<<<<<<driver closed properly>>>>>");
    }
}
