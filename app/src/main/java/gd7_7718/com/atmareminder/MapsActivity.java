package gd7_7718.com.atmareminder;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.audiofx.BassBoost;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.sql.Time;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Location point;
    private BroadcastReceiver broadcastReceiver;
    private Location kampus3;

    @Override
    protected void onResume() {
        super.onResume();
        if (broadcastReceiver==null){
            broadcastReceiver=new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    point = (Location) intent.getExtras().get("point");
                    //Toast.makeText(MapsActivity.this, point.latitude+","+point.longitude, Toast.LENGTH_SHORT).show();
                }
            };
        }
        registerReceiver(broadcastReceiver,new IntentFilter("location_update"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        kampus3=new Location("");
        kampus3.setLatitude(-7.778941);
        kampus3.setLongitude(110.415053);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if(!runtime_permissions()) {
            enableService();
        }
    }

    /*private void configureButton(){
        locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);
    }*/


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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng current = new LatLng(-7.778941, 110.415053);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(current));

        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED))
            mMap.setMyLocationEnabled(false);

    }

    private void enableService(){
        Intent intent=new Intent(this,GPSService.class);
        startService(intent);
    }

    private boolean runtime_permissions(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET
                },10);
                return true;
            }
            return false;
        }
        return false;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case 10:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                    enableService();
                else
                    runtime_permissions();
        }
    }

    public float getEstimation(){
        float distance=point.distanceTo(kampus3);
        return distance/(40000/60);
    }
}
