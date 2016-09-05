package subhamguptaminor.androidbelieve.drawerwithswipetabs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Timer;


public class start extends ActionBarActivity {

    int noofsize = 4;
    ViewPager myPager = null;
    int count = 0;
    Timer timer;
    private Button button;
    private Button button1;

    public static final String SHARED_PREF_NAME = "myloginapp";

    public static final String USERNAME_SHARED_PREF = "email";

    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
    private boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        button=(Button)findViewById(R.id.register1);
        button1=(Button)findViewById(R.id.login1);


        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent("subhamguptaminor.androidbelieve.drawerwithswipetabs.register");
                        startActivity(intent1);
                    }
                }
        );


        button1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent("subhamguptaminor.androidbelieve.drawerwithswipetabs.MainActivity");
                        startActivity(intent1);
                    }
                }
        );


        ViewPagerAdapter1 adapter1 = new ViewPagerAdapter1(start.this,noofsize);
        myPager = (ViewPager)findViewById(R.id.reviewpager);
        myPager.setAdapter(adapter1);
        myPager.setCurrentItem(0);

        if (count <= 3) {
            myPager.setCurrentItem(count);
            count++;
        } else {

            count = 0;
            myPager.setCurrentItem(count);
        }
        // Timer for auto sliding
         /*
        timer  = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                start.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (count <= 3) {
                            myPager.setCurrentItem(count);
                            count++;
                        } else {
                            count = 0;
                            myPager.setCurrentItem(count);
                        }
                    }
                });
            }
        }, 100, 3000);


*/






    }


    @Override
    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(LOGGEDIN_SHARED_PREF, false);
        // username=sharedPreferences.getString(username,USERNAME_SHARED_PREF);
        //If we will get true
        if (loggedIn) {
            //We will start the Profile Activity
            Intent intent = new Intent(start.this, MainActivity.class);
            startActivity(intent);
        }

    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
