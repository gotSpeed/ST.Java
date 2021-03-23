package dao.interfaces;


import core.models.personrelated.Crew;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;



public interface CrewDao {

    @Nullable
    Crew get(long id);

    @NotNull
    List<Crew> getAll();

    void create(Crew obj);

    void update(Crew obj);

    void delete(Crew obj);

}
