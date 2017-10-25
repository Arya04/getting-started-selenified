package coveros.getting_started;

import com.coveros.selenified.Locator;
import com.coveros.selenified.application.App;
import com.coveros.selenified.element.Element;
import org.openqa.selenium.Keys;

public class GooglePage {
	private Element searchBox;
	private Element about;
	private Element store;
	private Element settings;
	private Element searchHelp;
	App app;
	
	public GooglePage(App app) {
		searchBox = app.newElement(Locator.NAME, "q");
		about = app.newElement(Locator.LINKTEXT, "About");
		store = app.newElement(Locator.LINKTEXT, "Store");
		settings = app.newElement(Locator.LINKTEXT, "Settings");
		searchHelp = app.newElement(Locator.LINKTEXT, "Search Help");
		this.app = app;
		
	}
	public void search(String term) {
		searchBox.type(term);
		searchBox.type(Keys.ENTER);
	}
	public void assertSearch(String term) {
		searchBox.assertContains().value(term);
	}
	public void assertText(String text) {
		app.azzert().textPresent(text);
	}
	public void assertTitle(String text) {
		app.azzert().titleEquals(text);
	}
	public void goToDropDown() {
		settings.click();
		searchHelp.click();
	}
	public void goToStore() {
		store.click();
	}
	public void goToAbout() {
		about.click();
	}
	
}
