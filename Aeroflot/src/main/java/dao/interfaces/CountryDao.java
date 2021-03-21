package dao.interfaces;


import core.models.countries.Country;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;



public interface CountryDao {

    @Nullable
    Country get(long id);

    @NotNull
    List<Country> getAll();

    void create(Country obj);

    void update(Country obj);

    void delete(Country obj);

}
