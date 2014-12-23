package api;

import java.util.ArrayList;
import java.util.List;

import jssc.SerialPort;
import jssc.SerialPortList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import api.config.Configuration;
import api.util.Utils;
import api.vo.ZoneInfo;

@Controller
public class MultiZoneController {
	@Autowired
	private SimpMessagingTemplate template;
	
	
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
    public String home(Model model) {
        //model.addAttribute("name", name);
    	List<ZoneInfo>zfs = new ArrayList<ZoneInfo>();
        for(int i=0; i<6; i++)
			try {
				ZoneInfo zoneInfo = Utils.createZoneInfoFromString("010203040506070809101112131415161718");
				zoneInfo.setZoneName("Zone "+i+1);
				zfs.add(zoneInfo);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        model.addAttribute("zones", zfs);
        return "home";
    }
    
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public List<ZoneInfo> zoneInfo(String message) throws Exception {
        Thread.sleep(3000); // simulated delay
        List<ZoneInfo>zfs = new ArrayList<ZoneInfo>();
        for(int i=0; i<3; i++)
        	zfs.add(Utils.createZoneInfoFromString("010203040506070809101112131415161718"));
        
        return zfs;
    }
    
    
    @RequestMapping(value="/greetings")
    public void greet(String greeting) throws Exception {
    	List<ZoneInfo>zfs = new ArrayList<ZoneInfo>();
    	for(int i=0; i<3; i++)
        	zfs.add(Utils.createZoneInfoFromString("010203040506070809101112131415161718"));
        template.convertAndSend("/topic/greetings",zfs);
    }

}
