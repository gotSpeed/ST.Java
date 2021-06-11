package com.aeroflot.webapp.models.transportrelated;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class Manufacturer {

    @Id
    @GeneratedValue
    @Column(name = "id")
    protected int mId;

    @Column(name = "title")
    protected String mTitle = "UNKNOWN";



    // region getters/setters
    public int getId() {

        return mId;
    }



    public void setId(int id) {

        mId = id < 1 ? -1 : id;
    }



    public String getTitle() {

        return mTitle;
    }



    public void setTitle(String title) {

        mTitle = title;
    }
    // endregion

}
