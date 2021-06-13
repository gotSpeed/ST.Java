package com.aeroflot.webapp.repositories;


import com.aeroflot.webapp.models.flightrelated.Flight;
import org.springframework.data.jpa.repository.JpaRepository;



public interface IFlightRepository extends JpaRepository<Flight, Long> {

}
