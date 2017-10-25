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

public class AmazonPageTest extends Selenified {
	
	ThreadLocal<AmazonPage> amazon = new ThreadLocal<AmazonPage>();

    @BeforeClass(alwaysRun = true)
    public void beforeClass(ITestContext test) {
        // set the base URL for the tests here
        setTestSite(this, test, "https://www.amazon.com");
        // set the author of the tests here
        setAuthor(this, test, "Arya Atighehchian\n<br/>arya.atighehchian@coveros.com");
        // set the version of the tests or of the software, possibly with a
        // dynamic check
        setVersion(this, test, "0.0.1");
    }

   

    @BeforeMethod(alwaysRun = true)
    public void setUpPage() {
    	amazon.set(new AmazonPage(this.apps.get()));
    }

    @Test(groups = { "amazon" }, description = "A sample test to check cart")
    public void testCart() throws Exception {
    	
    	amazon.get().search("Destiny 2");
    	amazon.get().goToItem("//*[@id=\"result_0\"]/div/div/div/div[2]/div[2]/div[1]/a/h2");
    	amazon.get().addToCart();
    	amazon.get().goToCart();
    	amazon.get().assertText("Destiny 2 - PC Standard Edition");
  
        finish();
    
    }
    
}
