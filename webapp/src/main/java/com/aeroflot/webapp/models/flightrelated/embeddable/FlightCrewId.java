package com.aeroflot.webapp.models.flightrelated.embeddable;


import javax.persistence.Embeddable;
import java.io.Serializable;



@Embeddable
public class FlightCrewId implements Serializable {

    private long flightId;
    private long crewId;



    public FlightCrewId() {

    }



    public FlightCrewId(long flightId, long crewId) {

        this.flightId = flightId;
        this.crewId   = crewId;
    }



    public long getFlightId() {

        return flightId;
    }



    public void setFlightId(long flightId) {

        this.flightId = flightId;
    }



    public long getCrewId() {

        return crewId;
    }



    public void setCrewId(long crewId) {

        this.crewId = crewId;
    }

}
