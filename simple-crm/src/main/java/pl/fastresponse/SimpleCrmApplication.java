package pl.fastresponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.aopalliance.aop.*;
@SpringBootApplication
public class SimpleCrmApplication {

	private String unused;
	
    public static void main(String[] args) {
    	String unused2;
        SpringApplication.run(SimpleCrmApplication.class, args);
    }
}
