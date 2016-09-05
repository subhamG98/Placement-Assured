package subhamguptaminor.androidbelieve.drawerwithswipetabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import subhamguptaminor.androidbelieve.drawerwithswipetabs.Model.Flower3;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class adapter3 extends ArrayAdapter<Flower3> {

//    String url="http://services.hanselandpetal.com/photos/";
     //String url="http://hello45.esy.es/PhotoUpload/getAll.php";

    Integer flag=0;
    private Context context;
    private List<Flower3> flowerList;
    private ArrayList<Flower3> arraylist;

    public adapter3(Context context, int resource, List<Flower3> objects) {
        super(context, resource, objects);
        this.context = context;
        this.flowerList = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_chatscreen, parent, false);
        Flower3 flower = flowerList.get(position);
        TextView name = (TextView) view.findViewById(R.id.company1);
        name.setText(flower.getName());
        ImageView image = (ImageView) view.findViewById(R.id.imageDownloaded1);
        Picasso.with(getContext()).load(""+flower.getImage()).resize(150, 150).into(image);

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(context, chatmessage.class);
                // Pass all data rank
                intent.putExtra("receiver",
                        (flowerList.get(position).getName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });





        return view;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        flowerList.clear();
        if (charText.length() == 0) {
            flowerList.addAll(arraylist);
        } else {
            for (Flower3 wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    flowerList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }




}
