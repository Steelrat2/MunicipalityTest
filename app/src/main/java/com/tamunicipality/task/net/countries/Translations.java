package com.tamunicipality.task.net.countries;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


/**
 * Created by Dmitriy on 19.06.2017.
 */

public class Translations implements Parcelable {

    @SerializedName("de")
    private String de;
    @SerializedName("es")
    private String es;
    @SerializedName("fr")
    private String fr;
    @SerializedName("ja")
    private String ja;
    @SerializedName("it")
    private String it;
    @SerializedName("br")
    private String br;
    @SerializedName("pt")
    private String pt;

    public Translations(){ }

    public Translations setDe(String de){
        this.de = de;
        return this;
    }
    public String getDe(){
        return this.de;
    }
    public Translations setEs(String es){
        this.es = es;
        return this;
    }
    public String getEs(){
        return this.es;
    }
    public Translations setFr(String fr){
        this.fr = fr;
        return this;
    }
    public String getFr(){
        return this.fr;
    }
    public Translations setJa(String ja){
        this.ja = ja;
        return this;
    }
    public String getJa(){
        return this.ja;
    }
    public Translations setIt(String it){
        this.it = it;
        return this;
    }
    public String getIt(){
        return this.it;
    }
    public Translations setBr(String br){
        this.br = br;
        return this;
    }
    public String getBr(){
        return this.br;
    }
    public Translations setPt(String pt){
        this.pt = pt;
        return this;
    }
    public String getPt(){
        return this.pt;
    }

    public Translations(Parcel in){
        String[] data = new String[7];
        in.readStringArray(data);
        this.de=data[0];
        this.es=data[1];
        this.fr=data[2];
        this.ja=data[3];
        this.it=data[4];
        this.br=data[5];
        this.pt=data[6];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {
                this.de,
                this.es,
                this.fr,
                this.ja,
                this.it,
                this.br,
                this.pt
        });
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Translations createFromParcel(Parcel in) {
            return new Translations(in);
        }

        public Translations[] newArray(int size) {
            return new Translations[size];
        }
    };
}
