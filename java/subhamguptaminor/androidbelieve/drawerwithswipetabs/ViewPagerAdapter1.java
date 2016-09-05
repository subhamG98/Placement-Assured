package subhamguptaminor.androidbelieve.drawerwithswipetabs;
import android.app.Activity;
import android.content.Context;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewPagerAdapter1 extends PagerAdapter {
    int size;
    Activity act;
    View layout;
    TextView pagenumber1,pagenumber2,pagenumber3,pagenumber4,pagenumber5;
    ImageView pageImage;
    Button click;
    public ViewPagerAdapter1(FragmentActivity mainActivity, int noofsize) {
        // TODO Auto-generated constructor stub
        size = noofsize;
        act = mainActivity;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return size;
    }
    @Override
    public Object instantiateItem(View container, int position) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = (LayoutInflater) act
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = inflater.inflate(R.layout.pages1, null);
 /*       pagenumber1 = (TextView)layout.findViewById(R.id.pagenumber1);
        pagenumber2 = (TextView)layout.findViewById(R.id.pagenumber2);
        pagenumber3 = (TextView)layout.findViewById(R.id.pagenumber3);
        pagenumber4 = (TextView)layout.findViewById(R.id.pagenumber4);
        pagenumber5 = (TextView)layout.findViewById(R.id.pagenumber5);
   */
        pageImage = (ImageView)layout.findViewById(R.id.imageView1);
        int pagenumberTxt=position + 1;
        //pagenumber1.setText("Now your in Page No  " +pagenumberTxt );

        try {
            if(pagenumberTxt == 1){
                pageImage.setBackgroundResource(R.drawable.bgf11);
            }
            else if(pagenumberTxt == 2){
                pageImage.setBackgroundResource(R.drawable.bgf22);
            }
            else if(pagenumberTxt == 3){
                pageImage.setBackgroundResource(R.drawable.bgf33);
            }
            else {
                pageImage.setBackgroundResource(R.drawable.bgf44);

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ((ViewPager) container).addView(layout, 0);
        return layout;
    }
    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);
    }
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == ((View) arg1);
    }
    @Override
    public Parcelable saveState() {
        return null;
    }

    // }
}