package com.thanachat.myfootball;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.thanachat.myfootball.R;

public class MapsActivity extends AppCompatActivity implements
        GoogleMap.OnMarkerClickListener,
        OnMapReadyCallback {

    private static final LatLng PSU = new LatLng(7.894524, 98.3521533);
    private static final LatLng SamkongPremier = new LatLng(7.907871, 98.373439);
    private static final LatLng SevenSoc = new LatLng(7.86572, 98.37322);


    private Marker mPSU;
    private Marker mSamkongPremier;
    private Marker m7Soc;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /** Called when the map is ready. */
    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;

        // Add some markers to the map, and add a data object to each marker.
        mPSU = mMap.addMarker(new MarkerOptions().position(PSU).title("PSU").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        mSamkongPremier = mMap.addMarker(new MarkerOptions().position(SamkongPremier).title("Samkong Premier").icon(BitmapDescriptorFactory.fromResource(R.drawable.markerpay)));
        m7Soc = mMap.addMarker(new MarkerOptions().position(SevenSoc).title("7 Soccer Club").icon(BitmapDescriptorFactory.fromResource(R.drawable.markerpay)));

        //Camera Position
        LatLng coordinate = new LatLng(7.8927067,98.3779442); //Store these lat lng values somewhere. These should be constant.
        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
                coordinate, 12);
        mMap.animateCamera(location);

        // Set a listener for marker click.
        mMap.setOnMarkerClickListener(this);
    }

    /** Called when the user clicks a marker. */
    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.
        Intent i = new Intent(getApplicationContext(), GmapPSU.class);
        Intent ii = new Intent(getApplicationContext(), GmapPremierActivity.class);
        Intent iii = new Intent(getApplicationContext(), Gmap7Soc.class);

        if (marker.getTitle().equals("PSU"))
            startActivity(i);
        else if (marker.getTitle().equals("Samkong Premier"))
            startActivity(ii);
        else if (marker.getTitle().equals("7 Soccer Club"))
            startActivity(iii);

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

}
