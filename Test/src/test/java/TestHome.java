import POM.Home;
import POM.SelectDocument;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;

public class TestHome {
    Home home_obj ;
    SelectDocument Selectdocument_obj ;
    WebDriver driver ;

    @BeforeTest
    public void setUp()
   {
        //browserActions = new BrowserActions(driver);
       //BrowserActions.IntializeTheBrowser("Chrome");
       WebDriverManager.chromedriver().setup();
       driver = new ChromeDriver();
       driver.get("https://www.levelset.com/");
       driver.manage().window().maximize();
       home_obj =  new Home(driver);
       Selectdocument_obj = new SelectDocument(driver);
   }

   @AfterTest
    public void teardown()

   {
      driver.close();
   }


   @Test
    public void TestPreliminary_Notice()
   {
       home_obj.Navigate_to_Home();
       home_obj.Press_ON_CreateDocument();
       Selectdocument_obj.Validate_Clickable_Field("20-Day Preliminary Notice");
       Selectdocument_obj.CheckPriceFree("20-Day Preliminary Notice");
       //assertEquals(Selectdocument_obj.CheckPriceFree("20-Day Preliminary Notice"), true);
       Selectdocument_obj.Free_OR_NOtFree("Free","20-Day Preliminary Notice");
   }
   @Test
    public void TestIntent_to_Lien()
   {
       home_obj.Navigate_to_Home();
       home_obj.Press_ON_CreateDocument();
       Selectdocument_obj.Validate_Clickable_Field("Notice of Intent to Lien");
      // assertEquals(Selectdocument_obj.CheckPriceFree("Notice of Intent to Lien"), true);
       Selectdocument_obj.Free_OR_NOtFree("Free","Notice of Intent to Lien");

   }
    @Test
    public void Test_Lien_Bond_Claim()
    {
        home_obj.Navigate_to_Home();
        home_obj.Press_ON_CreateDocument();
        Selectdocument_obj.Validate_Clickable_Field("Lien / Bond Claim");
        assertEquals(Selectdocument_obj.Validate_unFree_Field("Lien / Bond Claim"),true);
       // Selectdocument_obj.Free_OR_NOtFree("NotFree","Lien / Bond Claim");
    }
    @Test
    public void Test_Lien_Waiver()
    {
      home_obj.Navigate_to_Home();
      home_obj.Press_ON_CreateDocument();
      Selectdocument_obj.Validate_Clickable_Field("Lien Waiver");
      assertEquals(Selectdocument_obj.CheckPriceFree("Lien Waiver"),true);
    }



}
