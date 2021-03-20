package core.transportrelated;


public class Manufacturer {

    protected int    mId;
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
