package dao.interfaces;


import core.models.personrelated.Person;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;



public interface PersonDao {

    @Nullable
    Person get(long id);

    @NotNull
    List<Person> getAll();

    void create(Person obj);

    void update(Person obj);

    void delete(Person obj);

}
