package com.tamunicipality.task.net.countries;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


/**
 * Created by Dmitriy on 19.06.2017.
 */

public class Currency implements Parcelable {

    @SerializedName("code")
    private String code;
    @SerializedName("name")
    private String name;
    @SerializedName("symbol")
    private String symbol;

    public Currency(){ }

    public Currency setCode(String code){
        this.code = code;
        return this;
    }
    public String getCode(){
        return this.code;
    }
    public Currency setName(String name){
        this.name = name;
        return this;
    }
    public String getName(){
        return this.name;
    }
    public Currency setSymbol(String symbol){
        this.symbol = symbol;
        return this;
    }
    public String getSymbol(){
        return this.symbol;
    }

    public Currency(Parcel in){
        String[] data = new String[3];
        in.readStringArray(data);
        this.code = data[0];
        this.name = data[1];
        this.symbol = data[2];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {
                this.code,
                this.name,
                this.symbol
        });
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Currency createFromParcel(Parcel in) {
            return new Currency(in);
        }

        public Currency[] newArray(int size) {
            return new Currency[size];
        }
    };
}
