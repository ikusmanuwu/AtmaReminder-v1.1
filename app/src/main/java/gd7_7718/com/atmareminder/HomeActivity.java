package gd7_7718.com.atmareminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Created by fransiscus on 5/9/2017.
 */

public class HomeActivity extends AppCompatActivity {
    Button lg,set;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        lg = (Button)findViewById(R.id.btnSchedule);
        set = (Button)findViewById(R.id.btnReminder);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ReminderActivity.class);
                startActivity(intent);
            }
        });
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
//                Intent intent = new Intent(getApplicationContext(), ReminderActivity.class);
//                startActivity(intent);
//            }
//        });
//    }

}
