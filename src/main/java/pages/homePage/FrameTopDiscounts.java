package pages.homePage;

import enums.FindType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import services.DriverService;

public class FrameTopDiscounts extends BasePage {

    public boolean isTitleDisplayed(String titleId) {
        return isElementDisplayed("//android.widget.TextView[contains(@text,\"Топ лучших скидок\")]" );
    }

    public boolean isProductNameDisplayed(String productNameId) {
        return isElementDisplayed("//android.widget.TextView[@text=\""+ productNameId + "\"]");
    }

    public boolean isPriceDisplayed(String priceId) {
        return isElementDisplayed("//android.widget.TextView[contains(@text, '" + priceId + "')]" );
    }

    private String getPriceColor(String priceId) {
        WebElement price = getElement("//android.widget.TextView[contains(@text, '" + priceId + "')]");
        while (price == null) {
            DriverService.scrollDown();
            price = getElement("//android.widget.TextView[contains(@text, '" + priceId + "')]");
        }

//        Point elementsCenter = getCenter(price);
//
//        File scrFile = ((TakesScreenshot) DriverInitializer.getDriver()).getScreenshotAs(OutputType.FILE);
//
//        BufferedImage image = null;
//        try {
//            image = ImageIO.read(scrFile);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        // Getting pixel color by position x and y
//        int clr=  image.getRGB(elementsCenter.x,elementsCenter.y);
//        int  red   = (clr & 0x00ff0000) >> 16;
//        int  green = (clr & 0x0000ff00) >> 8;
//        int  blue  =  clr & 0x000000ff;
//        System.out.println("Red Color value = "+ red);
//        System.out.println("Green Color value = "+ green);
//        System.out.println("Blue Color value = "+ blue);

        return price.getCssValue("color");
    }

    public boolean isPriceColorCorrect(String priceColorApi, String priceId) {
        return getPriceColor(priceId).equals(priceColorApi);
    }




    private WebElement getElement(String elementText) {
        return DriverService.findElementBy(FindType.xpath, elementText);
    }

    private boolean isElementDisplayed(String elementPath) {
        WebElement element = getElement(elementPath);
        while (element == null) {
            DriverService.scrollDown();
            element = getElement(elementPath);
        }
        return element.isDisplayed();
    }

    private Point getCenter(WebElement element){
        int leftX = element.getLocation().getX();
        int rightX = element.getSize().getWidth();
        int middleX = rightX + (leftX / 2);

        int upperY = element.getLocation().getY();
        int lowerY = element.getSize().getHeight();
        int middleY = upperY + (lowerY / 2);
        return new Point(middleX, middleY);
    }
}
