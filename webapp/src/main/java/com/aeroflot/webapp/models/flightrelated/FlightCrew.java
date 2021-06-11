package com.aeroflot.webapp.models.flightrelated;



import com.aeroflot.webapp.models.personrelated.Crew;



public class FlightCrew {

    private Flight mFlight;
    private Crew   mCrew;



    public FlightCrew() {

    }



    public FlightCrew(Flight flight, Crew crew) {

        mFlight = flight;
        mCrew   = crew;
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
