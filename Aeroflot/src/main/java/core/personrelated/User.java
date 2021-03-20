package core.personrelated;


public class User {

    protected long   mId;
    protected String mUsername;
    protected String mPassword;
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
