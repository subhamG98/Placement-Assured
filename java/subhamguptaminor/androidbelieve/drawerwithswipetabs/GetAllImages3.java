package subhamguptaminor.androidbelieve.drawerwithswipetabs;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class GetAllImages3 {

    public static String[] details;
    public static String[] price;

    public static Bitmap[] bitmaps2;

    public static final String JSON_ARRAY="result";
    public static final String IMAGE_URL = "image";
    private static final String TAG_DETAILS = "details";
    private static final String TAG_PRICE = "price";

    public static ArrayList<WorldPopulation2> arraylist = new ArrayList<WorldPopulation2>();
    ListViewAdapter2 adapter1;
    ListView list1;
    private String json;
    private JSONArray urls;
    public GetAllImages3(String json){
        this.json = json;
        try {
            JSONObject jsonObject = new JSONObject(json);
            urls = jsonObject.getJSONArray(JSON_ARRAY);

            Log.i("checkb122","urls="+urls);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Bitmap getImage(JSONObject jo){
        URL url = null;

        Bitmap image = null;

        try {
            url = new URL(jo.getString(IMAGE_URL));
            Log.i("Check book",""+IMAGE_URL);
            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            //     image1.setImageBitmap(Bitmap.createScaledBitmap(bitmaps1[position], 150, 150, false));
            Log.i("circular","image="+image);
       //     roundedImage = new RoundImage(image);
        //    image1=roundedImage.getBitmap();
         //   Log.i("circular1","imag1e="+image1);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return image;
    }



    public void getAllImages() throws JSONException {
     Log.i("checking url lrngth",""+urls.length());
        details = new String[urls.length()];
        price = new String[urls.length()];

        bitmaps2 = new Bitmap[urls.length()];


        for(int i=0;i<urls.length();i++){
            details[i] = urls.getJSONObject(i).getString(TAG_DETAILS);
            price[i] = urls.getJSONObject(i).getString(TAG_PRICE);

            JSONObject jsonObject = urls.getJSONObject(i);
            bitmaps2[i]=getImage(jsonObject);
            WorldPopulation2 wp = new WorldPopulation2(details[i],price[i],bitmaps2[i]);
            arraylist.add(wp);
        }
    }
}