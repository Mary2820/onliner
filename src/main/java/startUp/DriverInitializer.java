package startUp;

import constants.Configuration;
import constants.PlatformConstants;
import enums.Capabilities;
import enums.PlatformName;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.proxy.NotImplementedException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;

abstract public class DriverInitializer {
    private static AppiumDriver driver = null;

    public static AppiumDriver getDriver() {
        if (driver == null) {
            try {
                if(Configuration.PLATFORM == PlatformName.Android){
                    driver = new AndroidDriver(new URL(Configuration.APPIUM_AGENT_URL), setCapabilities());
                }else if(Configuration.PLATFORM == PlatformName.iOS){
                    driver = new IOSDriver(new URL(Configuration.APPIUM_AGENT_URL), setCapabilities());
                }else{
                    Assert.fail("Incorrect platform name: " + Configuration.PLATFORM);
                }
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        return driver;
    }

    private static DesiredCapabilities setCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (PlatformName.Android == Configuration.PLATFORM) {
            capabilities.setCapability(String.valueOf(Capabilities.platformName), PlatformName.Android);
            capabilities.setCapability(String.valueOf(Capabilities.platformVersion), PlatformConstants.Android.PLATFORM_VERSION);
            capabilities.setCapability(String.valueOf(Capabilities.deviceName), PlatformConstants.Android.DEVICE_NAME);
            capabilities.setCapability(String.valueOf(Capabilities.app), PlatformConstants.Android.APP);
            capabilities.setCapability(String.valueOf(Capabilities.appWaitActivity), PlatformConstants.Android.APP_WAIT_ACTIVITY);
            capabilities.setCapability(String.valueOf(Capabilities.automationName), PlatformConstants.Android.AUTOMATER_NAME);
//            capabilities.setCapability(String.valueOf(Capabilities.noReset), PlatformConstants.Android.NO_RESET);
        }
        else if (PlatformName.iOS == Configuration.PLATFORM) {
            throw new NotImplementedException();
        }
        return capabilities;
    }

    public static void setDriverNull () {
        driver.quit();
        driver = null;
    }
}