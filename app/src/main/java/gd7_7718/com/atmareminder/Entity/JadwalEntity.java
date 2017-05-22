package gd7_7718.com.atmareminder.Entity;

import java.sql.Time;

/**
 * Created by Reo Ramalika_2 on 25/04/2017.
 */

public class JadwalEntity {
    private int id;
    private String hari;
    private Time jam_mulai;
    private Time jam_selesai;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public Time getJam_mulai() {
        return jam_mulai;
    }

    public void setJam_mulai(Time jam_mulai) {
        this.jam_mulai = jam_mulai;
    }

    public Time getJam_selesai() {
        return jam_selesai;
    }

    public void setJam_selesai(Time jam_selesai) {
        this.jam_selesai = jam_selesai;
    }
}
