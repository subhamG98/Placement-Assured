package subhamguptaminor.androidbelieve.drawerwithswipetabs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class chatmessage extends ActionBarActivity {
    String name1;
    String myJSON;
    int k=0;

    private Timer myTimer;
    Timer timer;
    private static final String MY_TAG = "the_custom_message";
    int flag=1;

    private static final String TAG_RESULTS="result";
    private static final String TAG_NAME = "name";
    private static final String TAG_MSG ="message";
    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;

    Button button_send;
    public EditText message_send;
    ListView list;
    DatabaseHelper myDb;

    String username;
    String receiver1="";
    private String TAG_USER=username;




    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    public static final String LOGIN_SUCCESS = "success";



    public static final String SHARED_PREF_NAME = "myloginapp";

    public static final String USERNAME_SHARED_PREF = "email";

    public static String s = "";

    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
//    private boolean loggedIn = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_chatmessage);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        s = sharedPreferences.getString(USERNAME_SHARED_PREF, "Not Available");

//        username= (MainActivity.username);
        Intent i = getIntent();
        // Get the results of rank
        receiver1 = i.getStringExtra("receiver");
        receiver1=receiver1.replace(" ","");
        overridePendingTransition(R.layout.pull_in_right, R.layout.pull_out_left);
        message_send=(EditText)findViewById(R.id.message_send);
        button_send=(Button)findViewById(R.id.button_send);
        list = (ListView) findViewById(R.id.listView1);
        personList = new ArrayList<HashMap<String,String>>();
        myDb= new DatabaseHelper(this);

          Log.i("very critial"," username"+MainActivity.username+" check s"+s);

        myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                checkforinternet();
                if (flag == 1) {
                    //sendbutton();
                    Log.i(MY_TAG, "found internet calling getdata()");
                    list = (ListView) findViewById(R.id.listView1);
                    personList = new ArrayList<HashMap<String, String>>();
                    getData();
                } else {
                    Log.i(MY_TAG, "calling again check for ");
                    Toast.makeText(chatmessage.this, "Not able to receive Messages!!", Toast.LENGTH_SHORT).show();
                    viewAll();                    //sendbutton();
                }
            }
        }, 0, 4000);

        //getData();
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.layout.pull_in_left, R.layout.pull_out_right);
    }

    public void viewAll()
    {
        Cursor res = myDb.getAllData();

        if (res.getCount() == 0) {
            showMessage("Error", "No Data Found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {

            buffer.append("Name: " + res.getString(0) + "\n");
            buffer.append("Message: " + res.getString(1) + "\n");
        }

        showMessage("Data", buffer.toString());
    }




    public void showMessage(String title ,String Message){


        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }








    private void checkforinternet() {
        Log.i(MY_TAG, "Checking for internet!");
        ConnectivityManager cm2 = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni2 = cm2.getActiveNetworkInfo();
        if (ni2 == null) {
            Toast.makeText(this, "Not Connected to Internet!!", Toast.LENGTH_SHORT).show();
            flag=0;
            Log.i(MY_TAG, "internet not found!");
            return;
        }
    }


    protected void showList(){

        try {
            ListAdapter adapter;
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);
            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);
                String name = c.getString(TAG_NAME);
                name1=name;
                String message = c.getString(TAG_MSG);
                // String id = c.getString(TAG_ID);
                HashMap<String,String> persons = new HashMap<String,String>();
                //persons.put(TAG_ID,id);
                persons.put(TAG_NAME,name);
                persons.put(TAG_MSG, message);
                personList.add(persons);
            }
            list = (ListView) findViewById(R.id.listView1);




            if(peoples.length()==k) {

//no listview
            }
            else{

                Log.i("subham",name1);
             /* adapter = new SimpleAdapter(
                      getApplicationContext(), personList, R.layout.list_item2,
                      new String[]{TAG_NAME, TAG_MSG},
                      new int[]{R.id.name, R.id.message});
*/

/*                Intent intent = new Intent();
                PendingIntent pIntent = PendingIntent.getActivity(chatmessage.this, 0, intent, 0);
                Notification noti = new Notification.Builder(chatmessage.this).setTicker("Ticker").
                        setContentTitle("Placement Assured").setContentText("New Chat Message Received").
                        setSmallIcon(R.drawable.ic_chat).
                        setContentIntent(pIntent).getNotification();

                noti.flags = Notification.FLAG_AUTO_CANCEL;
                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                nm.notify(0, noti);
*/
                if(name1==s)

                {
                    adapter = new SimpleAdapter(
                            getApplicationContext(), personList, R.layout.list_item2,
                            new String[]{TAG_NAME, TAG_MSG},
                            new int[]{R.id.name_mutual, R.id.message}

                    );


                }
                else {


                    adapter = new SimpleAdapter(
                            getApplicationContext(), personList, R.layout.list_item2,
                            new String[]{TAG_NAME, TAG_MSG},
                            new int[]{R.id.name_mutual, R.id.message}

                    );

                }

                list.setAdapter(adapter);

                k=peoples.length();
                //  AddData();

            }




        }
        catch (JSONException e)
        {
            Toast.makeText(this,"Could not send message",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }


    public void AddData()
    {
        boolean isInserted = myDb.insertData(TAG_NAME,TAG_MSG, receiver1);

        if (isInserted == true)
            Toast.makeText(chatmessage.this, "Data Inserted", Toast.LENGTH_SHORT).show();
        else

            Toast.makeText(chatmessage.this, "Data  not Inserted", Toast.LENGTH_SHORT).show();


    }



    public void insert_message(View view){
        String messagesend = message_send.getText().toString();
        String sendermsg=s;
        String receivermsg=receiver1;
        insertTo(sendermsg,messagesend,receivermsg);
    }

    private void insertTo(String sendermsg,String messagesend,String receivermsg) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String parammess = params[0];
                String paramsender = params[1];
                String paramreceiver = params[2];


                String sendermsg=s;
                String receivermsg=receiver1;

                String messagesend = message_send.getText().toString();
                InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("name", sendermsg));

                nameValuePairs.add(new BasicNameValuePair("message", messagesend));
                nameValuePairs.add(new BasicNameValuePair("receiver", receivermsg));
                String result = null;
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://hello45.esy.es/sendmessage.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();

                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                if (result.contains("success")) {
                    EditText message_send=(EditText) findViewById(R.id.message_send);
                    message_send.setText("");

//                    Toast.makeText(getApplicationContext(), "success! ", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "failed! to send message"+result, Toast.LENGTH_LONG).show();
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(messagesend,sendermsg,receivermsg);
    }
    private void getData() {

        class LoginAsync extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected String doInBackground(String... params) {
                InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("username", s));
                nameValuePairs.add(new BasicNameValuePair("receiver", receiver1));
                Log.i("fdf", "result receiver:" + receiver1+" user= "+s);


                String result = null;

                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://hello45.esy.es/selectchat.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                    is = entity.getContent();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.i(MY_TAG, "result send:"+result);
                return result;
            }

            @Override
            public void onPostExecute(String result){
                // Toast.makeText(userpage.this,""+result,Toast.LENGTH_LONG).show();

                myJSON=result;
                showList();
            }
        }

        LoginAsync la = new LoginAsync();
        la.execute();

    }



}
