package pageObjects;

import com.persado.oss.quality.stevia.selenium.core.SteviaContext;
import com.persado.oss.quality.stevia.selenium.core.WebComponent;

public class AmazonCartPage extends WebComponent {

    public enum AmazonCartPageLocators{
        ITEM_PRICE("xpath=.//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold']");

        private String componentLocator;

        AmazonCartPageLocators(String locator){
            componentLocator = locator;
        }

        public String get(){
            return componentLocator;
        }
    }

    public String getItemPriceCart(){

        return controller().getText(AmazonCartPageLocators.ITEM_PRICE.componentLocator);
    }

    public void verifyProductPrice(String s1, String s2){
        SteviaContext.verify().equal(s1,s2);
    }
}
