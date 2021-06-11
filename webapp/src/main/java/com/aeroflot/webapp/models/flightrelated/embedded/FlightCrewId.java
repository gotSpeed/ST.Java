package com.aeroflot.webapp.models.flightrelated.embedded;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;



@Embeddable
public class FlightCrewId implements Serializable {

    private long mFlightId;
    private long mCrewId;



    public long getFlightId() {

        return mFlightId;
    }



    public void setFlightId(long flightId) {

        mFlightId = flightId;
    }



    public long getCrewId() {

        return mCrewId;
    }



    public void setCrewId(long crewId) {

        mCrewId = crewId;
    }

}
