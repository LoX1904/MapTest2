package com.gym.leon.maptest;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.gym.leon.maptest.view.MarkerDetailView;


public class MainActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private Marker user;
    private Marker[] SeWues;
    private LatLng[] positionSeWues;
    private Context context;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0; // 10 meters
    private static final long MIN_TIME_BW_UPDATES = 1000 * 10 * 0; // 1 minute

    private LatLng newPosition;

    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean canGetLocation = false;

    Location location; // location
    double latitude; // latitude
    double longitude; // longitude

    protected LocationManager locationManager;
    protected String bestProvider;

    private Sehenswuerdigkeit Marienkirche, Heimatmuseum, Pulverturm, Ehrenhain, Rathaus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        this.context = this;
        Criteria crit = new Criteria();
        crit.setAccuracy(Criteria.ACCURACY_FINE);
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        if(checkLocationPermission()) {
            Log.d("GetPermission", "True");
            if (locationManager != null) {
                bestProvider = this.locationManager.getBestProvider(crit, false);
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    Log.d("test", "Nix Permission");
                } else {
                    Log.d("test", this.locationManager.getBestProvider(crit, false));
                    this.locationManager.requestLocationUpdates(bestProvider, 0, 1, locationListener);
                }
                Log.d("LocManager", "NotNull");
            } else {
                Log.d("LocManager", "Null");
            }
        }else{
            Log.d("GetPermission", "false");

        }
        Marienkirche = new Sehenswuerdigkeit(0, "Schwanenteich", 12.875973, 52.099349 );
        Heimatmuseum = new Sehenswuerdigkeit(1, "Heimatmuseum", 12.867861, 52.096210);
        Pulverturm = new Sehenswuerdigkeit(2, "Pulverturm",12.869102,52.098490);
        Ehrenhain = new Sehenswuerdigkeit(3, "Ehrenhain",12.872023,52.093908);
        Rathaus = new Sehenswuerdigkeit(4, "Rathaus",12.870919,52.097188 );
        SeWues = new Marker[5];
        positionSeWues = new LatLng[5];
        positionSeWues[0] = new LatLng(Marienkirche.getLat(),Marienkirche.getLongi());
        positionSeWues[1] = new LatLng(Heimatmuseum.getLat(), Heimatmuseum.getLongi());
        positionSeWues[2] = new LatLng(Pulverturm.getLat(), Pulverturm.getLongi());
        positionSeWues[3] = new LatLng(Ehrenhain.getLat(), Ehrenhain.getLongi());
        positionSeWues[4] = new LatLng(Rathaus.getLat(), Rathaus.getLongi());
        this.newPosition = new LatLng(52.0916007,12.8655166);
    }

    LocationListener locationListener=new LocationListener() {
        @Override
        public void onLocationChanged(android.location.Location location) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if(this.getLocation() != null){
            this.user = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(this.getLocation().getLatitude(), this.getLocation().getLongitude()))
                    .title("Current Position")

            );
        }else{
            LatLng fallback = new LatLng(52.0916007,12.8655166);
            this.user = mMap.addMarker(new MarkerOptions()
                    .position(fallback)
                    .title("Treuenbrietzen")
            );
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(this.user.getPosition()));
        mMap.setOnMarkerClickListener(this);
        mMap.addMarker(new MarkerOptions()
                        .position(positionSeWues[0])
                        .title("Marienkirche")

        );
        mMap.addMarker(new MarkerOptions()
                .position(positionSeWues[1])
                .title("Heimatmuseum")

        );
        mMap.addMarker(new MarkerOptions()
                .position(positionSeWues[2])
                .title("Pulverturm")
        );
        mMap.addMarker(new MarkerOptions()
                        .position(positionSeWues[3])
                        .title("Ehrenhain")
        );
        mMap.addMarker(new MarkerOptions()
                        .position(positionSeWues[4])
                        .title("Rathaus")
        );
        mMap.moveCamera(CameraUpdateFactory.newLatLng(newPosition));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(newPosition, 16.0f));

    }

    public boolean onMarkerClick(final Marker marker){
        Log.d("Titel : ", marker.getTitle());
       // if(istInNaehe(marker.getTitle())) {
            if (marker.getTitle().matches("Marienkirche")) {
                Intent myInt = new Intent(this, MarkerDetailView.class);
                Bundle b = new Bundle();
                b.putInt("key", Marienkirche.getId());
                myInt.putExtras(b);
                startActivity(myInt);
            } else if (marker.getTitle().matches("Heimatmuseum")) {
                Intent myInt = new Intent(this, MarkerDetailView.class);
                Bundle b = new Bundle();
                b.putInt("key", Heimatmuseum.getId());
                myInt.putExtras(b);
                startActivity(myInt);
            } else if (marker.getTitle().matches("Pulverturm")) {
                Intent myInt = new Intent(this, MarkerDetailView.class);
                Bundle b = new Bundle();
                b.putInt("key", Pulverturm.getId());
                myInt.putExtras(b);
                startActivity(myInt);
            } else if (marker.getTitle().matches("Ehrenhain")) {
                Intent myInt = new Intent(this, MarkerDetailView.class);
                Bundle b = new Bundle();
                b.putInt("key", Ehrenhain.getId());
                myInt.putExtras(b);
                startActivity(myInt);
            } else if (marker.getTitle().matches("Rathaus")) {
                Intent myInt = new Intent(this, MarkerDetailView.class);
                Bundle b = new Bundle();
                b.putInt("key", Rathaus.getId());
                myInt.putExtras(b);
                startActivity(myInt);
            }
          //  return false;
      //  }
        else if(marker.getTitle().matches("Current Position") || marker.getTitle().matches("Aufenthaltsort")){
            return false;
        }
        else{
            Toast.makeText(this, "Zu weit von der Sehenswürdigkeit entfernt.",Toast.LENGTH_SHORT).show();
            return false;
        }
        return false;
    }

    @Override
    public void onLocationChanged(Location location) {
        this.newPosition = new LatLng(location.getLatitude(), location.getLongitude());
        user.setPosition(newPosition);
        Log.d("long", Double.toString(newPosition.longitude));
        Log.d("lat", Double.toString(newPosition.latitude));
        user.setTitle("Aufenthaltsort");
        mMap.moveCamera(CameraUpdateFactory.newLatLng(newPosition));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission. ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission. ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle(R.string.title_location_permission)
                        .setMessage(R.string.text_location_permission)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission. ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission. ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        locationManager.requestLocationUpdates(bestProvider, 400, 1, this);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }

    public Location getLocation() {
        try {
            locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

            // getting GPS status
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            // getting network status
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                this.canGetLocation = true;
                // First get location from Network Provider
                if (isNetworkEnabled) {
                    if(location == null){
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        Log.d("Network", "Network");
                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }
    /*
       Funktion welche überprüft ob eigene position nahe genug an Marker liegt
       @params String s : Titel des Gedrückten Markers
     */
    public boolean istInNaehe(String s){
        Log.d("Abstand Latitude : ", Double.toString(Math.abs(Marienkirche.getLat() - newPosition.latitude)));
        Log.d("Abstand Longitude : ", Double.toString(Math.abs(Marienkirche.getLongi() - newPosition.longitude)));
        if(s.matches("Schwanenteich") && Math.abs(Marienkirche.getLat() - newPosition.latitude) <= 0.0020 && Math.abs(Marienkirche.getLongi() - newPosition.longitude) <= 0.0020){
            return true;
        }
        else if(s.matches("Heimatmuseum") && Math.abs(Heimatmuseum.getLat() - newPosition.latitude) <= 0.0020 && Math.abs(Heimatmuseum.getLongi() - newPosition.longitude) <= 0.0020){
            return true;
        }
        else if(s.matches("Rathaus") && Math.abs(Rathaus.getLat() - newPosition.latitude) <= 0.0020 && Math.abs(Rathaus.getLongi() - newPosition.longitude) <= 0.0020){
            return true;
        }
        else if(s.matches("Ehrenhain") && Math.abs(Ehrenhain.getLat() - newPosition.latitude) <= 0.0020 && Math.abs(Ehrenhain.getLongi() - newPosition.longitude) <= 0.0020){
            return true;
        }
        else if(s.matches("Pulverturm") && Math.abs(Pulverturm.getLat() - newPosition.latitude) <= 0.0020 && Math.abs(Pulverturm.getLongi() - newPosition.longitude) <= 0.0020){
            return true;
        }
        return false;
    }



}
