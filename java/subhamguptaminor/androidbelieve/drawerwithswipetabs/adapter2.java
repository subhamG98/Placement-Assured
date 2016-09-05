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

import subhamguptaminor.androidbelieve.drawerwithswipetabs.Model.Flower2;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class adapter2 extends ArrayAdapter<Flower2> {

//    String url="http://services.hanselandpetal.com/photos/";
     //String url="http://hello45.esy.es/PhotoUpload/getAll.php";

    private Context context;
    private List<Flower2> flowerList;
    private ArrayList<Flower2> arraylist;

    public adapter2(Context context, int resource, List<Flower2> objects) {
        super(context, resource, objects);
        this.context = context;
        this.flowerList = objects;

        this.arraylist = new ArrayList<Flower2>();
        this.arraylist.addAll(flowerList);

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listview_item, parent, false);
        Flower2 flower = flowerList.get(position);
        TextView name = (TextView) view.findViewById(R.id.name_mutual);
        name.setText(flower.getName());
        TextView address = (TextView) view.findViewById(R.id.address_mutual);
        address.setText(flower.getAddress());


        ImageView imageView2 = (ImageView) view.findViewById(R.id.imageView2);
        Picasso.with(getContext()).load(""+flower.getImage()).resize(150, 150).into(imageView2);

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(context, SingleItemView.class);
                // Pass all data rank
                intent.putExtra("name",
                        (flowerList.get(position).getName()));
                // Pass all data country
                intent.putExtra("address",
                        (flowerList.get(position).getAddress()));
                // Pass all data population

                intent.putExtra("image",flowerList.get(position).getImage());
                // Pass all data flag
                // Start SingleItemView Class
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });








        return view;
    }
    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        flowerList.clear();
        if (charText.length() == 0) {
            flowerList.addAll(arraylist);
        } else {
            for (Flower2 wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    flowerList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }



}
