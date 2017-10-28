package com.tamunicipality.task.net.countries;

/**
 * Created by Dmitriy on 19.06.2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Country implements Parcelable {

    @SerializedName("name")
    private String name;
    @SerializedName("topLevelDomain")
    private List<String> topLevelDomain; // String
    @SerializedName("alpha2Code")
    private String alpha2Code;
    @SerializedName("alpha3Code")
    private String alpha3Code;
    @SerializedName("callingCodes")
    private List<String> callingCodes; // String
    @SerializedName("capital")
    private String capital;
    @SerializedName("altSpellings")
    private List<String> altSpellings; // String
    @SerializedName("region")
    private String region;
    @SerializedName("subregion")
    private String subregion;
    @SerializedName("population")
    private int population;
    @SerializedName("latlng")
    private List<Float> latlng; // float
    @SerializedName("demonym")
    private String demonym;
    @SerializedName("area")
    private float area;
    @SerializedName("gini")
    private float gini;
    @SerializedName("timezones")
    private List<String> timezones; // Strings
    @SerializedName("borders")
    private List<String> borders; // strings
    @SerializedName("nativeName")
    private String nativeName;
    @SerializedName("numericCode")
    private String numericCode;
    @SerializedName("currencies")
    private List<Currency> currencies;
    @SerializedName("languages")
    private List<Language> languages;
    @SerializedName("translations")
    private Translations translations;
    @SerializedName("flag")
    private String flagUrl;
//    @SerializedName("regionalBlocs")
//    private List<RegionalBlocs> regionalBlocs;


    public Country(){ }

    public Country setName(String name){
        this.name = name;
        return this;
    }
    public String getName(){
        return this.name;
    }
    public Country setTopLevelDomain(List<String> topLevelDomain){
        this.topLevelDomain = topLevelDomain;
        return this;
    }
    public List<String> getTopLevelDomain(){
        return this.topLevelDomain;
    }
    public Country setAlpha2Code(String alpha2Code){
        this.alpha2Code = alpha2Code;
        return this;
    }
    public String getAlpha2Code(){
        return this.alpha2Code;
    }
    public Country setAlpha3Code(String alpha3Code){
        this.alpha3Code = alpha3Code;
        return this;
    }
    public String getAlpha3Code(){
        return this.alpha3Code;
    }
    public Country setCallingCodes(List<String> callingCodes){
        this.callingCodes = callingCodes;
        return this;
    }
    public List<String> getCallingCodes(){
        return this.callingCodes;
    }
    public Country setCapital(String capital){
        this.capital = capital;
        return this;
    }
    public String getCapital(){
        return this.capital;
    }
    public Country setAltSpellings(List<String> altSpellings){
        this.altSpellings = altSpellings;
        return this;
    }
    public List<String> getAltSpellings(){
        return this.altSpellings;
    }
    public Country setRegion(String region){
        this.region = region;
        return this;
    }
    public String getRegion(){
        return this.region;
    }
    public Country setSubregion(String subregion){
        this.subregion = subregion;
        return this;
    }
    public String getSubregion(){
        return this.subregion;
    }
    public Country setPopulation(int population){
        this.population = population;
        return this;
    }
    public int getPopulation(){
        return this.population;
    }
    public Country setLatlng(List<Float> latlng){
        this.latlng = latlng;
        return this;
    }
    public List<Float> getLatlng(){
        return this.latlng;
    }
    public Country setDemonym(String demonym){
        this.demonym = demonym;
        return this;
    }
    public String getDemonym(){
        return this.demonym;
    }
    public Country setArea(float area){
        this.area = area;
        return this;
    }
    public float getArea(){
        return this.area;
    }
    public Country setGini(float gini){
        this.gini = gini;
        return this;
    }
    public float getGini(){
        return this.gini;
    }
    public Country setTimezones(List<String> timezones){
        this.timezones = timezones;
        return this;
    }
    public List<String> getTimezones(){
        return this.timezones;
    }
    public Country setBorders(List<String> borders){
        this.borders = borders;
        return this;
    }
    public List<String> getBorders(){
        return this.borders;
    }
    public Country setNativeName(String nativeName){
        this.nativeName = nativeName;
        return this;
    }
    public String getNativeName(){
        return this.nativeName;
    }
    public Country setNumericCode(String numericCode){
        this.numericCode = numericCode;
        return this;
    }
    public String getNumericCode(){
        return this.numericCode;
    }
    public Country setCurrencies(List<Currency> currencies){
        this.currencies = currencies;
        return this;
    }
    public List<Currency> getCurrencies(){
        return this.currencies;
    }
    public Country setLanguages(List<Language> languages){
        this.languages = languages;
        return this;
    }
    public List<Language> getLanguages(){
        return this.languages;
    }
    public Country setTranslations(Translations translations){
        this.translations = translations;
        return this;
    }
    public Translations getTranslations(){
        return this.translations;
    }
    public Country setFlag(String url){
        this.flagUrl = url;
        return this;
    }
    public String getFlag(){
        return this.flagUrl;
    }

    public Country(Parcel in){
        name = in.readString();
        topLevelDomain = in.createStringArrayList();
        alpha2Code = in.readString();
        alpha3Code = in.readString();
        callingCodes = in.createStringArrayList();
        capital = in.readString();
        altSpellings = in.createStringArrayList();
        region = in.readString();
        subregion = in.readString();
        population = in.readInt();
        float[] fl = in.createFloatArray();
        latlng = Arrays.asList(ArrayUtils.toObject(fl));
        demonym = in.readString();
        area = in.readFloat();
        gini = in.readFloat();
        timezones = in.createStringArrayList();
        borders = in.createStringArrayList();
        nativeName = in.readString();
        numericCode = in.readString();
        currencies = in.createTypedArrayList(Currency.CREATOR);
        languages = in.createTypedArrayList(Language.CREATOR);
        translations = in.readParcelable(Translations.class.getClassLoader());
        flagUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeStringList(topLevelDomain);
        dest.writeString(alpha2Code);
        dest.writeString(alpha3Code);
        dest.writeStringList(callingCodes);
        dest.writeString(capital);
        dest.writeStringList(altSpellings);
        dest.writeString(region);
        dest.writeString(subregion);
        dest.writeInt(population);
        float fl[] = ArrayUtils.toPrimitive(latlng.toArray(new Float[latlng.size()]), 0.0F);
        dest.writeFloatArray(fl);
        dest.writeString(demonym);
        dest.writeFloat(area);
        dest.writeFloat(gini);
        dest.writeStringList(timezones);
        dest.writeStringList(borders);
        dest.writeString(nativeName);
        dest.writeString(numericCode);
        dest.writeTypedList(currencies);
        dest.writeTypedList(languages);
        dest.writeParcelable(translations, flags);
        dest.writeString(flagUrl);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

//    public Country setRegionalBlocs(List<RegionalBlocs> regionalBlocs){
//        this.regionalBlocs = regionalBlocs;
//        return this;
//    }
//    public List<RegionalBlocs> getRegionalBlocs(){
//        return this.regionalBlocs;
//    }

}
