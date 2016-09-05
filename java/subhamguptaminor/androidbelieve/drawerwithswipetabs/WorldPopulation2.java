package subhamguptaminor.androidbelieve.drawerwithswipetabs;

import android.graphics.Bitmap;

public class WorldPopulation2 {
	private String details;
	private String price;

	private Bitmap image;

	public WorldPopulation2(String details,String price, Bitmap image) {
		this.details = details;
		this.price = price;

		this.image = image;

	}


	public String getRank() {
		return this.details;
	}
	public String getPrice() {
		return this.price;
	}


	public  Bitmap getPopulation() {


		return this.image;
	}
}
