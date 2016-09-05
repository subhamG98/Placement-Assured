package subhamguptaminor.androidbelieve.drawerwithswipetabs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;


import android.app.ListActivity;


import subhamguptaminor.androidbelieve.drawerwithswipetabs.Model.Flower2;
import subhamguptaminor.androidbelieve.drawerwithswipetabs.Network.api2;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ma1 extends AppCompatActivity{
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    EditText editText4;
    private Toolbar toolbar;
    public static Integer selector=0;

    public static final String GET_IMAGE_URL1="http://hello45.esy.es/DpUpload/getAllImages.php";
    String myJSON1;
    private static final String TAG_RESULTS1="result1";
    JSONArray peoples1 = null;
    ArrayList<HashMap<String, String>> personList1;
    ListView list1;
    List<Flower2> flowerList;
   ListActivity obj;

    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    public static final String LOGIN_SUCCESS = "success";



    public static final String SHARED_PREF_NAME = "myloginapp";

    public static final String USERNAME_SHARED_PREF = "email";

    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
//    private boolean loggedIn = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ma1);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_search);
        actionBar.setDisplayHomeAsUpEnabled(true);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(USERNAME_SHARED_PREF,"Not Available");
        /**
         *Setup the DrawerLayout and NavigationView
         */
        list1 = (ListView)findViewById(R.id.list);
        list1.setVisibility(View.GONE);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff) ;

        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */

        final RestAdapter restadapter = new RestAdapter.Builder().setEndpoint("http://hello45.esy.es").build();
        api2 flowerapi = restadapter.create(api2.class);

        flowerapi.getData(new Callback<List<Flower2>>() {
            @Override
            public void success(List<Flower2> flowers, Response response) {
                flowerList = flowers;
                final adapter2 adapt = new adapter2(getApplicationContext(), R.layout.listview_item, flowerList);
                //ListView listView = (ListView) findViewById(R.id.list);


                Log.i("retro check", "" + adapt);
                list1.setAdapter(adapt);


                editText4.addTextChangedListener(new TextWatcher() {


                    @Override
                    public void afterTextChanged(Editable arg0) {
                        // TODO Auto-generated method stub
                        String text = editText4.getText().toString().toLowerCase(Locale.getDefault());
                        if (text.equals("")) {
                            list1.setVisibility(View.GONE);
                        } else {
                            list1.setVisibility(View.VISIBLE);
                        }

                        adapt.filter(text);
                        Log.i("Congo 1", "check 10");

                    }

                    @Override
                    public void beforeTextChanged(CharSequence arg0, int arg1,
                                                  int arg2, int arg3) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                              int arg3) {
                        list1.setVisibility(View.VISIBLE);
                        // TODO Auto-generated method stub
                    }
                });










            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), "Failed" + error + error.getUrl() + error.getBody(), Toast.LENGTH_SHORT).show();
                Log.i("dude", "" + error + error.getUrl() + error.getBody());
            }
        });





        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();
        /**
         * Setup click events on the Navigation View Items.
         */

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                                                              @Override
                                                              public boolean onNavigationItemSelected(MenuItem menuItem) {


                                                                  mDrawerLayout.closeDrawers();

                                                                  if (menuItem.getItemId() == R.id.home) {
                                                                      Intent i=new Intent("subhamguptaminor.androidbelieve.drawerwithswipetabs.ma1");
                                                                      startActivity(i);

                                                                  }

                                                                  if (menuItem.getItemId() == R.id.email) {
                                                                      Intent i=new Intent("subhamguptaminor.androidbelieve.drawerwithswipetabs.email");
                                                                      startActivity(i);

                                                                  }

                                                                  if (menuItem.getItemId() == R.id.compare) {
                                                                      FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                                                                      fragmentTransaction.replace(R.id.containerView, new compareFragment()).commit();

                                                                  }


                                                                  if (menuItem.getItemId() == R.id.nav_item_sent) {
                                                                      FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                                                                      fragmentTransaction.replace(R.id.containerView, new SentFragment()).commit();

                                                                  }

                                                                  if (menuItem.getItemId() == R.id.location) {

                                                                   /*   FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();

                                                                      xfragmentTransaction.replace(R.id.containerView, new locationFragment()).commit();
                                                                  */
                                                                      Intent i=new Intent("subhamguptaminor.androidbelieve.drawerwithswipetabs.MapsActivity");
                                                                      startActivity(i);


                                                                  }

                                                                  if (menuItem.getItemId() == R.id.forum) {

                                                                   /*   FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();

                                                                      xfragmentTransaction.replace(R.id.containerView, new locationFragment()).commit();
                                                                  */
     //                                                                 Intent i1=new Intent("subhamguptaminor.androidbelieve.drawerwithswipetabs.Forum");
       //                                                               startActivity(i1);


                                                                  }






                                                                  if (menuItem.getItemId() == R.id.role) {
                                                                     FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();


                                                                      xfragmentTransaction.replace(R.id.containerView, new roleFragment()).commit();


                                                                  }

                                                                  if (menuItem.getItemId() == R.id.company) {
                                                                      FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                                                                      xfragmentTransaction.replace(R.id.containerView, new companyFragment()).commit();
                                                                  }
                                                                  if (menuItem.getItemId() == R.id.logout) {
                                                                      //Creating an alert dialog to confirm logout
                                                                      AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ma1.this);
                                                                      alertDialogBuilder.setMessage("Are you sure you want to logout?");
                                                                      alertDialogBuilder.setPositiveButton("Yes",
                                                                              new DialogInterface.OnClickListener() {
                                                                                  @Override
                                                                                  public void onClick(DialogInterface arg0, int arg1) {

                                                                                      //Getting out sharedpreferences
                                                                                      SharedPreferences preferences = getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
                                                                                      //Getting editor
                                                                                      SharedPreferences.Editor editor = preferences.edit();

                                                                                      //Puting the value false for loggedin
                                                                                      editor.putBoolean(LOGGEDIN_SHARED_PREF, false);

                                                                                      //Putting blank value to email
                                                                                      editor.putString(USERNAME_SHARED_PREF, "");

                                                                                      //Saving the sharedpreferences
                                                                                      editor.commit();
                                                                                      MainActivityFragment.name1="";
                                                                                      MainActivityFragment.password1="";

                                                                                      FacebookSdk.sdkInitialize(getApplicationContext());
                                                                                      LoginManager.getInstance().logOut();

                                                                                      //Starting login activity
                                                                                       Intent intent = new Intent(ma1.this,start.class);
                                                                                      startActivity(intent);
                                                                                  }
                                                                              });

                                                                      alertDialogBuilder.setNegativeButton("No",
                                                                              new DialogInterface.OnClickListener() {
                                                                                  @Override
                                                                                  public void onClick(DialogInterface arg0, int arg1) {

                                                                                  }
                                                                              });

                                                                      //Showing the alert dialog
                                                                      AlertDialog alertDialog = alertDialogBuilder.create();
                                                                      alertDialog.show();
                                                                  }



                                                                  return false;
                                                              }

                                                          }

        );

            /**
             * Setup Drawer Toggle of the Toolbar
             */

            android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
            ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,R.string.app_name , R.string.app_name);

            mDrawerLayout.setDrawerListener(mDrawerToggle);
        editText4=(EditText)findViewById(R.id.editText4);

            mDrawerToggle.syncState();

          //    getURLs1();









    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /** Create an option menu from res/menu/items.xml */
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if(id==R.id.chat)
        {


                    //Toast.makeText(getActivity(), "sss", Toast.LENGTH_LONG).show();
                    ma1.this.startService(new Intent(ma1.this, ChatHeadService.class));
                    // startService(new Intent(getApplication(), ChatHeadService.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}