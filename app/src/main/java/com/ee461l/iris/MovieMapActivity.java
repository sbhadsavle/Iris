package com.ee461l.iris;

import android.location.Address;
import android.support.v7.app.AppCompatActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

/**
 * Created by Andoji on 4/3/16.
 */
public class MovieMapActivity extends AppCompatActivity implements OnMapReadyCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we
     * just add a marker near Africa.
     */
    @Override
    public void onMapReady(GoogleMap map) {

        //map.addMarker(new MarkerOptions().position(new LatLng(30, 97)).title("Marker"));
        map.clear();
        Double[] latitude = {-97.7431, -31.2357};
        Double[] longitude = {30.2672, -30.0444};
        //-122.4194 lat, 37.7749 long
        String[] titles = {"ATX", "SFA"};
        String[] addresses = {"Current Location", "Theater 1"};

        for(int i = 0; i < 2; i += 1) {
            if (map != null) {
                MarkerOptions m = new MarkerOptions()
                        .position(new LatLng(latitude[i], longitude[i]))
                        .title(titles[i])
                        .snippet(addresses[i])
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                        .alpha(1);
                map.addMarker(m);
            }
        }
    }

}
