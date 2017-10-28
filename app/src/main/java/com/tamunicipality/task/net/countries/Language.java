package com.tamunicipality.task.net.countries;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


/**
 * Created by Dmitriy on 19.06.2017.
 */

public class Language implements Parcelable {

    @SerializedName("iso639_1")
    private String iso639_1;
    @SerializedName("iso639_2")
    private String iso639_2;
    @SerializedName("name")
    private String name;
    @SerializedName("nativeName")
    private String nativeName;

    public Language(){ }

    public Language setIso639_1(String iso639_1){
        this.iso639_1 = iso639_1;
        return this;
    }
    public String getIso639_1(){
        return this.iso639_1;
    }
    public Language setIso639_2(String iso639_2){
        this.iso639_2 = iso639_2;
        return this;
    }
    public String getIso639_2(){
        return this.iso639_2;
    }
    public Language setName(String name){
        this.name = name;
        return this;
    }
    public String getName(){
        return this.name;
    }
    public Language setNativeName(String nativeName){
        this.nativeName = nativeName;
        return this;
    }
    public String getNativeName(){
        return this.nativeName;
    }

    public Language(Parcel in) {
        String[] data = new String[4];
        in.readStringArray(data);
        this.iso639_1=data[0];
        this.iso639_2=data[1];
        this.name=data[2];
        this.nativeName=data[3];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {
            this.iso639_1,
            this.iso639_2,
            this.name,
            this.nativeName
        });
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Language createFromParcel(Parcel in) {
            return new Language(in);
        }

        public Language[] newArray(int size) {
            return new Language[size];
        }
    };
}
