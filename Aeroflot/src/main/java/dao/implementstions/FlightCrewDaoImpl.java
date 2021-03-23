package dao.implementstions;


import core.models.flightrelated.FlightCrew;
import dao.context.PostgresDbContext;
import dao.interfaces.CrewDao;
import dao.interfaces.FlightCrewDao;
import dao.interfaces.FlightDao;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class FlightCrewDaoImpl implements FlightCrewDao {

    // TODO: Bean here.
    private Connection mConnection;

    // TODO: Beans here.
    private CrewDao   mCrewDao;
    private FlightDao mFlightDao;



    {
        try {
            mConnection = DriverManager.getConnection(PostgresDbContext.URL,
                                                      PostgresDbContext.USERNAME,
                                                      PostgresDbContext.PASSWORD);

            mCrewDao   = new CrewDaoImpl();
            mFlightDao = new FlightDaoImpl();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public @Nullable FlightCrew get(long id) {

        try {
            PreparedStatement query = mConnection.prepareStatement(
                "SELECT * FROM flightcrew WHERE flight_id=?;"
            );

            query.setLong(1, id);

            ResultSet result = query.executeQuery();
            if (result.next()) {
                return parseObject(result);
            }
            else {
                return null;
            }
        }
        catch (SQLException e) {
            // TODO: logger.
            e.printStackTrace();

            return null;
        }
    }



    @Override
    public @NotNull List<FlightCrew> getAll() {

        try {
            PreparedStatement query = mConnection.prepareStatement(
                "SELECT * FROM flightcrew;"
            );

            ResultSet result = query.executeQuery();
            List<FlightCrew> flightCrewList = new ArrayList<>();

            while (result.next()) {
                FlightCrew flightCrew = parseObject(result);
                if (flightCrew != null) {
                    flightCrewList.add(flightCrew);
                }
            }

            return flightCrewList;
        }
        catch (SQLException e) {
            // TODO: logger.
            e.printStackTrace();

            return new ArrayList<>(0);
        }
    }



    @Override
    public void create(FlightCrew obj) {

        try {
            PreparedStatement query = mConnection.prepareStatement(
                "INSERT INTO flightcrew (crew_id, flight_id) VALUES (?, ?);"
            );

            query.setLong(1, obj.getCrew().getId());
            query.setLong(2, obj.getFlight().getId());

            query.executeUpdate();
        }
        catch (SQLException e) {
            // TODO: logger.
            e.printStackTrace();
        }
    }



    @Override
    public void update(FlightCrew obj) {

    }



    @Override
    public void delete(FlightCrew obj) {

    }



    @Nullable
    private FlightCrew parseObject(ResultSet result) {

        try {
            FlightCrew obj = new FlightCrew();

            obj.setCrew(
                mCrewDao.get(result.getLong("crew_id"))
            );
            obj.setFlight(
                mFlightDao.get(result.getLong("flight_id"))
            );

            return obj;
        }
        catch (SQLException e) {
            // TODO: logger.
            e.printStackTrace();

            return null;
        }
    }

}
