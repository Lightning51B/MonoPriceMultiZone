package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

import config.Configuration;

@ComponentScan
@EnableAutoConfiguration
public class Application {
	@Autowired Configuration configuration;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
