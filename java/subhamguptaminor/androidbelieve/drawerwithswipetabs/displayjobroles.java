package subhamguptaminor.androidbelieve.drawerwithswipetabs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;


public class displayjobroles extends ActionBarActivity {


    public String joblocation;


    ArrayList<HashMap<String, String>> personList;
    ListView list;

    public static ArrayList<WorldPopulation5> arraylist = new ArrayList<WorldPopulation5>();
    public static  String GET_IMAGE_URL1 = "http://hello45.esy.es/byrole.php";


    String myJSON1;
    private static final String TAG_RESULTS1 = "result1";
    JSONArray peoples1 = null;
    ArrayList<HashMap<String, String>> personList1;
    ListView list1;
    ListViewAdapter5 adapter1;
    public GetAllImages6 getAlImages6;
    EditText editTextSort;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayjobroles);

        Intent i = getIntent();

        joblocation = i.getStringExtra("joblocation");
        GET_IMAGE_URL1=GET_IMAGE_URL1.concat("?joblocation=" + joblocation);
        Log.i("show me","check 9"+GET_IMAGE_URL1);

        list1 = (ListView)findViewById(R.id.listViewJobs);
        personList = new ArrayList<HashMap<String, String>>();
        editTextSort=(EditText)findViewById(R.id.editTextSort);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        getURLs1();


    }



    private void getImages6(){
        class GetImages extends AsyncTask<Void,Void,Void> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //      loading = ProgressDialog.show(getActivity(),"Refreshing People","Please wait...",false,false);
                Log.i("Congo 1", "check 6");

            }

            @Override
            protected void onPostExecute(Void v) {
                super.onPostExecute(v);
                //    loading.dismiss();
                //Toast.makeText(ImageListView.this,"Success",Toast.LENGTH_LONG).show();
                //CustomList customList = new CustomList(getActivity(),GetAllImages.company,GetAllImages.news,GetAllImages.likes,GetAllImages.bitmaps,GetAllImages.bitmaps1);
                //list.setAdapter(customList);
                // arraylist.add(wp);
                Log.i("Congo 1", "check 7");

                adapter1 = new ListViewAdapter5(displayjobroles.this, GetAllImages6.arraylist);
                Log.i("World popu people", "im:should go to listview");
                Log.i("Congo 1","check 8");

                // Binds the Adapter to the ListView
                list1.setAdapter(adapter1);
                Log.i("aadapter setu", "im:should go to listview");
                Log.i("Congo 1","check 9");



                editTextSort.addTextChangedListener(new TextWatcher() {


                    @Override
                    public void afterTextChanged(Editable arg0) {
                        // TODO Auto-generated method stub
                        String text = editTextSort.getText().toString().toLowerCase(Locale.getDefault());
                        adapter1.filter(text);
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
            protected Void doInBackground(Void... voids) {
                try {
                    getAlImages6.getAllImages();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        GetImages getImages6 = new GetImages();
        getImages6.execute();
    }

    private void getURLs1() {
        class GetURLs extends AsyncTask<String,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                Log.i("Congo 1", "check 1");

                super.onPreExecute();
                // loading = ProgressDialog.show(getActivity(),"Loading1...","Please Wait...",true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.i("Congo 1", "check 2");

                // loading.dismiss();
                Log.i("Congo 1", "check 3");

                getAlImages6 = new GetAllImages6(s);
                getImages6();
                Log.i("Congo 1", "check 4");

            }

            @Override
            protected String doInBackground(String... strings) {
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }

                    return sb.toString().trim();

                }catch(Exception e){
                    return null;
                }
            }
        }
        GetURLs gu = new GetURLs();
        gu.execute(GET_IMAGE_URL1);
        Log.i("Congo 1", "check 5");

    }













    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_displayjob, menu);
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
