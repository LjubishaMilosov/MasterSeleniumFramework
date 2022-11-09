package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class HomePage extends BasePage {
   private By storeMenuLink = By.cssSelector("#menu-item-1227 > a");
    private By cartMenuLink = By.cssSelector("#ast-site-header-cart > div.ast-site-header-cart-li > a > div > span");

    public HomePage(WebDriver driver) {

        super(driver);
    }
    public HomePage load(){
        load("/");
        wait.until(ExpectedConditions.titleContains("AskOmDch"));
        return this;
    }

    public StorePage navigateToStoreUsingMenu(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(storeMenuLink)).click();
        return new StorePage(driver);
    }
    public CartPage navigateToCartUsingMenu(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartMenuLink)).click();
        return new CartPage(driver);
    }
}
