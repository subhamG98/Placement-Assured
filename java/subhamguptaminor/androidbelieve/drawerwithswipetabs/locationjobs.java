package subhamguptaminor.androidbelieve.drawerwithswipetabs;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


public class locationjobs extends ActionBarActivity {

    String myJSON;
    private static final String TAG_RESULTS="result";
    //Declaring an Spinner
    private Spinner spinner;

    //An ArrayList for Spinner Items
    private ArrayList<String> locations;

    //JSON Array
    private JSONArray result;

    //TextViews to display details
    private TextView textViewLocation;
    //JSON URL
    public static final String DATA_URL = "http://hello45.esy.es/locations.php";

    //Tags used in the JSON String
//    public static final String TAG_ROLES = "location";

    //JSON array name
    public static final String JSON_ARRAY = "result";








    private static final String TAG_RESULTS1="result1";
    private static final String MY_TAG = "the_custom_message";
    private static final String TAG_TITLE = "title";
    private static final String TAG_SALARY = "salary";
    private static final String TAG_LOCATION = "location";
    private static final String TAG_SKILLS = "skills";
//    private static final String TAG_ROLE = "location";


    JSONArray peoples = null;
    JSONArray peoples1 = null;
    ArrayList<HashMap<String, String>> personList;
    ArrayList<HashMap<String, String>> personList1;

    public ArrayAdapter<String> adapter2;
    public ArrayAdapter<String> adapter1;

    ListView list;


    TextView textView3;
    String companyname;
 //   Button button8;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locationjobs);
        textView3=(TextView)findViewById(R.id.textView3);
        personList1 = new ArrayList<HashMap<String, String>>();
   //     button8=(Button)findViewById(R.id.display);
        //getData2();
        list = (ListView)findViewById(R.id.listViewLocations);
        //Initializing the ArrayList
        locations = new ArrayList<String>();
        textViewLocation=(TextView)findViewById(R.id.textViewLocation);
        textViewLocation.setText(MapsActivity.nearest);

        //Initializing Spinner
        //spinner = (Spinner)findViewById(R.id.spinner);

        //Adding an Item Selected Listener to our Spinner
        //As we have implemented the class Spinner.OnItemSelectedListener to this class iteself we are passing this to setOnItemSelectedListener
        //spinner.setOnItemSelectedListener(this);

        //Initializing TextViews
       // textViewLocation = (TextView) findViewById(R.id.textViewLocation);

        //This method will fetch the data from the URL
        ///  getData2();




        Log.i("subham", "get data 0");


                        personList = new ArrayList<HashMap<String, String>>();


                        companyname = textViewLocation.getText().toString().toLowerCase();

                        companyname=MapsActivity.nearest;

                        Log.i("new compnay",""+companyname);
                        //companyname = companyname.replace(" ", "-");
                        getData();


 }




    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {


            @Override
            protected String doInBackground(String... params) {
                Log.i("subham", "get data 1");
                Log.i("subham", "get oye company 1"+companyname);
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                Log.i("subham", "get oye company 1"+companyname);
                String url="http://hello45.esy.es/bylocation.php?joblocation="+companyname;
                Log.i("subham url","dhd "+url);
                HttpPost httppost = new HttpPost(url);
                Log.i("subham", "get oye data 1"+httppost);
                Log.i("subham", "get oye company 1" + companyname);
                // Depends on your web service
                httppost.setHeader("Content-type", "application/json");

                InputStream inputStream = null;
                String result = null;

                try {
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();
                    Log.i("subham", "get data 2");
                    inputStream = entity.getContent();
                    // json is UTF-8 by default
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();
                    Log.i("subham", "get data 3");
                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                        Log.i("subham", "get data 4");
                    }
                    result = sb.toString();
                } catch (Exception e) {
                    // Oops
                }
                finally {
                    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
                }
                Log.i("subham", "Result of question" + result);

                return result;

            }

            @Override
            protected void onPostExecute(String result){
                myJSON=result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }



    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);
                String title = c.getString(TAG_TITLE);
                String salary = c.getString(TAG_SALARY);
                String location = c.getString(TAG_LOCATION);
                String skills = c.getString(TAG_SKILLS);

                HashMap<String,String> persons = new HashMap<String,String>();

                persons.put(TAG_TITLE,title);
                persons.put(TAG_SALARY,salary);
                persons.put(TAG_LOCATION,location);
                persons.put(TAG_SKILLS,skills);

                personList.add(persons);
            }

            ListAdapter adapter2 = new SimpleAdapter(
                    locationjobs.this, personList, R.layout.listview_jobs,
                    new String[]{TAG_TITLE,TAG_SALARY,TAG_LOCATION,TAG_SKILLS},
                    new int[]{ R.id.title,R.id.salary,R.id.location,R.id.skills}
            );


            list.setAdapter(adapter2);
            Toast.makeText(this, "Updating", Toast.LENGTH_SHORT).show();




        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
