package subhamguptaminor.androidbelieve.drawerwithswipetabs;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Timer;


import android.app.ListActivity;

import subhamguptaminor.androidbelieve.drawerwithswipetabs.Network.api3;

import subhamguptaminor.androidbelieve.drawerwithswipetabs.Model.Flower3;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class chatscreen extends ListActivity {


    String myJSON;
    private static final String TAG_RESULTS = "result";
    String username = MainActivity.username;
    public static String receiver = "";
    private Timer myTimer;
    int k = 0;
    Timer timer;
    int flag1 = 1;
    private static final String MY_TAG = "the_custom_message";

    List<Flower3> flowerList;
    private static final String TAG_NAME = "name";
    JSONArray peoples = null;
    ArrayList<HashMap<String, String>> personList;
    ListView list;
    public static final String GET_IMAGE_URL1 = "http://hello45.esy.es/DpUpload/getAllImages1.php";
    String myJSON1;
    private static final String TAG_RESULTS1 = "result1";
    JSONArray peoples1 = null;
    ArrayList<HashMap<String, String>> personList1;
    ListView list1;
EditText editText5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatscreen);
        overridePendingTransition(R.layout.pull_in_right, R.layout.pull_out_left);
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;

//        getWindow().setLayout((int) (width * .8), (int) (height * .8));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.x = -100;
        params.height = height;
        params.width = 550;
        params.y = -50;

        this.getWindow().setAttributes(params);

 //       list1 = (ListView) findViewById(R.id.listView);
        personList = new ArrayList<HashMap<String, String>>();
        editText5=(EditText)findViewById(R.id.editText5);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        final RestAdapter restadapter = new RestAdapter.Builder().setEndpoint("http://hello45.esy.es").build();
        api3 flowerapi = restadapter.create(api3.class);

        flowerapi.getData( new Callback<List<Flower3>>() {
            @Override
            public void success(List<Flower3> flowers, Response response) {
                flowerList = flowers;
                final adapter3 adapt = new adapter3(getApplicationContext(), R.layout.list_chatscreen, flowerList);
                //ListView listView = (ListView) findViewById(R.id.list);


                Log.i("retro check", "" + adapt);
                setListAdapter(adapt);

                editText5.addTextChangedListener(new TextWatcher() {


                    @Override
                    public void afterTextChanged(Editable arg0) {
                        // TODO Auto-generated method stub
                        String text = editText5.getText().toString().toLowerCase(Locale.getDefault());
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







    }





    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.layout.pull_in_left, R.layout.pull_out_right);
    }



}









































