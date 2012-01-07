
package mvc.temp;

import java.util.List;

public interface TuoteDao {
    Tuote findById(int id);
    List<Tuote> findAll();
    void insert(Tuote tuote);
    void update(Tuote tuote);
}
