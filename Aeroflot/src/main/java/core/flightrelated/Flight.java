package core.flightrelated;


import core.countries.Country;
import core.personrelated.User;
import core.predefined.flightstatus.Status;
import core.transportrelated.Plane;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static dao.context.ModelsContext.TIMESTAMP_PATTERN;



public class Flight implements Serializable {

    protected long          mId;
    protected User          mAdministrator;
    protected Status        mStatus;
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



    public Status getStatus() {

        return mStatus;
    }



    public void setStatus(Status status) {

        mStatus = status;
    }



    public LocalDateTime getWhenRegisteredDateTime() {

        return mWhenRegisteredDateTime;
    }



    public void setWhenRegisteredDateTime(String whenRegisteredDateTime) {

        mWhenRegisteredDateTime = LocalDateTime.parse(
            whenRegisteredDateTime,
            DateTimeFormatter.ofPattern(TIMESTAMP_PATTERN)
        );
    }



    public LocalDateTime getDepartureDateTime() {

        return mDepartureDateTime;
    }



    public void setDepartureDateTime(String departureDateTime) {

        LocalDateTime.parse(
            departureDateTime,
            DateTimeFormatter.ofPattern(TIMESTAMP_PATTERN)
        );
    }



    public LocalDateTime getArrivalDateTime() {

        return mArrivalDateTime;
    }



    public void setArrivalDateTime(String arrivalDateTime) {

        LocalDateTime.parse(
            arrivalDateTime,
            DateTimeFormatter.ofPattern(TIMESTAMP_PATTERN)
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
