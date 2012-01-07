package mvc.service;

import java.util.ArrayList;
import java.util.List;
import mvc.dao.SpitterDao;
import mvc.dao.SpittleDao;
import mvc.model.Spitter;
import mvc.model.Spittle;

import org.springframework.beans.factory.annotation.Autowired;

public class SpitterServiceImpl implements SpitterService {

    @Autowired
    private SpittleDao spittleDao;
    
    @Autowired
    private SpitterDao spitterDao;    
    
    @Override
    public List<Spittle> getRecentSpittles(int count) {        
        return spittleDao.getAll();
    }

    @Override
    public void saveSpittle(Spittle spittle) {
        spittleDao.save(spittle);
    }

    @Override
    public void saveSpitter(Spitter spitter) {
        spitterDao.saveSpitter(spitter);
    }

    @Override
    public Spitter getSpitter(long id) {
        return spitterDao.getSpitter(id);
    }

    @Override
    public Spitter getSpitter(String name) {
        return spitterDao.getSpitter(name);
    }    
    
    @Override
    public void startFollowing(Spitter follower, Spitter followee) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Spittle> getSpittlesForSpitter(Spitter spitter) {
        List<Spittle> spittles = spittleDao.getSpittlesForSpitter(spitter);
        
        if ( spittles==null ) spittles = new ArrayList<Spittle>(); 
        
        return spittles;
    }

    @Override
    public List<Spittle> getSpittlesForSpitter(String name) {
        return getSpittlesForSpitter( spitterDao.getSpitter(name) );
    }



    @Override
    public Spittle getSpittleById(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteSpittle(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Spitter> getAllSpitters() {
        return spitterDao.getAllSpitters();
    }
}
