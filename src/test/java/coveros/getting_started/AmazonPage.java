package coveros.getting_started;

import com.coveros.selenified.Locator;
import com.coveros.selenified.application.App;
import com.coveros.selenified.element.Element;
import org.openqa.selenium.Keys;


public class AmazonPage {
	private Element searchBox;
	App app;
	
	public AmazonPage(App app) {
		searchBox = app.newElement(Locator.ID, "twotabsearchtextbox");
		this.app = app;
		
	}
	public void search(String searchTerm) {
		searchBox.type(searchTerm);
		searchBox.type(Keys.ENTER);
	}
	public void goToItem(String xPath) {
		Element item = app.newElement(Locator.XPATH, xPath);
		item.click();
		
		
	}
	public void addToCart() {
		Element cart = app.newElement(Locator.ID,"add-to-cart-button");
		cart.click();
		
	}
	public void goToCart() {
		Element goToCart = app.newElement(Locator.ID, "nav-cart-count");
		goToCart.click();
		
		
	}
	public void assertText(String text) {
		app.azzert().textPresent(text);
	}
	
}
