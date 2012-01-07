package nhlapp;


import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpSession;
import nhlapp.dao.PlayerDao;
import nhlapp.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NhlController {
    @Autowired
    PlayerDao playerDao;
            
    @RequestMapping({"/","nhl"}) 
    public String showFrontPage(Map<String, Object> model, HttpSession session) {
        model.put("players", playerDao.findAll());
        
        return "nhl";
    }    
    
    @RequestMapping({"/add"}) 
    public String addPlayer(Map<String, Object> model, HttpSession session) {
        playerDao.save(new Player("Jagr", "PHI", 68, 22, 43, 24));
        return "redirect:/";
    }   
}
