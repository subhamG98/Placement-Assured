package subhamguptaminor.androidbelieve.drawerwithswipetabs.Network;

import subhamguptaminor.androidbelieve.drawerwithswipetabs.Model.Flower4;
import subhamguptaminor.androidbelieve.drawerwithswipetabs.PrimaryFragment;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface api4 {

    String s= PrimaryFragment.s;
  //  String s=MainActivity.USERNAME_SHARED_PREF;


/*
     @GET("/PhotoUpload/getAllRetro.php")
     Response getMyThing(
             @Query("username") String s);

     public void getData(Callback<List<Flower>> response);
*/

@GET("/mutualfriend.php")

    public void getData(@Query("username") String s, Callback<List<Flower4>> response);

}
