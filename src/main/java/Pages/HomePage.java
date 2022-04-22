package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomePage extends BasePage {
    private static Logger log = LoggerFactory.getLogger("HomePage.class");
    private WebDriverWait wait;
    @FindBy(css = ".sf-menu>li:nth-of-type(1)>a")
    private WebElement women;
    @FindBy(css = "[title=Blouses]")
    private WebElement blouses;
    @FindBy(css = ".quick-view")
    private WebElement quickView;
    @FindBy(id = ".fancybox-type-iframe")
    private WebElement categoryFrame;
    @FindBy(css = ".product-image-container  img")
    private WebElement image;
    @FindBy(css = "#thumbs_list img")
    private List<WebElement> iconList;
    @FindBy(css = "image-block")
    private WebElement imageBlock;
    @FindBy(css = "#bigpic")
    private WebElement mainPicture;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void goBlouses() {
        mouseHover(women);
        clickOnElement(blouses);
    }

    public void goToCategoryFrame() throws InterruptedException {
        scrollToElement(image);
        clickOnElement(quickView);
        Thread.sleep(3000);
        driver.switchTo().frame(0);
    }

    public Map<String, String> getMapOfLittleIconSrc() {
        Map<String, String> srcMap = new HashMap<>();
        for (WebElement littleIcon : iconList) {
            mouseHover(littleIcon);
            String src = littleIcon.getAttribute("src");
            String srcCut = src.substring(0, src.indexOf("-"));
            log.info("SRC: " + srcCut);
            String mainScr = mainPicture.getAttribute("src");
            String mainSrcCut = mainScr.substring(0, mainScr.indexOf("-"));
            log.info("Main icon src: " + mainSrcCut);
            srcMap.put(srcCut, mainSrcCut);
        }
        return srcMap;
    }
}




