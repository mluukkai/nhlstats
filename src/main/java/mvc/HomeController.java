package mvc;

import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpSession;
import mvc.service.SpitterService;
import mvc.temp.HibernateTuoteDao2;
import mvc.temp.Tuote;
import mvc.temp.TuoteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    public static final int DEFAULT_SPITTLES_PER_PAGE = 25;
    
    private SpitterService spitterService;         
    
    @Autowired
    public HomeController(SpitterService spitterService) {
        this.spitterService = spitterService;
    }
            
    @RequestMapping({"/","/home"}) //(value="/test")
    public String showHomePage(Map<String, Object> model, HttpSession session) {
        model.put("spittles", spitterService.getRecentSpittles(DEFAULT_SPITTLES_PER_PAGE));
        model.put("session", session.getAttribute("spitter")!=null);
        
        return "home";
    }
    
    @RequestMapping({"admin"})
    public String showAdminPage(Map<String, Object> model, HttpSession session) {
        return "admin/admin";
    }    

    @RequestMapping({"reset"})
    public String reset(Map<String, Object> model, HttpSession session) {
        try{
            mvc.util.DBReset.createTable();
            System.out.println("you");
        } catch(Exception e){
            System.out.println("paska");
        }
        
        return "home";
    }    
    
    @Autowired
    private HibernateTuoteDao2 tuoteDao;
    
    @RequestMapping({"tuote"})
    public String tuote(Map<String, Object> model, HttpSession session) {
        String[] nimet = {"karhu", "koff", "lapin kusi", "fink", "bisse", "mikkeller", "stone", 
                          "ipa", "weihenstephan"};
        
        Random arpa = new Random();
        
        Tuote t = new Tuote();
        t.setNimi( nimet[arpa.nextInt(nimet.length)] );
        t.setHinta( 1 + arpa.nextInt(7));
        t.setSaldo(10*arpa.nextInt(20));
                        
        tuoteDao.insert(t);
        
        model.put("oluet", tuoteDao.findAll());
        model.put("luku", tuoteDao.findAll().size() );
        
        return "tuote";
    }       
}
