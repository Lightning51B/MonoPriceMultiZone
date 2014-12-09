package api.util;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

@Component
public class MultiZoneSerialPortEventListener implements SerialPortEventListener{

	@Autowired public SerialPortWrapper serialPortWrapper;
	
	@Override
	public void serialEvent(SerialPortEvent serialPortEvent) {
        
        if(serialPortEvent.isRXCHAR()){
            try {
            	
                int byteCount = serialPortEvent.getEventValue();
                byte bufferIn[] = serialPortWrapper.getSerialPort().readBytes(byteCount);
                 
                String stringIn = "";
                try {
                    stringIn = new String(bufferIn, "UTF-8");
                    
                } catch (UnsupportedEncodingException ex) {
                   
                }
                //
                System.out.println(stringIn);
                
                 
            } catch (SerialPortException ex) {
                
            }
             
        }
         
    }

}
