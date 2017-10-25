package coveros.getting_started;

import java.io.IOException;
import java.util.HashSet;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.coveros.selenified.Locator;
import com.coveros.selenified.Selenified;
import com.coveros.selenified.application.App;
import com.coveros.selenified.element.Element;

public class SampleTests extends Selenified {

    @BeforeClass(alwaysRun = true)
    public void beforeClass(ITestContext test) {
        // set the base URL for the tests here
        setTestSite(this, test, "https://www.google.com");
        // set the author of the tests here
        setAuthor(this, test, "Matt Grasberger\n<br/>matthew.grasberger@coveros.com");
        // set the version of the tests or of the software, possibly with a
        // dynamic check
        setVersion(this, test, "0.0.1");
    }

    @DataProvider(name = "google search terms", parallel = true)
    public Object[][] DataSetOptions() {
        return new Object[][] { new Object[] { "python" }, new Object[] { "perl" }, new Object[] { "bash" }, };
    }

    @Test(groups = { "sample" }, description = "A sample test to check a title")
    public void sampleTest() throws IOException {
        // grab our main app object
        App app = this.apps.get();
        // perform some actions
        app.azzert().titleEquals("Google");
        // verify no issues
        finish();
    }

    @Test(dataProvider = "google search terms", groups = { "sample" },
            description = "A sample test using a data provider to perform searches")
    public void sampleTestWDataProvider(String searchTerm) throws Exception {
        // grab our main app object
        App app = this.apps.get();
        // perform some actions
        app.newElement(Locator.NAME, "q").type(searchTerm);
        app.newElement(Locator.NAME, "q").type(Keys.ENTER);
        app.newElement(Locator.ID, "resultStats").waitFor().displayed();
        app.azzert().titleEquals(searchTerm + " - Google Search");
        // verify no issues
        finish();
    }
    
    @Test
    public void testAboutButton() throws Exception {
    	App app = this.apps.get();
    	app.newElement(Locator.LINKTEXT,"About").click();
    	//app.azzert().urlEquals("https://www.google.com/intl/en/about/?fg=1&utm_source=google.com&utm_medium=referral&utl_campaign=hp-header");
    	app.azzert().titleEquals("Our Latest | Google");
    	finish();
    }
    
    
    @Test
    public void testStoreButton() throws Exception {
    	App app = this.apps.get();
    	app.newElement(Locator.LINKTEXT, "Store").click();
    	//app.azzert().urlEquals("https://store.google.com/?utm_source=hp_header&utm_medium=google_oo&utm_campaign=GS100042");
    	app.azzert().titleEquals("Shop Pixel, Chromecast, Google Home and more at Google Store");
    	finish();
    }
    
    @Test
    public void testArtist() throws Exception {
    	App app = this.apps.get();
    	app.newElement(Locator.CLASSNAME, "_XIi").click();
    	//app.azzert().urlEquals("https://tv.youtube.com/welcome/?utm_source=hpp&utm_medium=hybrid&utm_campaign=ws_1024");
    	app.azzert().titleEquals("Meet the artist who photographed the world without leaving home | Google");
    	finish();
    }
    
    @Test
    public void testDropDown() throws Exception {
    	App app = this.apps.get();
    	app.newElement(Locator.LINKTEXT, "Settings").click();
    	app.newElement(Locator.LINKTEXT, "Search Help").click();
    	//app.azzert().urlEquals("https://support.google.com/websearch/?visit_id=1-636444718668472423-981266810&hl=en&rd=2#topic=3378866");
    	app.azzert().textPresent("Google Search Help");
    	finish();
    }
    @Test
    public void testHover() throws Exception {
    	App app = this.apps.get();
        app.newElement(Locator.ID, "gbqfbb").hover();
        Element feelingButton = app.newElement(Locator.XPATH, "//*[@id='tsf']/div[2]/div[3]/center/div");
        
        HashSet<String> lucky = new HashSet<String>();
        
        lucky.add("I'm Feeling Doodley");
        lucky.add("I'm Feeling Artisitc");
        lucky.add("I'm Feeling Hungry");
        lucky.add("I'm Feeling Puzzled");
        lucky.add("I'm Feeling Trendy");
        lucky.add("I'm Feeling Stellar");
        lucky.add("I'm Feeling Playful");
        lucky.add("I'm Feeling Wonderful");
        lucky.add("I'm Feeling Generous");
        lucky.add("I'm Feeling Curious");
        feelingButton.waitFor().displayed();
        System.out.println("'" + feelingButton.get().attribute("aria-label") + "'");
        Assert.assertTrue(lucky.contains(feelingButton.get().attribute("aria-label")));
        
        // verify no issues
        finish();
    }
    
    @Test
    public void testCart() throws Exception {
    	App app = this.apps.get();
    	app.goToURL("https://www.amazon.com/");
        app.newElement(Locator.ID, "twotabsearchtextbox").type("destiny 2");
        app.newElement(Locator.ID, "twotabsearchtextbox").type(Keys.ENTER);        
        app.newElement(Locator.XPATH, "//*[@id=\"result_0\"]/div/div/div/div[2]/div[2]/div[1]/a/h2").click();
        app.newElement(Locator.ID, "add-to-cart-button").click();
        app.newElement(Locator.ID, "nav-cart-count").click();
        app.azzert().textPresent("Destiny 2 - PC Standard Edition");
    
    }
    
}
