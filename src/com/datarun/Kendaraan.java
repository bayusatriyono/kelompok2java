package src.com.datarun;

public class Kendaraan {
    private String idKendaraan;
    private String merkKendaraan;
    private String tipeKendaraan;
    private String platNomor;
    private String pengguna;

    public Kendaraan(String idKendaraan, String merk, String tipe, String platNomor, String pengguna) {
        this.idKendaraan = idKendaraan;
        this.merkKendaraan = merk;
        this.tipeKendaraan = tipe;
        this.platNomor = platNomor;
        this.pengguna = pengguna;
    }

    public String getIdKendaraan() {
        return idKendaraan;
    }

    public String getMerk() {
        return merkKendaraan;
    }

    public void setMerk(String merk) {
        this.merkKendaraan = merk;
    }

    public String getTipe() {
        return tipeKendaraan;
    }

    public void setTipe(String tipe) {
        this.tipeKendaraan = tipe;
    }

    public String getPlatNomor() {
        return platNomor;
    }

    public void setPlatNomor(String platNomor) {
        this.platNomor = platNomor;
    }

    public String getPengguna() {
        return pengguna;
    }

    public void setPengguna(String pengguna) {
        this.pengguna = pengguna;
    }
}