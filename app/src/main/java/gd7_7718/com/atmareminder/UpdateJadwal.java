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

import java.text.ParseException;
import java.util.ArrayList;

import gd7_7718.com.atmareminder.Entity.JadwalEntity;
public class UpdateJadwal extends AppCompatActivity {
    private ArrayList<String> dataHari = new ArrayList<>();
    private Spinner spinnerHari,text2;
    private String hari = "";
    private ArrayList<JadwalEntity> data;
    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton1, ton2;
    EditText text3, text4, text5,text6;
    TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_jadwal);

        spinnerHari = (Spinner) findViewById(R.id.txtUser);
        readXML();
        setForm();

        dbHelper = new DataHelper(this);
        text1 = (TextView) findViewById(R.id.txtNama);
        text2 = (Spinner) findViewById(R.id.txtUser);
        text3 = (EditText) findViewById(R.id.txtPass);
        text4 = (EditText) findViewById(R.id.txtUniv);
        text5 = (EditText) findViewById(R.id.editText5);
        text6 = (EditText) findViewById(R.id.editText6);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM jadwal WHERE _id = " +
                getIntent().getIntExtra("id", -1), null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0).toString());
            //  text2.setspi(cursor.getString(1).toString());
            text3.setText(cursor.getString(2).toString());
            text4.setText(cursor.getString(3).toString());
            text5.setText(cursor.getString(4).toString());
            text6.setText(cursor.getString(5).toString());
        }
        ton1 = (Button) findViewById(R.id.button1);
        ton2 = (Button) findViewById(R.id.btnKembali);
        // daftarkan even onClick pada btnSimpan
        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                long idhaaa;
                if (text2.getSelectedItem().toString().equalsIgnoreCase("Senin")) {
                    idhaaa = 2;
                } else if (text2.getSelectedItem().toString().equalsIgnoreCase("Selasa")) {
                    idhaaa = 3;
                } else if (text2.getSelectedItem().toString().equalsIgnoreCase("Rabu")) {
                    idhaaa = 4;
                } else if (text2.getSelectedItem().toString().equalsIgnoreCase("Kamis")) {
                    idhaaa = 5;
                } else if (text2.getSelectedItem().toString().equalsIgnoreCase("Jumat")) {
                    idhaaa = 6;
                } else if (text2.getSelectedItem().toString().equalsIgnoreCase("Sabtu")) {
                    idhaaa = 7;
                } else if (text2.getSelectedItem().toString().equalsIgnoreCase("Minggu")) {
                    idhaaa = 1;
                }

                // TODO Auto-generated method stub


                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update jadwal set hari='" +
                        text2.getSelectedItem().toString() + "', jammulai='" +
                        text3.getText().toString() + "', jamselesai='" +
                        text4.getText().toString() + "', makul='" +
                        text5.getText().toString() + "', ruangan='" +
                        text6.getText().toString() + "' where _id=" +
                        getIntent().getIntExtra("id", -1));
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                try {
                    JadwalActivity.ma.RefreshList();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
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