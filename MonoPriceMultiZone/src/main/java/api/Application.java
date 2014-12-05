package api;

import jssc.SerialPort;
import jssc.SerialPortException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import config.Configuration;

@ComponentScan
@EnableAutoConfiguration
public class Application {
	@Autowired Configuration configuration;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public SerialPort serialPort(){
    	SerialPort serialPort = new SerialPort("COM1");
        try {
            serialPort.openPort();//Open serial port
            serialPort.setParams(SerialPort.BAUDRATE_9600, 
                                 SerialPort.DATABITS_8,
                                 SerialPort.STOPBITS_1,
                                 SerialPort.PARITY_NONE);
            serialPort.writeBytes("This is a test string".getBytes());//Write data to port
        
            
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        }
        return serialPort;
    }
    
}
