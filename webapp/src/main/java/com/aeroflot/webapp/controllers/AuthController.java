package com.aeroflot.webapp.controllers;


import com.aeroflot.webapp.services.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;



@Controller
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    private Authentication mAuthentication;



    @GetMapping("/sign-in")
    public String signIn() {

        return "authentication";
    }



    @PostMapping("/sign-in")
    public String performSignIn(@RequestParam Map<String, String> params,
                                HttpSession httpSession) {

        if (mAuthentication.authenticate(params, httpSession)) {
            return "redirect:/";
        }
        else {
            return "redirect:/auth/sign-in";
        }
    }

}
