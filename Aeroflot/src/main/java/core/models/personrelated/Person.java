package core.models.personrelated;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static dao.context.ModelsContext.DATE_PATTERN;



public class Person {

    protected long      mId = -1;
    protected String    mName;
    protected String    mSurname;
    protected short     mAge;
    protected LocalDate mBirthDate;
    protected String    mSex;
    protected String    mPassportIdentifier;
    protected LocalDate mIssueDate;
    protected Position  mPosition;



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



    public LocalDate getBirthDate() {

        return mBirthDate;
    }



    public void setBirthDate(String birthDate) {

        mBirthDate = LocalDate.parse(
            birthDate,
            DateTimeFormatter.ofPattern(DATE_PATTERN)
        );
    }



    public String getSex() {

        return mSex;
    }



    public void setSex(String sex) {

        mSex = sex.toUpperCase().substring(0, 1);
    }



    public String getPassportIdentifier() {

        return mPassportIdentifier;
    }



    public void setPassportIdentifier(String passportIdentifier) {

        mPassportIdentifier = passportIdentifier.toUpperCase();
    }



    public LocalDate getIssueDate() {

        return mIssueDate;
    }



    public void setIssueDate(String issueDate) {

        mIssueDate = LocalDate.parse(
            issueDate,
            DateTimeFormatter.ofPattern(DATE_PATTERN)
        );
    }



    public Position getPosition() {

        return mPosition;
    }



    public void setPosition(Position position) {

        mPosition = position;
    }
    // endregion

}
