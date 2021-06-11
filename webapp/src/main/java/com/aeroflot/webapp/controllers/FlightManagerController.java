package com.aeroflot.webapp.controllers;


import com.aeroflot.webapp.models.countryrelated.Country;
import com.aeroflot.webapp.models.flightrelated.Flight;
import com.aeroflot.webapp.models.flightrelated.FlightCrew;
import com.aeroflot.webapp.models.personrelated.Crew;
import com.aeroflot.webapp.models.transportrelated.Plane;
import com.aeroflot.webapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class FlightManagerController {

    @Autowired
    private ICountryRepository mCountryRepository;
    @Autowired
    private IPlaneRepository   mPlaneRepository;
    @Autowired
    private IFlightRepository  mFlightRepository;
    @Autowired
    private ICrewRepository    mCrewRepository;



    @GetMapping("/new-flight-registration")
    public String newFlight(Model model) {

        Iterable<Country> countries = mCountryRepository.findAll(
          Sort.by(Sort.Direction.ASC, "id")
        );

        Iterable<Plane> planes = mPlaneRepository.findAll(
          Sort.by(Sort.Order.asc("id"))
        );

        Iterable<Crew> crewmates = mCrewRepository.findAllByOrderByName();

        model.addAttribute("countries", countries)
             .addAttribute("planes", planes)
             .addAttribute("crewmates", crewmates);

        return "create";
    }



    @PostMapping("/new-flight-registration")
    public String registerNewFlight(
      @RequestParam("departure_datetime") String departureDateTime,
      @RequestParam("arrival_datetime") String arrivalDateTime,
      @RequestParam("departure_point") String departurePoint,
      @RequestParam("arrival_point") String arrivalPoint,
      @RequestParam("plane") String planeId
    ) {

        Flight newFlight = new Flight();
        newFlight.setDeparturePoint(
          new Country(Short.parseShort(departurePoint))
        );
        newFlight.setArrivalPoint(
          new Country(Short.parseShort(arrivalPoint))
        );
        Plane plane = mPlaneRepository.getById(Long.parseLong(planeId));

        newFlight.setDepartureDateTime(departureDateTime);
        newFlight.setArrivalDateTime(arrivalDateTime);
        newFlight.setPlane(plane);

        mFlightRepository.save(newFlight);

        return null;
    }

}
