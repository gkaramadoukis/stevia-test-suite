package pageObjects;

import com.persado.oss.quality.stevia.selenium.core.WebComponent;

public class AmazonHomePage extends WebComponent {
    public enum AmazonHomePageLocators {

        TODAY_DEALS("xpath=.//div[@id='nav-xshop']/a[contains(text(),'Tod')]");

        private String componentLocator;

        AmazonHomePageLocators(String locator) {
            componentLocator = locator;
        }

        public String get() {
            return componentLocator;
        }

    }

    //method to click Today Deals Button in navbar
    public void clickTodayDeals(){
        controller().click(AmazonHomePageLocators.TODAY_DEALS.componentLocator);
    }

}
