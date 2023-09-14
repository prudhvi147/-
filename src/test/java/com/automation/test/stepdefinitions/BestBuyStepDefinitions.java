package com.automation.test.stepdefinitions;

import com.automation.test.pageObjects.Home;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.time.Duration;

public class BestBuyStepDefinitions {

    public WebDriver driver;
    Home home;

    @Given("Launch Best Buy Url In Edge")
    public void launchBestBuyInEdge() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        System.setProperty("webdriver.edge.driver", "src/test/resources/drivers/msedgedriver.exe");
        EdgeOptions edgeOptions = new EdgeOptions();
        capabilities.setCapability(EdgeOptions.CAPABILITY, edgeOptions);
        edgeOptions.addArguments("--remote-allow-origins=*");
        edgeOptions.merge(capabilities);
        driver = new EdgeDriver(edgeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.navigate().to("https://www.bestbuy.com/");

        home = new Home(driver);
        home.waitTillSearchBarIsLoaded(driver);
    }

    @And("User Searches for {string} product")
    public void userSearchesForProduct(String text) {
        home.searchForAProduct(driver, text);
    }

    @When("User adds the product to Cart")
    public void userAddsTheProductToCart() {
        home.addProductToCartForFirstElement(driver);
    }

    @And("User continues Shopping")
    public void userContinuesShopping() {
        home.lnkContinueShoppingInPopup.click();
    }

    @When("User redirects To Checkout page")
    public void userRedirectsToCheckoutPage() {
        home.lnkGoToCart.click();
    }

    @Then("User validates checkout page is displayed as expected")
    public void validateCheckoutPage() {
        home.validateCheckoutPage(driver);
    }
}
