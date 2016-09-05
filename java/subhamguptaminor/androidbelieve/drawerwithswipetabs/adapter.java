package subhamguptaminor.androidbelieve.drawerwithswipetabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import subhamguptaminor.androidbelieve.drawerwithswipetabs.Model.Flower;
import subhamguptaminor.androidbelieve.drawerwithswipetabs.Model.Flower4;
import com.squareup.picasso.Picasso;

import java.util.List;


public class adapter extends ArrayAdapter<Flower> {

//    String url="http://services.hanselandpetal.com/photos/";
     //String url="http://hello45.esy.es/PhotoUpload/getAll.php";

    Integer flag=0;
    private Context context;
    private List<Flower> flowerList;
    private List<Flower4> flowerList1;
    ImageButton addfollower;

    public adapter(Context context, int resource, List<Flower> objects,List<Flower4> objects1) {
        super(context, resource, objects);
        this.context = context;
        this.flowerList = objects;
        this.flowerList1 = objects1;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (position == 1) {
            try {
                View view = inflater.inflate(R.layout.list_item_file, parent, false);


                final Flower4 flower1 = flowerList1.get(position);
                final TextView name_mutual = (TextView) view.findViewById(R.id.name_mutual);
                name_mutual.setText(flower1.getMutualfriend_name());
                final TextView address_mutual = (TextView) view.findViewById(R.id.address_mutual);
                address_mutual.setText(flower1.getMutualfriend_address());
                Log.i("ssss", "" + address_mutual);


                ImageView img1 = (ImageView) view.findViewById(R.id.imageView3);
                Picasso.with(getContext()).load("" + flower1.getMutualfriend_dp()).resize(300, 300).into(img1);
                addfollower = (ImageButton) view.findViewById(R.id.addfollower);
                addfollower.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(context, SingleItemView.class);
                                i.putExtra("name", name_mutual.getText().toString());
                                i.putExtra("address", address_mutual.getText().toString());
                                i.putExtra("image", flower1.getMutualfriend_dp());


                                context.startActivity(i);
                            }
                        }
                );


                return view;
            } catch (Exception e) {
                //Toast.makeText(getContext(), "Error Ocurred" + e, Toast.LENGTH_SHORT).show();

                e.printStackTrace();
                View view = inflater.inflate(R.layout.list_layout, parent, false);
                Flower flower = flowerList.get(position);
                TextView company = (TextView) view.findViewById(R.id.company1);
                company.setText(flower.getCompany());
                TextView news = (TextView) view.findViewById(R.id.news1);
                news.setText(flower.getNews());
                final TextView likes = (TextView) view.findViewById(R.id.likes);
                likes.setText(flower.getLikes());

                final ImageView img2 = (ImageView) view.findViewById(R.id.imageId1);
                img2.setImageResource(R.drawable.unlike);

                ImageView img1 = (ImageView) view.findViewById(R.id.imageDp);
                Picasso.with(getContext()).load("" + flower.getDp()).resize(200, 200).into(img1);

                ImageView img = (ImageView) view.findViewById(R.id.imageDownloaded1);
                Picasso.with(getContext()).load("" + flower.getUrl()).resize(500, 575).into(img);

                if (flag == 0) {
                    img2.setImageResource(R.drawable.unlike);
                } else {
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


        } else {

            View view = inflater.inflate(R.layout.list_layout, parent, false);
            Flower flower = flowerList.get(position);
            TextView company = (TextView) view.findViewById(R.id.company1);
            company.setText(flower.getCompany());
            TextView news = (TextView) view.findViewById(R.id.news1);
            news.setText(flower.getNews());
            final TextView likes = (TextView) view.findViewById(R.id.likes);
            likes.setText(flower.getLikes());

            final ImageView img2 = (ImageView) view.findViewById(R.id.imageId1);
            img2.setImageResource(R.drawable.unlike);

            ImageView img1 = (ImageView) view.findViewById(R.id.imageDp);
            Picasso.with(getContext()).load("" + flower.getDp()).resize(200, 200).into(img1);

            ImageView img = (ImageView) view.findViewById(R.id.imageDownloaded1);
            Picasso.with(getContext()).load("" + flower.getUrl()).resize(500, 575).into(img);

            if (flag == 0) {
                img2.setImageResource(R.drawable.unlike);
            } else {
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

}
