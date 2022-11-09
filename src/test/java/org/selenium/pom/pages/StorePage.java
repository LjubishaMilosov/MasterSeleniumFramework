package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class StorePage extends BasePage {
    private By searchField = By.id("woocommerce-product-search-field-0");
    private By searchBtn = By.cssSelector("button[value='Search']");
    private By title = By.cssSelector(".woocommerce-products-header__title.page-title");
    private By viewCartLink = By.cssSelector("a[title='View cart']");



    public StorePage(WebDriver driver) {
        super(driver);
    }
    public boolean isLoaded(){
       return wait.until(ExpectedConditions.urlContains("/store"));
    }

    public StorePage enterTextInSearchField(String txt){
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchField)).sendKeys(txt);
        return this;
    }

    public StorePage load(){
        load("/store");
        return  this;
    }

    public StorePage clickSearchBtn(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBtn)).click();
         return this;
    }

    public StorePage checkIfUrlIsChanged(){
        wait.until(ExpectedConditions.urlContains("Blue&post_type=product"));
        return this;
    }

    public String getTitle(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();

    }

    private
    By getAddToCartBtnElement(String productName){
        return By.cssSelector("a[aria-label='Add “"+ productName +"” to your cart']");
    }

    public StorePage clickAddToCartBtn(String productName){
         By addToCartBtn = getAddToCartBtnElement(productName);
         wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
        return this;
    }

    public CartPage clickViewCart(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(viewCartLink)).click();
        return new CartPage(driver);
    }
}
