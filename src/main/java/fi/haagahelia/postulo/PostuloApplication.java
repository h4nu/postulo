package fi.haagahelia.postulo;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

import fi.haagahelia.postulo.domain.Requirement;
import fi.haagahelia.postulo.domain.RequirementRepository;
import fi.haagahelia.postulo.domain.Type;
import fi.haagahelia.postulo.domain.TypeRepository;
import fi.haagahelia.postulo.domain.User;
import fi.haagahelia.postulo.domain.UserRepository;

@SpringBootApplication
public class PostuloApplication extends SpringBootServletInitializer{
	private static final Logger log = LoggerFactory.getLogger(PostuloApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PostuloApplication.class, args);
	}
	
	@Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
    
	
	/*
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder app) { 
  	return app.sources(PostuloApplication.class); 
	}
	*/
	
	

}
