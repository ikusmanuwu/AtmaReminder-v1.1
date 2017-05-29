package gd7_7718.com.atmareminder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import gd7_7718.com.atmareminder.Entity.JadwalEntity;

public class JadwalActivity extends AppCompatActivity {
    ArrayList<JadwalEntity> daftar;
    ArrayList<String> daftarNama;
    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static JadwalActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);

        Button btn=(Button)findViewById(R.id.btnKembali);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte = new Intent(JadwalActivity.this, BuatJadwal.class);
                startActivity(inte);
            }
        });


        ma = this;
        dbcenter = new DataHelper(this);
        try {
            RefreshList();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void RefreshList() throws ParseException {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM jadwal",null);
        daftar = new ArrayList<>();
        daftarNama = new ArrayList<>();
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            JadwalEntity temp=new JadwalEntity();

            SimpleDateFormat parser = new SimpleDateFormat("HH:mm:ss");
            Date mulai=parser.parse(cursor.getString(cursor.getColumnIndex("jammulai")));
            Date selesai=parser.parse(cursor.getString(cursor.getColumnIndex("jamselesai")));

            cursor.moveToPosition(cc);
            temp.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            temp.setIdhari(cursor.getInt(cursor.getColumnIndex("idhari")));
            temp.setRuangan(cursor.getString(cursor.getColumnIndex("ruangan")));
            temp.setMakul(cursor.getString(cursor.getColumnIndex("makul")));
            temp.setJam_mulai(mulai);
            temp.setJam_selesai(selesai);
            temp.setHari(cursor.getString(cursor.getColumnIndex("hari")));

            daftar.add(temp);
            daftarNama.add(temp.getHari()+" | "+cursor.getString(cursor.getColumnIndex("jammulai"))+"-"+cursor.getString(cursor.getColumnIndex("jamselesai")));
        }

        ListView01 = (ListView)findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftarNama));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new OnItemClickListener() {


            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final int selection = daftar.get(arg2).getId();
                final CharSequence[] dialogitem = {"Lihat Jadwal", "Update Jadwal", "Hapus Jadwal"};
                AlertDialog.Builder builder = new AlertDialog.Builder(JadwalActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch(item){
                            case 0 :
                                Intent i = new Intent(getApplicationContext(), LihatJadwal.class);
                                i.putExtra("id", selection);
                                Log.i("cekID",""+selection);
                                startActivity(i);
                                break;
                            case 1 :
                                Intent in = new Intent(getApplicationContext(), UpdateJadwal.class);
                                in.putExtra("id", selection);
                                startActivity(in);
                                break;
                            case 2 :
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from jadwal where _id = "+selection+"");
                                try {
                                    RefreshList();
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                break;
                        }
                    }
                });
                builder.create().show();
            }});
        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
    }

}