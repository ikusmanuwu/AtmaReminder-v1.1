package gd7_7718.com.atmareminder;

/**
 * Created by pc on 16/05/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "jadwalkul.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
//        db.execSQL("create table "+tblJadwal+"("+jID+" INTEGER primary key,"+jHari+" text,"+jMakul+" text,"+jRuangan+" text,"+jJamMulai+" text,"+jJamSelesai+" text);");
        String sql = "create table jadwal(no integer primary key, hari text null, jammulai text null, jamselesai text null, makul text null, ruangan text null,idhari integer null );";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
        sql = "INSERT INTO jadwal (no, hari, jammulai, jamselesai,makul,ruangan,idhari) VALUES ('1', 'Selasa', '15:30', '18:00','Techno','3421','3');";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }
}