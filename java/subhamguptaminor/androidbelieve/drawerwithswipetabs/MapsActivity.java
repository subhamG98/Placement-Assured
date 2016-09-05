package subhamguptaminor.androidbelieve.drawerwithswipetabs;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
     Button jobslocation;

    Double lati,longi;

    Double[] d=new Double[17];
    Integer i=0;
    Integer j = 0;
    static String nearest="all";

    Double smallest=10000000.000;

   EditText Tfaddress;
  Button Bsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        Tfaddress=(EditText)findViewById(R.id.TFaddress);
        Bsearch=(Button)findViewById(R.id.Bsearch);
        Bsearch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("map check",""+Tfaddress.getText().toString());
                        if(Tfaddress.getText().toString().equals(""))
                        {
                            Toast.makeText(getApplication(),"Please specify location first",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            onSearch(v);
                        }
                    }
                }
        );
       jobslocation=(Button)findViewById(R.id.jobslocation);
        jobslocation.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(Tfaddress.getText().toString().equals("")) {
                            Toast.makeText(getApplication(), "Please specify location first", Toast.LENGTH_SHORT).show();
                        }else {
                            Intent i = new Intent("subhamguptaminor.androidbelieve.drawerwithswipetabs.locationjobs");
                            startActivity(i);
                        }
                      //  startActivity(new Intent(getApplicationContext(), locationFragment.class));
                    }
                }
        );


    }




    class DistanceCalculator
    {


        private  double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
            double theta = lon1 - lon2;
            double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
            dist = Math.acos(dist);
            dist = rad2deg(dist);
            dist = dist * 60 * 1.1515;
            if (unit == "K") {
                dist = dist * 1.609344;
            } else if (unit == "N") {
                dist = dist * 0.8684;
            }

            return (dist);
        }

        private  double deg2rad(double deg) {
            return (deg * Math.PI / 180.0);
        }
        private double rad2deg(double rad) {
            return (rad * 180 / Math.PI);
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }


    public void onSearch(View view)
    {
        DistanceCalculator[] obj=new DistanceCalculator[17];
        DistanceCalculator obj1=new DistanceCalculator();



        EditText location_tf = (EditText)findViewById(R.id.TFaddress);
        String location = location_tf.getText().toString();
        List<Address> addressList = null;
        List<Address> addressList1 = null;

        if(location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);


            } catch (IOException e) {
                e.printStackTrace();
            }

            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title("" + address.getAddressLine(0)));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));


            lati = address.getLatitude();
            longi = address.getLongitude();
            Log.i("latitude subham", "" + lati + "-" + longi);
            Log.i("distance fom mumai", "" + obj1.distance(lati, longi, 18.0750, 72.8258, "K"));
            obj[0] = new DistanceCalculator();
            obj[1] = new DistanceCalculator();
            obj[2] = new DistanceCalculator();
            obj[3] = new DistanceCalculator();
            obj[4] = new DistanceCalculator();
            obj[5] = new DistanceCalculator();
            obj[6] = new DistanceCalculator();
            obj[7] = new DistanceCalculator();
            obj[8] = new DistanceCalculator();
            obj[9] = new DistanceCalculator();
            obj[10] = new DistanceCalculator();
            obj[11] = new DistanceCalculator();
            obj[12] = new DistanceCalculator();
            obj[13] = new DistanceCalculator();
            obj[14] = new DistanceCalculator();
            obj[15] = new DistanceCalculator();
            obj[16] = new DistanceCalculator();

            Log.i("distance fom mumai", "" + obj[0].distance(lati, longi, 18.0750, 72.8258, "K"));

            d[0] = obj[0].distance(lati, longi, 18.0750, 72.8258, "K");
            Log.i("distance fom mumai", "" + d[0]);

            d[1] = obj[1].distance(lati, longi, 12.9667, 77.567, "K");
            d[2] = obj[2].distance(lati, longi, 13.0827, 80.2707, "K");
            d[3] = obj[3].distance(lati, longi, 22.5667, 88.3667, "K");
            d[4] = obj[4].distance(lati, longi, 17.3700, 78.4800, "K");
            d[5] = obj[5].distance(lati, longi, 26.8000, 80.9000, "K");
            d[6] = obj[6].distance(lati, longi, 28.4700, 77.0300, "K");
            d[7] = obj[7].distance(lati, longi, 28.5700, 77.3200, "K");
            d[8] = obj[8].distance(lati, longi, 18.5203, 73.8567, "K");
            d[9] = obj[9].distance(lati, longi, 23.0300, 72.5800, "K");
            d[10] = obj[10].distance(lati, longi, 30.7500, 76.7800, "K");
            d[11] = obj[11].distance(lati, longi, 23.2500, 77.4167, "K");
            d[12] = obj[12].distance(lati, longi, 26.9000, 75.8000, "K");
            d[13] = obj[13].distance(lati, longi, 11.3500, 77.7333, "K");
            d[14] = obj[14].distance(lati, longi, 21.1700, 72.8300, "K");
            d[15] = obj[15].distance(lati, longi, 19.1724, 72.9570, "K");
            d[16] = obj[16].distance(lati, longi, 9.9000, 78.1000, "K");


            for (i = 0; i < 17; i++) {

                if (smallest > d[i]) {

                    smallest = d[i];

                }

            }

            Log.i("smal", "smallest" + smallest);

            for (i = 0; i < 17; i++) {
                if (smallest == d[i]) {
                    j = i;
                }

            }


            if (j == 0) {
                nearest = "mumbai";
            } else if (j == 1) {
                nearest = "bangalore";
            } else if (j == 2) {
                nearest = "chennai";
            } else if (j == 3) {
                nearest = "kolkata";
            } else if (j == 4) {
                nearest = "hyderabad";
            } else if (j == 5) {
                nearest = "lucknow";
            } else if (j == 6) {
                nearest = "gurgaon";
            } else if (j == 7) {
                nearest = "noida";
            } else if (j == 8) {
                nearest = "pune";
            } else if (j == 9) {
                nearest = "ahmedabad";
            } else if (j == 10) {
                nearest = "chandigarh";
            } else if (j == 11) {
                nearest = "bhopal";
            } else if (j == 12) {
                nearest = "jaipur";
            } else if (j == 13) {
                nearest = "erode";
            } else if (j == 14) {
                nearest = "surat";
            } else if (j == 15) {
                nearest = "thane";
            } else if (j == 16) {
                nearest = "madurai";
            }


            String location1 = nearest;
            if (location1 != null || !location1.equals("")) {
                Geocoder geocoder1 = new Geocoder(this);
                try {
                    addressList1 = geocoder1.getFromLocationName(location1, 1);


                } catch (IOException e) {
                    e.printStackTrace();
                }

                Address address1 = addressList1.get(0);
                LatLng latLng1 = new LatLng(address1.getLatitude(), address1.getLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng1).title("Nearest Place from "+address.getAddressLine(0)+"is-> " + address1.getAddressLine(0)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));


                Toast.makeText(getApplicationContext(), "Nearest place " + nearest+" is "+smallest+" km away", Toast.LENGTH_LONG).show();
            }
        }

            //Toast.makeText(this,""+address.getLatitude()+""+address.getLongitude(),Toast.LENGTH_LONG).show();

    }

    public void onZoom(View view)
    {
        if(view.getId() == R.id.Bzoomin)
        {
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        }
        if(view.getId() == R.id.Bzoomout)
        {
            mMap.animateCamera(CameraUpdateFactory.zoomOut());
        }
    }






    public void changeType(View view)
    {
        if(mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL)
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }
        else
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

//AIzaSyAR0rSCLGldGuoRUOvtyzC6JiINWZGDyzo

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        mMap.setMyLocationEnabled(true);



    }
}
