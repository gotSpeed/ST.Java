package servlets;


import core.models.countries.Country;
import core.models.flightrelated.Flight;
import core.models.flightrelated.FlightCrew;
import core.models.personrelated.Crew;
import core.models.personrelated.User;
import core.models.transportrelated.Plane;
import dao.context.PostgresDbContext;
import dao.implementstions.*;
import dao.interfaces.*;
import services.Authentication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



@WebServlet(name = "CreateServlet", urlPatterns = "/new")
public class CreateServlet extends HttpServlet {

    private CountryDao    mCountryDao    = new CountryDaoImpl();
    private PlaneDao      mPlaneDao      = new PlaneDaoImpl();
    private CrewDao       mCrewDao       = new CrewDaoImpl();
    private FlightDao     mFlightDao     = new FlightDaoImpl();
    private FlightCrewDao mFlightCrewDao = new FlightCrewDaoImpl();



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
                                                                                   ServletException,
                                                                                   IOException {

        if (Authentication.checkIfAuthenticated(request) != null) {

            List<Country> countries = mCountryDao.getAll();
            List<Plane> planes = mPlaneDao.getAll();
            List<Crew> personal = mCrewDao.getAll();

            request.setAttribute("countries", countries);
            request.setAttribute("planes", planes);
            request.setAttribute("personal", personal);

            request.getRequestDispatcher("/create.jsp").forward(request, response);
        }
        else {
            response.sendRedirect("/auth");
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
                                                                                    IOException {

        User currentUser = Authentication.checkIfAuthenticated(request);
        if (currentUser != null) {

            Flight flight = parseFormToFlight(request);
            flight.setAdministrator(currentUser);

            mFlightDao.create(flight);

            flight.setId((Long) PostgresDbContext.getLastId("flight", "id"));

            List<Crew> newCrewList = parseFormToCrewList(request);

            for (Crew item : newCrewList) {
                FlightCrew flightCrew = new FlightCrew(flight, item);
                mFlightCrewDao.create(flightCrew);
            }

            response.sendRedirect("/home");
        }
        else {
            response.sendRedirect("/auth");
        }
    }



    private Flight parseFormToFlight(HttpServletRequest request) {

        Flight newObj = new Flight();
        short departurePointId = Short.parseShort(request.getParameter("departure_point"));
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

        return newObj;
    }



    private List<Crew> parseFormToCrewList(HttpServletRequest request) {

        List<Crew> crewList = new ArrayList<>();
        String[] personal = request.getParameterValues("personal");

        for (String person : personal) {
            Crew crew = mCrewDao.get(Long.parseLong(person));
            if (crew != null) {
                crewList.add(crew);
            }
        }

        return crewList;
    }

}
