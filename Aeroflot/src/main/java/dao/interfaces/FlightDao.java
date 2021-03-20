package dao.interfaces;


import core.flightrelated.Flight;
import org.jetbrains.annotations.NotNull;

import java.util.List;



public interface FlightDao {

    Flight get(long id);

    @NotNull
    List<Flight> getAll();

    void create(Flight obj);

    void update(Flight obj);

    void delete(Flight obj);

}
