package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.PriceHelper;

public class QuickViewIframePage extends BasePage {
    @FindBy(how = How.ID, using = "our_price_display")
    private WebElement priceSpan;

    @FindBy(how = How.ID, using = "quantity_wanted")
    private WebElement quantityInput;

    @FindBy(how = How.ID, using = "group_1")
    private WebElement sizeDropdown;

    @FindBy(how = How.ID, using = "color_to_pick_list")
    private WebElement colorPicker;

    @FindBy(how = How.ID, using = "add_to_cart")
    private WebElement addToCartButton;

    @FindBy(how = How.XPATH, using = "//*[@itemprop='name']")
    private WebElement productName;

    public QuickViewIframePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(driver, this);
    }

    public void setQuantity(int quantity) {
        quantityInput.clear();
        quantityInput.sendKeys(Integer.toString(quantity));
    }

    public void selectSize(String size) {
        try {
            sizeDropdown.findElement(By.xpath("//*[text()='" + size + "']"));
        } catch (NoSuchElementException e) {
            Assert.fail("Size '" + size + "' not found");
            return;
        }

        Select sizeSelect = new Select(sizeDropdown);
        sizeSelect.selectByVisibleText(size);
    }

    public void selectColor(String color) {
        WebElement selectedColor;
        try {
            selectedColor = colorPicker.findElement(By.xpath("//*[@name='" + color + "']"));
        } catch (NoSuchElementException e) {
            Assert.fail("Color '" + color + "' not found");
            return;
        }

        selectedColor.click();
    }

    public float getDressPrice() {
        return PriceHelper.getAsFloat(priceSpan.getText());
    }

    public String getDressName() {
        return productName.getText();
    }

    public void addToCart() {
        addToCartButton.click();

        driver.switchTo().defaultContent();

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart")));
        } catch (TimeoutException e) {
            Assert.fail("Add to cart summary modal not shown");
        }
    }
}
