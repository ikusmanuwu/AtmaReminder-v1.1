package gd7_7718.com.atmareminder;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Reo Ramalika_2 on 23/05/2017.
 */

public class GPSService extends Service{
    LocationListener listener;
    LocationManager locationManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        listener=new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                Log.i("Location Changed",location.getLatitude()+","+location.getLongitude());
                Location point=location;
                Intent i=new Intent("location_update");
                i.putExtra("point",point);
                sendBroadcast(i);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        };
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);Log.i("GPS Service","GPS Service Runnig");

//        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //noinspection MissingPermission
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
//        }
//        else if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            //noinspection MissingPermission
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
    }
}
