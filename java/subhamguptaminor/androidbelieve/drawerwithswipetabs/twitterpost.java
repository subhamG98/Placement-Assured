package subhamguptaminor.androidbelieve.drawerwithswipetabs;

import android.app.ListActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.CompactTweetView;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TimelineResult;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.TweetViewFetchAdapter;

import java.util.Arrays;
import java.util.List;


public class twitterpost extends ListActivity {

    List<Long> tweetIds = Arrays.asList(503435417459249153L, 510908133917487104L, 473514864153870337L, 477788140900347904L);
    final TweetViewFetchAdapter adapter =
            new TweetViewFetchAdapter<CompactTweetView>(twitterpost.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitterpost);
        final SwipeRefreshLayout swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);

        final SearchTimeline searchTimeline = new SearchTimeline.Builder()
                .query("#placement")
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(twitterpost.this)
                .setTimeline(searchTimeline)
                .build();
        setListAdapter(adapter);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeLayout.setRefreshing(true);
                adapter.refresh(new Callback<TimelineResult<Tweet>>() {
                    @Override
                    public void success(Result<TimelineResult<Tweet>> result) {
                        swipeLayout.setRefreshing(false);
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        // Toast or some other action
                    }
                });
            }
        });

    }
      /*  setListAdapter(adapter);
        adapter.setTweetIds(tweetIds, new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {
                // my custom actions
            }

            @Override
            public void failure(TwitterException exception) {
                // Toast.makeText(...).show();
            }
        });

    }    */


/*
        // TODO: Use a more specific parent
        final ViewGroup parentView = (ViewGroup) getWindow().getDecorView().getRootView();
        // TODO: Base this Tweet ID on some data from elsewhere in your app
        long tweetId = 631879971628183552L;
        TweetUtils.loadTweet(tweetId, new Callback<Tweet>() {
            @Override
            public void success(Result<Tweet> result) {
                TweetView tweetView = new TweetView(twitterpost.this, result.data);
                parentView.addView(tweetView);
            }

            @Override
            public void failure(TwitterException exception) {
                Log.i("TwitterKit", "Load Tweet failure", exception);
            }
        });
*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_twitterpost, menu);
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
