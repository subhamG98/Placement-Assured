package subhamguptaminor.androidbelieve.drawerwithswipetabs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

public class UploadStatus extends AppCompatActivity implements View.OnClickListener {




    public static  String UPLOAD_URL = "http://hello45.esy.es/PhotoUpload/upload.php";
    public static final String UPLOAD_KEY = "image";
    public static final String TAG = "MY MESSAGE";

    private int PICK_IMAGE_REQUEST = 1;

    private Button buttonChoose;
    private Button buttonUpload;
    private Button buttonView;
    private EditText editText3;

    private ImageView imageView;

    private Bitmap bitmap;
    int variablesAdded = 0;
    private Uri filePath;
    public static String company="";
 public static   String news="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_status);

        buttonChoose = (Button) findViewById(R.id.buttonDp1);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        //buttonView = (Button) findViewById(R.id.buttonViewImage);
        editText3=(EditText)findViewById(R.id.editText3);
        imageView = (ImageView) findViewById(R.id.imageView);

        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);

         company = MainActivity.username;
         news = editText3.getText().toString();
        Log.i("fddgdg","value of edittext msg is"+news);




    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            Log.i("dfdf","Filepath="+filePath);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                Log.i("dfdf","Bitmap1="+bitmap);


                imageView.setImageBitmap(bitmap);
                Log.i("dfdf", "Filepath=" + imageView);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

       /* else {
           filePath= Uri.parse("content://media/external/images/media/20161");
            Log.i("dfdf","Filepath xtra="+filePath);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                Log.i("dfdf","Bitmap1 xtra="+bitmap);


                imageView.setImageBitmap(bitmap);
                Log.i("dfdf", " image xtra=" + imageView);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
*/
        }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage(){
        class UploadImage extends AsyncTask<Bitmap,Void,String>{

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(UploadStatus.this, "Uploading Image", "Please wait...",true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                Intent i=new Intent("ma1");
                startActivity(i);
            }

            @Override
            protected String doInBackground(Bitmap... params) {

                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);

                HashMap<String,String> data = new HashMap<>();
                data.put(UPLOAD_KEY, uploadImage);

                String result = rh.sendPostRequest(UPLOAD_URL,data);

                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonChoose) {
            showFileChooser();
        }
        if(v == buttonUpload){


              news=editText3.getText().toString();
            if(company != null && !company.equals("")) {
                if(variablesAdded == 0)
                    try {
                        UPLOAD_URL = UPLOAD_URL + "?company=" + URLEncoder.encode(company, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                else
                    try {
                        UPLOAD_URL = UPLOAD_URL + "&company=" + URLEncoder.encode(company, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                variablesAdded++;
            }

            if(news != null && !news.equals("")) {
                if(variablesAdded == 0)
                    try {
                        UPLOAD_URL = UPLOAD_URL + "?news=" + URLEncoder.encode(news, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                else
                    try {
                        UPLOAD_URL = UPLOAD_URL + "&news=" + URLEncoder.encode(news, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                variablesAdded++;
            }

            try {
                URL url = new URL(UPLOAD_URL);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            uploadImage();
        }
    }
}