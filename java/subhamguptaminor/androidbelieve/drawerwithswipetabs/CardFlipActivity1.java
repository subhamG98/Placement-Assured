package subhamguptaminor.androidbelieve.drawerwithswipetabs;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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



public class CardFlipActivity1 extends ActionBarActivity
        implements FragmentManager.OnBackStackChangedListener {
    /**
     * A handler object, used for deferring UI operations.
     */
    private Handler mHandler = new Handler();

    /**
     * Whether or not we're showing the back of the card (otherwise showing the front).
     */
    private boolean mShowingBack = false;
    static Button button9;
    static View v,v1;
    static String myJSON; static String myJSON1;
    private static final String TAG_RESULTS="result";
    private static final String TAG_RESULTS1="result1";
    private static final String MY_TAG = "the_custom_message";
    public String[] company1;
    private static final String TAG_COMPANY = "company";

    static JSONArray peoples = null;
    static JSONArray peoples1 = null;
    static ArrayList<HashMap<String, String>> personList;
    static ArrayList<HashMap<String, String>> personList1;

    public ArrayAdapter<String> adapter;
    static ListView list;
    static ListView list1;
    private static final String TAG_QUES = "ques";
    private static final String TAG_ANS = "ans";

    static Button buttonans;
    static Button buttonnext;
    static Integer testno1=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_AppCompat_Light_DarkActionBar);
        setTitle("Aptitude Paper");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_card_flip_activity1);

        if (savedInstanceState == null) {
            // If there is no saved instance state, add a fragment representing the
            // front of the card to this activity. If there is saved instance state,
            // this fragment will have already been added to the activity.
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new CardFrontFragment())
                    .commit();
        } else {
            mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
        }

        // Monitor back stack changes to ensure the action bar shows the appropriate
        // button (either "photo" or "info").
        getFragmentManager().addOnBackStackChangedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        // Add either a "photo" or "finish" button to the action bar, depending on which page
        // is currently selected.
        MenuItem item = menu.add(Menu.NONE, R.id.action_flip, Menu.NONE,
                mShowingBack
                        ? R.string.action_photo
                        : R.string.action_info);
        item.setIcon(mShowingBack
                ? R.drawable.ic_action_photo
                : R.drawable.ic_action_info);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Navigate "up" the demo structure to the launchpad activity.
                // See http://developer.android.com/design/patterns/navigation.html for more.
                NavUtils.navigateUpTo(this, new Intent(this, PrimaryFragment.class));
                return true;

            case R.id.action_flip:
                flipCard();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void flipCard() {
        if (mShowingBack) {
            getFragmentManager().popBackStack();
            return;
        }

        // Flip to the back.

        mShowingBack = true;

        // Create and commit a new fragment transaction that adds the fragment for the back of
        // the card, uses custom animations, and is part of the fragment manager's back stack.

        getFragmentManager()
                .beginTransaction()

                        // Replace the default fragment animations with animator resources representing
                        // rotations when switching to the back of the card, as well as animator
                        // resources representing rotations when flipping back to the front (e.g. when
                        // the system Back button is pressed).
                .setCustomAnimations(
                        R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in, R.animator.card_flip_left_out)

                        // Replace any fragments currently in the container view with a fragment
                        // representing the next page (indicated by the just-incremented currentPage
                        // variable).
                .replace(R.id.container, new CardBackFragment())

                        // Add this transaction to the back stack, allowing users to press Back
                        // to get to the front of the card.
                .addToBackStack(null)

                        // Commit the transaction.
                .commit();

        // Defer an invalidation of the options menu (on modern devices, the action bar). This
        // can't be done immediately because the transaction may not yet be committed. Commits
        // are asynchronous in that they are posted to the main thread's message loop.
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                invalidateOptionsMenu();
            }
        });
    }

    @Override
    public void onBackStackChanged() {
        mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);

        // When the back stack changes, invalidate the options menu (action bar).
        invalidateOptionsMenu();
    }

    /**
     * A fragment representing the front of the card.
     */
    @SuppressLint("ValidFragment")
    public class CardFrontFragment extends Fragment {
        public CardFrontFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            v=inflater.inflate(R.layout.fragment_card_front1, container, false);

            list = (ListView) v.findViewById(R.id.listViewTech);
            list.setDivider(null);
            personList = new ArrayList<HashMap<String, String>>();
            Log.i("subham", "get data 0");
            testno1=UpdatesFragment.testno;


            getData();



            return v;

        }
    }

    /**
     * A fragment representing the back of the card.
     */


    @SuppressLint("ValidFragment")
    public class CardBackFragment extends Fragment {
        public CardBackFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            v1= inflater.inflate(R.layout.fragment_card_back1, container, false);
            buttonans=(Button)v.findViewById(R.id.button6);
            list1 = (ListView) v1.findViewById(R.id.listViewTechAns);
            list1.setDivider(null);
            personList1 = new ArrayList<HashMap<String, String>>();
            getData2();

            return v1;
        }
    }





    protected void showList2(){

        try {
            JSONObject jsonObj1 = new JSONObject(myJSON1);
            peoples1 = jsonObj1.getJSONArray(TAG_RESULTS1);

            for(int i=0;i<peoples1.length();i++){
                JSONObject c = peoples1.getJSONObject(i);
                String ans = c.getString(TAG_ANS);

                HashMap<String,String> persons1 = new HashMap<String,String>();

                persons1.put(TAG_ANS,ans);

                personList1.add(persons1);
            }


            ListAdapter adapter1 = new SimpleAdapter(CardFlipActivity1.this, personList1, R.layout.list_tech_ans,
                    new String[]{TAG_ANS},
                    new int[]{ R.id.answer}
            );


            list1.setAdapter(adapter1);
            //Toast.makeText(, "Updating", Toast.LENGTH_SHORT).show();




        } catch (JSONException e) {
            e.printStackTrace();
        }

    }





















    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {


            @Override
            protected String doInBackground(String... params) {
                Log.i("subham", "get data 1");
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("http://hello45.esy.es/selecttechques.php?testno="+testno1);
                Log.i("subham", "get data 1");
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
                Log.i("subham", "Result of question"+result);

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
                String ques = c.getString(TAG_QUES);

                HashMap<String,String> persons = new HashMap<String,String>();

                persons.put(TAG_QUES,ques);

                personList.add(persons);
            }

            ListAdapter adapter = new SimpleAdapter(
                    CardFlipActivity1.this, personList, R.layout.list_tech_quest,
                    new String[]{TAG_QUES},
                    new int[]{ R.id.question}
            );


            list.setAdapter(adapter);
            Toast.makeText(this, "Updating", Toast.LENGTH_SHORT).show();




        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



    public void getData2(){
        class GetDataJSON extends AsyncTask<String, Void, String> {


            @Override
            protected String doInBackground(String... params) {
                Log.i("subham", "get data 1");
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("http://hello45.esy.es/selecttechans.php?testno="+testno1);
                Log.i("subham", "get data 1");
                // Depends on your web service
                httppost.setHeader("Content-type", "application/json");

                InputStream inputStream = null;
                String result1 = null;
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
                    result1 = sb.toString();
                } catch (Exception e) {
                    // Oops
                }
                finally {
                    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
                }
                Log.i("subham", "Result1 of question"+result1);

                return result1;

            }

            @Override
            protected void onPostExecute(String result1){
                myJSON1=result1;
                showList2();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }














}
