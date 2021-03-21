package dao.implementstions;


import core.models.transportrelated.Plane;
import dao.context.PostgresDbContext;
import dao.interfaces.ManufacturerDao;
import dao.interfaces.PlaneDao;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class PlaneDaoImpl implements PlaneDao {

    // TODO: Bean here.
    private Connection mConnection;

    // TODO: Bean here.
    private ManufacturerDao mManufacturerDao;

    private List<Plane> mCachedPlaneList;
    private Plane       mCachedPlane;



    {
        try {
            mConnection      = DriverManager.getConnection(PostgresDbContext.URL,
                                                           PostgresDbContext.USERNAME,
                                                           PostgresDbContext.PASSWORD);
            mManufacturerDao = new ManufacturerDaoImpl();
            mCachedPlane     = new Plane();
        }
        catch (SQLException e) {
            // TODO: logger.
            e.printStackTrace();
        }
    }



    @Override
    @Nullable
    public Plane get(long id) {

        assert mCachedPlane != null : "Cached Plane object is null.";
        if (mCachedPlane.getId() == id) {
            return mCachedPlane;
        }

        try {
            PreparedStatement query = mConnection.prepareStatement(
                "SELECT * FROM plane WHERE id=?;"
            );

            query.setLong(1, id);

            ResultSet result = query.executeQuery();
            if (result.next()) {
                mCachedPlane = parseObject(result);
                return mCachedPlane;
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
    public List<Plane> getAll() {

        if (mCachedPlaneList != null) {
            return mCachedPlaneList;
        }

        try {
            PreparedStatement query = mConnection.prepareStatement(
                "SELECT * FROM plane;"
            );

            ResultSet result = query.executeQuery();
            mCachedPlaneList = new ArrayList<>();

            while (result.next()) {
                Plane plane = parseObject(result);
                if (plane != null) {
                    mCachedPlaneList.add(plane);
                }
            }

            return mCachedPlaneList;
        }
        catch (SQLException e) {
            // TODO: logger.
            e.printStackTrace();

            return new ArrayList<>(0);
        }
    }



    @Override
    public void create(Plane obj) {

    }



    @Override
    public void update(Plane obj) {

    }



    @Override
    public void delete(Plane obj) {

    }



    @Nullable
    private Plane parseObject(ResultSet result) {

        try {
            Plane obj = new Plane();

            obj.setId(result.getLong("id"));
            obj.setTitle(result.getString("title"));
            obj.setModel(result.getString("model"));
            obj.setManufacturer(
                mManufacturerDao.get(result.getInt("manufacturer"))
            );
            obj.setManufactureDate(result.getString("manufacture_date"));
            obj.setFlightsAmount(result.getShort("flights_amount"));
            obj.setSeatsCount(result.getShort("seats_count"));
            obj.setMaxFlightDistance(result.getShort("max_flight_distance"));

            return obj;
        }
        catch (SQLException e) {
            // TODO: logger.
            e.printStackTrace();

            return null;
        }
    }

}
