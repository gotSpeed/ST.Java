package servlets;


import core.models.countries.Country;
import core.models.flightrelated.Flight;
import dao.context.ModelsContext;
import dao.implementstions.CountryDaoImpl;
import dao.implementstions.FlightDaoImpl;
import dao.interfaces.CountryDao;
import dao.interfaces.FlightDao;
import services.Authentication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;



@WebServlet(name = "EditServlet", urlPatterns = "/edit")
public class EditServlet extends HttpServlet {

    // TODO: Bean.
    private FlightDao  mFlightDao  = new FlightDaoImpl();
    private CountryDao mCountryDao = new CountryDaoImpl();

    private Flight mEditableFlight;



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
                                                                                   ServletException,
                                                                                   IOException {

        if (Authentication.checkIfAuthenticated(request) != null) {

            long editableId = Long.parseLong(request.getParameter("editable_id"));
            mEditableFlight = mFlightDao.get(editableId);
            List<Country> countries = mCountryDao.getAll();

            request.setAttribute("flight", mEditableFlight);
            request.setAttribute("countries", countries);
            request.getRequestDispatcher("/edit.jsp").forward(request, response);
        }
        else {
            response.sendRedirect("/auth");
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
                                                                                    IOException {

        if (Authentication.checkIfAuthenticated(request) != null) {

            short country_id = Short.parseShort(request.getParameter("arrival_point"));
            if (country_id != mEditableFlight.getArrivalPoint().getId()) {
                mEditableFlight.setArrivalPoint(
                    mCountryDao.get(country_id)
                );
            }

            mEditableFlight.setArrivalDateTime(
                ModelsContext.toTimestampFormat(request.getParameter("arrival_datetime"))
            );

            mEditableFlight.setStatus(
                request.getParameter("status")
            );

            mFlightDao.update(mEditableFlight);

            response.sendRedirect("/home");
        }
        else {
            response.sendRedirect("/auth");
        }
    }

}
