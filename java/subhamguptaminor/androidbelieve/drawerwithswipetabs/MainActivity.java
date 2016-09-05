package subhamguptaminor.androidbelieve.drawerwithswipetabs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import com.twitter.sdk.android.core.*;
import com.twitter.sdk.android.core.models.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextUn;
    private EditText editTextPass;
    private Button button3;
    private Button button;

    private ImageButton fblogin;

    public static String username;
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    public static final String LOGIN_SUCCESS = "success";

//1638722466400475

    public static final String SHARED_PREF_NAME = "myloginapp";

    public static final String USERNAME_SHARED_PREF = "email";

    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
    private boolean loggedIn = false;
  String namefb="";
    String passwordfb="";
String name_twitter="";
    String username_twitter="";
    long userid_twitter;
    String dp_twitter="";

    private TwitterLoginButton loginButton;
String dp1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()
                final TwitterSession session = result.data;
                // TODO: Remove toast and use the TwitterSession's userID
                // with your app's user model
              name_twitter=session.getUserName().replace(" ","");
                username_twitter=session.getUserName().replace(" ", "");
                userid_twitter=session.getUserId();

                Log.i("twitter check", "" + name_twitter + " " + username_twitter);

                new MyTwitterApiClient(session).getUsersService().show(userid_twitter, null, true,
                        new Callback<User>() {
                            @Override
                            public void success(Result<User> result) {

                               // dp_twitter.equals(result.data.profileImageUrlHttps.toString());
                                dp_twitter = result.data.profileImageUrlHttps.toString();
                                Log.i("dp==", "-" + dp_twitter);
                                Log.i("hawwa", "" + result.data.profileImageUrlHttps.toString() + "and " + dp1);
                                inserttwitterlogin(session.getUserName().replace(" ", ""), session.getUserName().replace(" ", ""),dp_twitter);

                            }

                            @Override
                            public void failure(TwitterException exception) {
                                Log.i("twittercommunity", "exception is " + exception);
                            }
                        });


             //   Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });


        namefb=MainActivityFragment.name1;
        passwordfb=MainActivityFragment.password1;

        Log.i("Really",""+namefb+" --"+passwordfb);

        if(namefb=="" || passwordfb=="")
        {

        }
        else
        {
            checkToDatabase1(namefb,passwordfb);
        }




        editTextUn = (EditText) findViewById(R.id.editText);
        editTextPass = (EditText) findViewById(R.id.editText2);
        button3 = (Button) findViewById(R.id.button3);
//        button=(Button)findViewById(R.id.button);
        fblogin=(ImageButton)findViewById(R.id.fblogin);

        fblogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i1=new Intent("subhamguptaminor.androidbelieve.drawerwithswipetabs.ma2");
                        startActivity(i1);
                    }
                }
        );



        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
/*        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent("register");
                        startActivity(intent1);
                    }
                }
        );
*/
        button3.setOnClickListener(this);


    }


    class MyTwitterApiClient extends TwitterApiClient {
        public MyTwitterApiClient(TwitterSession session) {
            super(session);
        }

        public UsersServices getUsersService() {
            return getService(UsersServices.class);
        }
    }














    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }


    private void inserttwitterlogin(String name, final String username1,String twitter1) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramname = params[0];
                String paramusername = params[1];
                String paramdp = params[2];


                String namet=name_twitter;
                String usernamet=username_twitter;
                String dpt=dp_twitter;

                Log.i("twitter check1",""+namet+" "+usernamet+"dp==>"+dpt);

                InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("namet", namet));

                nameValuePairs.add(new BasicNameValuePair("usernamet", usernamet));
                nameValuePairs.add(new BasicNameValuePair("dpt", dpt));

                String result = null;
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://hello45.esy.es/inserttwitterlogin.php");
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

//                    Toast.makeText(getApplicationContext(), "success! ", Toast.LENGTH_LONG).show();
                    SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

                    //Creating editor to store values to shared preferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                        username=username_twitter;

                    Log.i("twitter check3",""+username);

                    //Adding values to editor
                    editor.putBoolean(LOGGEDIN_SHARED_PREF, true);
                    editor.putString(USERNAME_SHARED_PREF, username_twitter);

                    //Saving values to editor
                    editor.commit();

                    //Starting profile activity
                    Intent intent = new Intent(MainActivity.this,ma1.class);
                    startActivity(intent);





                } else {
                     Toast.makeText(getApplicationContext(), "failed! to cause twitter login"+result, Toast.LENGTH_SHORT).show();
                    Log.i("twitter check", "" + result);

                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(name, username1,twitter1);
    }








    public void check(View view){
        String un = editTextUn.getText().toString();
        String pass = editTextPass.getText().toString();

              checkToDatabase(un, pass);
}
/*public void sendforregister()
{

    Intent intent1 = new Intent("register")  ;
    startActivity(intent1);


}
*/
    public void checkToDatabase(final String un,String pass) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramun = params[0];
                String parampass = params[1];


                String un = editTextUn.getText().toString();
                String pass = editTextPass.getText().toString();
                InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("username", un));
                nameValuePairs.add(new BasicNameValuePair("password", pass));
                Log.i("username", "" + un);
                Log.i("username1", "" +pass);

                String result = null;
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://hello45.esy.es/checklogin.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);
                    Log.i("httppost", "" +httpPost);

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
                Log.i("result", ""+result);

                return result;

            }

            @Override
            protected void onPostExecute(String result) {
                if (result.contains("success")) {
                    username= editTextUn.getText().toString();
                    Toast.makeText(getApplicationContext(), "Welcome! "+username, Toast.LENGTH_SHORT).show();
                  //  String name  = editTextUn.getText().toString();
                   // String pass  = editTextPass.getText().toString();

                    SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

                    //Creating editor to store values to shared preferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    //Adding values to editor
                    editor.putBoolean(LOGGEDIN_SHARED_PREF, true);
                    editor.putString(USERNAME_SHARED_PREF, username);

                    //Saving values to editor
                    editor.commit();

                    //Starting profile activity
                    Intent intent = new Intent(MainActivity.this,ma1.class);
                    startActivity(intent);




                } else {
                    Toast.makeText(getApplicationContext(), "Login failed!"+result, Toast.LENGTH_LONG).show();
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(un, pass);
    }




    public void checkToDatabase1(String un,String pass) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramun = params[0];
                String parampass = params[1];


                String un = namefb;
                un=un.replace(" ","");
                String pass = passwordfb;
                pass=pass.replace(" ","");

                Log.i("should be correct",""+un+" "+pass);
                InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("username", un));
                nameValuePairs.add(new BasicNameValuePair("password", pass));
                Log.i("username", "" + un);
                Log.i("username1", "" +pass);

                String result = null;
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://hello45.esy.es/checklogin.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);
                    Log.i("httppost", "" +httpPost);

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
                Log.i("result", ""+result);

                return result;

            }

            @Override
            protected void onPostExecute(String result) {
                if (result.contains("success")) {
                    username= namefb;
                    username=username.replace(" ","");
                    Toast.makeText(getApplicationContext(), "Welcome! "+username, Toast.LENGTH_SHORT).show();
                    //  String name  = editTextUn.getText().toString();
                    // String pass  = editTextPass.getText().toString();

                    SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

                    //Creating editor to store values to shared preferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    //Adding values to editor
                    editor.putBoolean(LOGGEDIN_SHARED_PREF, true);
                    editor.putString(USERNAME_SHARED_PREF, username);

                    //Saving values to editor
                    editor.commit();

                    //Starting profile activity
                    Intent intent = new Intent(MainActivity.this,ma1.class);
                    startActivity(intent);




                } else {
                    Toast.makeText(getApplicationContext(), "failed!"+result, Toast.LENGTH_LONG).show();
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(un, pass);
    }

















    @Override
    public void onClick(View v) {
      check(v);

    }


    @Override
    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(LOGGEDIN_SHARED_PREF, false);
            // username=sharedPreferences.getString(username,USERNAME_SHARED_PREF);
        //If we will get true
        if(loggedIn){
            //We will start the Profile Activity
            Intent intent = new Intent(MainActivity.this, ma1.class);
            startActivity(intent);
        }
    }









}
