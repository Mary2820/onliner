package pages.homePage;

import base.BaseTest;
import info.InfoFromApi;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Model;
import services.DriverService;

import java.util.HashMap;

public class FrameTopDiscountsTests extends BaseTest {
    HomePage homePage;
    FrameTopDiscounts frame;
    HashMap<String, String> card1 = InfoFromApi.getValueFromCard1();

    @BeforeMethod
    public void init() {
        homePage = new HomePage();
        frame = new FrameTopDiscounts();
        Model model = new Model();
        model.clickCancelButton();
    }

    @Test
    public void isTitleVisible() {
        String title = card1.get("title");

        Assert.assertTrue(frame.isTitleDisplayed(title));
    }

    @Test
    public void isCardNameVisible() {
        String productName = card1.get("productName");

        Assert.assertTrue(frame.isProductNameDisplayed(productName));
    }

    @Test
    public void isPriceVisiable() {
        String price = card1.get("price");

        Assert.assertTrue(frame.isPriceDisplayed(price));
    }

    @Test
    public void isPriceColorCorrect() {
        String price = card1.get("price");
        String priceColor = card1.get("priceColor");

        Assert.assertTrue(frame.isPriceColorCorrect(priceColor, price));
    }
}
