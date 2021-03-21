package core.models.personrelated;


public class Position {

    protected short  mId = -1;
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
