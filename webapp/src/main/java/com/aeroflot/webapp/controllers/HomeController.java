package com.aeroflot.webapp.controllers;


import com.aeroflot.webapp.models.flightrelated.Flight;
import com.aeroflot.webapp.repositories.IFlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {

    @Autowired
    private IFlightRepository mFlightRepository;

    @GetMapping("/")
    public String Home(Model model) {

        Iterable<Flight> flights = mFlightRepository.findAllByOrderByIdAsc();

        model.addAttribute("allFlights", flights);
        return "home";
    }
}
