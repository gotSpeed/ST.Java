package com.aeroflot.webapp.models.personrelated;


import javax.persistence.*;



@Entity
public class Crew {

    @Id
    @GeneratedValue
    @Column(name = "id")
    protected long id;

    @OneToOne(
      fetch = FetchType.EAGER
    )
    @JoinColumn(
      name = "person",
      referencedColumnName = "id"
    )
    protected Person mPerson;

    @Column(name = "rank")
    protected String mRank;

    @Column(name = "experience_years")
    protected short mExperienceYears;



    // region getters/setters
    public long getId() {

        return id;
    }



    public void setId(long id) {

        this.id = id < 1 ? -1 : id;
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
