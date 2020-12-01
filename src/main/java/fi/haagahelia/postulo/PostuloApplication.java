package fi.haagahelia.postulo;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.postulo.domain.Requirement;
import fi.haagahelia.postulo.domain.RequirementRepository;
import fi.haagahelia.postulo.domain.Type;
import fi.haagahelia.postulo.domain.TypeRepository;
import fi.haagahelia.postulo.domain.User;
import fi.haagahelia.postulo.domain.UserRepository;

@SpringBootApplication
public class PostuloApplication {
	private static final Logger log = LoggerFactory.getLogger(PostuloApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PostuloApplication.class, args);
	}
	@Bean
	public CommandLineRunner requirementDemo(RequirementRepository rrepository, TypeRepository trepository, UserRepository urepository) {
		return (args) -> {
			log.info("save a couple of categories");
			
			trepository.save(new Type("Functional"));
			trepository.save(new Type("Non-functional"));
			trepository.save(new Type("Feature"));
			trepository.save(new Type("Epic"));
			trepository.save(new Type("Theme"));
			trepository.save(new Type("User story"));
			
			// lists requirement types created
			for (Type type : trepository.findAll()) {
				if(type == null) { 
				log.info("empty db");
				
				} else {
				log.info(type.getName().toString());	
				}
			}
			
			log.info("save a couple of requirements");
			// these generates the requirements multiple times
			//rrepository.save(new Requirement("reqid", "type", "summary", "rationale", "priority", "source", "owner", "rdate"));
			//rrepository.save(new Requirement("foobar-0001", trepository.findByName("Functional").get(0), "A sample requirement summary", "a sample rationale for testing the requirement database", "Must have", "Sample source", "John Owner", "2020-10-30"));
			rrepository.save(new Requirement("foobar-0001", trepository.findByName("Functional").get(0), "A sample requirement summary", "a sample rationale for testing the requirement database", "Must have", "Sample source", "John Owner", LocalDate.of(2020, 10, 18)));
			rrepository.save(new Requirement("foobar-0002", trepository.findByName("Functional").get(0), "Another test requirement", "Yet another rationale for testing the requirement app", "Could Have", "Sample source", "John Owner",LocalDate.of(2020, 11, 28)));
		
			log.info("fetch all requirements");
			for (Requirement requirement : rrepository.findAll()) {
				log.info(requirement.toString());
			}
			// Create users: admin/admin user/user
			// System.out.println();
			
	
			// These are already generated once to heroku-postgresql so these needs to be commented out...
			// however, the test will fail if johndoe will not be created manually
			urepository.save(new User("user", "$2a$10$KkBleYKXVS.U72vsMeAgmeIMyl7Z985YNETMrZicCoLUH4QnYfkNm", "user@foo.bar", "USER") );
            urepository.save(new User("admin", "$2a$10$7TmD6APcS5O08dkAnf340e.H0JzlN3j8iffXhiSGsq6eKFx6S8BlS", "admin@foo.bar", "ADMIN"));
            urepository.save(new User("johndoe", "$2a$10$mrFtIoSfeBQE9EPwgoRUYeXBp.CbkX4Xw6OinxKooc3rewtxDhz8C", "john@doe.bar", "USER"));
            log.info("Create users");
            for (User user : urepository.findAll()) {
				log.info(user.getUsername().toString());
			}

		};
	}
	

}
