package com.aeroflot.webapp.controllers;


import com.aeroflot.webapp.models.countryrelated.Country;
import com.aeroflot.webapp.models.flightrelated.Flight;
import com.aeroflot.webapp.models.flightrelated.FlightCrew;
import com.aeroflot.webapp.models.personrelated.Crew;
import com.aeroflot.webapp.models.transportrelated.Plane;
import com.aeroflot.webapp.repositories.*;
import com.aeroflot.webapp.services.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;



@Controller
public class FlightManagerController {

    @Autowired
    private ICountryRepository    mCountryRepository;
    @Autowired
    private IPlaneRepository      mPlaneRepository;
    @Autowired
    private IFlightRepository     mFlightRepository;
    @Autowired
    private ICrewRepository       mCrewRepository;
    @Autowired
    private IFlightCrewRepository mFlightCrewRepository;
    @Autowired
    private IUserRepository       mUserRepository;

    @Autowired
    private Authentication mAuthentication;



    @GetMapping("/new-flight-registration")
    public String newFlight(Model model, HttpSession httpSession) {

        if (mAuthentication.checkIfAuthenticated(httpSession) != null) {
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
        else {
            return "redirect:/auth/sign-in";
        }
    }



    @PostMapping("/new-flight-registration")
    public ModelAndView registerNewFlight(
      @RequestParam Map<String, String> requestParams,
      @RequestParam(value = "crewmates", required = false) Long[] crewmates
    ) {

        Flight           newFlight      = mapFlight(requestParams);
        List<Crew>       crewList       = mapCrew(crewmates);
        List<FlightCrew> flightCrewList = mapFlightCrew(newFlight, crewList);

        mFlightRepository.save(newFlight);
        mFlightCrewRepository.saveAll(flightCrewList);

        return new ModelAndView("redirect:/");
    }



    private Flight mapFlight(Map<String, String> params) {

        Flight flight = new Flight();

        flight.setDeparturePoint(
          mCountryRepository.getById(
            Short.parseShort(params.get("departure_point"))
          )
        );
        flight.setArrivalPoint(
          mCountryRepository.getById(
            Short.parseShort(params.get("arrival_point"))
          )
        );
        flight.setDepartureDateTime(params.get("departure_datetime"));
        flight.setArrivalDateTime(params.get("arrival_datetime"));
        flight.setPlane(
          mPlaneRepository.getById(
            Long.parseLong(params.get("plane"))
          )
        );
        // TODO: replace 1L.
        flight.setAdministrator(
          mUserRepository.getById(1L)
        );
        flight.setStatus("Scheduled");

        return flight;
    }



    private List<Crew> mapCrew(Long[] pickedCrewIds) {

        List<Crew> crewList = new ArrayList<>();

        for (Long crewmateId : pickedCrewIds) {
            Optional<Crew> crewmate = mCrewRepository.findById(crewmateId);

            crewmate.ifPresent(crewList::add);
        }

        return crewList;
    }



    private List<FlightCrew> mapFlightCrew(Flight flight, List<Crew> crewList) {

        List<FlightCrew> flightCrewList = new ArrayList<>(crewList.size());

        for (Crew crewmate : crewList) {
            flightCrewList.add(
              new FlightCrew(flight, crewmate)
            );
        }

        return flightCrewList;
    }

}
