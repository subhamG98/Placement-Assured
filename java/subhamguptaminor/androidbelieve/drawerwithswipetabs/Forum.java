package subhamguptaminor.androidbelieve.drawerwithswipetabs;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;


public class Forum extends ActionBarActivity {


    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);


        webView = (WebView)findViewById(R.id.web);
        //  webView.addJavascriptInterface(new ShowWebChartActivity.WebAppInterface(), "Android");
        webView.setPadding(0, 0, 0, 0);
        webView.setInitialScale(100);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://hello45.esy.es/chart2.php");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_forum, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
