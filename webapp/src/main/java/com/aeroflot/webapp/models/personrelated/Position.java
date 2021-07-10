package com.aeroflot.webapp.models.personrelated;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class Position {

    @Id
    @GeneratedValue
    @Column(name = "id")
    protected short mId;

    @Column(name = "title")
    protected String mTitle;



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

        mTitle = title;
    }
    // endregion

}
