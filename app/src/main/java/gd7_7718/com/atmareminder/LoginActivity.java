package gd7_7718.com.atmareminder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by fransiscus on 5/9/2017.
 */

public class LoginActivity extends Activity {

    private static int SPLASH = 0;
    Button lg;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(SPLASH == 0){
            setContentView(R.layout.splash);

            Thread sp = new Thread(){
                public void run(){
                    try{
                        sleep(2000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }finally {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                }
            };
            sp.start();
            SPLASH = SPLASH + 1;
        }else{
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }



        lg = (Button) findViewById(R.id.btnLogin);
    }

    public void onClick(View v){
        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
