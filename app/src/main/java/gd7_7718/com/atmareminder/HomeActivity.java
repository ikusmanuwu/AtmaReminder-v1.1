package gd7_7718.com.atmareminder;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


/**
 * Created by fransiscus on 5/9/2017.
 */

public class HomeActivity extends AppCompatActivity {
    Button lg,set,maps;
    private Location point,kampus3;
    private BroadcastReceiver broadcastReceiver;
    private SharedPreferences sharedPreferences;
    public static final String myPref="mySharedPreferences";
    public static final String keyStatusGPSService="keyStatusGPSService";
    private Button loc;

    @Override
    protected void onResume() {
        super.onResume();
        if (broadcastReceiver==null){
            broadcastReceiver=new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    point = (Location) intent.getExtras().get("point");
                    Log.i("Estimation",getEstimation()+" minutes");
                    //Toast.makeText(MapsActivity.this, point.latitude+","+point.longitude, Toast.LENGTH_SHORT).show();
                }
            };
        }
        registerReceiver(broadcastReceiver,new IntentFilter("location_update"));
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedPreferences=getSharedPreferences(myPref,Context.MODE_PRIVATE);

        loc=(Button) findViewById(R.id.btnLocation);
        lg = (Button)findViewById(R.id.btnSchedule);
        set = (Button)findViewById(R.id.btnReminder);
        maps = (Button)findViewById(R.id.btnMaps);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ReminderActivity.class);
                startActivity(intent);
            }
        });
        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JadwalActivity.class);
                startActivity(intent);
            }
        });
        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });

        kampus3=new Location("");
        kampus3.setLatitude(-7.778941);
        kampus3.setLongitude(110.415053);

        runService();
    }

    public void runService(){
        if((!runtime_permissions())&&getStatusGPSService()) {
            enableService();
        }
    }

//    public void onClick(View v){
//        lg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), ScheduleActivity.class);
//                startActivity(intent);
//            }
//        });
//        set.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent m = new Intent(getApplicationContext(), ReminderActivity.class);
//                startActivity(intent);
//            }
//        });
//    }

    public boolean getStatusGPSService(){
        return sharedPreferences.getBoolean(keyStatusGPSService,false);
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
        return distance/1/*(40000/60)*/;
    }

}
