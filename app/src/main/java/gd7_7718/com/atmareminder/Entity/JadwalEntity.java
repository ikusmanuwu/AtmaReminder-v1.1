package gd7_7718.com.atmareminder.Entity;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Reo Ramalika_2 on 25/04/2017.
 */

public class JadwalEntity {
    private int id;
    private int idhari;
    private String hari;
    private String ruangan;
    private String makul;
    private Date jam_mulai;
    private Date jam_selesai;




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

    public Date getJam_mulai() {
        return jam_mulai;
    }

    public void setJam_mulai(Date jam_mulai) {
        this.jam_mulai = jam_mulai;
    }

    public Date getJam_selesai() {
        return jam_selesai;
    }

    public void setJam_selesai(Date jam_selesai) {
        this.jam_selesai = jam_selesai;
    }

    public String getRuangan() {
        return ruangan;
    }

    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }

    public String getMakul() {
        return makul;
    }

    public void setMakul(String makul) {
        this.makul = makul;
    }

    public int getIdhari() {
        return idhari;
    }

    public void setIdhari(int idhari) {
        this.idhari = idhari;
    }
}
