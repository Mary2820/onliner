package base;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import startUp.DriverInitializer;

public class BaseTest {
    @AfterMethod
    public void teardown() {
        DriverInitializer.setDriverNull();
    }

    @BeforeMethod
    public void startDriver () {
        DriverInitializer.getDriver();
    }
}
