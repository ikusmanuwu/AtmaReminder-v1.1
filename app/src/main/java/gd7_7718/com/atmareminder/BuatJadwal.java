package gd7_7718.com.atmareminder;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import gd7_7718.com.atmareminder.Entity.JadwalEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BuatJadwal extends AppCompatActivity {
    private ArrayList<String> dataHari = new ArrayList<>();
    private Spinner spinnerHari, text2;
    protected Cursor cursor;
    private String hari = "";
    private ArrayList<JadwalEntity> data;
    private DataHelper dbo;
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
        dbo = new DataHelper(this);
        //SQLiteDatabase db = dbHelper.getReadableDatabase();
        data=new ArrayList<JadwalEntity>();
        text1 = (EditText) findViewById(R.id.txtNama);
        text2 = (Spinner) findViewById(R.id.txtUser);
        text3 = (EditText) findViewById(R.id.txtPass);
        text4 = (EditText) findViewById(R.id.txtUniv);
        text5 = (EditText) findViewById(R.id.editText5);
        text6 = (EditText) findViewById(R.id.editText6);
        ton1 = (Button) findViewById(R.id.button1);
        ton2 = (Button) findViewById(R.id.btnKembali);
        textID = (TextView) findViewById(R.id.textViewID);
        //textID.setText(dbHelper.getLastID());
        //Log.d("Cek ID",textID.getText()+"");

        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                int idhaaa;
                if(text2.getSelectedItemId()==6  )
                {
                    idhaaa = 1;
                }
                else
                {
                    idhaaa = (int)(text2.getSelectedItemId()+2);
                }

                String u=text2.getSelectedItem().toString();
                String p=text3.getText().toString();
                String w=text4.getText().toString();
                String x=text5.getText().toString();
                String y=text6.getText().toString();
                //int z=Integer.parseInt(text6.getText().toString());
                JadwalEntity jadwal=new JadwalEntity();
                //String input = "Thu Jun 18 20:56:02 EDT 2009";
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                Date mulai = null;
                try {
                    mulai = sdf.parse(p);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date selesai = null;
                try {
                    selesai = sdf.parse(w);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                jadwal.setHari(u);
                jadwal.setJam_mulai(mulai);
                jadwal.setJam_selesai(selesai);
                jadwal.setMakul(x);
                jadwal.setRuangan(y);
                jadwal.setIdhari(idhaaa);
                Log.d("TES",u+" "+mulai+" "+selesai+" "+x+" "+y+" "+idhaaa+"");
                data.add(jadwal);
                dbo.insert(jadwal);
                finish();
                /*
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into jadwal(no, hari, jammulai, jamselesai, makul, ruangan,idhari) values('" +
                        textID.toString() + "','" +
                        text2.getSelectedItem().toString() + "','" +
                        text3.getText().toString() + "','" +
                        text4.getText().toString() + "','" +
                        text5.getText().toString() + "','" +
                        text6.getText().toString() + "','" +
                        idhaaa + "')");
                */
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
//                Toast.makeText(getApplicationContext(), " Select ID Hari :"+text2.getSelectedItemId(), Toast.LENGTH_SHORT).show();
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