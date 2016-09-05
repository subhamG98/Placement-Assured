package subhamguptaminor.androidbelieve.drawerwithswipetabs;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class GetAllImages6 {

    public static String[] title;
    public static String[] salary;
    public static String[] location;
    public static String[] skills;

    public static final String JSON_ARRAY="result";
    private static final String TAG_TITLE = "title";
    private static final String TAG_SALARY = "salary";
    private static final String TAG_LOCATION = "location";
    private static final String TAG_SKILLS = "skills";


    public static ArrayList<WorldPopulation5> arraylist = new ArrayList<WorldPopulation5>();
    ListViewAdapter5 adapter1;
    ListView list1;
    private String json;
    private JSONArray urls;

    public GetAllImages6(String json){
        this.json = json;
        try {
            JSONObject jsonObject = new JSONObject(json);
            urls = jsonObject.getJSONArray(JSON_ARRAY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }




    public void getAllImages() throws JSONException {

        title = new String[urls.length()];
        salary = new String[urls.length()];
        location = new String[urls.length()];
        skills = new String[urls.length()];

        for(int i=0;i<urls.length();i++){
            title[i] = urls.getJSONObject(i).getString(TAG_TITLE);
            salary[i] = urls.getJSONObject(i).getString(TAG_SALARY);
            location[i] = urls.getJSONObject(i).getString(TAG_LOCATION);
            skills[i] = urls.getJSONObject(i).getString(TAG_SKILLS);


            JSONObject jsonObject = urls.getJSONObject(i);
            WorldPopulation5 wp = new WorldPopulation5(title[i],salary[i],location[i],skills[i]);
            arraylist.add(wp);
        }
    }
}