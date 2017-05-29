package gd7_7718.com.atmareminder;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String username;
    private String password;
    private SharedPreferences sp;
    private final String name = "myShared";
    public static final int mode = Activity.MODE_PRIVATE;
    private String user;
    private String pass;
    private String nama = "";
    private String universitas = "";
    private Button ton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//<<<<<<< HEAD
        loadPreferences();

        ton1 = (Button) findViewById(R.id.btnRegister);
        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void loadPreferences() {
        sp = getSharedPreferences(name,mode);
        if(sp!=null) {
            user = sp.getString("username","");
            pass = sp.getString("password","");
        }
//=======


//>>>>>>> master
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
            //if(username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin"))
            if(username.equals(sp.getString("username","")) && password.equals(sp.getString("password","")))
            {
                Intent intent = new Intent(v.getContext(), HomeActivity.class/*MapsActivity.class*/);
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
