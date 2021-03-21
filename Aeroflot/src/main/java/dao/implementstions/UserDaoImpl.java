package dao.implementstions;


import core.models.personrelated.User;
import dao.context.PostgresDbContext;
import dao.interfaces.PersonDao;
import dao.interfaces.UserDao;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class UserDaoImpl implements UserDao {

    // TODO: Bean here.
    private Connection mConnection;

    // TODO: Bean here.
    private PersonDao mPersonDao;

    private List<User> mCachedUserList;
    private User       mCachedUser;



    {
        try {
            mConnection = DriverManager.getConnection(PostgresDbContext.URL,
                                                      PostgresDbContext.USERNAME,
                                                      PostgresDbContext.PASSWORD);
            mPersonDao  = new PersonDaoImpl();
            mCachedUser = new User();
        }
        catch (SQLException e) {
            // TODO: logger.
            e.printStackTrace();
        }
    }



    @Override
    @Nullable
    public User get(long id) {

        assert mCachedUser != null : "Cached User object is null.";
        if (mCachedUser.getId() == id) {
            return mCachedUser;
        }

        try {
            PreparedStatement query = mConnection.prepareStatement(
                "SELECT * FROM \"user\" WHERE id=?;"
            );

            query.setLong(1, id);

            ResultSet result = query.executeQuery();
            if (result.next()) {
                mCachedUser = parseObject(result);
                return mCachedUser;
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
    public List<User> getAll() {

        if (mCachedUserList != null) {
            return mCachedUserList;
        }

        try {
            PreparedStatement query = mConnection.prepareStatement(
                "SELECT * FROM \"user\";"
            );

            ResultSet result = query.executeQuery();
            mCachedUserList = new ArrayList<>();

            while (result.next()) {
                User user = parseObject(result);
                if (user != null) {
                    mCachedUserList.add(user);
                }
            }

            return mCachedUserList;
        }
        catch (SQLException e) {
            // TODO: logger.
            e.printStackTrace();

            return new ArrayList<>(0);
        }
    }



    @Override
    public void create(User obj) {

    }



    @Override
    public void update(User obj) {

    }



    @Override
    public void delete(User obj) {

    }



    private User parseObject(ResultSet result) {

        try {
            User obj = new User();

            obj.setId(result.getLong("id"));
            obj.setUsername(result.getString("username"));
            obj.setPassword(result.getString("password"));
            obj.setPerson(
                mPersonDao.get(result.getLong("person"))
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
