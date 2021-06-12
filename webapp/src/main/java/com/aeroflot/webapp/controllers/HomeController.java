package com.aeroflot.webapp.controllers;


import com.aeroflot.webapp.models.flightrelated.Flight;
import com.aeroflot.webapp.repositories.IFlightRepository;
import com.aeroflot.webapp.services.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;



@Controller
public class HomeController {

    @Autowired
    private IFlightRepository mFlightRepository;

    @Autowired
    private Authentication mAuthentication;



    @GetMapping("/")
    public String home(HttpSession httpSession, Model model) {

        if (mAuthentication.checkIfAuthenticated(httpSession) != null) {
            Iterable<Flight> flights = mFlightRepository.findAllByOrderByIdAsc();

            model.addAttribute("allFlights", flights);
            return "home";
        }
        else {
            return "redirect:/auth/sign-in";
        }
    }

}
