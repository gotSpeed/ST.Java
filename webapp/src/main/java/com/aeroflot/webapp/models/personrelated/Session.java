package com.aeroflot.webapp.models.personrelated;


import javax.persistence.*;



@Entity
public class Session {

    @Id
    @Column(name = "id")
    private String id = "";

    @ManyToOne
    @JoinColumn(
      name = "user_id",
      referencedColumnName = "id"
    )
    private User mUser;

    @Column(name = "expires_at")
    private long mExpiresAt;



    // region getters/setters
    public String getId() {

        return id;
    }



    public void setId(String id) {

        this.id = id;
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
