package StepDefinitions;

import cucumber.api.java.en.*;
import framework.Driver;
import framework.Report;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.List;

public class AddToCart  {
    UserManagement user = new UserManagement();

    @And("^I am logged in$")
    public void iAmLoggedIn()   {

        //navigate to SignIn page
        user.iAmInSignInPage();

        WebElement logIn_email, logIn_password, logIn_submitButton;
        logIn_email = Driver.getElement("logIn_email");
        logIn_password = Driver.getElement("logIn_password");
        logIn_submitButton = Driver.getElement("logIn_submitButton");

        if (logIn_submitButton.isDisplayed() && logIn_submitButton.isEnabled())   {
            Report.pass("LogIn button located.");
            logIn_email.sendKeys(UserManagement.registrationEmail);
            logIn_password.sendKeys(UserManagement.data.get("Password"));
            logIn_submitButton.click();
        }
        else
            Report.fail("LogIn button is not present.");
    }

    @Given("^I am in Landing page$")
    public void iAmInLandingPage() {
        WebElement viewMyAccount = Driver.getElement("viewMyAccount");
        if (viewMyAccount.isDisplayed())
            Report.pass("User is on landing page with " + UserManagement.registrationEmail);
        else
            Report.fail("User could not be on landing page.");
    }

    @When("^I select Search menu$")
    public void iSelectSearchMenu() {
        WebElement searchBox;
        searchBox= Driver.getElement("searchBox");
        if (searchBox.isDisplayed())    {
            searchBox.click();
            Report.pass("Search box selected");
        }
        else
            Report.fail("Search box cannot be located");
    }

    @And("^I enter \"([^\"]*)\"$")
    public void iEnter(String arg0) {
        WebElement searchBox= Driver.getElement("searchBox");
        if (searchBox.isEnabled())  {
            Report.pass("Search bos is enabled");
            searchBox.sendKeys(arg0);
        }
        else
            Report.fail("Search box is disabled.");
    }

    @And("^I click on Search button$")
    public void iClickOnSearchButton() {
        WebElement searchButton = Driver.getElement("searchButton");
        searchButton.click();
    }

    @And("^only (\\d+) result is found$")
    public void onlyResultIsFound(int arg0) {
        List<WebElement> searchResults = Driver.getElements("searchResults");
        if (searchResults.size() == arg0)
            Report.pass(arg0 + " product found.");
        else
            Report.fail("More than " + arg0 + " products found.");
    }

    @And("^I select the item$")
    public void iSelectTheItem()    {
        List<WebElement> searchResults = Driver.getElements("searchResults");
        if (searchResults.get(0).isEnabled())   {
            searchResults.get(0).click();
            Report.pass("First item selected.");
        }
        else
            Report.fail("Item not enabled.");
    }

    @And("^item description and condition is displayed$")
    public void itemDescriptionAndConditionIsDisplayed() {
        WebElement product_condition, short_description_block;
        product_condition= Driver.getElement("product_condition");
        short_description_block= Driver.getElement("short_description_block");

        if (product_condition.isDisplayed() && short_description_block.isDisplayed())   {
            Report.pass("Product description is displayed.");
            Report.pass("Product condition is displayed.");
        }
        else    {
            Report.fail("Product description is not displayed.");
            Report.fail("Product condition is not displayed.");
        }
    }

    @And("^I change quantity to (\\d+)$")
    public void iChangeQuantityTo(int arg0) {
        WebElement quantity = Driver.getElement("quantity");
        quantity.clear();
        quantity.sendKeys(Integer.toString(arg0));
    }

    @And("^I set size to S$")
    public void iSetSizeToS()   {
        WebElement sizeElement = Driver.getElement("size");
        Select size= new Select(sizeElement);
        if (sizeElement.isDisplayed() && sizeElement.isEnabled()) {
            Report.pass("Size field found.");
            size.selectByVisibleText("S");
        }
        else
            Report.fail("Size field is either disabled or not present.");
    }

    @And("^I set color to White$")
    public void iSetColorToWhite()  {
        WebElement productColor_White;
        productColor_White= Driver.getElement("productColor_White");
        productColor_White.click();
    }

    @And("^I select Add to cart button$")
    public void iSelectAddToCartButton() {
        WebElement addToCart = Driver.getElement("addToCart");

        if (addToCart.isEnabled() && addToCart.isEnabled()) {
            Report.pass("Add to cart button located.");
            addToCart.click();
        }
        else
            Report.fail("Add to Cart button is either not enabled or present.");
    }

    @And("^item is successfully added to cart$")
    public void itemIsSuccessfullyAddedToCart() {
        WebElement addtoCart_confirmationMessage;
        addtoCart_confirmationMessage = Driver.getElement("addtoCart_confirmationMessage");
        if (addtoCart_confirmationMessage.isDisplayed())
            Report.pass("Item successfully added to cart.");
        else
            Report.fail("Item could not be added to cart.");
    }

    @And("^I click on Proceed to checkout button$")
    public void iClickOnProceedToCheckoutButton()   {
        WebElement proceedToCheckout = Driver.getElement("proceedToCheckout");

        if (proceedToCheckout.isEnabled() && proceedToCheckout.isEnabled()) {
            Report.pass("Proceed to checkout button located.");
            proceedToCheckout.click();
        }
        else
            Report.fail("Proceed to checkout button is either not enabled or present.");
    }

    @Then("^Shopping cart summary page is opened$")
    public void shoppingCartSummaryPageIsOpened()   {
        WebElement shoppingCartSummary;
        shoppingCartSummary = Driver.getElement("shoppingCartSummary");
        if (shoppingCartSummary.isDisplayed())
            Report.pass("Shopping Cart Summary is opened");
        else
            Report.fail("Shopping Cart Summary is not opened.");
    }

    @And("^correct description is specified$")
    public void correctDescriptionIsSpecified() {
        String[] arrDescription_out, arrDescription_in;
        HashMap<String, String> descriptions = new HashMap<>();

        WebElement description_prodSummaryPage;
        description_prodSummaryPage = Driver.getElement("description_prodSummaryPage");
        String description= description_prodSummaryPage.getAttribute("value");
        arrDescription_out = description.split(",");

        //Fetch description and store it in HashMap
        for (int i=0; i< arrDescription_out.length; i++)  {
            arrDescription_in = arrDescription_out[i].split(":");
            descriptions.put(arrDescription_in[0].trim(), arrDescription_in[1].trim());
        }

        //Compare descriptions

    }

    @And("^amount is correctly calculated$")
    public void amountIsCorrectlyCalculated() {
    }

    @And("^Proceed to checkout button is visible$")
    public void proceedToCheckoutButtonIsVisible() {
        WebElement proceedToCheckout = Driver.getElement("proceedToCheckout");
        if (proceedToCheckout.isEnabled() && proceedToCheckout.isEnabled())
            Report.pass("Proceed to checkout button present on product summary page.");
        else
            Report.pass("Proceed to checkout button is not present on product summary page.");
    }
}