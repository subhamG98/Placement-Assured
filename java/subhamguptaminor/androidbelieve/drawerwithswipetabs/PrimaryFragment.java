package subhamguptaminor.androidbelieve.drawerwithswipetabs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;

import retrofit.RestAdapter;
import android.app.ListActivity;

import subhamguptaminor.androidbelieve.drawerwithswipetabs.Model.Flower4;
import subhamguptaminor.androidbelieve.drawerwithswipetabs.Network.api;

import subhamguptaminor.androidbelieve.drawerwithswipetabs.Model.Flower;
import subhamguptaminor.androidbelieve.drawerwithswipetabs.Network.api4;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class PrimaryFragment extends ListFragment {
    Button startService;
    Button buttonStatus;
    String myJSON;
    private static final String TAG_RESULTS="result";
    String myJSON1;
    private static final String TAG_RESULTS1="result1";
    //String username= MainActivity.username;
    //public static String receiver="";
    private Timer myTimer;
    int k=0;
    private Timer myTimer1;
    Toolbar toolbar;
    ImageButton FAB;
    ImageButton FAB1;

    Timer timer;
    Timer timer1;
    int flag1=1;
    int flag=1;
    private static final String MY_TAG = "the_custom_message";
    public String[] company1;
    public String[] news1;
    public String[] likes1;
    public Integer[] imageId1;

    private static final String TAG_COMPANY = "company";
    private static final String TAG_NEWS = "news";
    private static final String TAG_LIKES = "likes";
    private static final String TAG_INFO = "info";
    private static final String TAG_NAME= "name";
    private static final String TAG_ADDRESS = "address";
    private static final String TAG_IMAGE = "image";
    List<Flower> flowerList;

    JSONArray peoples = null;
    ArrayList<HashMap<String, String>> personList;
    JSONArray peoples1 = null;
    ArrayList<HashMap<String, String>> personList1;
    //ListView list;
    View v;
    ListView list;
    ListView list1;
    TextView user;
   EditText editText4;
    public static final String BITMAP_ID = "BITMAP_ID";
    public static String GET_IMAGE_URL="http://hello45.esy.es/PhotoUpload/getAllImages.php";
    public static final String GET_IMAGE_URL1="http://hello45.esy.es/DpUpload/getAllImages.php";
    //public ArrayAdapter<String> adapter;
    ArrayList<WorldPopulation> arraylist = new ArrayList<WorldPopulation>();
    private ListActivity obj;


    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    public static final String LOGIN_SUCCESS = "success";



    public static final String SHARED_PREF_NAME = "myloginapp";

    public static final String USERNAME_SHARED_PREF = "email";

    public static String s = "";

    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
//    private boolean loggedIn = false;




    List<Flower4> flowerList1;




    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

         v =inflater.inflate(R.layout.primary_layout,container,false);
          obj=new ListActivity();

/*
        FragmentManager fm = getFragmentManager();
      fm.addOnBackStackChangedListener(
              new FragmentManager.OnBackStackChangedListener() {
                  @Override
                  public void onBackStackChanged() {
                      getActivity().getFragmentManager().popBackStack();
                  }
              }

      );

*/
        FAB = (ImageButton) v.findViewById(R.id.imageButton);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent("subhamguptaminor.androidbelieve.drawerwithswipetabs.UploadStatus");
                startActivity(intent);


            }
        });


        FAB1 = (ImageButton) v.findViewById(R.id.imageButton3);
        FAB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent("subhamguptaminor.androidbelieve.drawerwithswipetabs.twitterpost");
                startActivity(intent);


            }
        });
//



//        list = (ListView) v.findViewById(R.id.list54);
  //      list.setDividerHeight(20);

        final RestAdapter restadapter = new RestAdapter.Builder().setEndpoint("http://hello45.esy.es").build();
        final RestAdapter restadapter1 = new RestAdapter.Builder().setEndpoint("http://hello45.esy.es").build();

        //Fetching email from shared preferences
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
         s = sharedPreferences.getString(USERNAME_SHARED_PREF, "Not Available");
        api flowerapi = restadapter.create(api.class);
        api4 flowerapi4 = restadapter.create(api4.class);

        flowerapi.getData(s,new Callback<List<Flower>>() {
            @Override
            public void success(List<Flower> flowers, Response response) {
                flowerList = flowers;
                adapter adapt = new adapter(getActivity(), R.layout.list_layout, flowerList,flowerList1);
                //ListView listView = (ListView) findViewById(R.id.list);


                Log.i("retro check",""+adapt);
                setListAdapter(adapt);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), "Failed" + error + error.getUrl() + error.getBody(), Toast.LENGTH_SHORT).show();
                Log.i("dude", "" + error + error.getUrl() + error.getBody());
            }
        });



        flowerapi4.getData(s,new Callback<List<Flower4>>() {
            @Override
            public void success(List<Flower4> flowers, Response response) {
                flowerList1 = flowers;
              //  adapter4 adapt1 = new adapter4(getActivity(), R.layout.list_item_file, flowerList1);
                //ListView listView = (ListView) findViewById(R.id.list);


              //  Log.i("retro check",""+adapt1);
               // setListAdapter(adapt1);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), "Failed" + error + error.getUrl() + error.getBody(), Toast.LENGTH_SHORT).show();
                Log.i("dude", "" + error + error.getUrl() + error.getBody());
            }
        });






        GET_IMAGE_URL="http://hello45.esy.es/PhotoUpload/getAllImages.php";
//        user.setText("" + s + "'s WALL");
        GET_IMAGE_URL=GET_IMAGE_URL.concat("?username="+MainActivity.username);
       // editText4=(EditText)v.findViewById(R.id.editText4);





        //list1.setVisibility(View.GONE);
        //list.setDivider(null);
               insert(v);
        insert1(v);
       // list.setOnItemClickListener(getActivity());
       // getURLs();





   setHasOptionsMenu(true);
        return v;
    }



    public void insert(View view){
        String name = MainActivity.username;

        insertToDatabase(name);
    }

    private void insertToDatabase(String name){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramname = params[0];


                String name = MainActivity.username;

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("name", name));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://hello45.esy.es/hackcrawl.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "success";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);


            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(name);
    }




    public void insert1(View view){
        String name = MainActivity.username;

        insertToDatabase1(name);
    }

    private void insertToDatabase1(String name){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramname = params[0];


                String name = MainActivity.username;

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("name", name));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://hello45.esy.es/hackcrawl1.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "success";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);


            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(name);
    }














}

