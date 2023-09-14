package com.automation.test.pageObjects;

import io.cucumber.java.en_old.Ac;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Home {

    @FindBy(id = "gh-search-input")
    public WebElement edtSearchBar;
    @FindBy(xpath = "//h4[@class='sku-title']/a")
    public WebElement lnkFirstItemNameInResultsPage;
    @FindBy(xpath = "//button[@data-button-state='ADD_TO_CART']")
    public WebElement btnAddToCartForFirstItem;
    @FindBy(xpath = "//a[@title='Cart']//div")
    public WebElement txtTotalElementsInCart;
    @FindBy(xpath = "//div[@class='shop-commerce-elements']//span")
    public WebElement txtAddedToCartInPopup;
    @FindBy(xpath = "//button[contains(@data-track,'Continue shopping')]")
    public WebElement lnkContinueShoppingInPopup;
    @FindBy(xpath = "//a[@href='/cart']")
    public WebElement lnkGoToCart;
    @FindBy(id = "cart-order-summary")
    public WebElement txtOrderSummary;
    @FindBy(xpath = "//button[@data-track='Checkout - Top']")
    public WebElement btnGoToCheckout;
    @FindBy(id = "header-clear-search-icon")
    public WebElement btnClearSearchIcon;


    WebDriver driver;

    public Home(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitTillSearchBarIsLoaded(WebDriver driver) {
        waitForElementToBeDisplayed(driver, edtSearchBar);
    }

    public void searchForAProduct(WebDriver driver, String text) {
        Actions actions = new Actions(driver);
        try {
            btnClearSearchIcon.click();
        } catch (WebDriverException we) {
            System.out.println("Element No present... ignore and continue");
        }
        edtSearchBar.sendKeys(text);
        edtSearchBar.click();
        actions.sendKeys(Keys.ENTER).build().perform();
        waitForElementToBeDisplayed(driver, lnkFirstItemNameInResultsPage);
        waitForElementTextToBeDisplayed(driver, lnkFirstItemNameInResultsPage, text);
        Assert.assertEquals(lnkFirstItemNameInResultsPage.getText(), text);
    }

    public boolean waitForElementToBeDisplayed(WebDriver driver, WebElement element) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        return true;
    }

    public boolean waitForElementTextToBeDisplayed(WebDriver driver, WebElement element, String text) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        webDriverWait.until(ExpectedConditions.textToBePresentInElement(element, text));
        return true;
    }

    public void addProductToCartForFirstElement(WebDriver driver) {
        int text1, text2;
        try {
            text1 = Integer.parseInt(txtTotalElementsInCart.getText());
        } catch (WebDriverException we) {
            text1 = 0;
        }
        btnAddToCartForFirstItem.click();
        waitForElementToBeDisplayed(driver, txtAddedToCartInPopup);
        text2 = Integer.parseInt(txtTotalElementsInCart.getText());
        Assert.assertEquals("Cart count is increased:", text2, text1 + 1);
    }

    public void validateCheckoutPage(WebDriver driver) {
        waitForElementToBeDisplayed(driver, txtOrderSummary);
        waitForElementToBeDisplayed(driver, btnGoToCheckout);
    }
}
