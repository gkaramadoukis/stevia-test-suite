package pageObjects;

import com.persado.oss.quality.stevia.selenium.core.WebComponent;

import java.text.MessageFormat;

public class AmazonTodayDealsPage extends WebComponent {

    private static int index = 3;

    public static int getIndex() {
        return index;
    }

    public static void setIndex(int index) {
        AmazonTodayDealsPage.index = index;
    }

    public enum AmazonTodayDealsPageLocators{
        SPINNER("xpath=.//div[@id='attach-add-to-cart-message-spinner']"),
        NTH_ITEM("xpath=.//div[@id='''widgetContent''']/div[{0}]/div/div/div/a"),
        ITEM_PRICE("xpath=.//div[@class='a-row a-spacing-top-small huc-v2-subcart a-size-small']/div/span/span[contains(@class, 'color')]"),
        ADD_TO_CART_BUTTON("xpath=.//span[@id='submit.add-to-cart-announce']/parent::span"),
        GO_TO_CART_BUTTON("xpath=.//a[@id='nav-cart']"),
        CLOSE_SIDELINK_BUTTON("xpath=.//a[@id='attach-close_sideSheet-link']"),
        TODAY_DEALS("xpath=.//div[@id='nav-xshop']/a[contains(text(),'Tod')]");


        private String componentLocator;

        AmazonTodayDealsPageLocators(String locator){
            componentLocator = locator;
        }

        public String get(){
            return componentLocator;
        }
    }

    public String getItemPrice(){
        if(controller().isComponentVisible(AmazonTodayDealsPageLocators.CLOSE_SIDELINK_BUTTON.componentLocator)){
            controller().click(AmazonTodayDealsPageLocators.CLOSE_SIDELINK_BUTTON.componentLocator);
        }
        return controller().getText(AmazonTodayDealsPageLocators.ITEM_PRICE.componentLocator);
    }

    public void clickAddToCard(){
        controller().click(AmazonTodayDealsPageLocators.ADD_TO_CART_BUTTON.componentLocator);
    }

    public void clickGoToCard() {
        controller().waitForElementInvisibility(AmazonTodayDealsPageLocators.SPINNER.componentLocator);
        if(!controller().isComponentNotPresent(AmazonTodayDealsPageLocators.CLOSE_SIDELINK_BUTTON.componentLocator)){
            controller().click(AmazonTodayDealsPageLocators.CLOSE_SIDELINK_BUTTON.componentLocator);
        }
        controller().click(AmazonTodayDealsPageLocators.GO_TO_CART_BUTTON.componentLocator);
    }

    public boolean isCartButtonDisplayed(){
        return controller().isComponentVisible(AmazonTodayDealsPageLocators.ADD_TO_CART_BUTTON.componentLocator);
    }

    public void selectNextProductIfSoldOut(){
        controller().click(MessageFormat.format(AmazonTodayDealsPageLocators.NTH_ITEM.componentLocator, index));
        while(!isCartButtonDisplayed()){
            controller().click(AmazonTodayDealsPageLocators.TODAY_DEALS.componentLocator);
            setIndex(index+1);
            controller().click(MessageFormat.format(AmazonTodayDealsPageLocators.NTH_ITEM.componentLocator, index));
        }
    }



}
