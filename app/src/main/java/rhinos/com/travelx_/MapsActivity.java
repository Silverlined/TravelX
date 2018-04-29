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
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent in = getIntent();
        chosenDestination = in.getIntExtra("rhinos.com.travelx_.ITEM_INDEX", -1);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

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
        LatLng sydney = new LatLng(-34, 151);
        LatLng california = new LatLng(36.778259, -119.417931);
        LatLng oxford = new LatLng(51.752022, -1.257677);
        LatLng moscow = new LatLng(55.751244, 37.618423);
        LatLng hongKong = new LatLng(22.28552, 114.15769);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(california).title("California"));
        mMap.addMarker(new MarkerOptions().position(oxford).title(""));
        mMap.addMarker(new MarkerOptions().position(moscow).title(""));
        mMap.addMarker(new MarkerOptions().position(hongKong).title(""));

        switch (chosenDestination) {
            case 0:
                return california;
            case 1:
                return oxford;
            case 2:
                return moscow;
            case 3:
                return sydney;
            case 4:
                return hongKong;
            case 5:
                return null;
        }
        return null;
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
            mMap.addMarker(new MarkerOptions().position(loc1).title("Your Current Location"));
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
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
        );
    }
}
