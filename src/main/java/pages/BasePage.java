package pages;

import services.DriverService;

public class BasePage {
    public BasePage() {
        DriverService.initPageElements(this);
    }
}