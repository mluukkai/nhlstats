package mvc;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpSession;
import mvc.service.SpitterService;
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
      
    
    @RequestMapping({"/home"}) 
    public String showHomePage(Map<String, Object> model, HttpSession session) {
        model.put("spittles", spitterService.getRecentSpittles(DEFAULT_SPITTLES_PER_PAGE));
        model.put("session", session.getAttribute("spitter")!=null);
        
        return "home";
    }     
    
}
