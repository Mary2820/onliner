package pages.homePage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import services.DriverService;

public class CatalogContentPage extends BasePage {
    @FindBy(xpath = "(//android.view.ViewGroup[@resource-id=\"by.onliner.catalog:id/view\"])[3]")
    private WebElement searchElement;

    @FindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"Каталог\"]")
    private WebElement catalogTab;

    public void cancelAdvertising () {
        DriverService.waitElementUseElement(searchElement);
        searchElement.click();
    }

    public boolean IsCatalogContentPageOpened () {
        DriverService.waitElementUseElement(catalogTab);
        return catalogTab.isDisplayed();
    }
}
