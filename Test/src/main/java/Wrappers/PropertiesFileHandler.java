package Wrappers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileHandler {

    static String FilenameAndPath ="config.properties";
    public static Properties ProFile = new Properties();

    static
    {
        FileInputStream FS = null ;

        try
        {
          FS = new FileInputStream(FilenameAndPath);
        }
       catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try
        {
            ProFile.load(FS);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
           try
           {
             FS.close();
           }
           catch (IOException e) {
               e.printStackTrace();
           }
        }
    }

    public static String getProperty(String Key_OR_Value)
    {
        try
        {
         return ProFile.getProperty(Key_OR_Value);
        }
        catch (Exception e)
        {
           return null;
        }
    }


}
