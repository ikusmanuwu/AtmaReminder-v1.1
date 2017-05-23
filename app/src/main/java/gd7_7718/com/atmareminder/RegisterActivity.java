package gd7_7718.com.atmareminder;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

import gd7_7718.com.atmareminder.R;

/**
 * Created by fransiscus on 5/23/2017.
 */

public class RegisterActivity extends AppCompatActivity {
    private ArrayList<String> dataGenre = new ArrayList<>();
    private Spinner spinnerMovie;
    private SharedPreferences sp;
    private final String name = "myShared";
    public static final int mode = Activity.MODE_PRIVATE;
    private String nama = "";
    private String universitas = "";
    private Button ton1, ton2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        loadPreferences();

        ton2 = (Button) findViewById(R.id.btnKembali);


        ton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }



    private void loadPreferences() {
        sp = getSharedPreferences(name,mode);
        if(sp!=null) {
            nama = sp.getString("nama","");
            nama = sp.getString("username","");
            nama = sp.getString("password","");
            nama = sp.getString("universitas","");

        }
    }

    private void savePreverences() {
        EditText txtNamaa = (EditText)findViewById(R.id.txtNama);
        EditText txtUsername = (EditText)findViewById(R.id.txtUser);
        EditText txtPassword = (EditText)findViewById(R.id.txtPass);
        EditText txtUniversitas = (EditText)findViewById(R.id.txtUniv);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("nama", txtNamaa.getText().toString());
        editor.putString("password", txtPassword.getText().toString());
        editor.putString("username", txtUsername.getText().toString());
        editor.putString("universitas", txtUniversitas.getText().toString());
        editor.apply();
    }

    public void buttonSimpan(View v) {
        savePreverences();
        Toast.makeText(getApplicationContext(),"Register berhasil", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(v.getContext(), MainActivity.class);
        startActivity(intent);
    }
}
