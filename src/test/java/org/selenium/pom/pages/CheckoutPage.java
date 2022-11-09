package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;

public class CheckoutPage extends BasePage {

    private By firstNameFld = By.id("billing_first_name");
    private By lastnameFld = By.id("billing_last_name");
    private By addressLineOneFld = By.id("billing_address_1");
    private By cityFld = By.id("billing_city");
    private By postalCodeFld = By.id("billing_postcode");

    private By emailFld = By.id("billing_email");
    private By placeOrderBtn = By.id("place_order");
    private By successNotice = By.cssSelector(".woocommerce-notice");

    private By loginLink = By.className("showlogin");
    private By userNameField = By.id("username");
    private By passWord = By.id("password");
    private By loginBtn = By.name("login");
    private  By overlay = By.cssSelector(".blockUI.blockOverlay");
    private By countryDropDown = By.id("billing_country");
    private By stateDropDown = By.id("billing_state");
    private By directBankTransferRadioBtn = By.id("payment_method_bacs");

    private final By productName = By.cssSelector("td[class='product-name']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage load(){
        load("/checkout/");
        return this;
    }

    public CheckoutPage enterFirstName(String firstname){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameFld));
        e.clear();
        e.sendKeys(firstname);
        return this;
    }
    public CheckoutPage enterLastName(String lastName){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(lastnameFld));
        e.clear();
        e.sendKeys(lastName);
        return this;
    }

    public CheckoutPage selectCountry(String countryName){
        Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(countryDropDown)));
        select.selectByVisibleText(countryName);
        return this;
    }

    public CheckoutPage enterBillingAddress(String addressLineOne){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(addressLineOneFld));
        e.clear();
        e.sendKeys(addressLineOne);
        return this;
    }

    public CheckoutPage enterBillingCity(String city){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(cityFld));
        e.clear();
        e.sendKeys(city);
        return this;
    }

    public CheckoutPage selectState(String stateName){
        Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(stateDropDown)));
        select.selectByVisibleText(stateName);
        return this;
    }

    public CheckoutPage enterBillingPostCode(String postCode){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeFld));
        e.clear();
        e.sendKeys(postCode);
        return this;
    }
    public CheckoutPage enterBillingEmail(String email){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(emailFld));
        e.clear();
        e.sendKeys(email);
        return this;
    }
    public CheckoutPage setBillingAddress(BillingAddress billingAddress){
        return  enterFirstName(billingAddress.getFirstName()).
                enterLastName(billingAddress.getLastName()).
                selectCountry(billingAddress.getCountry()).
                enterBillingAddress(billingAddress.getAddressLineOne()).
                enterBillingCity(billingAddress.getCity()).
                selectState(billingAddress.getState()).
                enterBillingPostCode(billingAddress.getPostalCode()).
                enterBillingEmail(billingAddress.getEmail());
    }
    public CheckoutPage clickPlaceOrderBtn(){
        waitForOverlaysToDisappear(overlay);
        driver.findElement(placeOrderBtn).click();
        return this;
    }
    public String getNotice(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successNotice)).getText();

    }
    public CheckoutPage clickHereToLoginLink() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginLink)).click();
        return this;
    }
        public CheckoutPage enterUserName(String username){
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameField)).sendKeys(username);
        return this;
        }
    public CheckoutPage enterPassword(String password){
         wait.until(ExpectedConditions.visibilityOfElementLocated(passWord)).sendKeys(password);
        return this;
    }
    public CheckoutPage clickLoginBtn() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginBtn)).click();
        return this;
    }
    // functional page object/functional method:
    public CheckoutPage login( String username, String password){
        return enterUserName(username).
               enterPassword(password).
                clickLoginBtn();
    }
    public CheckoutPage login(){
        return clickLoginBtn();
    }

    public CheckoutPage selectDirectBankTransfer(){
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(directBankTransferRadioBtn));
        if(!e.isSelected()){
            e.click();
        }
        return this;
    }

    public String getproductname(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
    }
    }

