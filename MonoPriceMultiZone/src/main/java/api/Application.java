package api;

import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import api.config.Configuration;
import api.util.RequestRangeFilter;
import api.util.SerialPortWrapper;

@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//	public Filter applicationContextIdFilter(ApplicationContext context) {
//		return new RequestRangeFilter();
//	}
//   
    
    
}
