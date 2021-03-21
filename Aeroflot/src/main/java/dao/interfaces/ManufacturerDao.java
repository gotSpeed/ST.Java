package dao.interfaces;


import core.models.transportrelated.Manufacturer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;



public interface ManufacturerDao {

    @Nullable
    Manufacturer get(int id);

    @NotNull
    List<Manufacturer> getAll();

    void create(Manufacturer obj);

    void update(Manufacturer obj);

    void delete(Manufacturer obj);

}
