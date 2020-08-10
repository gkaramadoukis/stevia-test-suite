package tests;

import com.persado.oss.quality.stevia.annotations.Preconditions;
import com.persado.oss.quality.stevia.selenium.core.SteviaContext;
import com.persado.oss.quality.stevia.spring.SteviaTestBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import pageObjects.AmazonCartPage;
import pageObjects.AmazonHomePage;
import pageObjects.AmazonTodayDealsPage;

import static com.persado.oss.quality.stevia.selenium.core.controllers.SteviaWebControllerFactory.LOG;


public class VerifyProductPrice extends SteviaTestBase {

    String productPriceCart;

    @Autowired
    AmazonHomePage homePage;

    @Autowired
    AmazonCartPage cartPage;

    @Autowired
    AmazonTodayDealsPage dealsPage;

    @Test(priority = 1)
    @Preconditions({"getProductPrice"})
    public void executePreconditions(){
    }



    @Test(dependsOnMethods = {"executePreconditions"}, priority = 2)
    public void verifyProductPrice() throws InterruptedException {
        dealsPage.clickGoToCard();
        LOG.info("User navigates to the cart");
        cartPage.verifyProductPrice(cartPage.getItemPriceCart(), productPriceCart);
    }


    public void getProductPrice(){
        SteviaContext.getWebController().navigate("https://amazon.com");
        LOG.info("User navigation to https://amazon.com");

        homePage.clickTodayDeals();
        LOG.info("User clicked today's deals");

        dealsPage.selectNextProductIfSoldOut();
        LOG.info("User browses among items");

        dealsPage.clickAddToCard();
        LOG.info("User adds the item to cart");

        productPriceCart = dealsPage.getItemPrice();

        LOG.info("Item price is: " + productPriceCart);
    }
}
