package subhamguptaminor.androidbelieve.drawerwithswipetabs.Network;

import subhamguptaminor.androidbelieve.drawerwithswipetabs.MainActivity;
import subhamguptaminor.androidbelieve.drawerwithswipetabs.Model.Flower2;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public interface api2 {

    String s=MainActivity.username.toUpperCase();

/*
     @GET("/PhotoUpload/getAllRetro.php")
     Response getMyThing(
             @Query("username") String s);

     public void getData(Callback<List<Flower>> response);
*/

@GET("/DpUpload/getAllImages.php")

    public void getData( Callback<List<Flower2>> response);

}
