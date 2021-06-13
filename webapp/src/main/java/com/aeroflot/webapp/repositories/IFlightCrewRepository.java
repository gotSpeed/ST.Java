package com.aeroflot.webapp.repositories;


import com.aeroflot.webapp.models.flightrelated.FlightCrew;
import com.aeroflot.webapp.models.flightrelated.embeddable.FlightCrewId;
import org.springframework.data.jpa.repository.JpaRepository;



public interface IFlightCrewRepository extends JpaRepository<FlightCrew, FlightCrewId> {

}
