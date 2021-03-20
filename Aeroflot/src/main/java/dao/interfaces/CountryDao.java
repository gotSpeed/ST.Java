package dao.interfaces;


import core.countries.Country;

import java.util.List;



public interface CountryDao {

    Country get(long id);

    List<Country> get();

    void create(Country obj);

    void update(Country obj);

    void delete(Country obj);

}
