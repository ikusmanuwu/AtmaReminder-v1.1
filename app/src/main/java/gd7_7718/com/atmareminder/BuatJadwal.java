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
import android.widget.TextView;
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
    TextView textID;
    EditText text1, text3, text4, text5,text6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_jadwal);
        spinnerHari = (Spinner)findViewById(R.id.txtUser);
        readXML();
        setForm();

        dbHelper = new DataHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        text1 = (EditText) findViewById(R.id.txtNama);
        text2 = (Spinner) findViewById(R.id.txtUser);
        text3 = (EditText) findViewById(R.id.txtPass);
        text4 = (EditText) findViewById(R.id.txtUniv);
        text5 = (EditText) findViewById(R.id.editText5);
        text6 = (EditText) findViewById(R.id.editText6);
        ton1 = (Button) findViewById(R.id.button1);
        ton2 = (Button) findViewById(R.id.btnKembali);
        textID = (TextView) findViewById(R.id.textViewID);
        textID.setText(dbHelper.getLastID());

        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                long idhaaa;
                if(text2.getSelectedItemId()==6  )
                {
                    idhaaa = 1;
                }
                else
                {
                    idhaaa = text2.getSelectedItemId()+2;
                }
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into jadwal(no, hari, jammulai, jamselesai, makul, ruangan,idhari) values('" +
                        textID.toString() + "','" +
                        text2.getSelectedItem().toString() + "','" +
                        text3.getText().toString() + "','" +
                        text4.getText().toString() + "','" +
                        text5.getText().toString() + "','" +
                        text6.getText().toString() + "','" +
                        idhaaa + "')");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
//                Toast.makeText(getApplicationContext(), " Select ID Hari :"+text2.getSelectedItemId(), Toast.LENGTH_SHORT).show();
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
        spinnerHari = (Spinner)findViewById(R.id.txtUser);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataHari);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHari.setAdapter(adapter);
//        Toast.makeText(getApplicationContext(), " Select ID Hari :"+text2.getSelectedItemId(), Toast.LENGTH_SHORT).show();
    }
}