package POM;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.AssertJUnit.assertTrue;

public class Home{

    WebDriver driver ;
    String Home_URL = "https://www.levelset.com/";
    String Validate_HomePage = "//h2[text()='Payment Help is Here']";
    String CreateDocuMent = "//a[text()='Create a Document ']";
    //Constroctor to links pages
    public Home(WebDriver driver)
    {
        this.driver=driver ;
    }
    public void Navigate_to_Home()
    {
      driver.get(Home_URL);
      By Element = new By.ByXPath(Validate_HomePage);
      assertTrue( First_Validate_ON_Element(Element,"Presence") );
    }
    public void Press_ON_CreateDocument()
    {
     By Element = new By.ByXPath(CreateDocuMent);
       if(Second_Validate_ON_Element(Element,ExpectedConditions.elementToBeClickable(Element)))
       {
         driver.findElement(Element).click();
         By DocumentSearchLocator = new By.ByCssSelector(new SelectDocument(driver).DocumentSearchSelector);
         assertTrue(First_Validate_ON_Element(DocumentSearchLocator,"Presence"));

       }
    }

    public boolean First_Validate_ON_Element(By element , String S)
    {
        ExpectedCondition<WebElement> X = null;
        switch(S)
        {
            case("Presence"):
             X = ExpectedConditions.presenceOfElementLocated(element);
             break;
            case ("Clickable"):
                X=ExpectedConditions.elementToBeClickable(element);
             break;
        }
        try {
           if(X!=null)
           new WebDriverWait(driver,7).until(X);
           return true;
        }
        catch (Exception e)
        {
           return false;
        }
    }
    public boolean Second_Validate_ON_Element(By element , ExpectedCondition<WebElement> s)
    {
      try
      {
          new WebDriverWait(driver,8).until(s);
          return true;
      }
      catch (Exception e)
           {
             return false ;
           }
    }

    //Day2-->Fun that validate on button-->more more Generic
    public void click_on_btn(By element , By ExpectedElement)
    {
        if(First_Validate_ON_Element(element,"Clickable"))
        {
            driver.findElement(element).click();
        }
        First_Validate_ON_Element(ExpectedElement,"Presence");

    }
    public void Press_ON_CreateDocument2()
    {
       By elemnet = new By.ByXPath(CreateDocuMent);
       By ExpectedElement = new By.ByCssSelector(new SelectDocument(driver).DocumentSearchSelector);
       click_on_btn(elemnet,ExpectedElement);
    }


}
