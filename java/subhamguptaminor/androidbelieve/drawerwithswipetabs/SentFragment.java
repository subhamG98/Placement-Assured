package subhamguptaminor.androidbelieve.drawerwithswipetabs;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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

public class SentFragment extends Fragment implements Spinner.OnItemSelectedListener{
    View v;
    String myJSON;
    private static final String TAG_RESULTS="result";
    //private static final String TAG_RESULTS1="result1";
    private static final String MY_TAG = "the_custom_message";
    private static final String TAG_DETAIL = "detail";
    private static final String TAG_COMPANY_NAME = "companyname";
    private static final String TAG_COMPANYNAME = "companyname";

    public static final String DATA_URL = "http://hello45.esy.es/selectcompanyname.php";

    //Tags used in the JSON String


    //JSON array name
    public static final String JSON_ARRAY = "result1";
    JSONArray peoples = null;
    JSONArray peoples1 = null;
    ArrayList<HashMap<String, String>> personList;
    ArrayList<HashMap<String, String>> personList1;

    public ArrayAdapter<String> adapter;
    public ArrayAdapter<String> adapter1;

    ListView list;


    TextView textView3;
    String companyname;
    Button button8;
    Button button_data;


    //Declaring an Spinner
    private Spinner spinner;

    //An ArrayList for Spinner Items
    private ArrayList<String> companies;

    //JSON Array
    private JSONArray results;

    //TextViews to display details
    private TextView textViewCompanyName;








    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.sent_layout,container,false);

        HorizontalListView listview = (HorizontalListView) v.findViewById(R.id.listview);
        listview.setAdapter(mAdapter);

        textView3=(TextView)v.findViewById(R.id.textView3);
        personList1 = new ArrayList<HashMap<String, String>>();
       button8=(Button)v.findViewById(R.id.button8);
//        button_data=(Button)v.findViewById(R.id.button_data);

               getData2();
        list = (ListView) v.findViewById(R.id.listViewDetail);
        list.setDivider(null);


        //Initializing the ArrayList
        companies = new ArrayList<String>();

        //Initializing Spinner
        spinner = (Spinner) v.findViewById(R.id.spinner);

        //Adding an Item Selected Listener to our Spinner
        //As we have implemented the class Spinner.OnItemSelectedListener to this class iteself we are passing this to setOnItemSelectedListener
        spinner.setOnItemSelectedListener(this);

        //Initializing TextViews
        textViewCompanyName = (TextView) v.findViewById(R.id.textViewCompanyName);

        //This method will fetch the data from the URL
        getData2();






        Log.i("subham", "get data 0");
        button8.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        personList = new ArrayList<HashMap<String, String>>();

                        companyname = textViewCompanyName.getText().toString();
                        companyname = companyname.replace(" ", "-");
                        companyname = companyname.replace(".", "");
                        getData();
                    }
                }
        );



        return v;
    }




    private static String[] dataObjects = new String[]{ "SnapDeal",
            "Practo",
            "Amazon","Altimetrik","FoodPanda","Adobe"};

    int[] co = new int[]{R.drawable.co1, R.drawable.co2,R.drawable.co33,R.drawable.co4,R.drawable.co5,R.drawable.co6};

    private BaseAdapter mAdapter = new BaseAdapter() {

        private View.OnClickListener mOnButtonClicked = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("You started following it" + v);
                builder.setPositiveButton("Continue", null);
                builder.show();

            }
        };

        @Override
        public int getCount() {
            return dataObjects.length;
        }

        @Override
        public Object getItem(int position) {

            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View retval = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewitem, null);
            TextView title = (TextView) retval.findViewById(R.id.title);
           // Button button = (Button) retval.findViewById(R.id.clickbutton);
           // button.setOnClickListener(mOnButtonClicked);
            title.setText(dataObjects[position]);
            ImageView img= (ImageView)retval.findViewById(R.id.image);
            img.setImageResource(co[position]);

            return retval;
        }

    };





    private void getData2(){
        //Creating a string request

        StringRequest stringRequest = new StringRequest(DATA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            results = j.getJSONArray(JSON_ARRAY);

                            Log.i("result spinner",""+results);
                            //Calling method getStudents to get the students from the JSON Array
                            getStudents(results);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getStudents(JSONArray j){
        //Traversing through all the items in the json array
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                companies.add(json.getString(TAG_COMPANYNAME));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        spinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, companies));
    }

    //Method to get student name of a particular position
    private String getName(int position){
        String name="";
        try {
            //Getting object of given index
            JSONObject json = results.getJSONObject(position);

            //Fetching name from that object
            name = json.getString(TAG_COMPANYNAME);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return name;
    }
/*
    //Doing the same with this method as we did with getName()
    private String getCourse(int position){
        String course="";
        try {
            JSONObject json = result.getJSONObject(position);
            course = json.getString(Config.TAG_COURSE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return course;
    }

    //Doing the same with this method as we did with getName()
    private String getSession(int position){
        String session="";
        try {
            JSONObject json = result.getJSONObject(position);
            session = json.getString(Config.TAG_SESSION);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return session;
    }
*/

    //this method will execute when we pic an item from the spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Setting the values to textviews for a selected item
        textViewCompanyName.setText(getName(position));
  /*      textViewCourse.setText(getCourse(position));
        textViewSession.setText(getSession(position));

    */}

    //When no item is selected this method would execute
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        textViewCompanyName.setText("here");
    }










    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {


            @Override
            protected String doInBackground(String... params) {
                Log.i("subham", "get data 1");
                Log.i("subham", "get oye company 1"+companyname);
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                Log.i("subham", "get oye company 1"+companyname);
                HttpPost httppost = new HttpPost("http://hello45.esy.es/selectdetail.php?companyname="+companyname);
                Log.i("subham", "get oye data 1"+httppost);
                Log.i("subham", "get1 oye company 1"+companyname);
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
                Log.i("subham", "Result1 of question"+result);

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
                String detail = c.getString(TAG_DETAIL);

                HashMap<String,String> persons = new HashMap<String,String>();

                persons.put(TAG_DETAIL,detail);

                personList.add(persons);
            }

            ListAdapter adapter = new SimpleAdapter(
                    getActivity(), personList, R.layout.list_detail,
                    new String[]{TAG_DETAIL},
                    new int[]{ R.id.detail}
            );


            list.setAdapter(adapter);
            Toast.makeText(getActivity(), "Updating", Toast.LENGTH_SHORT).show();




        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
