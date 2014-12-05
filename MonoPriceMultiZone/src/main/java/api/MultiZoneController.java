package api;

import jssc.SerialPortList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import config.Configuration;

@Controller
public class MultiZoneController {
	@Autowired Configuration configuration;
	@RequestMapping("/admin")
    public String admin(Model model) {
		String[] portNames = SerialPortList.getPortNames();
        for(int i = 0; i < portNames.length; i++){
            System.out.println(portNames[i]);
        }
        model.addAttribute("portNames", portNames);
        return "admin";
    }
	
	@RequestMapping("/save")
    public String save(Model model) {
		
        return "/";
    }
	
    @RequestMapping("/")
    public String greeting(Model model) {
        //model.addAttribute("name", name);
        return "home";
    }

}
