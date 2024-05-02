package services;

import constants.Configuration;
import enums.FindType;
import enums.PlatformName;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.proxy.NotImplementedException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import startUp.DriverInitializer;

import java.time.Duration;
import java.util.Collections;

public class DriverService {
    private static final int deviceWidth = getDeviceWidth();
    private static final int deviceHeight = getDeviceHeight();

    public static void acceptAlert() {
        DriverInitializer.getDriver().switchTo().alert().accept();
    }



    public static void waitLoadPage () {
        DriverInitializer.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }

    public static void initPageElements(Object page) {
        PageFactory.initElements(DriverInitializer.getDriver(), page);
    }

    public static void hideKeyboard() {
        if (PlatformName.Android == Configuration.PLATFORM) {
            ((AndroidDriver) DriverInitializer.getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
        } else if (PlatformName.iOS == Configuration.PLATFORM) {
            throw new NotImplementedException();
        }
    }

    public static WebElement waitElementUseElement(WebElement element) {
        return waitElement().until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitElementUseLocator(By locator) {
        return waitElement().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement findElementBy(FindType findType, String path) {
        By by = null;
        if (findType == FindType.xpath) {
            by = AppiumBy.xpath(path);
        } else if (findType == FindType.id) {
            by = AppiumBy.id(path);
        }
        WebElement element = null;
        try {
            element = DriverInitializer.getDriver().findElement(by);
        } catch (Exception e) {
            System.out.println();
        }
        return element;
    }

    public static void scrollDown() {
        int heightOneEight = deviceHeight / 8;
        int heightQuarter = deviceHeight / 4;
        int widthHalf = deviceWidth / 2;

        Point point1 = new Point(widthHalf, heightQuarter);
        Point point2 = new Point(widthHalf, heightQuarter-heightOneEight);

        moveFingerByCoordinates(point1, point2);
    }

    public static void fastScrollDownTo(String elementToScroll) {
        DriverInitializer.getDriver().findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"" + elementToScroll + "\"))"));
    }

    public static void swipeLeft(WebElement element) {
        int widthQuarter = deviceWidth / 3;
        int widthHalf = deviceWidth / 2;
        Point elementLocation = element.getLocation();

        Point point1 = new Point(widthHalf, elementLocation.y);
        Point point2 = new Point(widthHalf - widthQuarter, elementLocation.y);

        moveFingerByCoordinates(point1, point2);
    }

    private static Dimension getSize () {
        return DriverInitializer.getDriver().manage().window().getSize();
    }

    private static int getDeviceHeight() {
        return getSize().getHeight();
    }

    private static int getDeviceWidth() {
        return getSize().getWidth();
    }

    private static void moveFingerByCoordinates(Point point1, Point point2) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence scroll = new Sequence(finger, 1);
        scroll.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), point1.x, point1.y));
        scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        scroll.addAction(finger.createPointerMove(Duration.ofMillis(250), PointerInput.Origin.viewport(), point2.x, point2.y));

        scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        DriverInitializer.getDriver().perform(Collections.singletonList(scroll));
    }
    private static FluentWait<AppiumDriver> waitElement() {
        return new FluentWait<>(DriverInitializer.getDriver())
                .withTimeout(Duration.ofSeconds(10))
                .withMessage("Элемент не найден")
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
    }
}