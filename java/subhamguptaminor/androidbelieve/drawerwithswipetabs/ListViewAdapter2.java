package subhamguptaminor.androidbelieve.drawerwithswipetabs;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter2 extends BaseAdapter {

	// Declare Variables
	Context mContext;
	LayoutInflater inflater;
	private List<WorldPopulation2> worldpopulationlist = null;
	private ArrayList<WorldPopulation2> arraylist;


	public ListViewAdapter2(Context context,
							List<WorldPopulation2> worldpopulationlist) {
		mContext = context;
		this.worldpopulationlist = worldpopulationlist;
		inflater = LayoutInflater.from(mContext);
		this.arraylist = new ArrayList<WorldPopulation2>();
		this.arraylist.addAll(worldpopulationlist);
	}

	public class ViewHolder {
		TextView details;
		TextView price;
		ImageView image;


	}

	@Override
	public int getCount() {
		return worldpopulationlist.size();
	}

	@Override
	public WorldPopulation2 getItem(int position) {
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

//		cart=(ImageButton)view.findViewById(R.id.imageButton2);

		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.listview_books, null);
			// Locate the TextViews in listview_item.xml
			holder.details = (TextView) view.findViewById(R.id.details);
			holder.price = (TextView) view.findViewById(R.id.price);
			holder.image = (ImageView) view.findViewById(R.id.image);



			// Locate the ImageView in listview_item.xml
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
			Log.i("Congo 1","check 13");

		}
		// Set the results into TextViews
		holder.details.setText(worldpopulationlist.get(position).getRank());
		holder.price.setText(worldpopulationlist.get(position).getPrice());

		holder.image.setImageBitmap(Bitmap.createScaledBitmap(worldpopulationlist.get(position).getPopulation(), 100, 130, false));

		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Log.i("Allcool","check webview");

				Intent i=new Intent("subhamguptaminor.androidbelieve.drawerwithswipetabs.booksdetailed");
				mContext.startActivity(i);

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
			for (WorldPopulation2 wp : arraylist) {
				if (wp.getRank().toLowerCase(Locale.getDefault())
						.contains(charText)) {
					worldpopulationlist.add(wp);
				}
			}
		}
		notifyDataSetChanged();
	}

}
