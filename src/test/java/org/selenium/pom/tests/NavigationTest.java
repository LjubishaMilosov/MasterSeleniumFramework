package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {
    @Test
    public void NavigateFromHomeToStoreUsingMainMenu(){
        StorePage storePage = new HomePage(getDriver()).
                load().
                navigateToStoreUsingMenu();
        storePage.isLoaded();
        Assert.assertEquals(storePage.getTitle(), "Store");
    }


    @Test
    public void NavigateFromHomeToCartUsingMainMenu(){
        CartPage cartPage = new HomePage(getDriver()).
                load().
                navigateToCartUsingMenu();
        cartPage.isLoaded();
        Assert.assertEquals(cartPage.getTitle(), "Cart");
    }
}
