package dao.implementstions;


import core.models.personrelated.Person;
import dao.context.PostgresDbContext;
import dao.interfaces.PersonDao;
import dao.interfaces.PositionDao;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class PersonDaoImpl implements PersonDao {

    // TODO: Bean here.
    private Connection mConnection;

    // TODO: Bean here.
    private PositionDao mPositionDao;

    private List<Person> mCachedPersonList;
    private Person       mCachedPerson;



    {
        try {
            mConnection   = DriverManager.getConnection(PostgresDbContext.URL,
                                                        PostgresDbContext.USERNAME,
                                                        PostgresDbContext.PASSWORD);
            mPositionDao  = new PositionDaoImpl();
            mCachedPerson = new Person();
        }
        catch (SQLException e) {
            // TODO: logger.
            e.printStackTrace();
        }
    }



    @Override
    @Nullable
    public Person get(long id) {

        assert mCachedPerson != null : "Cached Person object is null.";
        if (mCachedPerson.getId() == id) {
            return mCachedPerson;
        }

        try {
            PreparedStatement query = mConnection.prepareStatement(
                "SELECT * FROM person WHERE id=?;"
            );

            query.setLong(1, id);

            ResultSet result = query.executeQuery();
            if (result.next()) {
                mCachedPerson = parseObject(result);
                return mCachedPerson;
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
    public List<Person> getAll() {

        if (mCachedPersonList != null) {
            return mCachedPersonList;
        }

        try {
            PreparedStatement query = mConnection.prepareStatement(
                "SELECT * FROM person;"
            );

            ResultSet result = query.executeQuery();
            mCachedPersonList = new ArrayList<>();

            while (result.next()) {
                Person person = parseObject(result);
                if (person != null) {
                    mCachedPersonList.add(person);
                }
            }

            return mCachedPersonList;
        }
        catch (SQLException e) {
            // TODO: logger.
            e.printStackTrace();

            return new ArrayList<>(0);
        }
    }



    @Override
    public void create(Person obj) {

    }



    @Override
    public void update(Person obj) {

    }



    @Override
    public void delete(Person obj) {

    }



    @Nullable
    private Person parseObject(ResultSet result) {

        try {
            Person obj = new Person();

            obj.setId(result.getLong("id"));
            obj.setName(result.getString("name"));
            obj.setSurname(result.getString("surname"));
            obj.setAge(result.getShort("age"));
            obj.setBirthDate(result.getString("birth_date"));
            obj.setSex(result.getString("sex"));
            obj.setPassportIdentifier(result.getString("passport_identifier"));
            obj.setIssueDate(result.getString("issue_date"));
            obj.setPosition(
                mPositionDao.get(result.getShort("position"))
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
