package subhamguptaminor.androidbelieve.drawerwithswipetabs;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter5 extends BaseAdapter {

	// Declare Variables
	Context mContext;
	LayoutInflater inflater;
	private List<WorldPopulation5> worldpopulationlist = null;
	private ArrayList<WorldPopulation5> arraylist;
	public ListViewAdapter5(Context context,
							List<WorldPopulation5> worldpopulationlist) {
		mContext = context;
		this.worldpopulationlist = worldpopulationlist;
		inflater = LayoutInflater.from(mContext);
		this.arraylist = new ArrayList<WorldPopulation5>();
		this.arraylist.addAll(worldpopulationlist);
	}

	public class ViewHolder {
		TextView title;

		TextView salary;

		TextView location;
		TextView skills;


	}

	@Override
	public int getCount() {
		return worldpopulationlist.size();
	}

	@Override
	public WorldPopulation5 getItem(int position) {
		return worldpopulationlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup parent) {
		final ViewHolder holder;
		Log.i("World popu", "im:" );
		Log.i("Congo 1","check 11");

		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.listview_jobs_roles, null);
			// Locate the TextViews in listview_item.xml
			holder.title = (TextView) view.findViewById(R.id.title);
			holder.salary = (TextView) view.findViewById(R.id.salary);
			holder.location = (TextView) view.findViewById(R.id.location);
			holder.skills = (TextView) view.findViewById(R.id.skills);


			// Locate the ImageView in listview_item.xml
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
			Log.i("Congo 1","check 13");

		}
		// Set the results into TextViews
		holder.title.setText(worldpopulationlist.get(position).getRank());
		holder.salary.setText(worldpopulationlist.get(position).getsalary());
		holder.location.setText(worldpopulationlist.get(position).getlocation());
		holder.skills.setText(worldpopulationlist.get(position).getskills());
		//holder.image.setImageResource(worldpopulationlist.get(position).getPopulation());
		//holder.image.setImageBitmap(Bitmap.createScaledBitmap(worldpopulationlist.get(position).getPopulation(), 100, 100, false));

		Log.i("Congo 1", "check 14");
		// Set the results into ImageView
		// Listen for ListView Item Click
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Send single item click data to SingleItemView Class
			//	Intent intent = new Intent(mContext, displayjob.class);
				// Pass all data rank
			//	intent.putExtra("joblocation",
			//			(worldpopulationlist.get(position).getRank()));
				// Pass all data population

				// Pass all data flag
				// Start SingleItemView Class
			//	mContext.startActivity(intent);
				Log.i("Congo 111", "check 14");
			}
		});

		return view;
	}

	// Filter Class
	public void filter(String charText) {
		charText = charText.toLowerCase(Locale.getDefault());
		worldpopulationlist.clear();
		if (charText.length() == 0) {
			worldpopulationlist.addAll(arraylist);
		} else {
			for (WorldPopulation5 wp : arraylist) {
				if (wp.getRank().toLowerCase(Locale.getDefault())
						.contains(charText)) {
					worldpopulationlist.add(wp);
				}
			}
		}
		notifyDataSetChanged();
	}

}
