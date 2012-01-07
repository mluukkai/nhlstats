package nhlapp;


import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpSession;
import nhlapp.dao.PlayerDao;
import nhlapp.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NhlController {
    @Autowired
    PlayerDao playerDao;
            
    @RequestMapping({"/","nhl"}) 
    public String showFrontPage(Map<String, Object> model, HttpSession session) {
        model.put("players", playerDao.findAll());
        
        return "nhl";
    }    
    
    @RequestMapping(value="/add", method= RequestMethod.GET) 
    public String addPlayerForm(Model model){
        model.addAttribute(new Player());                
        
        return "player/form";
    }   
    
    @RequestMapping(value="/add", method= RequestMethod.POST) 
    public String addPlayer(Player player, BindingResult bindingResult) {
        Player p = playerDao.findByName(player.getName());
        
        if ( p!=null ) {
            FieldError fe = new FieldError("player", "name", "player exists already");
            bindingResult.addError( fe ); 
            return "player/form";
        }
        
        playerDao.save(player);        
        return "redirect:/";
    }     
}
