package pages;

import enums.FindType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import services.DriverService;

public class SideBar extends BasePage {

    public boolean isSideBarVisible () {
        DriverService.waitElementUseLocator(By.id("by.onliner.catalog:id/menu_navigation_main"));
        WebElement main = DriverService.findElementBy(FindType.id, "by.onliner.catalog:id/menu_navigation_main");
        return main.isDisplayed();
    }
}