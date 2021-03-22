package dao.implementstions;


import core.models.personrelated.Session;
import dao.context.PostgresDbContext;
import dao.interfaces.SessionDao;
import dao.interfaces.UserDao;
import org.jetbrains.annotations.Nullable;

import java.sql.*;



public class SessionDaoImpl implements SessionDao {

    // TODO: Bean here.
    private Connection mConnection;

    // TODO: Bean here.
    private UserDao mUserDao = new UserDaoImpl();



    {
        try {
            mConnection = DriverManager.getConnection(PostgresDbContext.URL,
                                                      PostgresDbContext.USERNAME,
                                                      PostgresDbContext.PASSWORD);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public @Nullable Session get(String id) {

        try {
            PreparedStatement query = mConnection.prepareStatement(
                "SELECT * FROM session WHERE id=?;"
            );

            query.setString(1, id);

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
    public void create(Session session) {

        try {
            PreparedStatement query = mConnection.prepareStatement(
                "INSERT INTO session (id, user_id, expires_at) VALUES (?, ?, ?);"
            );

            query.setString(1, session.getId());
            query.setLong(2, session.getUser().getId());
            query.setLong(3, session.getExpiresAt());

            query.executeUpdate();
        }
        catch (SQLException e) {
            // TODO: logger.
            e.printStackTrace();
        }
    }



    @Override
    public void delete(Session session) {

        try {
            PreparedStatement query = mConnection.prepareStatement(
                "DELETE FROM session WHERE id=?;"
            );

            query.setString(1, session.getId());

            query.executeUpdate();
        }
        catch (SQLException e) {
            // TODO: logger.
            e.printStackTrace();
        }
    }



    @Nullable
    private Session parseObject(ResultSet result) {

        try {
            Session session = new Session();

            session.setId(result.getString("id"));
            session.setUser(
                mUserDao.get(result.getLong("user_id"))
            );
            session.setExpiresAt(result.getLong("expires_at"));

            return session;
        }
        catch (SQLException e) {
            e.printStackTrace();

            return null;
        }
    }

}
