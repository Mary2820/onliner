package pages.homePage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import services.DriverService;

public class HomePage extends BasePage {
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"by.onliner.catalog:id/logo_toolbar\"]/android.widget.ImageView")
    private WebElement title;

    @FindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]")
    private WebElement burgerMenu;

    @FindBy(id = "by.onliner.catalog:id/searchPlate")
    private WebElement searchElement;

    @FindBy(xpath = "//androidx.drawerlayout.widget.DrawerLayout//android.widget.LinearLayout/android.widget.ImageView")
    private WebElement searchIcon;

    @FindBy(xpath = "//android.widget.TextView[@text = \"Поиск в каталоге\"]")
    private WebElement searchText;

    @FindBy(id = "by.onliner.catalog:id/st_storyly_list_recycler_view")
    private WebElement storyBar;

    @FindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@resource-id=\"by.onliner.catalog:id/images\"]")
    private WebElement cardCarousel;

    public boolean isLogoVisible () {
        return title.isDisplayed();
    }

    public boolean isBurgerMenuVisible () {
        DriverService.waitElementUseElement(burgerMenu);
        return burgerMenu.isDisplayed();
    }

    public boolean isSearchElementsDispayed () {
        DriverService.waitElementUseElement(searchElement);
        return searchElement.isDisplayed() && searchIcon.isDisplayed() && searchText.isDisplayed();
    }

    public void clickSearchField () {
        DriverService.waitElementUseElement(searchElement);
        searchElement.click();
    }

    public void clickBurgerMenu () {
        DriverService.waitElementUseElement(burgerMenu);
        burgerMenu.click();
    }

    public boolean isStoryBarDisplayed () {
        DriverService.waitElementUseElement(storyBar);
        return storyBar.isDisplayed();
    }

    public boolean isCardsCarouselDisplayed () {
        DriverService.waitElementUseElement(cardCarousel);
        return cardCarousel.isDisplayed();
    }
}