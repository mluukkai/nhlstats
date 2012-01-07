
package nhlapp.dao;

import java.util.List;
import nhlapp.domain.Tuote;

public interface TuoteDao {
    Tuote findById(int id);
    List<Tuote> findAll();
    void insert(Tuote tuote);
    void update(Tuote tuote);
}
