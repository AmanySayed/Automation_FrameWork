import POM.Home;
import POM.SelectDocument;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class TestHome3 {

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
    @Test(dataProvider = "GetDoc_Name" )
    public void TestFourField_Doc(String Doc_Name)
    {
      home_obj.Navigate_to_Home();
      home_obj.Press_ON_CreateDocument();
      Selectdocument_obj.Validate_Clickable_Field(Doc_Name);
      assertEquals(Selectdocument_obj.CheckPriceFree(Doc_Name),true);
    }
    @DataProvider(name = "GetDoc_Name")
    public Object[] GetDoc_Name()
    {
        return new Object[]
                {
                        "20-Day Preliminary Notice",
                        "Notice of Intent to Lien",
                        "Lien / Bond Claim",
                        "Lien Waiver"
                };
    }


}
