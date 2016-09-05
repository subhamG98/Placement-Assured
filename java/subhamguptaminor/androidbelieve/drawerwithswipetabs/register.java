package subhamguptaminor.androidbelieve.drawerwithswipetabs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
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


public class register extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextUn;
    private EditText editTextPass;
    private EditText editTextAdd;
    private Button button2;
    private Button buttonDp1;
    private int PICK_IMAGE_REQUEST = 1;
    private ImageView imageView2;
    private Bitmap bitmap1;
    int variablesAdded = 0;
    private Uri filePath;
    public static String UPLOAD_URL = "http://hello45.esy.es/DpUpload/upload.php";
    public static final String TAG = "MY MESSAGE";
    public static final String UPLOAD_KEY = "image";
    public static String name = "";
    public static String un = "";
    public static String address = "";
    public static String pass = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        /*DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .5));
*/
        imageView2 = (ImageView) findViewById(R.id.imageViewDp);

        buttonDp1 = (Button) findViewById(R.id.buttonDp1);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextAdd = (EditText) findViewById(R.id.editTextAdd);
        editTextUn = (EditText) findViewById(R.id.editTextUn);
        editTextPass = (EditText) findViewById(R.id.editTextPass);
        button2 = (Button) findViewById(R.id.button2);
        buttonDp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showFileChooser();
            }
        });

     button2.setOnClickListener(
             new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     editTextName = (EditText) findViewById(R.id.editTextName);
                     editTextAdd = (EditText) findViewById(R.id.editTextAdd);
                     editTextUn = (EditText) findViewById(R.id.editTextUn);
                     editTextPass = (EditText) findViewById(R.id.editTextPass);
                     name = editTextName.getText().toString();
                     un = editTextUn.getText().toString();
                     address = editTextAdd.getText().toString();
                     pass = editTextPass.getText().toString();


                     if (name != null && !name.equals("")) {
                         if (variablesAdded == 0)
                             try {
                                 UPLOAD_URL = UPLOAD_URL + "?name=" + URLEncoder.encode(name, "UTF-8");
                             } catch (UnsupportedEncodingException e) {
                                 e.printStackTrace();
                             }
                         else
                             try {
                                 UPLOAD_URL = UPLOAD_URL + "&name=" + URLEncoder.encode(name, "UTF-8");
                             } catch (UnsupportedEncodingException e) {
                                 e.printStackTrace();
                             }
                         variablesAdded++;
                     }

                     if (un != null && !un.equals("")) {
                         if (variablesAdded == 0)
                             try {
                                 UPLOAD_URL = UPLOAD_URL + "?un=" + URLEncoder.encode(un, "UTF-8");
                             } catch (UnsupportedEncodingException e) {
                                 e.printStackTrace();
                             }
                         else
                             try {
                                 UPLOAD_URL = UPLOAD_URL + "&un=" + URLEncoder.encode(un, "UTF-8");
                             } catch (UnsupportedEncodingException e) {
                                 e.printStackTrace();
                             }
                         variablesAdded++;
                     }
                     if (pass != null && !pass.equals("")) {
                         if (variablesAdded == 0)
                             try {
                                 UPLOAD_URL = UPLOAD_URL + "?pass=" + URLEncoder.encode(pass, "UTF-8");
                             } catch (UnsupportedEncodingException e) {
                                 e.printStackTrace();
                             }
                         else
                             try {
                                 UPLOAD_URL = UPLOAD_URL + "&pass=" + URLEncoder.encode(pass, "UTF-8");
                             } catch (UnsupportedEncodingException e) {
                                 e.printStackTrace();
                             }
                         variablesAdded++;
                     }
                     if (address != null && !address.equals("")) {
                         if (variablesAdded == 0)
                             try {
                                 UPLOAD_URL = UPLOAD_URL + "?address=" + URLEncoder.encode(address, "UTF-8");
                             } catch (UnsupportedEncodingException e) {
                                 e.printStackTrace();
                             }
                         else
                             try {
                                 UPLOAD_URL = UPLOAD_URL + "&address=" + URLEncoder.encode(address, "UTF-8");
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
     );

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
            Log.i("dfdf", "Filepath=" + filePath);
            try {
                bitmap1 = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                Log.i("dfdf", "Bitmap1=" + bitmap1);


                imageView2.setImageBitmap(bitmap1);
                Log.i("dfdf", "Filepath=" + imageView2);

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

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage() {
        class UploadImage extends AsyncTask<Bitmap, Void, String> {

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(register.this, "Uploading Profile", "Please wait...", true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }

            @Override
            protected String doInBackground(Bitmap... params) {

                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);

                HashMap<String, String> data = new HashMap<>();
                data.put(UPLOAD_KEY, uploadImage);

                String result = rh.sendPostRequest(UPLOAD_URL, data);

                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap1);
    }

}

