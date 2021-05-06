import Wrappers.JsonFileParser;
import Wrappers.PropertiesFileHandler;
import Wrappers.Read_JSON_File;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestWrapper {
    @Test
    public void Assert_Name()
    {
        Assert.assertEquals(PropertiesFileHandler.getProperty("Name"),"Amany");
        Assert.assertEquals(PropertiesFileHandler.getProperty("JOB"),"Quality Assurance");
    }
    @Test
    public void TestJson()
    {
      Assert.assertEquals(new JsonFileParser(JsonFileParser.jsonFile).GetValueOfParent_Node("Name").toString(),"NoRa");
    }

    @Test
    public void TestJson2()
    {
        Assert.assertEquals(new JsonFileParser(JsonFileParser.jsonFile).GetValueOFNode("Information/Name2").toString(),"Amany");
    }

   /* @Test
    public void TestJson3()
    {
        LoggingHandling.logger.info(new JsonFileParser(JsonFileParser.jsonFile).getValuesOf("Information","JOb").toString());
    }
   */


}
