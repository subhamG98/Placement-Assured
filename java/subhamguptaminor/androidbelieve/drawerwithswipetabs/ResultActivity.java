package subhamguptaminor.androidbelieve.drawerwithswipetabs;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.RatingBar;
import android.widget.TextView;
public class ResultActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);
//get rating bar object
        RatingBar bar=(RatingBar)findViewById(R.id.ratingBar1);
//get text view
        TextView t=(TextView)findViewById(R.id.textResult);
//get score
        Bundle b = getIntent().getExtras();
        int score= b.getInt("score");
//display score
        bar.setRating(score);
        switch (score)
        {
            case 1:
            case 2: t.setText("OOPS! Better Luck Next Time!");
                break;
            case 3:
            case 4:t.setText("Hmmmm.. Placement Assured suggests you to work harder!!");
                break;
            case 5:t.setText("Congratulations!! You did exceptionally Well");
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
    //    getMenuInflater().inflate(R.menu.activity_result, menu);
        return true;
    }
}