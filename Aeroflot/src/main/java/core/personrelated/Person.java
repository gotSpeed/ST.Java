package core.personrelated;


import java.util.Date;



public class Person {

    protected long     mId;
    protected String   mName;
    protected String   mSurname;
    protected short    mAge;
    protected Date     mBirthDate;
    protected char     mSex;
    protected String   mPassportIdentifier;
    protected Date     mIssueDate;
    protected Position mPosition;



    // region getters/setters
    public long getId() {

        return mId;
    }



    public void setId(long id) {

        mId = id < 1 ? -1 : id;
    }



    public String getName() {

        return mName;
    }



    public void setName(String name) {

        if (name != null) {
            mName = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        }
    }



    public String getSurname() {

        return mSurname;
    }



    public void setSurname(String surname) {

        if (surname != null) {
            mSurname = surname.substring(0, 1).toUpperCase() +
                       surname.substring(1).toLowerCase();
        }
    }



    public short getAge() {

        return mAge;
    }



    public void setAge(short age) {

        mAge = age < 0 ? -1 : age;
    }



    public Date getBirthDate() {

        return mBirthDate;
    }



    public void setBirthDate(Date birthDate) {

        mBirthDate = birthDate;
    }



    public char getSex() {

        return mSex;
    }



    public void setSex(char sex) {

        mSex = Character.toUpperCase(sex);
    }



    public String getPassportIdentifier() {

        return mPassportIdentifier;
    }



    public void setPassportIdentifier(String passportIdentifier) {

        mPassportIdentifier = passportIdentifier.toUpperCase();
    }



    public Date getIssueDate() {

        return mIssueDate;
    }



    public void setIssueDate(Date issueDate) {

        mIssueDate = issueDate;
    }



    public Position getPosition() {

        return mPosition;
    }



    public void setPosition(Position position) {

        mPosition = position;
    }
    // endregion

}
