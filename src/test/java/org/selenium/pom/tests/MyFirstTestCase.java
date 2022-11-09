package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyFirstTestCase extends BaseTest {

    @Test

    public void guestCheckOutUsingDirectBankTransfer() throws IOException {
        String searchFor = "Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);
       /*
        BillingAddress billingAddress = new BillingAddress().
        setFirstName("demo").
                setLastName("user").
                setAddressLineOne("San Francisco").
                setCity("San Francisco").
                setPostalCode("94188").
                setEmail("askomdch@gmail.com");
        */
        /*
        BillingAddress billingAddress = new BillingAddress("demo", "user", "San Francisco",
                "San Francisco", "94188", "askomdch@gmail.com");

         */
        //builder's pattern:
        StorePage storePage = new HomePage(getDriver()).
                load().
                navigateToStoreUsingMenu();
        storePage.isLoaded();
        storePage.enterTextInSearchField(searchFor).
                clickSearchBtn();
       Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchFor + "”");

       /*
       HomePage homePage = new HomePage(driver);
        StorePage storePage = homePage.navigateToStoreUsingMenu();
        storePage.
                enterTextInSearchField("Blue").
                clickSearchBtn();
        Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");
        */

        storePage.clickAddToCartBtn(product.getName());
        CartPage cartPage = storePage.clickViewCart();
        cartPage.isLoaded();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
        CheckoutPage checkoutPage = cartPage.
                checkout().
                setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                clickPlaceOrderBtn();
       Assert.assertEquals(checkoutPage.getNotice(),"Thank you. Your order has been received.");

    }

    @Test
    public void loginAndCheckOutUsingDirectBankTransfer() throws InterruptedException, IOException {
        String searchFor = "Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);
        User user = new User(ConfigLoader.getInstance().getUsername(), ConfigLoader.getInstance().getPassword());
        //builder's pattern:
        StorePage storePage = new HomePage(getDriver()).
                load().
                navigateToStoreUsingMenu().
                enterTextInSearchField(searchFor).
                clickSearchBtn();
       Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchFor + "”");



       /*
       HomePage homePage = new HomePage(driver);
        StorePage storePage = homePage.navigateToStoreUsingMenu();
        Thread.sleep(5000);
        storePage.
                enterTextInSearchField("Blue").
                clickSearchBtn();
        Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");
        */

        storePage.clickAddToCartBtn("Blue Shoes");
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), "Blue Shoes");
        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.clickHereToLoginLink();
        checkoutPage.
                login("Ljuc24","Ljuc24").
                enterFirstName("demo").
                enterLastName("user").
                enterBillingAddress("San Francisco").
                enterBillingCity("San Francisco").
                enterBillingPostCode("94188").
                enterBillingEmail("ljuc24@gmail.com").
                selectDirectBankTransfer().
                clickPlaceOrderBtn();




        Assert.assertEquals(checkoutPage.getNotice(),"Thank you. Your order has been received.");


    }


}
