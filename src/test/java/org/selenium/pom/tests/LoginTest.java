package org.selenium.pom.tests;

import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.FakerUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {


    @Test
    public void loginDuringCheckout() throws IOException, InterruptedException {


        String userName = "demo" + new FakerUtils().generateRandomNumber();
        User user = new User().
                setUsername(userName).
                setPassword("demopwd").
                setEmail(userName + "@askomdch.com");
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);

        CartApi cartApi = new CartApi();
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);


        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        Thread.sleep(5000);
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage.load();
        Thread.sleep(5000);
        checkoutPage.clickHereToLoginLink().
                login("demo","demopwd");
             //   enterFirstName("demo").
              //  enterLastName("user").
             //   enterBillingAddress("San Francisco").
              //  enterBillingCity("San Francisco").
              //  enterBillingPostCode("94188");
                      Thread.sleep(5000);
        Assert.assertTrue(checkoutPage.getproductname().contains(product.getName()));
    }
}
