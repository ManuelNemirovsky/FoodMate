package com.example.eyalm.foodmate;


import android.app.SearchManager;
import android.content.Intent;
import android.provider.SearchRecentSuggestions;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Random;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent());
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        // call detail activity for clicked entry
    }
    String user_input;
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            user_input = query;
            doSearch(query);
            SearchRecentSuggestions suggestions =
                    new SearchRecentSuggestions(this,
                            SampleRecentSuggestionsProvider.AUTHORITY,
                            SampleRecentSuggestionsProvider.MODE);
            suggestions.saveRecentQuery(query, null);
        }
    }



    private void doSearch(String queryStr) {
        // get a Cursor, prepare the ListAdapter
        // and set it
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        LatLng telAviv = new LatLng(32.081556, 34.791495);
        LatLng Jerusalem = new LatLng(31.774120, 35.216212);
        if (user_input.equals("Tel Aviv") || user_input.equals("tel aviv") || user_input.equals("Tel aviv") || user_input.equals("TLV") ||
                user_input.equals("tel aviv-yafo")) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(telAviv, 10));
            HashMap<Integer, LatLng> hm = helper(telAviv);
            String[] names = {"Leonardo", "Hilton", "Eyal", "Ofir", "Anna", "Manuel", "Maya", "Danielle", "Moshiko", "Rotem"};
            for (int i = 0; i < 10; i++) {
                if (i == 0) {
                    mMap.addMarker(new MarkerOptions().position(new LatLng(32.108224, 34.838084)).title("Catch a nap at " + names[i]
                    ).snippet("Tap for more info").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotelmarker)));
                } else if (i == 1) {
                    mMap.addMarker(new MarkerOptions().position(new LatLng(32.089086, 34.770713)).title("Catch a nap at " + names[i]
                    ).snippet("Tap for more info").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotelmarker)));
                } else {
                    mMap.addMarker(new MarkerOptions().position(hm.get(i)).title("Dine With " + names[i]
                    ).snippet("Tap for more info").icon(BitmapDescriptorFactory.fromResource(R.drawable.foodmarker)));
                }
            }
        }else{
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Jerusalem, 10));
            HashMap<Integer, LatLng> hm = helper(Jerusalem);
            String[] names = {"Leonardo", "Park Hotel", "Eyal", "Ofir", "Anna", "Manuel", "Maya", "Danielle", "Moshiko", "Rotem"};
            for (int i = 0; i < 10; i++) {
                if (i == 0) {
                    mMap.addMarker(new MarkerOptions().position(new LatLng(31.776032, 35.217601)).title("Catch a nap at " + names[i]
                    ).snippet("Tap for more info").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotelmarker)));
                } else if (i == 1) {
                    mMap.addMarker(new MarkerOptions().position(new LatLng(31.785092, 35.198064)).title("Catch a nap at " + names[i]
                    ).snippet("Tap for more info").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotelmarker)));
                } else {
                    mMap.addMarker(new MarkerOptions().position(hm.get(i)).title("Dine With " + names[i]
                    ).snippet("Tap for more info").icon(BitmapDescriptorFactory.fromResource(R.drawable.foodmarker)));
                }
            }
        }

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                startActivity(new Intent(getApplicationContext(),popupactivity1.class));
            }
        });
    }

    private HashMap<Integer, LatLng> helper(LatLng start){
        HashMap<Integer,LatLng> hm = new HashMap<Integer,LatLng>();
        Random rand = new Random();
        for(int i = 0; i<10; i++){
            double change = rand.nextDouble() / 50;
         if(i < 5){
             hm.put(i, new LatLng(start.latitude-change, start.longitude+change));
         }else{ hm.put(i, new LatLng(start.latitude - change, start.longitude));}

        }
        return hm;
    }
}
