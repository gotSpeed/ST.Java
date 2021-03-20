package dao.implementstions;


import core.flightrelated.Flight;
import core.predefined.flightstatus.Status;
import dao.context.PostgresDbContext;
import dao.interfaces.CountryDao;
import dao.interfaces.FlightDao;
import dao.interfaces.PlaneDao;
import dao.interfaces.UserDao;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class FlightDaoImpl implements FlightDao {

    private Connection mConnection;
    private UserDao    mUserDao;
    private PlaneDao   mPlaneDao;
    private CountryDao mCountryDao;

    private PreparedStatement mSelectAllQuery;

    private List<Flight> mFlights;



    // TODO: patch with Bean.
    /*init*/ {
        try {
            mConnection = DriverManager.getConnection(PostgresDbContext.URL,
                                                      PostgresDbContext.USERNAME,
                                                      PostgresDbContext.PASSWORD);

            mSelectAllQuery = mConnection.prepareStatement(
                "SELECT * FROM flight;"
            );
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }



    @Override
    public Flight get(long id) {

        return null;
    }



    @Override
    @NotNull
    public List<Flight> getAll() {

        // Cached.
        if (mFlights != null) {
            return mFlights;
        }

        try {
            ResultSet result = mSelectAllQuery.executeQuery();

            mFlights = new ArrayList<>();

            Flight tmp;
            while (result.next()) {
                tmp = parseObject(result);

                if (tmp != null) {
                    mFlights.add(tmp);
                }
            }

            return mFlights;
        }
        catch (SQLException e) {
            // TODO: there could be a normal logger.
            System.out.println(e.getMessage());
            e.printStackTrace();

            return new ArrayList<>(0);
        }
    }



    @Override
    public void create(Flight obj) {

    }



    @Override
    public void update(Flight obj) {

    }



    @Override
    public void delete(Flight obj) {

    }



    private Flight parseObject(ResultSet resultSet) {

        try {
            Flight obj = new Flight();

            obj.setId(resultSet.getLong("id"));
            obj.setAdministrator(
                mUserDao.get(resultSet.getLong("created_by"))
            );
            obj.setStatus(
                Status.valueOf(resultSet.getString("status"))
            );
            obj.setWhenRegisteredDateTime(resultSet.getString("when_registered"));
            obj.setDepartureDateTime(resultSet.getString("departure_datetime"));
            obj.setArrivalDateTime(resultSet.getString("arrival_datetime"));
            obj.setDeparturePoint(
                mCountryDao.get(resultSet.getShort("departure_point"))
            );
            obj.setArrivalPoint(
                mCountryDao.get(resultSet.getShort("arrival_point"))
            );
            obj.setPlane(
                mPlaneDao.get(resultSet.getLong("plane"))
            );

            return obj;
        }
        catch (SQLException e) {
            // TODO: there could be a normal logger.
            System.out.println(e.getMessage());
            e.printStackTrace();

            return null;
        }
    }

}
