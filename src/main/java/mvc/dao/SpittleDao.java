
package mvc.dao;

import java.util.List;
import mvc.model.Spitter;
import mvc.model.Spittle;

public interface SpittleDao {
    List<Spittle> getAll();
    void save(Spittle spittel);    
    List<Spittle> getSpittlesForSpitter(Spitter spitter);
}
