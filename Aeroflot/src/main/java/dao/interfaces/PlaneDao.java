package dao.interfaces;


import core.transportrelated.Plane;

import java.util.List;



public interface PlaneDao {

    Plane get(long id);

    List<Plane> get();

    void create(Plane obj);

    void update(Plane obj);

    void delete(Plane obj);

}
