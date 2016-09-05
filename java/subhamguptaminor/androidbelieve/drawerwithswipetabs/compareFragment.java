package subhamguptaminor.androidbelieve.drawerwithswipetabs;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.webkit.WebView;


public class compareFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
View v;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    WebView webView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_compare, container, false);

       /* Intent i=new Intent("com.androidbelieve.drawerwithswipetabs.ShowWebChartActivity");
        startActivity(i);
*/
        webView = (WebView)v.findViewById(R.id.web);
      //  webView.addJavascriptInterface(new ShowWebChartActivity.WebAppInterface(), "Android");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://hello45.esy.es/chart2.php");


    return v;

    }


}
