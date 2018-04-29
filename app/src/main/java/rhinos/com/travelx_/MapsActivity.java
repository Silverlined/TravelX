package rhinos.com.travelx_;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int MY_REQUEST_INT = 177;
    private GoogleMap mMap;
    private int chosenDestination;
    private LatLng destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent in = getIntent();
        chosenDestination = in.getIntExtra("rhinos.com.travelx_.ITEM_INDEX", -1);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Enable Current Location:
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, MY_REQUEST_INT);
            return;
        } else {
            mMap.setMyLocationEnabled(true);

            destination = addMarkers();

            moveCameraToChosenLocation();
        }
    }

    private LatLng addMarkers() {
        LatLng kennedy = new LatLng(28.6050359132,  -80.6026042562);
        LatLng california = new LatLng(36.778259, -119.417931);
        LatLng kourou = new LatLng(5.16281, -52.64265);
        LatLng vandenberg = new LatLng(34.633, -120.613);
        LatLng baikonur = new LatLng(45.965, 63.305);
        addMarkers(kennedy, california, kourou, vandenberg, baikonur);
        switch (chosenDestination) {
            case 0:
                return california;
            case 1:
                return kourou;
            case 2:
                return vandenberg;
            case 3:
                return kennedy;
            case 4:
                return baikonur;
            case 5:
                return null;
        }
        return null;
    }

    private void addMarkers(LatLng kennedy, LatLng california, LatLng kourou, LatLng vandenberg, LatLng baikour) {
        mMap.addMarker(new MarkerOptions().position(kennedy)
                .title("Kennedy Cosmodrome")
                .snippet("Launches 59"));
        mMap.addMarker(new MarkerOptions().position(california)
                .title("Elon Musk's HQ")
                .snippet("Rocket Rd, Hawthorne, CA 90250, USA"));
        mMap.addMarker(new MarkerOptions().position(kourou)
                .title("Kourou Cosmodrome")
                .snippet("Oxford OX1 2JD, The Great Britain"));
        mMap.addMarker(new MarkerOptions().position(vandenberg)
                .title("Vandenberg")
                .snippet("Tsverkaia st., 26/1, Moscow, Russia, 125009"));
        mMap.addMarker(new MarkerOptions().position(baikour)
                .title("Hong Kong")
                .snippet("64 Mody Rd, Tsim Sha Tsui East, Hong Kong"));
    }

    private void moveCameraToChosenLocation() {
        LocationManager manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Criteria mCriteria = new Criteria();
        assert manager != null;
        String bestProvider = String.valueOf(manager.getBestProvider(mCriteria, true));

        @SuppressLint("MissingPermission") Location mLocation = manager.getLastKnownLocation(bestProvider);
        if (mLocation != null) {
            Log.e("TAG", "GPS is on");
            final double currentLatitude = mLocation.getLatitude();
            final double currentLongitude = mLocation.getLongitude();
            LatLng loc1 = new LatLng(currentLatitude, currentLongitude);
            mMap.addMarker(new MarkerOptions().position(loc1).title("Your Current Location").icon(BitmapDescriptorFactory
                    .defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
            if (destination == null) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLatitude, currentLongitude), 15));
            } else {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(destination, 15));
            }
            mMap.animateCamera(CameraUpdateFactory.zoomTo(3), 2000, null);
        }
    }

    private void setFullScreen() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
        );
    }
}
