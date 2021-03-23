package dao.implementstions;


import core.models.personrelated.Crew;
import dao.context.PostgresDbContext;
import dao.interfaces.CrewDao;
import dao.interfaces.PersonDao;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class CrewDaoImpl implements CrewDao {

    // TODO: Bean here.
    private Connection mConnection;

    // TODO: Bean here.
    private PersonDao mPersonDao;

    private List<Crew> mCachedCrewList;
    private Crew       mCachedCrew;



    {
        try {
            mConnection = DriverManager.getConnection(PostgresDbContext.URL,
                                                      PostgresDbContext.USERNAME,
                                                      PostgresDbContext.PASSWORD);

            mPersonDao = new PersonDaoImpl();
            //            mCachedCrew = new Crew();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }



    @Override
    public @Nullable Crew get(long id) {

        //        assert mCachedCrew != null : "Cached Crew object is null.";
        //        if (mCachedCrew.getId() == id) {
        //            return mCachedCrew;
        //        }

        try {
            PreparedStatement query = mConnection.prepareStatement(
                "SELECT * FROM crew WHERE id=?;"
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
    public @NotNull List<Crew> getAll() {

        //        if (mCachedFlightList != null) {
        //            return mCachedFlightList;
        //        }

        try {
            PreparedStatement query = mConnection.prepareStatement(
                "SELECT * FROM crew;"
            );

            ResultSet result = query.executeQuery();
            mCachedCrewList = new ArrayList<>();

            while (result.next()) {
                Crew crew = parseObject(result);
                if (crew != null) {
                    mCachedCrewList.add(crew);
                }
            }

            return mCachedCrewList;
        }
        catch (SQLException e) {
            // TODO: logger.
            e.printStackTrace();

            return new ArrayList<>(0);
        }
    }



    @Override
    public void create(Crew obj) {

    }



    @Override
    public void update(Crew obj) {

    }



    @Override
    public void delete(Crew obj) {

    }



    @Nullable
    private Crew parseObject(ResultSet result) {

        try {
            Crew obj = new Crew();

            obj.setId(result.getLong("id"));
            obj.setPerson(
                mPersonDao.get(result.getLong("person"))
            );
            obj.setRank(result.getString("rank"));
            obj.setExperienceYears(result.getShort("experience_years"));

            return obj;
        }
        catch (SQLException e) {
            // TODO: logger.
            e.printStackTrace();

            return null;
        }
    }

}
