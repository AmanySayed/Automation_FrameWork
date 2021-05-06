package Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.testng.AssertJUnit.assertEquals;

public class WebUIActions {

    public WebDriver driver;

    public  WebUIActions(WebDriver driver)
    {
        this.driver=driver;
    }

    public void SetText(By element ,String Text , boolean clear )
    {
        WebElement element1  = WaitUntil(element,"presenceOfElement");
       try {
           if (clear) {
               element1.clear();
               element1.sendKeys(Text);

               String ActualValue = element1.getAttribute("value") == null ?
                       element1.getAttribute("innerHTML") == null ?  element1.getText() :
                               element1.getAttribute("innerHTML") :element1.getAttribute("value") ;
               assertEquals(ActualValue, Text);
           }
       }
       catch (Exception e)
       {
         Assert.fail("Could't Set Text");
       }
    }
    //you sure that the type of text is acutally text -->created by me --not sure if work correctly
     public WebElement GetText(By element)
     {
        try {
         WebElement element1 = (new WebDriverWait(driver, 6).
                 until(ExpectedConditions.presenceOfElementLocated(element)));
         element1.getText();
         return element1 ;
        }
       catch (Exception e)
       {
           return  null ;
       }
     }

    public WebElement WaitUntil(By element_Locator , String condition)
    {
        try
        {
            WebElement element = null ;
            switch (condition)
            {
                case "presenceOfElement":
                    element = (new WebDriverWait(driver,6).until(ExpectedConditions.presenceOfElementLocated(element_Locator)));
                    return element ;

                case "elementToBeClickable":
                    element = (new WebDriverWait(driver,6).until(ExpectedConditions.elementToBeClickable(element_Locator)));
                    return element ;

                default:
                    element = null;
                    Assert.fail("wrong condition");
            }
            return element;
        }
        catch (Exception e)
        {
            return null ;
        }

    }
}
