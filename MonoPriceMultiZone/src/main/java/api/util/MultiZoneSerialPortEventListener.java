package api.util;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

@Component
public class MultiZoneSerialPortEventListener implements SerialPortEventListener{

	@Autowired public SerialPortWrapper serialPortWrapper;
	@Autowired private SimpMessagingTemplate template;
	
	@Override
	public void serialEvent(SerialPortEvent serialPortEvent) {
        
        if(serialPortEvent.isRXCHAR()){
            try {
            	
                int byteCount = serialPortEvent.getEventValue();
                byte bufferIn[] = serialPortWrapper.getSerialPort().readBytes(byteCount);
                 
                String stringIn = "";
                try {
                    stringIn = new String(bufferIn, "UTF-8");
                    if(stringIn.length() > 6){
                    	
                    	template.convertAndSend("/topic/greetings",Utils.createZoneInfoFromString(stringIn));
                    }else{
                    	
                    }
                    
                } catch (UnsupportedEncodingException ex) {
                   
                } catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
                System.out.println(stringIn);
               
                 
            } catch (SerialPortException ex) {
                
            }
             
        }
         
    }

}
