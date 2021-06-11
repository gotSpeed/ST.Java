package com.aeroflot.webapp.models.personrelated;


import javax.persistence.*;



@Entity
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    protected long mId;

    @Column(name = "username")
    protected String mUsername;

    @Column(name = "password")
    protected String mPassword;

    @OneToOne
    @JoinColumn(
      name = "person",
      referencedColumnName = "id"
    )
    protected Person mPerson;



    // region getters/setters
    public long getId() {

        return mId;
    }



    public void setId(long id) {

        mId = id < 1 ? -1 : id;
    }



    public String getUsername() {

        return mUsername;
    }



    public void setUsername(String username) {

        mUsername = username;
    }



    public String getPassword() {

        return mPassword;
    }



    public void setPassword(String password) {

        mPassword = password;
    }



    public Person getPerson() {

        return mPerson;
    }



    public void setPerson(Person person) {

        mPerson = person;
    }
    // endregion

}
