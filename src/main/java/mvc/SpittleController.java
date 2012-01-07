package mvc;

import java.io.*;
import javax.servlet.http.HttpSession;
import mvc.model.*;
import mvc.service.SpitterService;
import mvc.util.S3Util;
import org.apache.commons.io.FileUtils;
import org.jets3t.service.*;
import org.jets3t.service.acl.AccessControlList;
import org.jets3t.service.acl.GroupGrantee;
import org.jets3t.service.acl.Permission;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;
import org.jets3t.service.security.AWSCredentials;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/spittles")
public class SpittleController {

    private @Value("${resources.path}") String resourcePath;
   
    private SpitterService spitterService;        
    
    private S3Util s3Util;
    
    @Autowired
    public SpittleController(SpitterService spitterService) {
        this.spitterService = spitterService;      
    }

    @Autowired
    public void setS3Util(S3Util s3Util) {
        this.s3Util = s3Util;
    }
        
    @RequestMapping(method = RequestMethod.POST)
    public String addSpitterFromForm(Spittle spittle, BindingResult bindingResult, HttpSession session) {
        spittle.setSpitter((Spitter) session.getAttribute("spitter"));

        spitterService.saveSpittle(spittle);

        return "redirect:/spitters/" + spittle.getSpitter().getName();
    }

    @RequestMapping(value = "/pic", method = RequestMethod.GET)
    public String picForm(Model model) {
        return "spittles/pic";
    }

    @RequestMapping(value = "/pic", method = RequestMethod.POST)
    public String savePic(Spittle spittle, BindingResult bindingResult,
                          @RequestParam(value = "image", required = false) MultipartFile image, 
                          HttpSession session) {
        //requestParam(value="txt") String txt) {

        System.out.println( resourcePath );
        
        Spitter spitter = (Spitter) session.getAttribute("spitter");
       
        if (!s3Util.saveImage(image, spitter.getId())) {          
            bindingResult.addError( new FieldError("spitter", "name", "only jpgs please"));
             
            return "spittles/pic";
        }

        return "redirect:/spitters/"+spitter.getName();
    }

}
