package com.example.androiddevelopment.myapplication.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


/**
 * Created by androiddevelopment on 29.11.17..
 */

@DatabaseTable(tableName = TuristickaAtrakcija.TABLE_NAME_TURISTICKAATRAKCIJA)
public class TuristickaAtrakcija {

    public static final String TABLE_NAME_TURISTICKAATRAKCIJA = "turistickaatrakcija";
    public static final String FIELD_NAME_ID = "id";
    public static final String FIELD_NAME_NAZIV = "naziv";
    public static final String FIELD_NAME_OPIS = "opis";
    public static final String FIELD_NAME_FOTO = "fotografija";
    public static final String FIELD_NAME_POSTA = "postanskaadresa";
    public static final String FIELD_NAME_TELEFON = "brojtelefona";
    public static final String FIELD_NAME_WEB = "webadresa";
    public static final String FIELD_NAME_RADNOVREME = "radnovreme";
    public static final String FIELD_NAME_CENA = "cenaulaznice";
    public static final String FIELD_NAME_KOMENTAR = "komentar";

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int mId;

    @DatabaseField(columnName = FIELD_NAME_NAZIV)
    private String mNaziv;

    @DatabaseField(columnName = FIELD_NAME_OPIS)
    private String mOpis;

    @DatabaseField(columnName = FIELD_NAME_FOTO)
    private String mFotografija;

    @DatabaseField(columnName = FIELD_NAME_POSTA)
    private int mPosta;

    @DatabaseField(columnName = FIELD_NAME_TELEFON)
    private int mTelefon;

    @DatabaseField(columnName = FIELD_NAME_WEB)
    private String mWeb;

    @DatabaseField(columnName = FIELD_NAME_RADNOVREME)
    private int mRadnovreme;

    @DatabaseField(columnName = FIELD_NAME_CENA)
    private double mCena;

    @DatabaseField(columnName = FIELD_NAME_KOMENTAR)
    private String mKomentar;

    public TuristickaAtrakcija() {
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmNaziv() {
        return mNaziv;
    }

    public void setmNaziv(String mNaziv) {
        this.mNaziv = mNaziv;
    }

    public String getmOpis() {
        return mOpis;
    }

    public void setmOpis(String mOpis) {
        this.mOpis = mOpis;
    }

    public String getmFotografija() {
        return mFotografija;
    }

    public void setmFotografija(String mFotografija) {
        this.mFotografija = mFotografija;
    }

    public int getmPosta() {
        return mPosta;
    }

    public void setmPosta(int mPosta) {
        this.mPosta = mPosta;
    }

    public int getmTelefon() {
        return mTelefon;
    }

    public void setmTelefon(int mTelefon) {
        this.mTelefon = mTelefon;
    }

    public String getmWeb() {
        return mWeb;
    }

    public void setmWeb(String mWeb) {
        this.mWeb = mWeb;
    }

    public int getmRadnovreme() {
        return mRadnovreme;
    }

    public void setmRadnovreme(int mRadnovreme) {
        this.mRadnovreme = mRadnovreme;
    }

    public double getmCena() {
        return mCena;
    }

    public void setmCena(double mCena) {
        this.mCena = mCena;
    }

    public String getmKomentar() {
        return mKomentar;
    }

    public void setmKomentar(String mKomentar) {
        this.mKomentar = mKomentar;
    }

    @Override
    public String toString() { return mNaziv; }
}
