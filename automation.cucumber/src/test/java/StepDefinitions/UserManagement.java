package StepDefinitions;

import cucumber.api.DataTable;
import cucumber.api.java.en.*;
import framework.*;
import org.openqa.selenium.WebElement;
import java.util.Map;

public class UserManagement extends MyRunner {

    private static Map<String, String> data;
    private static String registrationEmail;

    @Given("^application is launched$")
    public void applicationIsLaunched() {
        webDriver.get("http://automationpractice.com/index.php");
        webDriver.manage().window().maximize();

        if (webDriver.getTitle().equals("My Store"))
            Report.pass("Application successfully launched.");
        else
            Report.pass("Application could not be launched.");
    }

    @And("^I am in Sign In page$")
    public void iAmInSignInPage() {
        WebElement signIn_Home,signInButton;
        signIn_Home= Driver.getElement("signIn_Home");

        if (signIn_Home.isDisplayed()) {
            signIn_Home.click();
        }

        signInButton= Driver.getElement("signInButton");
        if (signInButton.isDisplayed())
            Report.pass("User is on Sign in page");
        else
            Report.fail("User is not on sign in page");

        System.out.println("You are in sign in page.");
    }

    @When("^I enter email \"([^\"]*)\" in Create New Account section$")
    public void iEnterEmailInCreateNewAccountSection(String args0) throws InterruptedException {
        WebElement email_accountCreation, createAccountButton;
        registrationEmail= args0;

        synchronized (webDriver) {
            email_accountCreation= Driver.getElement("email_accountCreation");
            createAccountButton= Driver.getElement("createAccountButton");

            if (email_accountCreation.isDisplayed())    {
                email_accountCreation.sendKeys(args0);
                createAccountButton.click();
                Report.pass("Email ID entered: " + args0);
                webDriver.wait(5000);
            }
            else
                Report.fail("Email ID field could not be located");
        }
    }

    @And("^I enter valid account details$")
    public void iEnterValidAccountDetails(DataTable registrationData) {
        WebElement salutation_mr, salutation_mrs, firstName, lastName, password, dob_day, dob_month, dob_year,
                newsLetter, specialOffers, company, firstName1, lastName1,address1, address2, city, state, zipCode, country,
                additionalInfo, homePhone, mobilePhone, alias;

        synchronized (webDriver) {
            salutation_mr= Driver.getElement("reg_salutation_Mr");
            salutation_mrs= Driver.getElement("reg_salutation_Mrs");
            firstName = Driver.getElement("reg_firstName");
            lastName = Driver.getElement("reg_lastName");
            password = Driver.getElement("reg_password");
            dob_day = Driver.getElement("reg_dob_day");
            dob_month = Driver.getElement("reg_dob_month");
            dob_year = Driver.getElement("reg_dob_year");
            newsLetter = Driver.getElement("reg_newsLetter");
            specialOffers = Driver.getElement("reg_specialOffers");
            firstName1= Driver.getElement("reg_firstName1");
            lastName1= Driver.getElement("reg_lastName1");
            company = Driver.getElement("reg_Company");
            address1 = Driver.getElement("reg_address1");
            address2 = Driver.getElement("reg_address2");
            city = Driver.getElement("reg_city");
            state = Driver.getElement("reg_state");
            zipCode = Driver.getElement("reg_zipcode");
            country = Driver.getElement("reg_country");
            additionalInfo = Driver.getElement("reg_additionalInformation");
            homePhone = Driver.getElement("reg_HomePhone");
            mobilePhone = Driver.getElement("reg_MobilePhone");
            alias = Driver.getElement("reg_alias");

            //Inserting data into their respective fields
            data = registrationData.asMap(String.class, String.class);
            if (data.get("Salutation").equals("Mr."))
                salutation_mr.click();
            else if (data.get("Salutation").equals("Mrs."))
                salutation_mrs.click();
            else
                Report.fail("Wrong data in TITLE field.");

            Driver.sendKeys(firstName, data.get("FirstName"));
            Driver.sendKeys(lastName, data.get("LastName"));
            Driver.sendKeys(password, data.get("Password"));

            //Date Of Birth
            String[] dob = data.get("DOB").split("-");
            Driver.sendKeys(dob_day, dob[0]);
            Driver.sendKeys(dob_month, dob[1]);
            Driver.sendKeys(dob_year, dob[2]);

            //Newsletter field
            if (data.get("SignUp_NewsLetter").toUpperCase().equals("Y") && (!newsLetter.isSelected())) {
                newsLetter.click();
                if (newsLetter.isSelected())
                    Report.pass("Newsletter check box selected");
                else
                    Report.fail("Newsletter check box could not be selected");
            }

            //Special Offers field
            if (data.get("SpecialOffers").toUpperCase().equals("Y") && (!specialOffers.isSelected())) {
                specialOffers.click();
                if (specialOffers.isSelected())
                    Report.pass("Special Offer check box selected");
                else
                    Report.fail("Special Offer check box could not be selected");
            }

            if (firstName1.getAttribute("value").equals(""))
                Driver.sendKeys(firstName1, data.get("FirstName"));
            if (lastName1.getAttribute("value").equals(""))
                Driver.sendKeys(lastName1, data.get("LastName"));

            Driver.sendKeys(company, data.get("Company"));
            Driver.sendKeys(address1, data.get("Address_1"));
            Driver.sendKeys(address2, data.get("Address_2"));
            Driver.sendKeys(city, data.get("City"));
            Driver.sendKeys(state, data.get("State"));
            Driver.sendKeys(zipCode, data.get("ZipCode"));
            Driver.sendKeys(country, data.get("Country"));
            Driver.sendKeys(additionalInfo, data.get("AdditionalInfo"));
            Driver.sendKeys(homePhone, data.get("HomePhone"));
            Driver.sendKeys(mobilePhone, data.get("MobileNumber"));
            Driver.sendKeys(alias, data.get("Alias"));
        }
    }

    @And("^click on Register button$")
    public void clickOnRegisterButton() {
        WebElement registrationButton= Driver.getElement("registerButton");
        if (registrationButton.isDisplayed() && registrationButton.isEnabled()) {
            registrationButton.click();
            Report.pass("Clicked on registration button.");
        }
        else
            Report.fail("Unable to click on registration button.");
    }

    @And("^My Account page is opened$")
    public void myAccountPageIsOpened() {
        WebElement myAccount_heading= Driver.getElement("myAccount_heading");
        if (myAccount_heading.isDisplayed())
            Report.pass("My account page is opened.");
        else
            Report.fail("My account page could not be opened.");
    }

    @And("^I click on My Personal Information button$")
    public void iClickOnMyPersonalInformationButton() {
        WebElement myPersonalInfo = Driver.getElement("myPersonalInformationMenu");
        if (myPersonalInfo.isDisplayed() && myPersonalInfo.isEnabled()) {
            Report.pass("MY PERSONAL INFORMATION is clicked");
        }
        else
            Report.fail("MY PERSONAL INFORMATION could not be clicked");
    }

    @Then("^Your Personal Information page is opened$")
    public void yourPersonalInformationPageIsOpened()   {
        WebElement myPersonalInfoHeading = Driver.getElement("myPersonalInformationHeading");
        if (myPersonalInfoHeading.isDisplayed())
            Report.pass("PERSONAL INFORMATION page is opened");
        else
            Report.fail("PERSONAL INFORMATION page could not be opened");
    }

    @And("^correct personal information is displayed$")
    public void correctPersonalInformationIsDisplayed() {
        WebElement salutation_mr, salutation_mrs, firstName, lastName,emailAddress, dob_day, dob_month, dob_year;
        salutation_mr= Driver.getElement("reg_salutation_Mr");
        salutation_mrs= Driver.getElement("reg_salutation_Mrs");
        firstName = Driver.getElement("reg_firstName");
        lastName = Driver.getElement("reg_lastName");
        emailAddress = Driver.getElement("reg_email");
        dob_day = Driver.getElement("reg_dob_day");
        dob_month = Driver.getElement("reg_dob_month");
        dob_year = Driver.getElement("reg_dob_year");

        //verifySalutation
        if (data.get("salutation_mr").equals("Mr."))

        //verify firstName
        if (firstName.getAttribute("value").equals(data.get("FirstName")))
            Report.pass("FIRST NAME matches the input data");
        else
            Report.fail("FIRST NAME does not match the input data.");

        //verify lastName
        if (lastName.getAttribute("value").equals(data.get("LastName")))
            Report.pass("LAST NAME matches the input data");
        else
            Report.fail("LAST NAME does not match the input data.");

        //verify emailAddress
        if (emailAddress.getAttribute("value").equals(registrationEmail))
            Report.pass("EMAIL ID matches the input data");
        else
            Report.fail("EMAIL ID does not match the input data.");

        //verify date of birth - DAY
        String day = dob_day.getAttribute("value");
        String month = dob_month.getAttribute("value");
        String year = dob_year.getAttribute("value");
        if ((day + "-" + month + "+" + year).equals(data.get("DOB")))
            Report.pass("DOB matches the input data");
        else
            Report.fail("DOB does not match the input data.");
    }
}
