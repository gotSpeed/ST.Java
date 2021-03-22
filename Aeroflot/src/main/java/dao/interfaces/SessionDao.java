package dao.interfaces;


import core.models.personrelated.Session;
import org.jetbrains.annotations.Nullable;



public interface SessionDao {

    @Nullable
    Session get(String id);

    void create(Session session);

    void delete(Session session);

}
