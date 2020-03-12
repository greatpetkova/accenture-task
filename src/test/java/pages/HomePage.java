package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class HomePage extends BasePage {
    @FindBy(how = How.ID, using = "block_top_menu")
    private WebElement topMenu;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(driver, this);
    }

    public void hoverOnWomanSection() {
        WebElement womanSectionLink;
        try {
            womanSectionLink = topMenu.findElement(By.linkText("WOMEN"));
        } catch (NoSuchElementException e) {
            Assert.fail("Women section link not found");
            return;
        }

        this.hoverOnElement(womanSectionLink);
    }

    public void clickOnSummerDresses() {
        WebElement summerDressesLink;
        try {
            summerDressesLink = wait.until(
                    ExpectedConditions.presenceOfNestedElementLocatedBy(topMenu, By.linkText("Summer Dresses")));
        } catch (TimeoutException e) {
            Assert.fail("Summer Dresses link not found");
            return;
        }

        summerDressesLink.click();
    }
}
