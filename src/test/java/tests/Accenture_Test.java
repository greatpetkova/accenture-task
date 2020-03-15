package tests;

import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.ListingPage;
import pages.QuickViewIframePage;
import utils.BrowserFactory;
import utils.EnvironmentHelper;

import java.util.concurrent.TimeUnit;

public class Accenture_Test {
    private WebDriver driver;

    @BeforeTest
    public void boot() {
        driver = BrowserFactory.getChromeDriver();

        driver.get("http://automationpractice.com/");

        if (EnvironmentHelper.isDockerEnv()) {
            driver.manage().window().setSize(new Dimension(1920,1080)); //fake browser size for headless
        } else {
            driver.manage().window().maximize();
        }

        //Let's wait for everything to load
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @Test
    public void DressTest() {
        int testQuantity = 3;
        String testSize = "M";
        String testColor = "White";

        //Init homePage
        HomePage homePage = new HomePage(driver);

        homePage.hoverOnWomanSection();
        homePage.clickOnSummerDresses();

        //Init listingPage
        ListingPage listingPage = new ListingPage(driver);

        int listingPageItemsCount = 3;

        Assert.assertEquals(listingPage.getNumberOfItems(), listingPageItemsCount);

        listingPage.hoverOnMiddleDress();
        listingPage.clickOnQuickView();

        //Init quickViewIframePage
        QuickViewIframePage quickViewIframePage = listingPage.getQuickViewPage();

        quickViewIframePage.setQuantity(testQuantity);
        quickViewIframePage.selectSize(testSize);
        quickViewIframePage.selectColor(testColor);

        float dressPrice = quickViewIframePage.getDressPrice();
        String dressName = quickViewIframePage.getDressName();

        quickViewIframePage.addToCart();

        listingPage.closeAddToCartSummary();
        listingPage.hoverMiniBasket();

        Assert.assertEquals(listingPage.getMiniBasketProductQuantity(), testQuantity);
        Assert.assertEquals(listingPage.getMiniBasketProductSize(), testSize);
        Assert.assertEquals(listingPage.getMiniBasketProductColor(), testColor);

        float productPrice = listingPage.getMiniBasketProductPrice();
        float shippingPrice = listingPage.getMiniBasketShippingPrice();
        float totalPrice = listingPage.getMiniBasketTotalPrice();

        Assert.assertEquals(productPrice, dressPrice * testQuantity);
        Assert.assertEquals(totalPrice, (dressPrice * testQuantity) + shippingPrice);
        Assert.assertEquals(dressName, listingPage.getMiniBasketProductName());
    }

    @AfterTest
    public void shutdown() {
        driver.close();
    }
}