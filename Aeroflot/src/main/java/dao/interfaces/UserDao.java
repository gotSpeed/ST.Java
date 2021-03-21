package dao.interfaces;


import core.models.personrelated.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;



public interface UserDao {

    @Nullable
    User get(long id);

    @NotNull
    List<User> getAll();

    void create(User obj);

    void update(User obj);

    void delete(User obj);

}
