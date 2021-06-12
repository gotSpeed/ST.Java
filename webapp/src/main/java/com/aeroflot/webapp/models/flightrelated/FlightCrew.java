package com.aeroflot.webapp.models.flightrelated;



import com.aeroflot.webapp.models.flightrelated.embedded.FlightCrewId;
import com.aeroflot.webapp.models.personrelated.Crew;

import javax.persistence.*;



@Entity
@Table(name = "flightcrew")
public class FlightCrew {

    @EmbeddedId
    @AttributeOverrides(
      {
        @AttributeOverride(name = "flightId", column = @Column(name = "flight_id")),
        @AttributeOverride(name = "crewId", column = @Column(name = "crew_id"))
      }
    )
    private FlightCrewId mId;

    @ManyToOne
    @MapsId("flightId")
    @JoinColumn(name = "flight_id")
    private Flight mFlight;

    @ManyToOne
    @MapsId("crewId")
    @JoinColumn(name = "crew_id")
    private Crew mCrew;



    public FlightCrew() {

    }



    public FlightCrew(Flight flight, Crew crew) {

        mFlight = flight;
        mCrew   = crew;

        mId = new FlightCrewId(flight.getId(), crew.getId());
    }



    // region getters/setters
    public Flight getFlight() {

        return mFlight;
    }



    public void setFlight(Flight flight) {

        mFlight = flight;
    }



    public Crew getCrew() {

        return mCrew;
    }



    public void setCrew(Crew crew) {

        mCrew = crew;
    }
    // endregion

}
