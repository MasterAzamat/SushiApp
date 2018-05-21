package com.example.azaat.sushiapp;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MapActivity extends Activity implements OnMapReadyCallback {
    GoogleMap googleMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;

    LocationManager locationManager;

    View bottom;
    BottomSheetBehavior bottomSheetBehavior;

    private static final String fine_location = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String course_location = Manifest.permission.ACCESS_COARSE_LOCATION;
    private boolean mLocationPermissionsGranted = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        bottom = findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottom);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        getLocationPermition();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000 * 10, 10, locationListener);
    }
    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
//            if (location == null)return;
//            if (location.getProvider().equals(LocationManager.GPS_PROVIDER)){
//                getDeviceLocation();
//            }
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
                            location.getLatitude(),location.getLongitude())
                    , 15f));
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {
            getDeviceLocation();
        }

        @Override
        public void onProviderDisabled(String s) {
            Toast.makeText(MapActivity.this, "GPS disabled", Toast.LENGTH_SHORT).show();
        }
    };

    private void getLocationPermition() {
        String[] permissions = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                fine_location) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    course_location) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionsGranted = true;
                createMapView();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 10);
            }
        } else {
            ActivityCompat.requestPermissions(this, permissions, 10);
        }
    }

    private void getDeviceLocation() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try {
            if (mLocationPermissionsGranted) {
                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Location currentLocation = (Location) task.getResult();
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
                                    currentLocation.getLatitude(), currentLocation.getLongitude())
                                    , 15f));
                            MarkerOptions options = new MarkerOptions()
                                    .position(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()))
                                    .title("MyLocation");
                            googleMap.addMarker(options);
                        }
                    }
                });
            }

        } catch (SecurityException e) {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionsGranted = false;
        switch (requestCode) {
            case 10: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false;
                            return;
                        }
                    }
                    mLocationPermissionsGranted = true;
                    createMapView();
                }
            }
        }
    }

    private void createMapView() {
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.getUiSettings().setZoomControlsEnabled(true);
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            getDeviceLocation();
        }else {
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        }

        this.googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        this.googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                Toast.makeText(MapActivity.this, "loaded", Toast.LENGTH_SHORT).show();
            }
        });
//        if (mLocationPermissionsGranted) {
//            getDeviceLocation();
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//                return;
//            }
//            this.googleMap.setMyLocationEnabled(true);
//            this.googleMap.getUiSettings().setMyLocationButtonEnabled(false);
//        }
//        CameraPosition cameraPosition = new CameraPosition.Builder()
//                .target(new LatLng(40, 55))
//                .zoom(5)
//                .bearing(45)
//                .tilt(20)
//                .build();
//        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
//        this.googleMap.animateCamera(cameraUpdate);

    }


}
