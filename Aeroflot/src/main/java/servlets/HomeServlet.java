package servlets;


import core.models.flightrelated.Flight;
import dao.implementstions.FlightDaoImpl;
import dao.interfaces.FlightDao;
import services.Authentication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;



@WebServlet(name = "HomeServlet", urlPatterns = {"", "/home"})
public class HomeServlet extends HttpServlet {

    // TODO: Bean obj here.
    private FlightDao mFlightDao = new FlightDaoImpl();



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
                                                                                   ServletException,
                                                                                   IOException {

        if (Authentication.checkIfAuthenticated(request) != null) {
            List<Flight> flights = mFlightDao.getAll();

            request.setAttribute("flights", flights);
            request.getRequestDispatcher("/home.jsp").forward(request, response);
        }
        else {
            response.sendRedirect("/auth");
        }
    }

}
