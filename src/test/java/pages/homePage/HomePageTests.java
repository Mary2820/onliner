package pages.homePage;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CatalogPage;
import pages.Model;
import pages.ResultPage;
import pages.SideBar;

public class HomePageTests extends BaseTest {
    HomePage homePage;

    @BeforeMethod
    public void init() {
        homePage = new HomePage();
        Model model = new Model();
        model.clickCancelButton();
    }

    @Test
    public void whenOpeningPageLogoIsVisible() {
        Assert.assertTrue(homePage.isLogoVisible());
    }

    @Test
    public void whenClickBurgerMenuOpenSideBar() {
        SideBar sideBar = new SideBar();
        homePage.isBurgerMenuVisible();

        homePage.clickBurgerMenu();

        Assert.assertTrue(sideBar.isSideBarVisible());
    }

    @Test
    public void whenClickSearchFieldNavigateToCatalogPage() {
        CatalogPage catalogPage = new CatalogPage();

        homePage.isSearchElementsDispayed();
        homePage.clickSearchField();

        Assert.assertTrue(catalogPage.isCatalogPageDisplayed());
    }

    @Test
    public void whenOpeningPageStoryBarIsVisible() {
        Assert.assertTrue(homePage.isStoryBarDisplayed());
    }

    @Test
    public void whenOpeningPageCardsCarouselIsVisiable() {
        Assert.assertTrue(homePage.isCardsCarouselDisplayed());
    }

    @Test
    public void whenOpeningPageDiscountGridIsVisible() {
        DiscountGrid discountGrid = new DiscountGrid();

        Assert.assertTrue(discountGrid.isDiscountGridDisplayed());
    }

    @DataProvider(name = "gridElements")
    public Object[][] gridElements() {
        return new Object[][]{
                {"Все скидки Каталога!"},
                {"Игровые приставки"},
                {"Смартфоны"},
                {"Ноутбуки"},
                {"Наушники"},
                {"Телевизоры"},
                {"Стиральные машины"},
                {"Холодильники"},
                {"Роботы-пылесосы"},
                {"Пылесосы"},
                {"Моторные масла"},
                {"Видеокарты"}
        };
    }

    @Test(dataProvider = "gridElements")
    public void whenOpeningPageGridsElementIsVisible(String element) {
        DiscountGrid discountGrid = new DiscountGrid();

        Assert.assertTrue(discountGrid.isGridElementDisplayed(element));
    }


    @DataProvider(name = "gridElementsAndResultTitles")
    public Object[][] gridElementsAndResultTitles() {
        return new Object[][]{
                {"Все скидки Каталога!", "Все суперцены"},
                {"Игровые приставки", "Игровые приставки"},
                {"Смартфоны", "Мобильные телефоны"},
                {"Ноутбуки", "Ноутбуки"},
                {"Наушники", "Наушники и гарнитуры"},
                {"Телевизоры", "Телевизоры"},
                {"Стиральные машины", "Стиральные машины"},
                {"Холодильники", "Холодильники"},
                {"Роботы-пылесосы", "Роботы-пылесосы"},
                {"Пылесосы", "Пылесосы"},
                {"Моторные масла", "Моторные масла"},
                {"Видеокарты", "Видеокарты"}
        };
    }

    @Test(dataProvider = "gridElementsAndResultTitles")
    public void whenClickGridElementNavigateToResultPage(String element, String result) {
        DiscountGrid discountGrid = new DiscountGrid();
        ResultPage resultPage = new ResultPage();
        discountGrid.isGridElementDisplayed(element);

        discountGrid.clickGridElement(element);

        Assert.assertTrue(resultPage.isTitleResultPageDisplayed(result));
    }
}