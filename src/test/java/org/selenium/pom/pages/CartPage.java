package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class CartPage extends BasePage {

    private By productName = By.cssSelector("td[class='product-name'] a");
    private By checkoutBtn = By.cssSelector(".checkout-button");
    private By cartHeading = By.cssSelector(".has-text-align-center");
    private By title = By.cssSelector("#post-1220 > div > div > div > h1");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public Boolean isLoaded(){
        return wait.until(ExpectedConditions.textToBe(cartHeading, "Cart"));
    }

    public String getTitle(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();

    }

    public String getProductName(){
       return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();

    }

    public CheckoutPage checkout(){
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
        return  new CheckoutPage(driver);
    }
}
