package live.livelab.nginx.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zhouxiang on 3/14/2017.
 */
@Controller
@RequestMapping("api-doc")
public class ApiDocController extends BaseController{

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String view(){
        return "redirect:/swagger-ui.html";
    }
}
