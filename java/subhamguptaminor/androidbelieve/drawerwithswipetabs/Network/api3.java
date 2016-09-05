package subhamguptaminor.androidbelieve.drawerwithswipetabs.Network;

import subhamguptaminor.androidbelieve.drawerwithswipetabs.Model.Flower3;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public interface api3 {

//    String s=MainActivity.username.toUpperCase();

/*
     @GET("/PhotoUpload/getAllRetro.php")
     Response getMyThing(
             @Query("username") String s);

     public void getData(Callback<List<Flower>> response);
*/

@GET("/DpUpload/getAllImages1.php")

    public void getData(Callback<List<Flower3>> response);

}
