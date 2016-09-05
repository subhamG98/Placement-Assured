package subhamguptaminor.androidbelieve.drawerwithswipetabs;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import subhamguptaminor.androidbelieve.drawerwithswipetabs.Model.Flower;
import com.squareup.picasso.Picasso;

import java.util.List;


public class adapter1 extends ArrayAdapter<Flower> {

//    String url="http://services.hanselandpetal.com/photos/";
     //String url="http://hello45.esy.es/PhotoUpload/getAll.php";

Integer flag=0;
    private Context context;
    private List<Flower> flowerList;
    public adapter1(Context context, int resource, List<Flower> objects) {
        super(context, resource, objects);
        this.context = context;
        this.flowerList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_layout1, parent, false);
        Flower flower = flowerList.get(position);
        TextView company = (TextView) view.findViewById(R.id.company1);
        company.setText(flower.getCompany());
        TextView news = (TextView) view.findViewById(R.id.news1);
        news.setText(flower.getNews());
        TextView likes = (TextView) view.findViewById(R.id.likes1);
        likes.setText(flower.getLikes());

        final ImageView img2 = (ImageView) view.findViewById(R.id.imageId1);
        img2.setImageResource(R.drawable.unlike);

        ImageView img1 = (ImageView) view.findViewById(R.id.imageDp1);
        Picasso.with(getContext()).load(""+flower.getDp()).resize(150, 150).into(img1);

        ImageView img = (ImageView) view.findViewById(R.id.imageDownloaded1);
        Picasso.with(getContext()).load(""+flower.getUrl()).resize(500, 575).into(img);

        if(flag==0) {
            img2.setImageResource(R.drawable.unlike);
        }
        else
        {
            img2.setImageResource(R.drawable.like);

        }
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 0) {
                    flag = 1;
                    img2.setImageResource(R.drawable.like);

                } else {
                    flag = 0;
                    img2.setImageResource(R.drawable.unlike);

                }

            }
        });









        return view;

    }
}
