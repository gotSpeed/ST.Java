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
import java.util.stream.Collectors;



@WebServlet(name = "HomeServlet", urlPatterns = {"", "/home"})
public class HomeServlet extends HttpServlet {

    private static final int DEFAULT_PAGE_CAPACITY = 10;

    // TODO: Bean obj here.
    private FlightDao mFlightDao = new FlightDaoImpl();



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
                                                                                   ServletException,
                                                                                   IOException {

        if (Authentication.checkIfAuthenticated(request) != null) {

            int page = 1;
            String pageParam = request.getParameter("page");

            if (pageParam != null) {
                page = Integer.parseInt(pageParam);
            }

            List<Flight> flights = mFlightDao.getAll();

            int pagesCount = (int) Math.ceil(flights.size() / 10.0);

            flights = flights.stream()
                             .skip(DEFAULT_PAGE_CAPACITY * (page - 1))
                             .limit(DEFAULT_PAGE_CAPACITY)
                             .collect(Collectors.toList());

            request.setAttribute("flights", flights);
            request.setAttribute("page", page);
            request.setAttribute("pagesCount", pagesCount);
            request.getRequestDispatcher("/home.jsp").forward(request, response);
        }
        else {
            response.sendRedirect("/auth");
        }
    }

}
