package pages;

import enums.FindType;
import org.openqa.selenium.WebElement;
import services.DriverService;

public class ResultPage {

    private WebElement getTitle (String text) {
        String elementsXpath = "//android.widget.TextView[@text=\"" + text + "\"]";
        return DriverService.findElementBy(FindType.xpath, elementsXpath);
    }

    public boolean isTitleResultPageDisplayed (String text) {
        DriverService.waitLoadPage();
        return getTitle(text).isDisplayed();
    }
}
