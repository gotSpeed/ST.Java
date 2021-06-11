package com.aeroflot.webapp.models.countryrelated;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class Country {

    @Id
    @GeneratedValue
    @Column(name = "id")
    protected short mId;

    @Column(name = "title")
    protected String mTitle;

    @Column(name = "country_code")
    protected String mCountryCode;



    // region getters/setters
    public short getId() {

        return mId;
    }



    public void setId(short id) {

        mId = id < 1 ? -1 : id;
    }



    public String getTitle() {

        return mTitle;
    }



    public void setTitle(String title) {

        mTitle = title == null ? "UNDEFINED" : title;
    }



    public String getCountryCode() {

        return mCountryCode;
    }



    public void setCountryCode(String countryCode) {

        mCountryCode = countryCode.substring(0, 2).toUpperCase();
    }
    // endregion

}
