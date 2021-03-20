package servlets;


import core.flightrelated.Flight;
import dao.interfaces.FlightDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;



@WebServlet(name = "HomeServlet", urlPatterns = {"/", "/home"})
public class HomeServlet extends HttpServlet {

    //TODO: Bean obj here.
    private FlightDao mFlightDao;



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
                                                                                   ServletException,
                                                                                   IOException {

        List<Flight> flights = mFlightDao.getAll();

        request.setAttribute("flights", flights);
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws
                                                                            ServletException,
                                                                            IOException {

        super.doPost(req, resp);
    }



    public void setFlightDao(FlightDao flightDao) {

        mFlightDao = flightDao;
    }

}
