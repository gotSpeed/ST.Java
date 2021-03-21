package servlets;


import core.models.countries.Country;
import core.models.flightrelated.Flight;
import core.models.transportrelated.Plane;
import dao.implementstions.CountryDaoImpl;
import dao.implementstions.FlightDaoImpl;
import dao.implementstions.PlaneDaoImpl;
import dao.interfaces.CountryDao;
import dao.interfaces.FlightDao;
import dao.interfaces.PlaneDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;



@WebServlet(name = "RegisterServlet", urlPatterns = "/new")
public class RegisterServlet extends HttpServlet {

    private CountryDao mCountryDao = new CountryDaoImpl();
    private PlaneDao   mPlaneDao   = new PlaneDaoImpl();
    private FlightDao  mFlightDao  = new FlightDaoImpl();



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
                                                                                   ServletException,
                                                                                   IOException {

        List<Country> countries = mCountryDao.getAll();
        List<Plane> planes = mPlaneDao.getAll();

        request.setAttribute("countries", countries);
        request.setAttribute("planes", planes);
        request.getRequestDispatcher("/new.jsp").forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
                                                                                    ServletException,
                                                                                    IOException {

        Flight newObj = new Flight();
        short departurePointId
            = Short.parseShort(request.getParameter("departure_point"));
        short arrivalPointId = Short.parseShort(request.getParameter("arrival_point"));
        long planeId = Long.parseLong(request.getParameter("plane"));

        newObj.setDepartureDateTime(request.getParameter("departure_datetime"));
        newObj.setArrivalDateTime(request.getParameter("arrival_datetime"));
        newObj.setDeparturePoint(
            mCountryDao.get(departurePointId)
        );
        newObj.setArrivalPoint(
            mCountryDao.get(arrivalPointId)
        );
        newObj.setPlane(
            mPlaneDao.get(planeId)
        );

        mFlightDao.create(newObj);

        response.sendRedirect("/home");
    }

}
