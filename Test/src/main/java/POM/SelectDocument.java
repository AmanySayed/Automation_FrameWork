package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SelectDocument {
    Home Object_home =new Home(this.driver);
    String DocumentSearchSelector = "#document_search";
    String Free_Document = "//div[text()='%s']//..//span[text()='Free']";
    String not_Free_Field = "//div[text()='%s']//..//span[1]";
    String four_Field_Locator ="//div[@data-testid='document-tile']//../div[text()='%s']";
    String Tilte_in_Body_of_Fileds = "//div[@class='title']";
    WebDriver driver;
    public  SelectDocument(WebDriver driver)
    {
        this.driver=driver;
    }
    public void Validate_Clickable_Field(String text_Refer_to_field)
    {
       By Title_Locator = new By.ByXPath(Tilte_in_Body_of_Fileds);
        try
       {
           WebElement Element_Field = new WebDriverWait(driver,6).until(ExpectedConditions.presenceOfElementLocated(new By.ByXPath(String.format(four_Field_Locator,text_Refer_to_field))));
           WebElement Element_Tilte = new WebDriverWait(driver,2).until(ExpectedConditions.presenceOfElementLocated(Title_Locator));

           Element_Field.click();
           Assert.assertEquals(Element_Field.getText(),Element_Tilte.getText());

       }
       catch (Exception e)
       {
           Assert.fail("Couldn't select Document, Exception :");
       }

    }

    //Generlization of 4 locators By String.format
    public boolean CheckPriceFree(String text_Refer_to_field)
    {
      try
      {
        new WebDriverWait(driver,7).
                until(ExpectedConditions.presenceOfElementLocated(new By.ByXPath(String.format(Free_Document,text_Refer_to_field))));
        return  true ;
      }
      catch (Exception e)
      {
          return false ;
      }
    }
    public boolean Validate_unFree_Field (String text_Refer_to_field)
    {
        //By UNFree_Field_Locator = new By.ByXPath(not_Free_Field);
       try
       {
          new WebDriverWait(driver,6).until(ExpectedConditions.invisibilityOfElementLocated
                  (new By.ByXPath(String.format(String.valueOf(Free_Document),text_Refer_to_field))));

          return true ;
       }
       catch (Exception e)
       {
       // Assert.fail("not found Price in this field : ");
         return  false ;
       }
    }
    //------------------------------
    public void Free_OR_NOtFree(String Free_NOTFree , String text_Refer_to_field)
    {
       if(Free_NOTFree == "Free")
       {
           try
           {
              WebElement element = new WebDriverWait(driver,7).
                       until(ExpectedConditions.presenceOfElementLocated(new By.ByXPath(String.format(Free_Document,text_Refer_to_field))));
              element.getText();
              System.out.println(element.getText());
              Assert.assertEquals(element.getText(),"Free");
           }
           catch (Exception e)
           {
               Assert.fail("Not_Free");
           }
       }
       else
       {
           try
           {
               WebElement element = driver.findElement(new By.ByXPath(String.format(not_Free_Field,text_Refer_to_field)));
               new WebDriverWait(driver,7).until(ExpectedConditions.invisibilityOfElementLocated(new By.ByXPath(String.format(Free_Document,text_Refer_to_field))));
               element.getText();
               System.out.println(element.getText());
           }
           catch (Exception e)
           {
               Assert.fail("may be it's free doc");
           }
       }

    }


}
