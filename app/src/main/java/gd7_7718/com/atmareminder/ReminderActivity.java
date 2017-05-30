package gd7_7718.com.atmareminder;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by fransiscus on 5/9/2017.
 */

public class ReminderActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    AlarmManager alarmManager;
    private PendingIntent pending_intent;

    private TimePicker alarmTimePicker;
    private TextView alarmTextView;

    private AlarmReceiver alarm;
    private Switch switchDest;

    private SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;
    public static final String myPref="mySharedPreferences";
    public static final String keyStatusGPSService="keyStatusGPSService";
    int iii=1;
    ReminderActivity inst;
    Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setalarm);

//        Button next = (Button) findViewById(R.id.button);
//        next.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                Intent intent = new Intent(v.getContext(),
//                        HomeActivity.class);
//                startActivityForResult(intent, 0);
//
//            }
//        });
        sharedPreferences=getSharedPreferences(myPref,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);

        switchDest=(Switch) findViewById(R.id.switchDistance);
        switchDest.setChecked(sharedPreferences.getBoolean(keyStatusGPSService,false));
        switchDest.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    editor.putBoolean(keyStatusGPSService, true).apply();
                }
                else
                    editor.putBoolean(keyStatusGPSService,false).apply();

                Log.i("shrd","Value of KeyGPS: "+sharedPreferences.getBoolean(keyStatusGPSService,true));
            }
        });

        this.context = this;

        //alarm = new AlarmReceiver();
        alarmTextView = (TextView) findViewById(R.id.alarmText);

        final Intent myIntent = new Intent(this.context, AlarmReceiver.class);

        // Get the alarm manager service
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // set the alarm to the time that you picked
        final Calendar calendar = Calendar.getInstance();

        final int day = calendar.get(Calendar.DAY_OF_WEEK);

        Log.e("day",day+"");

        switch (day) {
            case Calendar.SUNDAY:
                // Current day is Sunday

            case Calendar.MONDAY:
                // Current day is Monday

            case Calendar.TUESDAY:
                // etc.
            case Calendar.WEDNESDAY:
            case Calendar.THURSDAY:
            case Calendar.FRIDAY:
            case Calendar.SATURDAY:
        }

        //alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);





        /*SQLiteDatabase db = helper.getReadableDatabase();

        String table = "table2";
        String[] columns = {"column1", "column3"};
        String selection = "column3 =?";
        String[] selectionArgs = {"apple"};
        String groupBy = null;
        String having = null;
        String orderBy = "column3 DESC";
        String limit = "10";

        Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

        ///////////////////getDatabase/////////////////////////////////

        dbHelper = new DataHelper(this);

        SQLiteDatabase db = dbHelper.getReadableDatabase();

         cursor = db.rawQuery("SELECT  * FROM jadwal WHERE idhari = '" +
                day + "'" + "AND jammulai",null);
        cursor.moveToFirst();
*/





        ///////////////////////////////////////////////////////////////


        Button start_alarm= (Button) findViewById(R.id.start_alarm);
        start_alarm.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)

            @Override
            public void onClick(View v) {

                calendar.add(Calendar.SECOND, 3);
                //setAlarmText("You clicked a button");

//                final int hour = alarmTimePicker.getCurrentHour();
//                final int minute = alarmTimePicker.getCurrentMinute();

                String string = null;
                try {
                    string = getJammulai();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(string.equalsIgnoreCase("Tidak ditemukan"))
                {
                    //Rabu
                    //Toast.makeText(getApplicationContext(), "MAAF UNTUK HARI INI TIDAK ADA JADWAL ", Toast.LENGTH_LONG).show();
                    try {
                        string = getJammulaiNext();
                        if(string.equalsIgnoreCase("Tidak ditemukan"))
                        {
                            //Kamis
                            string=getJammulaiNext();
                            if(string.equalsIgnoreCase("Tidak ditemukan"))
                            {
                                //Jumat
                                string=getJammulaiNext();
                                if(string.equalsIgnoreCase("Tidak ditemukan"))
                                {
                                    //Sabtu
                                    string=getJammulaiNext();
                                    if(string.equalsIgnoreCase("Tidak ditemukan"))
                                    {
                                        //Minggu
                                        string=getJammulaiNext();
                                        if(string.equalsIgnoreCase("Tidak ditemukan"))
                                        {
                                            //Exception
                                            AlertDialog ad = adb.create();
                                            ad.setMessage("Data Kosong");
                                            ad.show();
                                            //Toast.makeText(getApplicationContext(), "MAAF TIDAK ADA JADWAL ", Toast.LENGTH_LONG).show();
//                                            string=getJammulaiNext();
                                        }



                                    }

                                }
                            }
                        }
                    } catch (ParseException e) {

                        e.printStackTrace();
                    }
                }
//                else {
                    Log.i("Tesget", "Split " + string + " ");
                    String[] parts = string.split(":", 3);
                    String jam = parts[0]; // 004
                    String menit = parts[1]; // 034556-42
                    String detik = parts[2];
                    Log.i("Tesget", "Split " + string + " " + jam + " " + menit + " " + detik);
                    final int hour = Integer.parseInt(jam);
                    final int minute = Integer.parseInt(menit);
                    final int seconds = Integer.parseInt(detik);


                    Log.e("MyActivity", "In the receiver with " + (hour - 1) + " and " + minute);
                    setAlarmText("You clicked a " + hour + " and " + minute);

//              calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
//              calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
                    calendar.set(Calendar.MINUTE, minute);
                    calendar.set(Calendar.HOUR_OF_DAY, hour - 1);
                    calendar.set(Calendar.SECOND, seconds);
                    calendar.set(Calendar.DAY_OF_WEEK, day);

                    myIntent.putExtra("extra", "yes");
                    pending_intent = PendingIntent.getBroadcast(ReminderActivity.this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);
//              alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pending_intent);
                    // now you should change the set Alarm text so it says something nice


                    setAlarmText("Alarm set to " + (hour - 1) + ":" + minute);
                    //Toast.makeText(getApplicationContext(), "You set the alarm", Toast.LENGTH_SHORT).show();
                }
//            }

        });

        Button stop_alarm= (Button) findViewById(R.id.stop_alarm);
        stop_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int min = 1;
                int max = 9;

                Random r = new Random();
                int random_number = r.nextInt(max - min + 1) + min;
                Log.e("random number is ", String.valueOf(random_number));

                myIntent.putExtra("extra", "no");
                sendBroadcast(myIntent);

                alarmManager.cancel(pending_intent);
                setAlarmText("Alarm canceled");
                //setAlarmText("You clicked a " + " canceled");
            }
        });

    }

    public String getJammulai() throws ParseException {

        final Calendar calendar = Calendar.getInstance();

        final int day = calendar.get(Calendar.DAY_OF_WEEK);
        Log.i("cekDay",day+"");

        switch (day) {
            case Calendar.SUNDAY:
                // Current day is Sunday
            case Calendar.MONDAY:
                // Current day is Monday
            case Calendar.TUESDAY:
                // etc.
            case Calendar.WEDNESDAY:
            case Calendar.THURSDAY:
            case Calendar.FRIDAY:
            case Calendar.SATURDAY:
        }


        dbHelper = new DataHelper(this);

        Calendar c = Calendar.getInstance();
//        c.add(Calendar.HOUR_OF_DAY, 1);//
        c.get(Calendar.HOUR_OF_DAY);
        c.get(Calendar.MINUTE);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT  * FROM jadwal WHERE idhari = " + day + " AND jammulai > "+c.HOUR_OF_DAY+""+c.MINUTE+""+c.SECOND+" ;",null);
//        cursor = db.rawQuery("SELECT  * FROM jadwal WHERE idhari = " + day + ";",null);
        Log.i("Tesget","JAM MENIT "+c.get(Calendar.HOUR_OF_DAY)+" "+c.get(Calendar.MINUTE)+"");
//        cursor.moveToFirst();
//        if (cursor.getCount()>0)
//        {
//            cursor.moveToPosition(0);
//            Log.i("Tesget",cursor.getString(0).toString());
//            Log.i("Tesget",cursor.getString(1).toString());
//            Log.i("Tesget",cursor.getString(2).toString());
//            Log.i("Tesget",cursor.getString(3).toString());
//            Log.i("Tesget",cursor.getString(4).toString());
//            Log.i("Tesget",cursor.getString(5).toString());
//            Log.i("Tesget",cursor.getString(6).toString());
//
//        }


//        c.add(Calendar.HOUR_OF_DAY, 1);//
        while (cursor.moveToNext()){
            String temp = cursor.getString(cursor.getColumnIndex("jammulai"));
            String[] parts = temp.split(":", 3);
            String jam = parts[0]; // 004
            String menit = parts[1]; // 034556-42
            String detik = parts[2];
            int hour1 = Integer.parseInt(jam);
            int minutes1 = Integer.parseInt(menit);
            int jamskr = c.get(Calendar.HOUR_OF_DAY);
            int menitskr=c.get(Calendar.MINUTE);
            String tempjam= jam+":"+menit;
            String tempjamskr=(hour1-1)+":"+minutes1;
            Log.i("Tesget","Return jam : "+(hour1-1)+" menit :"+minutes1);//
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            Date date1 = format.parse(tempjam);
            Date date2 = format.parse(tempjamskr);
            //Log.i("Tesget","Return jam : "+(hour1-1)+" menit :"+minutes1);
            if (cursor.getInt(cursor.getColumnIndex("idhari"))== day && (hour1-1)>=jamskr  && minutes1>menitskr)

            return cursor.getString(cursor.getColumnIndex("jammulai"));
//            Log.i("Tesget","Return jam :"+hour1+" ");
        }
        return "Tidak ditemukan";

    }


    public String getJammulaiNext() throws ParseException {

        final Calendar c = Calendar.getInstance();
        int nextday = c.get(Calendar.DAY_OF_WEEK);
        nextday = nextday+iii;
//        Log.i("cekDay",day+"");

        switch (nextday) {
            case Calendar.SUNDAY:
                // Current day is Sunday
            case Calendar.MONDAY:
                // Current day is Monday
            case Calendar.TUESDAY:
                // etc.
            case Calendar.WEDNESDAY:
            case Calendar.THURSDAY:
            case Calendar.FRIDAY:
            case Calendar.SATURDAY:
        }


        dbHelper = new DataHelper(this);

        Calendar ca = Calendar.getInstance();
//        c.add(Calendar.HOUR_OF_DAY, 1);//
        ca.get(Calendar.HOUR_OF_DAY);
        ca.get(Calendar.MINUTE);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT  * FROM jadwal WHERE idhari = " + nextday + " AND jammulai > "+ca.HOUR_OF_DAY+""+ca.MINUTE+""+ca.SECOND+" ;",null);
//        cursor = db.rawQuery("SELECT  * FROM jadwal WHERE idhari = " + day + ";",null);
        Log.i("Tesget","JAM MENIT "+c.get(Calendar.HOUR_OF_DAY)+" "+c.get(Calendar.MINUTE)+"");
//

//
        while (cursor.moveToNext()){
            String temp = cursor.getString(cursor.getColumnIndex("jammulai"));
            String[] parts = temp.split(":", 3);
            String jam = parts[0]; // 004
            String menit = parts[1]; // 034556-42
            String detik = parts[2];
            int hour1 = Integer.parseInt(jam);
            int minutes1 = Integer.parseInt(menit);
            int jamskr = ca.get(Calendar.HOUR_OF_DAY);
            int menitskr=ca.get(Calendar.MINUTE);
            String tempjam= jam+":"+menit;
            String tempjamskr=(hour1-1)+":"+minutes1;
            Log.i("Tesget","Return jam : "+(hour1-1)+" menit :"+minutes1);//
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            Date date1 = format.parse(tempjam);
            Date date2 = format.parse(tempjamskr);
            long millis = date2.getTime() - date1.getTime();


            //if (cursor.getInt(cursor.getColumnIndex("idhari"))== nextday && (hour1-1)>=jamskr  && minutes1>menitskr)
            if (cursor.getInt(cursor.getColumnIndex("idhari"))== nextday && (date1.getTime()-date2.getTime()>=0))
            //date2.getTime() - date1.getTime()
                return cursor.getString(cursor.getColumnIndex("jammulai"));
//            Log.i("Tesget","Return jam :"+hour1+" ");
        }
        iii++;
        return "Tidak ditemukan";

    }





    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText);
    }



    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("MyActivity", "on Destroy");
    }
}


