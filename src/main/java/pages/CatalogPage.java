package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import services.DriverService;

public class CatalogPage extends BasePage {
    @FindBy(xpath = "//android.widget.TextView[1] [@text = \"Введите запрос\"]")
    private WebElement searchText;

    public boolean isCatalogPageDisplayed () {
        DriverService.waitElementUseElement(searchText);
        return searchText.isDisplayed();
    }
}