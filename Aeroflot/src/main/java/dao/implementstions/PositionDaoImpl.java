package dao.implementstions;


import core.models.personrelated.Position;
import dao.context.PostgresDbContext;
import dao.interfaces.PositionDao;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class PositionDaoImpl implements PositionDao {

    // TODO: Bean here.
    private Connection mConnection;

    private List<Position> mCachedPositionList;
    private Position       mCachedPosition;



    {
        try {
            mConnection     = DriverManager.getConnection(PostgresDbContext.URL,
                                                          PostgresDbContext.USERNAME,
                                                          PostgresDbContext.PASSWORD);
            mCachedPosition = new Position();
        }
        catch (SQLException e) {
            // TODO: logger.
            e.printStackTrace();
        }
    }



    @Override
    @Nullable
    public Position get(int id) {

        assert mCachedPosition != null : "Cached Position object is null.";
        if (mCachedPosition.getId() == id) {
            return mCachedPosition;
        }

        try {
            PreparedStatement query = mConnection.prepareStatement(
                "SELECT * FROM position WHERE id=?;"
            );

            query.setLong(1, id);

            ResultSet result = query.executeQuery();
            if (result.next()) {
                mCachedPosition = parseObject(result);
                return mCachedPosition;
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
    public List<Position> getAll() {

        if (mCachedPositionList != null) {
            return mCachedPositionList;
        }

        try {
            PreparedStatement query = mConnection.prepareStatement(
                "SELECT * FROM position;"
            );

            ResultSet result = query.executeQuery();
            mCachedPositionList = new ArrayList<>();

            while (result.next()) {
                Position position = parseObject(result);
                if (position != null) {
                    mCachedPositionList.add(position);
                }
            }

            return mCachedPositionList;
        }
        catch (SQLException e) {
            // TODO: logger.
            e.printStackTrace();

            return new ArrayList<>(0);
        }
    }



    @Override
    public void create(Position obj) {

    }



    @Override
    public void update(Position obj) {

    }



    @Override
    public void delete(Position obj) {

    }



    @Nullable
    private Position parseObject(ResultSet result) {

        try {
            Position obj = new Position();

            obj.setId(result.getShort("id"));
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
