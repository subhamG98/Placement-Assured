package subhamguptaminor.androidbelieve.drawerwithswipetabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Ratan on 7/29/2015.
 */
public class SocialFragment extends Fragment {
    int noofsize = 5;
    ViewPager myPager = null;
    int count = 0;
    Timer timer;


    GridView grid;
    String[] web = {
            "Bank",
            "Science",
            "Humanities",
            "Financial",
            "Engineering",
            "School",
            "Algorithms","Entrance","General"


    } ;
    int[] imageId = {
            R.drawable.c1,
            R.drawable.c2,
            R.drawable.c3,
            R.drawable.c4,
            R.drawable.c5,
            R.drawable.c6,
            R.drawable.c7,
            R.drawable.c8,
            R.drawable.c9



    };
    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v =inflater.inflate(R.layout.social_layout,container,false);



        //ViewPager Adapter to set image
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity(),noofsize);
        myPager = (ViewPager) v.findViewById(R.id.reviewpager);
        myPager.setAdapter(adapter);
        myPager.setCurrentItem(0);

        // Timer for auto sliding
        timer  = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (count <= 5) {
                            myPager.setCurrentItem(count);
                            count++;
                        } else {
                            count = 0;
                            myPager.setCurrentItem(count);
                        }
                    }
                });
            }
        }, 100, 3000);









        CustomGrid adapter1 = new CustomGrid(getActivity(), web, imageId);
        grid=(GridView)v.findViewById(R.id.grid);
        grid.setAdapter(adapter1);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getActivity(), "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();
                Intent i=new Intent("subhamguptaminor.androidbelieve.drawerwithswipetabs.DisplayBooks");
                i.putExtra("category", web[position]);

                startActivity(i);

            }
        });
        return v;



//http://javatechig.com/android/android-viewflipper-example
    }

    public void onPause(){

        super.onPause();

        timer.cancel();

    }

    public void onDestroyView()
    {
        super.onDestroyView();
        timer.cancel();

    }






}
