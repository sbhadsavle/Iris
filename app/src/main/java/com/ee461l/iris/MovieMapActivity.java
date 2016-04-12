package com.ee461l.iris;

import android.location.Address;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
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
public class MovieMapActivity extends AppCompatActivity{

    GoogleMap map = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

//        SupportMapFragment mapFragment =
//                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);

        setUpMap();
    }

    private void setUpMap(){
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.setIndoorEnabled(false);
        LatLng Austin = new LatLng(30.2672, -97.7431);
        Marker AustinMarker = map.addMarker(new MarkerOptions()
                                            .position(Austin)
                                            .title("Austin")
                                            .snippet("ATX"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(Austin, 15.0f));
        UiSettings mapUI = map.getUiSettings();
        mapUI.setAllGesturesEnabled(true);
        mapUI.setZoomControlsEnabled(true);

    }
    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we
     * just add a marker near Africa.
     */
//    @Override
//    public void onMapReady(GoogleMap map) {
//
//        //map.addMarker(new MarkerOptions().position(new LatLng(30, 97)).title("Marker"));
//        map.clear();
//        Double[] latitude = {100.0, 12.3};
//        Double[] longitude = {0.0, 45.4};
//        String[] titles = {"ATX", "SFA"};
//        String[] addresses = {"Current Location", "Theater 1"};
//
//        for(int i = 0; i < 2; i += 1) {
//            if (map != null) {
//                MarkerOptions m = new MarkerOptions()
//                        .position(new LatLng(latitude[i], longitude[i]))
//                        .title(titles[i])
//                        .snippet(addresses[i])
//                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
//                        .alpha(1);
//                map.addMarker(m);
//            }
//        }
//    }

}
