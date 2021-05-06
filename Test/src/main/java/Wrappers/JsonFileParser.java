package Wrappers;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.logging.LoggingHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonFileParser {
    public static String jsonFile = "config.json";

    JSONObject jsonObject;
    public JsonFileParser (String file)
    {
        parseJson(file);
    }

    public void parseJson (String file)
    {
        String jsonData = readFile(file);
        try
        {
         jsonObject = new JSONObject(jsonData);
        }
        catch (JSONException e)
        {
            //LoggingHandling.logError(e);
           // return null;
        }
    }
    //This can get parent node only , can't see Child
    public String GetValueOfParent_Node(String NodeName)
    {
       return jsonObject.getString(NodeName);
    }
    public String getVlueofNodes(String parent , String Child , String secondchild)
    {
          JSONObject parentNode;
          parentNode = (JSONObject) jsonObject.get(parent);
          parentNode = (JSONObject) parentNode.get(Child);
          return parentNode.getString(secondchild);
    }

    //To solve this problem and access child
    //Information/Job/Tester
    public String GetValueOFNode(String Parent )
    {
      String [] tree = Parent.split("/");
      int i = 1 ;
      JSONObject parentNode;
      parentNode = (JSONObject) jsonObject.get(tree[0]);

      while (i< tree.length-1)
      {
          parentNode = (JSONObject) parentNode.get(tree[i]);
          i++;
      }
      return parentNode.getString(tree[i]);
    }
    //more flexible and collect all thing
    public List<String> getValuesOf(String parent, String child) {
        JSONArray value = null;
        boolean isArray = false;
        String singleVal = null;
        Object ob = null;
        if (child == null) {
            try {
                ob = jsonObject.get(parent);
                if (ob instanceof JSONArray) {
                    value = new JSONArray(jsonObject.getJSONArray(parent).toString());
                    isArray = true;
                }
                else {
                    singleVal=  jsonObject.getString(parent);
                }
            } catch (JSONException e) {

               // LoggingHandling.logError(e);
               return null ;
            }
        } else {
            JSONObject parentNode;
            try {
                parentNode = (JSONObject) jsonObject.get(parent);
                ob = parentNode.get(child);
                if (ob instanceof JSONArray) {
                    value= new JSONArray(parentNode.getJSONArray(child).toString());
                    isArray = true;
                }
                else{
                    singleVal = parentNode.getString(child);
                }

            } catch (JSONException e) {
                System.out.println(e.getMessage());
                //LoggingHandling.logError(e);
                return null;
            }

        }
        List<String> list = new ArrayList<String>();
        if (isArray) {
            for (int i = 0; i < value.length(); i++) {
                try {
                    list.add(value.getString(i));
                } catch (JSONException e) {
                  //  LoggingHandling.logError(e);
                    return null;

                }
            }
        }
        else {
            if(singleVal !=null)
                list.add(singleVal);
        }
        return list;
    }

   //---------------------------------------------------------------

    public static String readFile(String FileName)
    {
        String result = "";
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(FileName));
            StringBuilder SB = new StringBuilder();
            String line = br.readLine();
            while (line != null)
            {
               SB.append(line);
               line = br.readLine();
            }
            result = SB.toString();
        }
        catch (Exception e)
        {
        //  LoggingHandling.logError(e);
        }
       return result;
    }







}
