package com.aeroflot.webapp.models.personrelated;


public class Crew {

    protected long   mId = -1;
    protected Person mPerson;
    protected String mRank;
    protected short  mExperienceYears;



    // region getters/setters
    public long getId() {

        return mId;
    }



    public void setId(long id) {

        mId = id < 1 ? -1 : id;
    }



    public Person getPerson() {

        return mPerson;
    }



    public void setPerson(Person person) {

        mPerson = person;
    }



    public String getRank() {

        return mRank;
    }



    public void setRank(String rank) {

        mRank = rank;
    }



    public short getExperienceYears() {

        return mExperienceYears;
    }



    public void setExperienceYears(short experienceYears) {

        mExperienceYears = experienceYears < 0 ? -1 : experienceYears;
    }
    // endregion

}
