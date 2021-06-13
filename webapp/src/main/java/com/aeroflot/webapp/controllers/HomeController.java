package com.aeroflot.webapp.controllers;


import com.aeroflot.webapp.models.flightrelated.Flight;
import com.aeroflot.webapp.repositories.IFlightRepository;
import com.aeroflot.webapp.services.Authentication;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.stream.Collectors;



@Controller
public class HomeController {

    @Autowired
    private IFlightRepository mFlightRepository;

    @Autowired
    private Authentication mAuthentication;

    private final int FLIGHTS_PER_PAGE = 10;

    private final Gson mGson;



    public HomeController() {

        mGson = new GsonBuilder().registerTypeAdapter(Flight.class, getDefaultTypeAdapter())
                                 .create();
    }



    @GetMapping("/")
    public String home(HttpSession httpSession, Model model) {

        if (mAuthentication.checkIfAuthenticated(httpSession) != null) {

            Pageable page = PageRequest.of(
              0,
              FLIGHTS_PER_PAGE,
              Sort.by(Sort.Order.asc("id"))
            );

            Page<Flight> specificFlights = mFlightRepository.findAll(page);

            model.addAttribute("allFlights", specificFlights.getContent())
                 .addAttribute("totalPages", specificFlights.getTotalPages());

            return "home";
        }
        else {
            return "redirect:/auth/sign-in";
        }
    }



    @GetMapping(value = "/get-page", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getPageSpecificFlights(@RequestParam("page") Integer page) {

        Pageable pageable = PageRequest.of(
          page,
          FLIGHTS_PER_PAGE,
          Sort.by(Sort.Order.asc("id"))
        );

        Collection<String> specificFlightsJson =
          mFlightRepository.findAll(pageable).stream()
                           .map(mGson::toJson)
                           .collect(Collectors.toList());

        return mGson.toJson(specificFlightsJson);
    }



    private JsonSerializer<Flight> getDefaultTypeAdapter() {

        return (flight, type, jsonSerializationContext) -> {

            JsonObject jsonObj = new JsonObject();

            jsonObj.addProperty("id", flight.getId());
            jsonObj.addProperty("departurePoint", flight.getDeparturePoint().getTitle());
            jsonObj.addProperty("arrivalPoint", flight.getArrivalPoint().getTitle());
            jsonObj.addProperty("departureDateTime", flight.getDepartureDateTime().toString());
            jsonObj.addProperty("arrivalDateTime", flight.getArrivalDateTime().toString());
            jsonObj.addProperty("status", flight.getStatus());

            return jsonObj;
        };
    }

}
