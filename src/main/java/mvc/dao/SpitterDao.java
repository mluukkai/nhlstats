package mvc.dao;

import java.util.*;
import mvc.model.Spitter;

public interface SpitterDao {
    void saveSpitter(Spitter spitter);
    Spitter getSpitter(long id);
    Spitter getSpitter(String name);
    List<Spitter> getAllSpitters();
}
