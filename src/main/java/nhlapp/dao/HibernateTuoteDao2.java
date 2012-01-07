
package nhlapp.dao;

import java.util.List;
import nhlapp.domain.Tuote;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HibernateTuoteDao2 extends HibernateDaoSupport implements TuoteDao {

    public HibernateTuoteDao2(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }
    
    @Override
    public Tuote findById(int id) {
        return (Tuote)getHibernateTemplate().get(Tuote.class, id);
    }

    @Override
    public List<Tuote> findAll() {
        return getHibernateTemplate().loadAll(Tuote.class);
    }

    @Override
    public void insert(Tuote tuote) {
        getHibernateTemplate().saveOrUpdate(tuote);
    }

    @Override
    public void update(Tuote tuote) {
        getHibernateTemplate().saveOrUpdate(tuote);        
    }
    
}
