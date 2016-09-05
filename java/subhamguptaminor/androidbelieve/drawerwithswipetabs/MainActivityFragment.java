package subhamguptaminor.androidbelieve.drawerwithswipetabs;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

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


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private CallbackManager callbackManager;
    private TextView textView;

    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;

    static String name1="";
    String username1="";
    static String password1="";
    String image1="";
    Button proceed;
    View v;

    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            displayMessage(profile);
            String userId = loginResult.getAccessToken().getUserId();
            String token = loginResult.getAccessToken().getToken();

            String imageUrl = String.format("https://graph.facebook.com/%s/picture?type=large", userId);
              image1=imageUrl;
            password1=userId;
            Log.i("profile pic user", "a=" + password1);
            Toast.makeText(getActivity(),"Facebook Login Successfull! Click on PROCEED button",Toast.LENGTH_LONG).show();



        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {

        }
    };

    public MainActivityFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
          FacebookSdk.sdkInitialize(getActivity().getApplicationContext());



        callbackManager = CallbackManager.Factory.create();

        accessTokenTracker= new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {

            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                displayMessage(newProfile);
            }
        };

        accessTokenTracker.startTracking();
        profileTracker.startTracking();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_main, container, false);
           proceed=(Button)v.findViewById(R.id.proceed);

            proceed.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            checkfblogin(username1, password1);

//                            Intent i = new Intent("android.intent.action.MAIN");


//                            startActivity(i);
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);


                        }
                    }
            );

         return v;

    }

    private void checkfblogin(String username, String password) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramusername = params[0];
                String parampassword = params[1];


                String username=username1;
                String password=password1;

                InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("username", username1));
                nameValuePairs.add(new BasicNameValuePair("password", password1));


                String result = null;
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://hello45.esy.es/checkfblogin.php");
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

                    //Toast.makeText(getActivity(), "Dont do anything ", Toast.LENGTH_LONG).show();

                } else {
                    insertfblogin(name1, username1, password1, image1);
               //     Toast.makeText(getActivity(), "Do insertion"+result, Toast.LENGTH_LONG).show();
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(username, password);
    }











    private void insertfblogin(String name, String username, String password, String image) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramname = params[0];
                String paramusername = params[1];
                String parampassword = params[2];
                String paramimage = params[3];


                String name=name1;
                String username=username1;
                String password=password1;
                String image=image1;

               InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("name", name));

                nameValuePairs.add(new BasicNameValuePair("username", username1));
                nameValuePairs.add(new BasicNameValuePair("password", password1));
                nameValuePairs.add(new BasicNameValuePair("image", image1));


                String result = null;
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://hello45.esy.es/insertloginfb.php");
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

                    Toast.makeText(getActivity(), "Registered through facebook successfully ", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getActivity(), "Could not Register through facebook"+result, Toast.LENGTH_LONG).show();
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(name,username,password,image);
    }







    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);
        textView = (TextView) view.findViewById(R.id.textView);

        loginButton.setReadPermissions("user_friends");
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager, callback);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    private void displayMessage(Profile profile){
        if(profile != null){
            textView.setText(profile.getName());
            Log.i("vimp", "" + profile.getName());
            Log.i("vimp1", "" + profile.getFirstName() + "" + profile.getLastName());
            Log.i("vimp2", "" + profile.getId());
                name1=profile.getName();
                name1=name1.replace(" ","");
            username1=profile.getFirstName().concat(profile.getLastName());
           // password1=profile.getId();

        }
    }

    @Override
    public void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    public void onResume() {
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        displayMessage(profile);
    }


}