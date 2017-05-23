package gd7_7718.com.atmareminder;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Calendar;
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

        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);





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

                String string = getJammulai();
                String[] parts = string.split(":", 2);
                String jam = parts[0]; // 004

                String menit = parts[1]; // 034556-42
                final int hour = Integer.parseInt(jam);
                final int minute = Integer.parseInt(menit);
                Log.e("MyActivity", "In the receiver with " + (hour-1) + " and " + minute);
                setAlarmText("You clicked a " + hour + " and " + minute);

//                calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
//                calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.HOUR_OF_DAY,hour-1);
                calendar.set(Calendar.DAY_OF_WEEK,day);

                myIntent.putExtra("extra", "yes");
                pending_intent = PendingIntent.getBroadcast(ReminderActivity.this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);
//                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pending_intent);
                // now you should change the set Alarm text so it says something nice


                setAlarmText("Alarm set to " + hour + ":" + minute);
                //Toast.makeText(getApplicationContext(), "You set the alarm", Toast.LENGTH_SHORT).show();
            }

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

    public String getJammulai(){

        final Calendar calendar = Calendar.getInstance();

        final int day = calendar.get(Calendar.DAY_OF_WEEK);

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

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT  * FROM jadwal WHERE idhari = " + day,null);

        while (cursor.moveToNext()){

            if (cursor.getInt(cursor.getColumnIndex("idhari"))== day)
            return cursor.getString(cursor.getColumnIndex("jammulai"));

        }
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


