package dao.interfaces;


import core.models.flightrelated.Flight;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;



public interface FlightDao {

    @Nullable
    Flight get(long id);

    @NotNull
    List<Flight> getAll();

    void create(Flight obj);

    void update(Flight obj);

    void delete(Flight obj);

}
