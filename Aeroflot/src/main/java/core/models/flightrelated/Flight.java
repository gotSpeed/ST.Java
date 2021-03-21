package core.models.flightrelated;


import core.models.countries.Country;
import core.models.personrelated.User;
import core.models.transportrelated.Plane;

import java.io.Serializable;
import java.time.LocalDateTime;

import static dao.context.ModelsContext.TIMESTAMP_FORMATTER;



public class Flight implements Serializable {

    protected long          mId = -1;
    protected User          mAdministrator;
    protected String        mStatus;
    protected LocalDateTime mWhenRegisteredDateTime;
    protected LocalDateTime mDepartureDateTime;
    protected LocalDateTime mArrivalDateTime;
    protected Country       mDeparturePoint;
    protected Country       mArrivalPoint;
    protected Plane         mPlane;



    // region getters/setters
    public long getId() {

        return mId;
    }



    public void setId(long id) {

        mId = id < 1 ? -1 : id;
    }



    public User getAdministrator() {

        return mAdministrator;
    }



    public void setAdministrator(User administrator) {

        mAdministrator = administrator;
    }



    public String getStatus() {

        return mStatus;
    }



    public void setStatus(String status) {

        mStatus = status;
    }



    public LocalDateTime getWhenRegisteredDateTime() {

        return mWhenRegisteredDateTime;
    }



    public void setWhenRegisteredDateTime(String whenRegisteredDateTime) {

        mWhenRegisteredDateTime = LocalDateTime.parse(
            whenRegisteredDateTime,
            TIMESTAMP_FORMATTER
        );
    }



    public LocalDateTime getDepartureDateTime() {

        return mDepartureDateTime;
    }



    public void setDepartureDateTime(String departureDateTime) {

        mDepartureDateTime = LocalDateTime.parse(
            departureDateTime,
            TIMESTAMP_FORMATTER
        );
    }



    public LocalDateTime getArrivalDateTime() {

        return mArrivalDateTime;
    }



    public void setArrivalDateTime(String arrivalDateTime) {

        mArrivalDateTime = LocalDateTime.parse(
            arrivalDateTime,
            TIMESTAMP_FORMATTER
        );
    }



    public Country getDeparturePoint() {

        return mDeparturePoint;
    }



    public void setDeparturePoint(Country departurePoint) {

        mDeparturePoint = departurePoint;
    }



    public Country getArrivalPoint() {

        return mArrivalPoint;
    }



    public void setArrivalPoint(Country arrivalPoint) {

        mArrivalPoint = arrivalPoint;
    }



    public Plane getPlane() {

        return mPlane;
    }



    public void setPlane(Plane plane) {

        mPlane = plane;
    }
    // endregion

}
