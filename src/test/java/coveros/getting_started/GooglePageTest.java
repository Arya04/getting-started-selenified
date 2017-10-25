package coveros.getting_started;

import java.io.IOException;
import java.util.HashSet;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.coveros.selenified.Locator;
import com.coveros.selenified.Selenified;
import com.coveros.selenified.application.App;
import com.coveros.selenified.element.Element;

public class GooglePageTest extends Selenified {
	
	ThreadLocal<GooglePage> google = new ThreadLocal<GooglePage>();

    @BeforeClass(alwaysRun = true)
    public void beforeClass(ITestContext test) {
        // set the base URL for the tests here
        setTestSite(this, test, "https://www.google.com");
        // set the author of the tests here
        setAuthor(this, test, "Arya Atighehchian\n<br/>arya.atighehchian@coveros.com");
        // set the version of the tests or of the software, possibly with a
        // dynamic check
        setVersion(this, test, "0.0.1");
    }

   

    @BeforeMethod(alwaysRun = true)
    public void setUpPage() {
    	google.set(new GooglePage(this.apps.get()));
    }

    @Test(groups = { "google" }, description = "A sample test to check google searches")
    public void testSearch() throws Exception {
    	google.get().search("bmw");
    	google.get().assertSearch("bmw");
    	App app = this.apps.get();
    	app.wait(2.5);
        finish();
    
    }
    
    @Test
    public void testDropDown() throws Exception {
    	google.get().goToDropDown();
    	google.get().assertText("Google Search Help");
    	finish();
    }
    
    @Test
    public void testStore() throws Exception {
    	google.get().goToStore();
    	google.get().assertTitle("Shop Pixel, Chromecast, Google Home and more at Google Store");
    	finish();
    }
    @Test
    public void testAbout() throws Exception {
    	google.get().goToAbout();
    	google.get().assertTitle("Our Latest | Google");
    }
    
}
