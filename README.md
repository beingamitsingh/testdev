# testdev
Test assignment for TestDevLab

# notes
1. The project contains config.properties file (containing artifact details, application URL, etc)
2. The project has an object repository (objects.xlsx) which contains all objects and their respective locators.
   The approach has been used instead of POM.
3. 'Extent reports' are used as a reporting tool.
4. Two scenarios have been covered - "Account creation" and "Adding item to the cart"
5. Data has been handled via .feature files instead of an excel sheet due to time constraint.
6. However, the framework can be expanded to fulfill a more data-driven approach.
7. Screenshot in case of failed steps have been included.
8. TestNGCucumberRunner has been used in Runner class.
9. Negative testcases (with invalid data) have not been covered in validation as priority was given
   to achievement of end-to-end flow of scenarios.
10. Latest test reports are stored at src/test/reports/

#steps to execute the project
STEP 1: Clone the project from Github on local machine.
STEP 2: Import the solution into IntelliJ as maven project.
STEP 3: Press "Run".

#Pre-requisites
1. Java 8 should be installed.
