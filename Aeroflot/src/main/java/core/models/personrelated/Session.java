package core.models.personrelated;


public class Session {

    private String mId = "";
    private User   mUser;
    private long   mExpiresAt;



    // region getters/setters
    public String getId() {

        return mId;
    }



    public void setId(String id) {

        mId = id;
    }



    public User getUser() {

        return mUser;
    }



    public void setUser(User user) {

        mUser = user;
    }



    public long getExpiresAt() {

        return mExpiresAt;
    }



    public void setExpiresAt(long expiresAt) {

        mExpiresAt = expiresAt < 0 ? 10 * 60 * 1000 : expiresAt;
    }
    // endregion

}
