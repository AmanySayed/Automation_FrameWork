package Actions;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserActions {

    public static  WebDriver driver;
    public static WebDriver IntializeTheBrowser(String Browser) {
        try {
            if (Browser == "Chrome") {
                driver = new ChromeDriver();
                driver.get("https://www.levelset.com/");
                return driver;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void MaxmizeScreen()
    {
        driver.manage().window().maximize();
    }
    public void CloseDriver()
    {
       driver.close();
    }


}