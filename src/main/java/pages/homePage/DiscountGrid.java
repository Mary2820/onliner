package pages.homePage;

import enums.FindType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import services.DriverService;

public class DiscountGrid extends BasePage {
    @FindBy(xpath = "//android.widget.GridView[@resource-id=\"by.onliner.catalog:id/images\"]")
    private WebElement discountGrid;

    public boolean isDiscountGridDisplayed () {
        DriverService.waitElementUseElement(discountGrid);
        return discountGrid.isDisplayed();
    }

    public boolean isGridElementDisplayed (String elementText) {
        WebElement element = getGridElement(elementText);
        while (element == null) {
            DriverService.fastScrollDownTo(elementText);
            element = getGridElement(elementText);
        }
        return element.isDisplayed();
    }

    public void clickGridElement (String elementText) {
        getGridElement(elementText).click();
    }

    private WebElement getGridElement (String elementText) {
        String elementsXpath = "//android.widget.TextView[@resource-id=\"by.onliner.catalog:id/title\" and @text=\"" + elementText + "\"]";
        return DriverService.findElementBy(FindType.xpath, elementsXpath);
    }













}
