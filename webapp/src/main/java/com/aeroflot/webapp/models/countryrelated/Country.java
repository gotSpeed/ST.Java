package com.aeroflot.webapp.models.countryrelated;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class Country {

    @GeneratedValue
    @Column(name = "id")
    protected short id;

    @Column(name = "title")
    protected String mTitle;

    @Column(name = "country_code")
    protected String mCountryCode;


    public Country() {}

    public Country(final short id) {
        this.id = id;
    }



    // region getters/setters
    @Id
    public short getId() {

        return id;
    }



    public void setId(short id) {

        this.id = id < 1 ? -1 : id;
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
