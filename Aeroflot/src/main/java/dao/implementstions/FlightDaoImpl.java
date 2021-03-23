package dao.implementstions;


import core.models.flightrelated.Flight;
import dao.context.ModelsContext;
import dao.context.PostgresDbContext;
import dao.interfaces.CountryDao;
import dao.interfaces.FlightDao;
import dao.interfaces.PlaneDao;
import dao.interfaces.UserDao;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



public class FlightDaoImpl implements FlightDao {

    // TODO: Bean here.
    private Connection mConnection;

    // TODO: Beans here.
    private UserDao    mUserDao;
    private PlaneDao   mPlaneDao;
    private CountryDao mCountryDao;

    private static List<Flight> mCachedFlightList;
    private static Flight       mCachedFlight;



    {
        try {
            mConnection = DriverManager.getConnection(PostgresDbContext.URL,
                                                      PostgresDbContext.USERNAME,
                                                      PostgresDbContext.PASSWORD);

            mUserDao      = new UserDaoImpl();
            mPlaneDao     = new PlaneDaoImpl();
            mCountryDao   = new CountryDaoImpl();
            mCachedFlight = new Flight();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }



    @Override
    public synchronized Flight get(long id) {

        assert mCachedFlight != null : "Cached Flight object is null.";
        if (mCachedFlight.getId() == id) {
            return mCachedFlight;
        }

        try {
            PreparedStatement query = mConnection.prepareStatement(
                "SELECT * FROM flight WHERE id=?;"
            );

            query.setLong(1, id);

            ResultSet result = query.executeQuery();
            if (result.next()) {
                mCachedFlight = parseToObject(result);
                return mCachedFlight;
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
    @NotNull
    public synchronized List<Flight> getAll() {

        if (mCachedFlightList != null) {
            return mCachedFlightList;
        }

        try {
            PreparedStatement query = mConnection.prepareStatement(
                "SELECT * FROM flight ORDER BY id;"
            );

            ResultSet result = query.executeQuery();
            mCachedFlightList = new ArrayList<>();

            while (result.next()) {
                Flight flight = parseToObject(result);
                if (flight != null) {
                    mCachedFlightList.add(flight);
                }
            }

            return mCachedFlightList;
        }
        catch (SQLException e) {
            // TODO: logger.
            e.printStackTrace();

            return new ArrayList<>(0);
        }
    }



    @Override
    public synchronized void create(Flight obj) {

        try {
            PreparedStatement query = mConnection.prepareStatement(
                "INSERT INTO flight (created_by, status, when_registered, " +
                "departure_datetime, arrival_datetime, departure_point, " +
                "arrival_point, plane) " +
                "VALUES (?, 'SCHEDULED', ?, ?, ?, ?, ?, ?);"
            );

            Timestamp currentTimestamp = Timestamp.valueOf(
                ModelsContext.toTimestampFormat(LocalDateTime.now().toString())
            );
            Timestamp departureTimestamp = Timestamp.valueOf(
                ModelsContext.toTimestampFormat(obj.getArrivalDateTime().toString())
            );
            Timestamp arrivalTimestamp = Timestamp.valueOf(
                ModelsContext.toTimestampFormat(obj.getArrivalDateTime().toString())
            );

            query.setLong(1, obj.getAdministrator().getId());
            query.setTimestamp(2, currentTimestamp);
            query.setTimestamp(3, departureTimestamp);
            query.setTimestamp(4, arrivalTimestamp);
            query.setShort(5, obj.getDeparturePoint().getId());
            query.setShort(6, obj.getArrivalPoint().getId());
            query.setLong(7, obj.getPlane().getId());

            query.executeUpdate();

            if (obj.getId() == mCachedFlight.getId()) {
                mCachedFlight = new Flight();
            }
            mCachedFlightList = null;
        }
        catch (SQLException e) {
            // TODO: logger.
            e.printStackTrace();
        }
    }



    @Override
    public synchronized void update(Flight obj) {

        try {
            PreparedStatement query = mConnection.prepareStatement(
                "UPDATE flight " +
                "SET arrival_point=?, arrival_datetime=?, status=? " +
                "WHERE id=?;"
            );

            Timestamp arrivalTimestamp = Timestamp.valueOf(
                ModelsContext.toTimestampFormat(obj.getArrivalDateTime().toString())
            );

            query.setShort(1, obj.getArrivalPoint().getId());
            query.setTimestamp(2, arrivalTimestamp);
            query.setString(3, obj.getStatus());
            query.setLong(4, obj.getId());

            query.executeUpdate();

            if (obj.getId() == mCachedFlight.getId()) {
                mCachedFlight = new Flight();
            }
            mCachedFlightList = null;
        }
        catch (SQLException e) {
            // TODO: logger.
            e.printStackTrace();
        }
    }



    @Override
    public synchronized void delete(Flight obj) {

    }



    @Nullable
    private Flight parseToObject(ResultSet resultSet) {

        try {
            Flight obj = new Flight();

            obj.setId(resultSet.getLong("id"));
            obj.setAdministrator(
                mUserDao.get(resultSet.getLong("created_by"))
            );
            obj.setStatus(resultSet.getString("status").toUpperCase());
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
            // TODO: logger.
            e.printStackTrace();

            return null;
        }
    }

}
