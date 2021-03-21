package dao.interfaces;


import core.models.transportrelated.Plane;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;



public interface PlaneDao {

    @Nullable
    Plane get(long id);

    @NotNull
    List<Plane> getAll();

    void create(Plane obj);

    void update(Plane obj);

    void delete(Plane obj);

}
