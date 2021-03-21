package dao.implementstions;


import core.models.countries.Country;
import dao.context.PostgresDbContext;
import dao.interfaces.CountryDao;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class CountryDaoImpl implements CountryDao {

    // TODO: Bean here.
    private Connection mConnection;

    private List<Country> mCachedCountryList;
    private Country       mCachedCountry;



    {
        try {
            mConnection    = DriverManager.getConnection(PostgresDbContext.URL,
                                                         PostgresDbContext.USERNAME,
                                                         PostgresDbContext.PASSWORD);
            mCachedCountry = new Country();
        }
        catch (SQLException e) {
            // TODO: logger.
            e.printStackTrace();
        }
    }



    @Override
    @Nullable
    public Country get(long id) {

        assert mCachedCountry != null : "Cached Country object is null.";
        if (mCachedCountry.getId() == id) {
            return mCachedCountry;
        }

        try {
            PreparedStatement query = mConnection.prepareStatement(
                "SELECT * FROM country WHERE id=?;"
            );

            query.setLong(1, id);

            ResultSet result = query.executeQuery();
            if (result.next()) {
                mCachedCountry = parseObject(result);
                return mCachedCountry;
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
    public List<Country> getAll() {

        if (mCachedCountryList != null) {
            return mCachedCountryList;
        }

        try {
            PreparedStatement query = mConnection.prepareStatement(
                "SELECT * FROM country;"
            );

            ResultSet result = query.executeQuery();
            mCachedCountryList = new ArrayList<>();

            while (result.next()) {
                Country country = parseObject(result);
                if (country != null) {
                    mCachedCountryList.add(country);
                }
            }

            return mCachedCountryList;
        }
        catch (SQLException e) {
            // TODO: logger.
            e.printStackTrace();

            return new ArrayList<>(0);
        }
    }



    @Override
    public void create(Country obj) {

    }



    @Override
    public void update(Country obj) {

    }



    @Override
    public void delete(Country obj) {

    }



    @Nullable
    private Country parseObject(ResultSet result) {

        try {
            Country obj = new Country();

            obj.setId(result.getShort("id"));
            obj.setTitle(result.getString("title"));
            obj.setCountryCode(result.getString("country_code"));

            return obj;
        }
        catch (SQLException e) {
            // TODO: logger.
            e.printStackTrace();

            return null;
        }
    }

}
