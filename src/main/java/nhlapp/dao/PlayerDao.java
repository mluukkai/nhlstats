
package nhlapp.dao;

import nhlapp.domain.Player;
import java.util.*;

public interface PlayerDao {
    List<Player> findAll();
    Player findByName(String name);
    void save(Player player);
}
