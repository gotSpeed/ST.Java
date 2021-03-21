package dao.interfaces;


import core.models.personrelated.Position;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;



public interface PositionDao {

    @Nullable
    Position get(int id);

    @NotNull
    List<Position> getAll();

    void create(Position obj);

    void update(Position obj);

    void delete(Position obj);

}
