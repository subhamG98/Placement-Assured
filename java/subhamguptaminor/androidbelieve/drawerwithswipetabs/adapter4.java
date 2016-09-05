package subhamguptaminor.androidbelieve.drawerwithswipetabs;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import subhamguptaminor.androidbelieve.drawerwithswipetabs.Model.Flower4;
import com.squareup.picasso.Picasso;

import java.util.List;


public class adapter4 extends ArrayAdapter<Flower4> {

//    String url="http://services.hanselandpetal.com/photos/";
     //String url="http://hello45.esy.es/PhotoUpload/getAll.php";

    Integer flag=0;
    private Context context;
    private List<Flower4> flowerList;
    public adapter4(Context context, int resource, List<Flower4> objects) {
        super(context, resource, objects);
        this.context = context;
        this.flowerList = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item_file, parent, false);
        Flower4 flower = flowerList.get(position);
        TextView name_mutual = (TextView) view.findViewById(R.id.name_mutual);
        name_mutual.setText(flower.getMutualfriend_name());
        TextView address_mutual = (TextView) view.findViewById(R.id.address_mutual);
        address_mutual.setText(flower.getMutualfriend_address());


        ImageView img1 = (ImageView) view.findViewById(R.id.imageView3);
        Picasso.with(getContext()).load(""+flower.getMutualfriend_dp()).resize(150, 150).into(img1);








        return view;
    }
}
