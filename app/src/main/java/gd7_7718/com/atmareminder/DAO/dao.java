package gd7_7718.com.atmareminder.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Reo Ramalika_2 on 25/04/2017.
 */

public class dao extends SQLiteOpenHelper{

    public static final String dbName="dbSchedule";

    //MahasiswaEntity
    public final String tblMahasiswa="tblMahasiswa";
    public final String mID="mID";
    public final String mNama="mNama";
    public final String mNIM="mNIM";
    public final String mProdi="mProdi";
    public final String mFakultas="mFakultas";
    public final String mUniv="mUniv";

    //MataKuliahEntity
    public final String tblMataKuliah="tblMataKuliah";
    public final String kID="kID";
    public final String kNama="kNama";
    public final String kJumlahAbsen="kJumlahAbsen";
    public final String kBatasAbsen="kBatasAbsen";
    public final String kKoordinatKampus="kKoordinatKampus";
    public final String kKeterangan="kKeterangan";

    //JadwalEntity
    public final String tblJadwal="tblJadwal";
    public final String jID="jID";
    public final String jHari="jHari";
    public final String jJamMulai="jJamMulai";
    public final String jJamSelesai="jJamSelesai";

    //KeteranganEntity
    public final String tblKeterangan="tblKeterangan";
    public final String rID="rID";
    public final String rKeterangan="rKeterangan";
    public final String rWaktu="rWaktu";

    public final String tblJadwalUTS="tblJadwalUTS";

    public final String tblJadwalUAS="tblJadwalUAS";

    public dao(Context context) {
        super(context, dbName,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+tblMahasiswa+"("+mID+" INTEGER primary key,"+mNama+" text,"+mNIM+" text,"+mProdi+" text,"+mFakultas+" text,"+mUniv+" text);");
        db.execSQL("create table "+tblJadwal+"("+jID+" INTEGER primary key,"+jHari+" text,"+jJamMulai+" text,"+jJamSelesai+" text);");
        db.execSQL("create table "+tblMataKuliah+"("+kID+" INTEGER primary key,"+kNama+" text,"+kJumlahAbsen+" INTEGER,"+kBatasAbsen+" INTEGER,"+kKoordinatKampus+" text,"+kKeterangan+" text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
