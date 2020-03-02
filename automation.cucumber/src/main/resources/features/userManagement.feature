Feature: User Management Section

  @userAccountCreation
  Scenario: Account creation
    Given application is launched
    And I am in Sign In page
    When I enter email "amit55@automation.com" in Create New Account section
    And I enter valid account details
      |Salutation   |Mr.  |
      |FirstName    |Amit |
      |LastName     |Singh  |
      |Password     |password  |
      |DOB          |12-July-1995  |
      |SignUp_NewsLetter  |N  |
      |SpecialOffers      |Y  |
      |Company      |testdevlabs  |
      |Address_1    |432  |
      |Address_2    |Annelinn  |
      |City         |Phoenix  |
      |State        |Arizona  |
      |ZipCode      |85014  |
      |Country      |United States of America  |
      |AdditionalInfo     |N/A  |
      |HomePhone    |  |
      |MobileNumber |+17865509876  |
      |Alias        |My address  |
    And click on Register button
    And My Account page is opened
    And I click on My Personal Information button
    Then Your Personal Information page is opened
    And correct personal information is displayed