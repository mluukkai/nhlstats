package nhlapp.dao;

import java.util.ArrayList;
import java.util.List;
import nhlapp.domain.Player;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HibernatePlayerDao extends HibernateDaoSupport implements PlayerDao {
 
    ArrayList<Player> players = new ArrayList<Player>();

    public HibernatePlayerDao(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
        
        players.add(new Player("Gretzky", "EDM", 80, 47, 102, 18));
        players.add(new Player("Lemieux", "PIT", 67, 51, 78, 104));
    }

    @Override
    public List<Player> findAll() {
        return getHibernateTemplate().loadAll(Player.class);
        //return players;
    }

    @Override
    public Player findByName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void save(Player player) {
        getHibernateTemplate().saveOrUpdate(player);
        //players.add(player);
    }
}
