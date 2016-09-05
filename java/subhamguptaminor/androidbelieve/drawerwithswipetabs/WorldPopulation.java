package subhamguptaminor.androidbelieve.drawerwithswipetabs;

import android.graphics.Bitmap;
import android.util.Log;

public class WorldPopulation {
	private String name;
	private String address;
	private Bitmap image;

	public WorldPopulation(String name, String address, Bitmap image) {
		this.name = name;
		this.address = address;
		this.image = image;

	}


	public String getRank() {
		return this.name;
	}

	public String getCountry() {
		return this.address;
	}

	public  Bitmap getPopulation() {
		return this.image;
	}
}
