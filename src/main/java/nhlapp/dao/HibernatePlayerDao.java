package nhlapp.dao;

import java.util.ArrayList;
import java.util.List;
import nhlapp.domain.Player;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HibernatePlayerDao extends HibernateDaoSupport implements PlayerDao {
    public HibernatePlayerDao(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public List<Player> findAll() {
        return getHibernateTemplate().loadAll(Player.class);
    }

    @Override
    public Player findByName(String name) {
        for (Player p : findAll()) {
            if ( p.getName().equalsIgnoreCase(name))
                return p;
        }
        
        return null;
    }

    @Override
    public void save(Player player) {
        getHibernateTemplate().saveOrUpdate(player);
    }
}
