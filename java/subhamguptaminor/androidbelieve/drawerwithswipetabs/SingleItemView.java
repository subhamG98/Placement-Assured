package subhamguptaminor.androidbelieve.drawerwithswipetabs;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
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
import android.app.ListActivity;

import subhamguptaminor.androidbelieve.drawerwithswipetabs.Model.Flower;
import subhamguptaminor.androidbelieve.drawerwithswipetabs.Network.api1;
import com.squareup.picasso.Picasso;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SingleItemView extends ListActivity {
	// Declare Variables
	TextView txtname;
	TextView txtaddress;
	String myJSON;
	private static final String TAG_RESULTS="result";
	private static final String TAG_CHECK="check";
	JSONArray peoples1 = null;
	ArrayList<HashMap<String, String>> personList1;
	ImageView imgflag;
	String name;
	String address;
	String image;
	public String check1;
	public Button ButtonFollow;
	public Button ButtonUnfollow;
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
	//ListViewAdapter adapter;
	JSONArray peoples = null;
	ArrayList<HashMap<String, String>> personList;
	public static String GET_IMAGE_URL="http://hello45.esy.es/PhotoUpload/getAllImagesPeople.php";
	public static String GET_IMAGE_URL1="http://hello45.esy.es/PhotoUpload/getAllImagesPeople.php";

	public ArrayAdapter<String> adapter;
	ListView list;
	public String username;
	List<Flower> flowerList;

	public static final String SHARED_PREF_NAME = "myloginapp";

	public static final String USERNAME_SHARED_PREF = "email";

	public static String s = "";


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.singleitemview);
		// Get the intent from ListViewAdapter
		Intent i = getIntent();
		// Get the results of rank
		name = i.getStringExtra("name");
		// Get the results of country
		address = i.getStringExtra("address");
		// Get the results of population
		image = i.getStringExtra("image");
	//	image=i.getBitmapExtra("image");
		// Get the results of flag


		final RestAdapter restadapter = new RestAdapter.Builder().setEndpoint("http://hello45.esy.es").build();
		api1 flowerapi = restadapter.create(api1.class);

		flowerapi.getData(name,address,new Callback<List<Flower>>() {
			@Override
			public void success(List<Flower> flowers, Response response) {
				flowerList = flowers;
				adapter1 adapt = new adapter1(getApplicationContext(), R.layout.list_layout1, flowerList);
				//ListView listView = (ListView) findViewById(R.id.list);


				Log.i("retro check",""+adapt);
				setListAdapter(adapt);
			}

			@Override
			public void failure(RetrofitError error) {
				Toast.makeText(getApplicationContext(), "Failed" + error + error.getUrl() + error.getBody(), Toast.LENGTH_SHORT).show();
				Log.i("dude", "" + error + error.getUrl() + error.getBody());
			}
		});







		GET_IMAGE_URL=GET_IMAGE_URL.concat("?reqcompany="+name+"&reqaddress="+address);
        GET_IMAGE_URL= GET_IMAGE_URL.replace(" ","+");
		Log.i("url check", "" + GET_IMAGE_URL);
		// Locate the TextViews in singleitemview.xml
		txtname = (TextView) findViewById(R.id.name_mutual);
		txtaddress = (TextView) findViewById(R.id.address_mutual);
		imgflag = (ImageView) findViewById(R.id.image);
		SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
		s = sharedPreferences.getString(USERNAME_SHARED_PREF, "Not Available");


		username=s;
		// Locate the ImageView in singleitemview.xml
//		imgflag = (ImageView) findViewById(R.id.flag);
		// Load the results into the TextViews
		txtname.setText(name);
		txtaddress.setText(address);

		Picasso.with(getApplicationContext()).load(""+image).resize(150, 150).into(imgflag);
//list = (ListView)findViewById(R.id.listView2);
		ButtonFollow=(Button)findViewById(R.id.ButtonFollow);
		ButtonUnfollow=(Button)findViewById(R.id.ButtonUnfollow);
		personList = new ArrayList<HashMap<String, String>>();

		getData();
		/*if(check1.equals("0")==true) {
			ButtonUnfollow.setVisibility(View.GONE);

			ButtonFollow.setVisibility(View.VISIBLE);
		}
		else
		{
			ButtonUnfollow.setVisibility(View.VISIBLE);

			ButtonFollow.setVisibility(View.GONE);
		}*/
		ButtonFollow.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						ButtonFollow.setVisibility(View.INVISIBLE);
						ButtonUnfollow.setVisibility(View.VISIBLE);

						insert(v);
					}
				}
		);

		ButtonUnfollow.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						ButtonUnfollow.setVisibility(View.INVISIBLE);
						ButtonFollow.setVisibility(View.VISIBLE);
				           insert1(v);
						}
				}
		);
//		getURLs();
		// Load the image into the ImageView
		//imgflag.setImageResource(flag);



	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		// getMenuInflater().inflate(R.menu.menu_login, menu);
		// return true;

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_main, menu);
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
	public void insert(View view){
		name=txtname.getText().toString();
        username=s;
		Log.i("bade gadbad ","n="+name+"u="+username);
		insertToDatabase(name, username);
	}

	private void insertToDatabase(String name,String username){
		class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
			@Override
			protected String doInBackground(String... params) {
				String paramname = params[0];
				String paramUsername = params[1];


				String name = txtname.getText().toString();
				String username1 = s;
				Log.i("bade gadbad1 ","n="+name+"u="+username1);

				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("name",name));
				nameValuePairs.add(new BasicNameValuePair("username", username1));

				try {
					HttpClient httpClient = new DefaultHttpClient();
					HttpPost httpPost = new HttpPost(
							"http://hello45.esy.es/updatefollower.php");
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

				Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
			}
		}
		SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
		sendPostReqAsyncTask.execute(name, username);
	}





	public void insert1(View view){
		name=txtname.getText().toString();
		username=s;
		Log.i("bade gadbad ","n="+name+"u="+username);
		insertToDatabase1(name, username);
	}

	private void insertToDatabase1(String name,String username){
		class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
			@Override
			protected String doInBackground(String... params) {
				String paramname = params[0];
				String paramUsername = params[1];


				String name = txtname.getText().toString();
				String username1 = s;
				Log.i("bade gadbad1 ","n="+name+"u="+username1);

				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("name",name));
				nameValuePairs.add(new BasicNameValuePair("username", username1));

				try {
					HttpClient httpClient = new DefaultHttpClient();
					HttpPost httpPost = new HttpPost(
							"http://hello45.esy.es/removefollower.php");
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

				Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
			}
		}
		SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
		sendPostReqAsyncTask.execute(name, username);
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
			//	Log.i("fdf", "result receiver:" + receiver1);


				String result = null;

				try{
					HttpClient httpClient = new DefaultHttpClient();
					//HttpPost httpPost = new HttpPost("http://hello45.esy.es/selectchat.php");
					HttpPost httpPost = new HttpPost("http://hello45.esy.es/checkfollower.php?name="+name+"&username="+username);
					Log.i("error detection", "result receiver:" + name +"username"+username);

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
                Log.i("in post","Post"+result);
				myJSON=result;
				showList();
			}
		}

		LoginAsync la = new LoginAsync();
		la.execute();

	}














	protected void showList(){
		try {
			JSONObject jsonObj = new JSONObject(myJSON);
			peoples = jsonObj.getJSONArray(TAG_RESULTS);
			Log.i("check 12","b"+peoples.length());
			for(int i=0;i<peoples.length();i++){
				JSONObject c = peoples.getJSONObject(i);
				String check = c.getString(TAG_CHECK);
				check1 = c.getString(TAG_CHECK);
                if(check1.equals("0")==true) {
                    ButtonUnfollow.setVisibility(View.INVISIBLE);

                    ButtonFollow.setVisibility(View.VISIBLE);
                }
                else
                {
                    ButtonUnfollow.setVisibility(View.VISIBLE);

                    ButtonFollow.setVisibility(View.INVISIBLE);
                }
               Log.i("check1==","b"+check1);
				HashMap<String,String> persons = new HashMap<String,String>();

				persons.put(TAG_CHECK,check);

				personList.add(persons);
			}






		} catch (JSONException e) {
			e.printStackTrace();
		}

	}











}