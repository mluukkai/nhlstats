package mvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import mvc.model.Spitter;
import mvc.model.Spittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class JdbcSpittleDao extends SimpleJdbcDaoSupport implements SpittleDao {

    private static int id = 0;
    
    @Autowired
    private SpitterDao spitterDao;

    public List<Spittle> getAll() {
        String SQL = "SELECT * FROM spitter.Spittle";

        return getJdbcTemplate().query(
                SQL,
                new ParameterizedRowMapperImpl() {

                    @Override
                    protected Spitter getSpitter(ResultSet rs) {
                        Spitter spitter = null;
                        try {
                            spitter = spitterDao.getSpitter(rs.getInt("spitter_id"));
                            if (spitter == null) {
                                spitter = Spitter.DEF;
                            }
                        } catch (SQLException ex) {
                        }
                        return spitter;
                    }
                });
    }

    @Override
    public List<Spittle> getSpittlesForSpitter(final Spitter spitter) {
        String SQL = "SELECT * FROM spitter.Spittle where spitter_id = ?";

        return getJdbcTemplate().query(
                SQL,
                new ParameterizedRowMapperImpl(spitter),
                spitter.getId());
    }

    public void save(Spittle spittle) {
        //String SQL = "INSERT INTO spitter.Spittle (spitter_id, content) VALUES (?, ?)";
        //getSimpleJdbcTemplate().update(SQL, spittle.getSpitter().getId(), spittle.getContent());
        
        // hack starts
        String SQL = "INSERT INTO spitter.Spittle (id, spitter_id, content) VALUES (?, ?, ?)";      
        spittle.setId( fetchId());
        getSimpleJdbcTemplate().update(SQL, spittle.getId(), spittle.getSpitter().getId(), spittle.getContent());
        // hack ends               
    }

    private long fetchId() {
        id++;
        long max = 0;
        
        for (Spittle s : getAll() ) {
            if ( s.getId()>max ) {
                max = s.getId();
            }
        }
        
        return max+1;
    }

    private static class ParameterizedRowMapperImpl implements ParameterizedRowMapper<Spittle> {

        private final Spitter spitter;

        public ParameterizedRowMapperImpl(Spitter spitter) {
            this.spitter = spitter;
        }

        public ParameterizedRowMapperImpl() {
            this.spitter = null;
        }

        @Override
        public Spittle mapRow(ResultSet rs, int i) throws SQLException {
            Spittle spittle = new Spittle();
            spittle.setId(rs.getInt("id"));
            spittle.setSpitter(getSpitter(rs));
            spittle.setContent(rs.getString("content"));

            return spittle;
        }

        protected Spitter getSpitter(ResultSet rs) {
            return spitter;
        }
    }
}
//        public List<Spittle> getAll() {
//        String SQL = "SELECT * FROM spitter.Spittle";
//
//        return getJdbcTemplate().query(SQL, new ParameterizedRowMapper<Spittle>() {
//
//            @Override
//            public Spittle mapRow(ResultSet rs, int i) throws SQLException {
//                Spittle spittle = new Spittle();
//                spittle.setId(rs.getInt("id"));
//
//                Spitter spitter = spitterDao.getSpitter(rs.getInt("spitter_id"));
//                if (spitter == null) {
//                    spitter = Spitter.DEF;
//                }
//
//                spittle.setSpitter(spitter);
//                spittle.setContent(rs.getString("content"));
//
//                return spittle;
//            }
//        });
//    }
//      INSERT INTO spitter.Spittle (id, spitter, content) VALUES (2, 'Niina', 'what is up with Spring')
//      INSERT INTO spitter.Spitter (name) VALUES ('Matti')
//      UPDATE spitter.Spittle SET spitter_id = 1 where id = 1
