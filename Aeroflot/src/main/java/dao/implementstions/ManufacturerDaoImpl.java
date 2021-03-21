package dao.implementstions;


import core.models.transportrelated.Manufacturer;
import dao.context.PostgresDbContext;
import dao.interfaces.ManufacturerDao;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class ManufacturerDaoImpl implements ManufacturerDao {

    // TODO: Bean here.
    private Connection mConnection;

    private List<Manufacturer> mCachedManufacturerList;
    private Manufacturer       mCachedManufacturer;



    {
        try {
            mConnection         = DriverManager.getConnection(PostgresDbContext.URL,
                                                              PostgresDbContext.USERNAME,
                                                              PostgresDbContext.PASSWORD);
            mCachedManufacturer = new Manufacturer();
        }
        catch (SQLException e) {
            // TODO: logger.
            e.printStackTrace();
        }
    }



    @Override
    @Nullable
    public Manufacturer get(int id) {

        assert mCachedManufacturer != null : "Cached Manufacturer object is null.";
        if (mCachedManufacturer.getId() == id) {
            return mCachedManufacturer;
        }

        try {
            PreparedStatement query = mConnection.prepareStatement(
                "SELECT * FROM manufacturer WHERE id=?;"
            );

            query.setInt(1, id);

            ResultSet result = query.executeQuery();
            if (result.next()) {
                mCachedManufacturer = parseObject(result);
                return mCachedManufacturer;
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
    public List<Manufacturer> getAll() {

        if (mCachedManufacturerList != null) {
            return mCachedManufacturerList;
        }

        try {
            PreparedStatement query = mConnection.prepareStatement(
                "SELECT * FROM manufacturer;"
            );

            ResultSet result = query.executeQuery();
            mCachedManufacturerList = new ArrayList<>();

            while (result.next()) {
                Manufacturer manufacturer = parseObject(result);
                if (manufacturer != null) {
                    mCachedManufacturerList.add(manufacturer);
                }
            }

            return mCachedManufacturerList;
        }
        catch (SQLException e) {
            e.printStackTrace();

            return new ArrayList<>(0);
        }
    }



    @Override
    public void create(Manufacturer obj) {

    }



    @Override
    public void update(Manufacturer obj) {

    }



    @Override
    public void delete(Manufacturer obj) {

    }



    @Nullable
    private Manufacturer parseObject(ResultSet result) {

        try {
            Manufacturer obj = new Manufacturer();
            obj.setId(result.getInt("id"));
            obj.setTitle(result.getString("title"));

            return obj;
        }
        catch (SQLException e) {
            // TODO: logger.
            e.printStackTrace();

            return null;
        }
    }

}
