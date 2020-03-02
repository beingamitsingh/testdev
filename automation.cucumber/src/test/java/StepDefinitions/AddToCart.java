package StepDefinitions;

import cucumber.api.java.en.*;
import framework.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

public class AddToCart  {

    private static HashMap<String, String> shoppingData;

    @And("^I am logged in with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iAmLoggedInWithUsernameAndPassword(String arg0, String arg1)   {

        //navigate to SignIn page
        UserManagement user = new UserManagement();
        user.iAmInSignInPage();

        WebElement logIn_email, logIn_password, logIn_submitButton;
        logIn_email = Driver.getElement("logIn_email");
        logIn_password = Driver.getElement("logIn_password");
        logIn_submitButton = Driver.getElement("logIn_submitButton");

        if (logIn_submitButton.isDisplayed() && logIn_submitButton.isEnabled())   {
            Report.pass("LogIn button located.");
            Driver.sendKeys(logIn_email, arg0);
            Driver.sendKeys(logIn_password, arg1);
            logIn_submitButton.click();
        }
        else
            Report.fail("LogIn button is not present.");
    }

    @Given("^I am in Landing page$")
    public void iAmInLandingPage() {
        WebElement viewMyAccount = Driver.getElement("viewMyAccount");
        if (viewMyAccount.isDisplayed())
            Report.pass("User is on landing page");
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
            Report.pass("Search box is enabled");
            Driver.sendKeys(searchBox, arg0);
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
        shoppingData = new HashMap<>();
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
        Driver.sendKeys(quantity, Integer.toString(arg0));
        shoppingData.put("Quantity", Integer.toString(arg0));
    }

    @And("^I set size to S$")
    public void iSetSizeToS()   {
        WebElement sizeElement = Driver.getElement("size");
        Select size= new Select(sizeElement);
        if (sizeElement.isEnabled()) {
            Report.pass("Size field found.");
            size.selectByVisibleText("S");
            shoppingData.put("Size", "S");
        }
        else
            Report.fail("Size field is either disabled or not present.");
    }

    @And("^I set color to White$")
    public void iSetColorToWhite()  {
        WebElement productColor_White;
        productColor_White= Driver.getElement("productColor_White");
        productColor_White.click();
        shoppingData.put("Color", "White");
    }

    @And("^I select Add to cart button$")
    public void iSelectAddToCartButton() throws InterruptedException {
        WebElement addToCart = Driver.getElement("addToCart");

        //Before adding to cart, calculate total product (without tax & shipping)
        shoppingData.put("TotalProduct", Float.toString(getTotalProduct()));

        if (addToCart.isEnabled()) {
            Report.pass("Add to cart button enabled.");
            addToCart.click();
            Report.pass("Add to cart button is clicked.");
        }
        else
            Report.fail("Add to Cart button is either not enabled.");
    }

    @And("^item is successfully added to cart$")
    public void itemIsSuccessfullyAddedToCart() throws InterruptedException {

        WebElement addtoCart_confirmationMessage;

        Driver.wait("addtoCart_confirmationMessage", 4);
        addtoCart_confirmationMessage = Driver.getElement("addtoCart_confirmationMessage");
        if (addtoCart_confirmationMessage.isDisplayed())    {
            Report.pass("Item successfully added to cart.");
        }
        else
            Report.fail("Item could not be added to cart.");
    }

    @And("^I click on Proceed to checkout button$")
    public void iClickOnProceedToCheckoutButton()   {
        WebElement proceedToCheckout = Driver.getElement("proceedToCheckout_popUp");
        if (proceedToCheckout.isEnabled()) {
            Report.pass("Proceed to checkout button is enabled.");
            proceedToCheckout.click();
        }
        else
            Report.fail("Proceed to checkout button is not enabled.");
    }

    @Then("^Shopping cart summary page is opened$")
    public void shoppingCartSummaryPageIsOpened()   {
        WebElement shoppingCartSummary = Driver.getElement("shoppingCartSummary");;
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
        String description= description_prodSummaryPage.getText();
        arrDescription_out = description.split(",");

        //Fetch description and store it in HashMap
        for (int i=0; i< arrDescription_out.length; i++)  {
            arrDescription_in = arrDescription_out[i].split(":");
            descriptions.put(arrDescription_in[0].trim(), arrDescription_in[1].trim());
        }

        //Compare descriptions: Color
        if (descriptions.get("Color").equals(shoppingData.get("Color")))
            Report.pass("Expected Color: " + shoppingData.get("Color") +", Actual Color: " + descriptions.get("Color"));
        else
            Report.fail("Expected Color: " + shoppingData.get("Color") +", Actual Color: " + descriptions.get("Color"));

        //Compare descriptions: Size
        if (descriptions.get("Size").equals(shoppingData.get("Size")))
            Report.pass("Expected Size: " + shoppingData.get("Size") +", Actual Size: " + descriptions.get("Size"));
        else
            Report.fail("Expected Size: " + shoppingData.get("Size") +", Actual Size: " + descriptions.get("Size"));
    }

    @And("^amount is correctly calculated$")
    public void amountIsCorrectlyCalculated()   {

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        WebElement totalProduct, total_shipping, total_tax, total_price;
        totalProduct = Driver.getElement("totalProduct");
        total_shipping = Driver.getElement("total_shipping");
        total_tax = Driver.getElement("total_tax");
        total_price = Driver.getElement("total_price");

        String actualTotalProductPrice, expectedTotalProductPrice, expectedTotalPrice, actualTotalPrice;
        actualTotalProductPrice= df.format(Float.parseFloat(totalProduct.getText().replace("$", "")));
        expectedTotalProductPrice= df.format(Float.parseFloat(shoppingData.get("TotalProduct")));

        //Verify if total product price (without extra costs) is correct
        if (actualTotalProductPrice.equals(expectedTotalProductPrice))
            Report.pass("Expected TOTAL PRODUCT: " + shoppingData.get("TotalProduct") + ", " +
                    "Actual TOTAL PRODUCT: " + totalProduct.getText());
        else
            Report.fail("Expected TOTAL PRODUCT: " + shoppingData.get("TotalProduct") + ", " +
                    "Actual TOTAL PRODUCT: " + totalProduct.getText());

        expectedTotalPrice= df.format(Float.parseFloat(shoppingData.get("TotalProduct")) +
                Float.parseFloat(total_shipping.getText().replace("$", "")) +
                Float.parseFloat(total_tax.getText().replace("$", "")));
        actualTotalPrice= df.format(Float.parseFloat(total_price.getText().replace("$", "")));

        //Verify if total price is calculated correctly
        if (expectedTotalPrice.equals(actualTotalPrice))
            Report.pass("Total price is calculated correctly.");
        else
            Report.fail("Total price is not calculated correctly.");
    }

    @And("^Proceed to checkout button is visible$")
    public void proceedToCheckoutButtonIsVisible()  {

        ((JavascriptExecutor) MyRunner.webDriver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");

        WebElement proceedToCheckout = Driver.getElement("proceedToCheckout_checkOutPage");
        if (proceedToCheckout.isDisplayed())
            Report.pass("Proceed to checkout button present on product summary page.");
        else
            Report.fail("Proceed to checkout button is not present on product summary page.");
    }

    //*************Additional functions*****************
    private float getTotalProduct() {
        float quantity, pricePerProduct;

        quantity = Integer.parseInt(shoppingData.get("Quantity"));
        pricePerProduct = Float.parseFloat(Driver.getElement("priceOfPerProduct")
                .getText().replace("$",""));

        return (quantity * pricePerProduct);
    }
}