package mvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import mvc.model.Spitter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class JdbcSpitterDao extends SimpleJdbcDaoSupport implements SpitterDao {

    private static int id = 0;
    
    public void saveSpitter(Spitter spitter) {
        //String SQL = "INSERT INTO spitter.Spitter (name) VALUES (?)";
        //getSimpleJdbcTemplate().update(SQL, spitter.getName());
        
        // hack starts
        String SQL = "INSERT INTO spitter.Spitter (id, name) VALUES (?, ?)";     
        spitter.setId( fetchId());
        getSimpleJdbcTemplate().update(SQL, spitter.getId(), spitter.getName());
        
        // hack ends                
    }

    public Spitter getSpitter(long id) {
        String SQL = "SELECT * FROM spitter.Spitter where id = ?";

        try {
            return getSimpleJdbcTemplate().
                    queryForObject(SQL, new ParameterizedRowMapperImpl(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Spitter getSpitter(String name) {
        String SQL = "SELECT * FROM spitter.Spitter where name = ?";

        try {
            return getSimpleJdbcTemplate().
                    queryForObject(SQL, new ParameterizedRowMapperImpl(), name);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Spitter> getAllSpitters() {
        String SQL = "SELECT * FROM spitter.Spitter";

        return getJdbcTemplate().query(SQL, new ParameterizedRowMapper<Spitter>() {

            @Override
            public Spitter mapRow(ResultSet rs, int i) throws SQLException {
                Spitter spitter = new Spitter();
                spitter.setId(rs.getInt("id"));
                spitter.setName(rs.getString("name"));

                return spitter;
            }
        });
    }

    private long fetchId() {
        //id++;
               
        long max = 0;
        
        for (Spitter s : getAllSpitters()) {
            if ( s.getId()>max ) {
                max = s.getId();
            }
        }
        
        return max+1;
    }

    private static class ParameterizedRowMapperImpl implements ParameterizedRowMapper<Spitter> {

        @Override
        public Spitter mapRow(ResultSet rs, int i) throws SQLException {
            Spitter spitter = new Spitter();

            spitter.setId(rs.getLong("id"));
            spitter.setName(rs.getString("name"));

            return spitter;
        }
    }
}
