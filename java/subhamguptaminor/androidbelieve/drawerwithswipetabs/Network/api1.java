package subhamguptaminor.androidbelieve.drawerwithswipetabs.Network;

import subhamguptaminor.androidbelieve.drawerwithswipetabs.MainActivity;
import subhamguptaminor.androidbelieve.drawerwithswipetabs.Model.Flower;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface api1 {

    String s= MainActivity.username.toUpperCase();

/*
     @GET("/PhotoUpload/getAllRetro.php")
     Response getMyThing(
             @Query("username") String s);

     public void getData(Callback<List<Flower>> response);
*/

@GET("/PhotoUpload/getAllImagesPeople.php")

    public void getData(@Query("reqcompany") String company,@Query("reqaddress")String address, Callback<List<Flower>> response);

}
