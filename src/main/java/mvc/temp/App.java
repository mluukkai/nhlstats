package mvc.temp;

import nhlapp.dao.TuoteDao;
import nhlapp.domain.Tuote;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App {

    public static void main(String[] args) {
        ApplicationContext appContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        TuoteDao tuoteDao = (TuoteDao) appContext.getBean("tuoteDao");

        Tuote t = tuoteDao.findById(11);
        System.out.println(t);

        //t.setNimi("Rauchweisse");
        //t.setHinta(4);
        
        //tuoteDao.update(t);       
//        System.out.println( t );
//
        Tuote t2 = new Tuote("Urbock", 5, 35);
        
        tuoteDao.insert(t2);
        
        System.out.println("tuotteet:");
        for (Tuote tuote : tuoteDao.findAll()) {
            System.out.println( tuote );
        }        
        
        //StockBo stockBo = (StockBo)appContext.getBean("stockBo");

        /** insert **/
        //Stock stock = new Stock();
        //stock.setStockCode("766812");
        //stock.setStockName("FYIq");
        //stockBo.save(stock);
        /** select **/
        //Stock stock2 = stockBo.findByStockCode("7668");
        //System.out.println(stock2);
        /** update **/
        //stock2.setStockName("HAIO-1");
        //stockBo.update(stock2);
        /** delete **/
        //stockBo.delete(stock2);
        System.out.println("Done");
    }
}
