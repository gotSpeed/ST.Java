package dao.interfaces;


import core.models.flightrelated.FlightCrew;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;



public interface FlightCrewDao {

    @Nullable
    FlightCrew get(long id);

    @NotNull
    List<FlightCrew> getAll();

    void create(FlightCrew obj);

    void update(FlightCrew obj);

    void delete(FlightCrew obj);

}
