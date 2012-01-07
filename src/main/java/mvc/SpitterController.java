
package mvc;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import mvc.model.Spitter;
import mvc.model.Spittle;
import mvc.service.SpitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/spitters")
public class SpitterController {
    private SpitterService spitterService;

    @Autowired
    public SpitterController(SpitterService spitterService) {
        this.spitterService = spitterService;
    }
    
    @RequestMapping(method= RequestMethod.GET) 
    public String showSpitters( Model model) {
 
        model.addAttribute( spitterService.getAllSpitters() );    
        
        return "spitters/list";
    }    
    
    @RequestMapping(value="/spittles") 
    public String showHomePage( @RequestParam("spitter") String name, Model model) {

        Spitter spitter = spitterService.getSpitter(name);   
        if ( spitter==null ) {
            spitter = Spitter.DEF;
        } 
        
        model.addAttribute( spitter );   
        model.addAttribute( spitterService.getSpittlesForSpitter(spitter) );    
        
        return "spittles/list";
    }
    
    @RequestMapping(method= RequestMethod.GET, params="new") 
    public String createSpitterProfile(Model model){
        model.addAttribute(new Spitter());
        return "spitters/edit";
    }
        
    @RequestMapping(method= RequestMethod.GET, params="my") 
    public String goToOwnPageIfLogged(HttpSession session){
        Spitter spitter = (Spitter)session.getAttribute("spitter");
        
        if ( spitter==null ) {
            return "redirect:/";
        }
        
        return "redirect:/spitters/"+spitter.getName();
    }                
    
    @RequestMapping(method= RequestMethod.GET, params="login") 
    public String askNameToLogin(Model model){
        model.addAttribute(new Spitter());
        return "spitters/login";
    }  
    
    @RequestMapping(method= RequestMethod.GET, params="logout") 
    public String logOut(HttpSession session){
        session.setAttribute("spitter", null);
        return "redirect:/";
    }      
    
    @RequestMapping(value="/login", method= RequestMethod.POST) 
    public String logUserIn(Spitter spitter, BindingResult bindingResult, HttpSession session){
        spitter = spitterService.getSpitter(spitter.getName());
        
        if ( spitter==null ) {
            FieldError fe = new FieldError("spitter", "name", "name does not exist");
            bindingResult.addError( fe );  
            
            return "spitters/login";
        }
        
        session.setAttribute("spitter", spitter);
        
        return "redirect:/spitters/"+spitter.getName();
    }    
    
    @RequestMapping(method= RequestMethod.POST)
    public String addSpitterFromForm(@Valid Spitter spitter, BindingResult bindingResult, HttpSession session){
        if ( bindingResult.hasErrors() ){            
            
            return "spitters/edit";
        }
        
        if ( spitterService.getSpitter(spitter.getName())!=null ) {    
            FieldError fe = new FieldError("spitter", "name", "name exists already");
            bindingResult.addError( fe );          
            
            return "spitters/edit";
        }
        
        session.setAttribute("spitter", spitter);
        
        spitterService.saveSpitter(spitter);
        return "redirect:/spitters/"+spitter.getName();
    }
    
    @RequestMapping(value="{name}", method= RequestMethod.GET)
    public String showSpitterProfile(@PathVariable String name, Model model, HttpSession session){
        Spitter spitter = spitterService.getSpitter(name);
        model.addAttribute(spitter);
        model.addAttribute(spitterService.getSpittlesForSpitter(spitter));
        
        Spitter logged =(Spitter)session.getAttribute("spitter");        
        if ( logged!=null && name.equals(logged.getName()) ) {
            model.addAttribute("me", true);
        } else {
            model.addAttribute("me", false);
        }

        Spittle spittle = new Spittle();
        spittle.setSpitter(spitter);
        
        model.addAttribute( spittle );
        
        return "spitters/view";
    }
}
