package dao.interfaces;


import core.personrelated.User;

import java.util.List;



public interface UserDao {

    User get(long id);

    List<User> get();

    void create(User obj);

    void update(User obj);

    void delete(User obj);

}
