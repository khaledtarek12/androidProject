package com.example.booking_airline_tickets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    GoogleMap gMap;
    FrameLayout map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        map = findViewById(R.id.map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.gMap = googleMap;


        LatLng mapapp = new LatLng(30.05028197793322, 31.23755221469704);
        LatLng mapapp2 = new LatLng(30.797116214630815, 30.997942765130542);

        this.gMap.addMarker(new MarkerOptions().position(mapapp).title("EGYPTAIR"));
        this.gMap.addMarker(new MarkerOptions().position(mapapp2).title("EGYPTAIR"));
        this.gMap.moveCamera(CameraUpdateFactory.newLatLng(mapapp));
        this.gMap.moveCamera(CameraUpdateFactory.newLatLng(mapapp2));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(mapapp, 5);
        this.gMap.animateCamera(cameraUpdate);
        CameraUpdate cameraUpdate2 = CameraUpdateFactory.newLatLngZoom(mapapp2, 5);
        this.gMap.animateCamera(cameraUpdate2);
        this.gMap.getUiSettings().setZoomControlsEnabled(true);
        this.gMap.getUiSettings().setCompassEnabled(true);
        this.gMap.getUiSettings().setZoomGesturesEnabled(true);
        this.gMap.getUiSettings().setScrollGesturesEnabled(true);
        this.gMap.getUiSettings().setRotateGesturesEnabled(false);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            return;
        }
        this.gMap.setMyLocationEnabled(true);


    }
}