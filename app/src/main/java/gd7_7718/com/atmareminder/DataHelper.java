package gd7_7718.com.atmareminder;

/**
 * Created by pc on 16/05/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
import gd7_7718.com.atmareminder.Entity.JadwalEntity;

import java.util.*;
import java.text.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import static android.R.id.input;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "jadwalkul.db";
    private static final int DB_VERSION = 1;


//    no integer primary key autoincrement,
//    hari text null,
//    jammulai text null,
//    jamselesai text null,
//    makul text null,
//    ruangan text null,
//    idhari integer null
    public static String Table_name="jadwal";
    public static String Column_Name_hari="hari";
    public static String Column_Name_jammulai="jammulai";
    public static String Column_Name_jamselesai="jamselesai";
    public static String Column_Name_makul="makul";
    public static String Column_Name_ruangan="ruangan";
    public static String Column_Name_idhari="idhari";
//    public static String Column_Name_

    SQLiteDatabase sqliteDB;

    public DataHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        sqliteDB=this.getWritableDatabase();
    }
    @Override

    public void onCreate(SQLiteDatabase db){
        String sqlquery="create table if not exists "+ Table_name + " (" + BaseColumns._ID + " integer primary key autoincrement, " + Column_Name_hari + " text not null, " + Column_Name_jammulai + " date not null, " + Column_Name_jamselesai + " date not null, " + Column_Name_makul + " text not null, " + Column_Name_ruangan + " text not null, " + Column_Name_idhari + " integer not null);";
        db.execSQL(sqlquery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }

    public ArrayList<JadwalEntity> getData() throws ParseException {
        ArrayList<JadwalEntity>userList=new ArrayList<JadwalEntity>();

        Cursor cursor=sqliteDB.query(Table_name,null,null,null,null,null,null);
        while(cursor.moveToNext())
        {

            String u=cursor.getString(cursor.getColumnIndex(Column_Name_hari));
            String p=cursor.getString(cursor.getColumnIndex(Column_Name_jammulai));
            String w=(cursor.getString(cursor.getColumnIndex(Column_Name_jamselesai)));
            String x=(cursor.getString(cursor.getColumnIndex(Column_Name_makul)));
            String y=cursor.getString(cursor.getColumnIndex(Column_Name_ruangan));
            int z=Integer.parseInt(cursor.getString(cursor.getColumnIndex(Column_Name_idhari)));
            JadwalEntity jadwal=new JadwalEntity();
            //String input = "Thu Jun 18 20:56:02 EDT 2009";
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date mulai = sdf.parse(p);
            Date selesai = sdf.parse(w);


            jadwal.setHari(u);
            jadwal.setJam_mulai(mulai);
            jadwal.setJam_selesai(selesai);
            jadwal.setMakul(x);
            jadwal.setRuangan(y);
            jadwal.setIdhari(z);
            Log.d("TES",u+" "+mulai+" "+selesai+" "+x+" "+y+" "+z+"");
            userList.add(jadwal);
        }
        return userList;
    }

    public ArrayList<String>showJadwalInList()
    {
        ArrayList<String> userList=new ArrayList<String>();
        Cursor cursor=sqliteDB.query(Table_name,null,null,null,null,null,null);
        while(cursor.moveToNext())
        {
            String u=cursor.getString(cursor.getColumnIndex(Column_Name_hari));

            userList.add(u);
        }
        return userList;
    }

    public void insert(JadwalEntity jadwalEntity){
        ContentValues contentValues=new ContentValues();
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm:ss");
        contentValues.put(Column_Name_hari,jadwalEntity.getHari());
        contentValues.put(Column_Name_jammulai,parser.format(jadwalEntity.getJam_mulai()));
        contentValues.put(Column_Name_jamselesai,parser.format(jadwalEntity.getJam_selesai()));
        contentValues.put(Column_Name_makul,jadwalEntity.getMakul());
        contentValues.put(Column_Name_ruangan,jadwalEntity.getRuangan());
        contentValues.put(Column_Name_idhari,jadwalEntity.getIdhari());

        sqliteDB.insert(Table_name,null,contentValues);
        sqliteDB.close();
    }

    public void delete(JadwalEntity id){
        String[] whereClauseArgument=new String[1];
        whereClauseArgument[0]=id.getHari();

        sqliteDB.delete(Table_name,Column_Name_hari+"=?",whereClauseArgument);
        sqliteDB.close();
    }

    public void update(JadwalEntity iD,String u,String p,String w,String x,String y,int z)
    {
        ContentValues cv=new ContentValues();
        cv.put(Column_Name_hari,u);
        cv.put(Column_Name_jammulai,p);
        cv.put(Column_Name_jamselesai,w);
        cv.put(Column_Name_makul,x);
        cv.put(Column_Name_ruangan,y);
        cv.put(Column_Name_idhari,z);

        String[] whereClauseArgument=new String[0];
        whereClauseArgument[0]=iD.getHari();
        System.out.println("whereClauseArgument[0] is : "+u+" "+p+whereClauseArgument[0]);
        sqliteDB.update(Table_name,cv,Column_Name_hari+"= ?",whereClauseArgument);
        sqliteDB.close();
    }


}