import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class PropertyJsonReader {

    public static void PropertyJsonReader1(Properties[] propertieslist){
        int listindex=0;
        JSONParser processor = new JSONParser();
        try (Reader file = new FileReader("property.json")){
            JSONObject jsonfile = (JSONObject) processor.parse(file);
            JSONArray Land = (JSONArray) jsonfile.get("1");
            JSONArray RailRoad = (JSONArray) jsonfile.get("2");
            JSONArray Company = (JSONArray) jsonfile.get("3");
            for(Object i:Land){
                //You can reach items by using statements below:
                propertieslist[listindex].id=Integer.parseInt((String)((JSONObject)i).get("id"));
                propertieslist[listindex].name=(String)((JSONObject)i).get("name");
                propertieslist[listindex].cost=Integer.parseInt((String)((JSONObject)i).get("cost"));
                //And you can add these items to any data structure (e.g. array, linkedlist etc.
                listindex++;



            }

            for(Object i:RailRoad){
                propertieslist[listindex]=new Properties();
                //You can reach items by using statements below:
                propertieslist[listindex].id=Integer.parseInt((String)((JSONObject)i).get("id"));
                propertieslist[listindex].name=(String)((JSONObject)i).get("name");
                propertieslist[listindex].cost=Integer.parseInt((String)((JSONObject)i).get("cost"));
                //And you can add these items to any data structure (e.g. array, linkedlist etc.
                listindex++;
            }

            for(Object i:Company){
                propertieslist[listindex]=new Properties();
                //You can reach items by using statements below:
                propertieslist[listindex].id=Integer.parseInt((String)((JSONObject)i).get("id"));
                propertieslist[listindex].name=(String)((JSONObject)i).get("name");
                propertieslist[listindex].cost=Integer.parseInt((String)((JSONObject)i).get("cost"));
                listindex++;
            }

        } catch (IOException e){
            e.printStackTrace();
        } catch (ParseException e){
            e.printStackTrace();
        }
    }
    //You can add function(s) if you want
}