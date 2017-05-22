package gd7_7718.com.atmareminder;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

public class BuatJadwal extends AppCompatActivity {
    private ArrayList<String> dataHari = new ArrayList<>();
    private Spinner spinnerHari, text2;
    protected Cursor cursor;
    private String hari = "";
    DataHelper dbHelper;
    Button ton1, ton2;
    EditText text1, text3, text4, text5,text6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_jadwal);
        spinnerHari = (Spinner)findViewById(R.id.editText2);
        readXML();
        setForm();

        dbHelper = new DataHelper(this);
        text1 = (EditText) findViewById(R.id.editText1);
        text2 = (Spinner) findViewById(R.id.editText2);
        text3 = (EditText) findViewById(R.id.editText3);
        text4 = (EditText) findViewById(R.id.editText4);
        text5 = (EditText) findViewById(R.id.editText6);
        text6 = (EditText) findViewById(R.id.editText12);
        ton1 = (Button) findViewById(R.id.button1);
        ton2 = (Button) findViewById(R.id.button2);

        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into jadwal(no, hari, makul, ruangan, jammulai, jamselesai) values('" +
                        text1.getText().toString() + "','" +
                        text2.getSelectedItem().toString() + "','" +
                        text3.getText().toString() + "','" +
                        text4.getText().toString() + "','" +
                        text5.getText().toString() + "','" +
                        text6.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                JadwalActivity.ma.RefreshList();
                finish();
            }
        });
        ton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }

    private void readXML() {
        try {
            XmlPullParser xpp = getResources().getXml(R.xml.list_hari);
            while(xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if(xpp.getEventType() == XmlPullParser.START_TAG) {
                    if(xpp.getName().equals("name")) {
                        dataHari.add(xpp.getAttributeValue(0));
                    }
                }
                xpp.next();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Gagal membaca xml...", Toast.LENGTH_SHORT).show();
        }
    }

    private void setForm() {
        spinnerHari = (Spinner)findViewById(R.id.editText2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataHari);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHari.setAdapter(adapter);
    }
}