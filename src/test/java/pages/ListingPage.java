package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utils.PriceHelper;

import java.util.List;

public class ListingPage extends BasePage {
    @FindBy(how = How.CLASS_NAME, using = "product_list")
    private WebElement productsListContainer;

    private List<WebElement> dresses;

    private WebElement quickViwLink;

    private WebElement fancyboxContainer;

    private WebElement miniBasketProduct;

    private WebElement miniBasketContainer;

    public ListingPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(driver, this);
    }

    public int getNumberOfItems() {
        dresses = productsListContainer.findElements(By.xpath("*"));
        return dresses.size();
    }

    public void hoverOnMiddleDress() {
        WebElement middleDress = dresses.get(1);

        this.hoverOnElement(middleDress);

        try {
            quickViwLink = wait.until(
                    ExpectedConditions.presenceOfNestedElementLocatedBy(middleDress, By.className("quick-view")));
        } catch (TimeoutException e) {
            Assert.fail("Quick view button not visible");
        }
    }

    public void clickOnQuickView() {
        quickViwLink.click();

        try {
            fancyboxContainer = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.className("fancybox-wrap")));
        } catch (TimeoutException e) {
            Assert.fail("Quick view modal not visible");
        }
    }

    public QuickViewIframePage getQuickViewPage() {
        driver.switchTo().frame(fancyboxContainer.findElement(By.className("fancybox-iframe")));

        return new QuickViewIframePage(driver);
    }

    public void closeAddToCartSummary() {
        WebElement addToCartSummaryContainer;
        try {
            addToCartSummaryContainer = driver.findElement(By.id("layer_cart"));
        } catch (NoSuchElementException e) {
            Assert.fail("Add to cart summary modal not found");
            return;
        }

        WebElement addToCartSummaryContainerCloseLink;

        try {
            addToCartSummaryContainerCloseLink = addToCartSummaryContainer.findElement(By.className("cross"));
        } catch (NoSuchElementException e) {
            Assert.fail("Add to cart summary modal close link not found");
            return;
        }

        addToCartSummaryContainerCloseLink.click();
    }

    public void hoverMiniBasket() {
        try {
            miniBasketContainer = driver.findElement(By.className("shopping_cart"));
        } catch (NoSuchElementException e) {
            Assert.fail("Mini basket container not found");
        }

        WebElement miniBasketContainerToggle;

        try {
            miniBasketContainerToggle = miniBasketContainer.findElement(By.tagName("a"));
        } catch (NoSuchElementException e) {
            Assert.fail("Mini basket toggle not found");
            return;
        }

        hoverOnElement(miniBasketContainerToggle);

        WebElement miniBasketPanel;

        try {
            miniBasketPanel = miniBasketContainer.findElement(By.className("cart_block"));
        } catch (NoSuchElementException e) {
            Assert.fail("Mini basket panel not found");
            return;
        }

        try {
            wait.until(ExpectedConditions.visibilityOf(miniBasketPanel));
        } catch (TimeoutException e) {
            Assert.fail("Mini basket panel not shown");
            return;
        }

        List<WebElement> miniBasketProducts = miniBasketPanel.findElement(By.className("products")).findElements(By.xpath("*"));

        miniBasketProduct = miniBasketProducts.get(0);
    }

    public String getMiniBasketProductName() {
        WebElement linkToProduct;
        try {
            linkToProduct = miniBasketProduct.findElement(By.className("product-name")).findElement(By.tagName("a"));
        } catch (NoSuchElementException e) {
            Assert.fail("Product name container not found");
            return "";
        }

        return linkToProduct.getAttribute("title");
    }

    public int getMiniBasketProductQuantity() {
        WebElement quantityContainer;
        try {
            quantityContainer = miniBasketProduct.findElement(By.className("quantity"));
        } catch (NoSuchElementException e) {
            Assert.fail("Quantity container not found");
            return 0;
        }

        return Integer.parseInt(quantityContainer.getText());
    }

    public String getMiniBasketProductSize() {
        WebElement linkToProduct;
        try {
            linkToProduct = miniBasketProduct.findElement(By.className("product-atributes")).findElement(By.tagName("a"));
        } catch (NoSuchElementException e) {
            Assert.fail("Attributes container not found");
            return "";
        }

        String[] attributes = linkToProduct.getText().split(",", 2);

        return attributes[1].trim();
    }

    public String getMiniBasketProductColor() {
        WebElement linkToProduct;
        try {
            linkToProduct = miniBasketProduct.findElement(By.className("product-atributes")).findElement(By.tagName("a"));
        } catch (NoSuchElementException e) {
            Assert.fail("Attributes container not found");
            return "";
        }

        String[] attributes = linkToProduct.getText().split(",", 2);

        return attributes[0].trim();
    }

    public float getMiniBasketProductPrice() {
        WebElement priceContainer;
        try {
            priceContainer = miniBasketProduct.findElement(By.className("price"));
        } catch (NoSuchElementException e) {
            Assert.fail("Product price container not found");
            return 0;
        }

        return PriceHelper.getAsFloat(priceContainer.getText());
    }

    public float getMiniBasketShippingPrice() {
        WebElement priceShippingContainer;
        try {
            priceShippingContainer = miniBasketContainer.findElement(By.className("ajax_cart_shipping_cost"));
        } catch (NoSuchElementException e) {
            Assert.fail("Shipping price container not found");
            return 0;
        }

        return PriceHelper.getAsFloat(priceShippingContainer.getText());
    }

    public float getMiniBasketTotalPrice() {
        WebElement priceTotalContainer;
        try {
            priceTotalContainer = miniBasketContainer.findElement(By.className("ajax_block_cart_total"));
        } catch (NoSuchElementException e) {
            Assert.fail("Total price container not found");
            return 0;
        }

        return PriceHelper.getAsFloat(priceTotalContainer.getText());
    }
}
