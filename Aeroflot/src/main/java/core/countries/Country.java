package core.countries;


public class Country {

    protected short  mId;
    protected String mTitle;
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

        mCountryCode = countryCode.toUpperCase().substring(0, 2);
    }
    // endregion

}
