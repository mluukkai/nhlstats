package nhlapp;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import nhlapp.util.S3Util;
import nhlapp.dao.PlayerDao;
import nhlapp.domain.Player;
import nhlapp.downloader.PlayerDownloader;
import nhlapp.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NhlController {
    private static String updated = "";
        
    @Autowired
    PlayerService playerService;    
    
    @RequestMapping({"/","players.html"}) 
    public String showFrontPage(Map<String, Object> model, HttpSession session) {
        model.put("players", playerService.findAll());
        model.put("updated", updated);
        
        return "nhl";
    }    
    
    @RequestMapping({"/players.txt"}) 
    public String showTxt(Map<String, Object> model, HttpSession session) {
        model.put("players", playerService.findAll());
        
        return "txt";
    } 
    
    @RequestMapping(value="/add", method= RequestMethod.GET) 
    public String addPlayerForm(Model model){
        model.addAttribute(new Player());                
        
        return "player/form";
    }   
    
    @RequestMapping(value="/add", method= RequestMethod.POST) 
    public String addPlayer(Player player, BindingResult bindingResult) {
        Player p = playerService.findByName(player.getName());
        
        if ( p!=null ) {
            FieldError fe = new FieldError("player", "name", "player exists already");
            bindingResult.addError( fe ); 
            return "player/form";
        }
        
        playerService.save(player);        
        return "redirect:/players.html";
    }     
    
    @RequestMapping({"/download"}) 
    public String download(Map<String, Object> model, HttpSession session) {
        playerService.download();                    
        updated = new Date().toString();
        
        return "redirect:/players.html";
    } 
    
    @RequestMapping({"/raw"}) 
    public String rawMode(Map<String, Object> model, HttpSession session) {
        boolean status = playerService.savePageSource(1, "page1.txt");

        model.put("text", status);
       
        return "raw";
    }       
    
    @RequestMapping({"admin"})
    public String showAdminPage(Map<String, Object> model, HttpSession session) {
        return "admin/admin";
    } 
}
