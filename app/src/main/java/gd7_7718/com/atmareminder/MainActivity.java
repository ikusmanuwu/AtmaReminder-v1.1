package gd7_7718.com.atmareminder;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Login (View v) {
        EditText txtUser = (EditText) findViewById(R.id.txtUsername);
        EditText txtPass = (EditText) findViewById(R.id.txtPassword);
        username = txtUser.getText().toString();
        password = txtPass.getText().toString();

        if(username.equalsIgnoreCase("") && password.equalsIgnoreCase("")){
            Toast.makeText(getApplicationContext(),"Username atau password tidak boleh kosong", Toast.LENGTH_LONG).show();
        }
        else{
            if(username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin"))
            {
                Intent intent = new Intent(v.getContext(), /*HomeActivity.class*/MapsActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"login berhasil", Toast.LENGTH_LONG).show();
//                Toast.makeText(getApplicationContext(), "Sundays value "+ Calendar.SATURDAY, Toast.LENGTH_SHORT).show();

            }
            else
            {
                Toast.makeText(getApplicationContext(),"Username or Password wrong", Toast.LENGTH_LONG).show();
            }
        }
    }
}
