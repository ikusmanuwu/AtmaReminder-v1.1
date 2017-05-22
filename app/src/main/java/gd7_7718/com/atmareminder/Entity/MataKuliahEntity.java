package gd7_7718.com.atmareminder.Entity;

/**
 * Created by Reo Ramalika_2 on 25/04/2017.
 */

public class MataKuliahEntity {
    private int ID;
    private int jumlahAbsen;
    private int batasAbsen;
    private String nama;
    private String koordinatKampus;
    private String keterangan;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getJumlahAbsen() {
        return jumlahAbsen;
    }

    public void setJumlahAbsen(int jumlahAbsen) {
        this.jumlahAbsen = jumlahAbsen;
    }

    public int getBatasAbsen() {
        return batasAbsen;
    }

    public void setBatasAbsen(int batasAbsen) {
        this.batasAbsen = batasAbsen;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKoordinatKampus() {
        return koordinatKampus;
    }

    public void setKoordinatKampus(String koordinatKampus) {
        this.koordinatKampus = koordinatKampus;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
