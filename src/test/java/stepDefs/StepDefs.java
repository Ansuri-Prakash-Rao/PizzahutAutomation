package stepDefs;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class StepDefs {

    WebDriver driver = Hooks.driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Given("I have launched the application")
    public void i_have_launched_the_application() throws InterruptedException {
        driver.get("https://www.pizzahut.co.in/");
		
    }

    @When("I enter the location as {string}")
    public void i_enter_the_location_as(String location) {
        WebElement locationBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter your location for delivery']")));
        locationBox.sendKeys(location);
    }

    @And("I select the very first suggestion from the list")
    public void i_select_the_first_suggestion() {
        WebElement firstSuggestion = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='Pune Railway Station']")));
        firstSuggestion.click();
    }

    @Then("I should land on the Deals page")
    public void i_should_land_on_the_deals_page() {
        WebElement dealsHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[contains(text(),'Deals')])[2]")));
        assert dealsHeader.isDisplayed();
    }

    @And("I select the tab as {string}")
    public void i_select_the_tab_as(String tabName) {
        WebElement tab = driver.findElement(By.xpath("//a[contains(@data-synth,'link--pizzas--side')]"));
        tab.click();
    }

    @And("I add {string} to the basket")
    public void i_add_pizza_to_basket(String pizzaName) {
        WebElement pizza = driver.findElement(By.xpath("//button[@data-synth='button--momo-mia-non-veg-recommended-pan-personal--one-tap']//span//span[contains(text(),'Add')]"));
        pizza.click();
    }

    @And("I note down the price displayed on the screen")
    public void i_note_down_the_price_displayed_on_the_screen() {
        WebElement priceElement = driver.findElement(By.xpath("//span[contains(@class,'amountdue')]"));  // update with actual class name
        String price = priceElement.getText();
        System.out.println("Price: " + price);
    }

    @Then("I should see the pizza {string} is added to the cart")
    public void i_should_see_pizza_added_to_cart(String pizzaName) {
        WebElement cartPizza = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Checkout')]")));
        assert cartPizza.isDisplayed();
    }

    @And("price is also displayed correctly")
    public void price_is_also_displayed_correctly() {
        // Code to check if the price displayed matches the one noted earlier
    }

    @And("I click on the Checkout button")
    public void i_click_on_checkout_button() {
        WebElement checkoutBtn = driver.findElement(By.xpath("//span[contains(text(),'Checkout')]"));  // Update with the correct ID
        checkoutBtn.click();
    }

    @Then("I should be landed on the secured checkout page")
    public void i_should_be_on_secured_checkout_page() {
        WebElement checkoutHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Secure Checkout')]")));
        assert checkoutHeader.isDisplayed();
    }

    @And("I enter the personal details")
    public void i_enter_personal_details(io.cucumber.datatable.DataTable dataTable) {
        // Enter details from the table
    }

    @And("I enter the address details")
    public void i_enter_address_details(io.cucumber.datatable.DataTable dataTable) {
        // Enter address from the table
    }
}
