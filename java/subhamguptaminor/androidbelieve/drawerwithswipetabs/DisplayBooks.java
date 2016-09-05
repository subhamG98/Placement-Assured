package subhamguptaminor.androidbelieve.drawerwithswipetabs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

public class DisplayBooks extends ActionBarActivity {

    EditText editText4;

    public static String GET_IMAGE_URL1="http://hello45.esy.es/BookUpload/getAllImages.php";
    String myJSON1;
    private static final String TAG_RESULTS1="result1";
    JSONArray peoples1 = null;
    ArrayList<HashMap<String, String>> personList1;
    ListView list1;
    ListViewAdapter2 adapter1;
    public GetAllImages3 getAlImages3;
     public String category="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_books);
        Intent i = getIntent();
        category = i.getStringExtra("category");
        GET_IMAGE_URL1="http://hello45.esy.es/BookUpload/getAllImages.php";
        GET_IMAGE_URL1=GET_IMAGE_URL1.concat("?category="+category);

         Log.i("imp", "Check site" + GET_IMAGE_URL1);
        list1 = (ListView)findViewById(R.id.listViewBooks);

        editText4=(EditText)findViewById(R.id.editText4);

        getURLs1();



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        list1.setAdapter(null);
        list1.invalidateViews();

         }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_books, menu);
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


    private void getImages(){
        class GetImages extends AsyncTask<Void,Void,Void> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //      loading = ProgressDialog.show(getActivity(),"Refreshing People","Please wait...",false,false);
                Log.i("Congob 1", "check 6");

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
                //list1.setAdapter(null);
                adapter1 = new ListViewAdapter2(DisplayBooks.this, GetAllImages3.arraylist);
                Log.i("World popu people", "im:should go to listview");
                Log.i("Congo 1","check 8");

                // Binds the Adapter to the ListView
                list1.setAdapter(adapter1);
                Log.i("aadapter setu", "im:should go to listview");
                Log.i("Congo 1","check 9");



                editText4.addTextChangedListener(new TextWatcher() {


                    @Override
                    public void afterTextChanged(Editable arg0) {
                        // TODO Auto-generated method stub
                        String text = editText4.getText().toString().toLowerCase(Locale.getDefault());
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
                        list1.setVisibility(View.VISIBLE);
                        // TODO Auto-generated method stub
                    }
                });





            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    getAlImages3.getAllImages();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        GetImages getImages3 = new GetImages();
        getImages3.execute();
    }

    private void getURLs1() {
        class GetURLs extends AsyncTask<String,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                Log.i("Congob 1", "check 1");

                super.onPreExecute();
                // loading = ProgressDialog.show(getActivity(),"Loading1...","Please Wait...",true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.i("Congob 1", "check 2");

                // loading.dismiss();
                Log.i("Congob 1", "check 3");

                getAlImages3 = new GetAllImages3(s);
                Log.i("checkb22","");

                getImages();
                Log.i("Congob 1", "check 4");

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
                            Log.i("checkb1",""+sb.toString().trim());
                    return sb.toString().trim();

                }catch(Exception e){
                    return null;
                }
            }
        }
        GetURLs gu = new GetURLs();
        gu.execute(GET_IMAGE_URL1);
        Log.i("Congob 1", "check 5");

    }



}
