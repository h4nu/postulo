package fi.haagahelia.postulo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

//JUnit 5 tests with import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;

//import org.junit.Test and org.junit.runner.RunWith used with JUnit 4
//import org.junit.Test;
//import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.postulo.domain.User;
import fi.haagahelia.postulo.domain.UserRepository;
import fi.haagahelia.postulo.domain.Requirement;
import fi.haagahelia.postulo.domain.RequirementRepository;
import fi.haagahelia.postulo.domain.Type;
import fi.haagahelia.postulo.domain.TypeRepository;


/*
 * These tests works when database configurations has been stated directly in the application.properties
 * but fails if using eclipse environt variables like spring.datasource.url=${SPRING_DATASOURCE_URL}
 * if separate environment variables has not been stated in the eclipse for the tests too
 * So, please DEFINE THE ENVIRONMENT VARIABLES FOR TESTS TOO!!!
 */

@DataJpaTest
// @AutoConfigureTestDatabase is needed to test with the real database instead of h2
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) 
public class RequirementRepositoryTest {
	private static final Logger log = LoggerFactory.getLogger(PostuloApplication.class);
	
	@Autowired
	private RequirementRepository requirementRepository;
	
	@Autowired
	private TypeRepository typeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	// RequirementRepository tests
	
	@Test
	public void findByReqIdReturnsOwner() {
		log.info("Running FindByReqIdReturnsOwner");
		List<Requirement> requirements = requirementRepository.findByReqid("foobar-0001");
		assertThat(requirements).hasSize(1);
		assertThat(requirements.get(0).getOwner()).isEqualTo("John Owner");
	}
	
	@Test
	public void createNewRequirement() {
		log.info("Running createNewRequirement");
		// Requirement requirement = new Requirement("foobar-0003", typeRepository.findByName("Functional").get(0), "Sample requirement", "Yet another rationale", "Should Have", "Customer Foo", "Jim Owner",LocalDate.of(2020, 11, 30));
		Requirement requirement = new Requirement("foobar-0003", typeRepository.findByName("Functional").get(0), "Testing requirement", "A testing rationale", "Could Have", "Test source", "John Owner",LocalDate.of(2020, 11, 28));
		requirementRepository.save(requirement);
		System.out.println("vaaatimusid tulisi tahan" + requirement.getId());
		assertThat(requirement.getId()).isNotNull();
	}
	
	@Test
	public void deleteRequirement() {
		log.info("Test delete a requirement");
		List<Requirement> requirement = requirementRepository.findByReqid("foobar-0002");
		if(requirement != null) {
			requirementRepository.deleteById(requirement.get(0).getId());
		}
	}
	
	// TypeRepository tests
	@Test
	public void findByType() {
		log.info("Running findByType");
		List<Type> types = typeRepository.findByName("Functional");
		assertThat(types.get(0).getName()).isEqualTo("Functional");
	}
		
	@Test
	public void createNewType() {
		log.info("test creating a new type");
		Type type = new Type("Just Fiction");
		typeRepository.save(type);
		assertThat(type.getTypeid()).isNotNull();
	}
	
	// UserRepository tests
	
	/*
	@Test
	public void findByUser() {
		log.info("Running findByUser");
		User users = userRepository.findByUsername("user");
		assertThat(users.getRole()).isEqualTo("USER");
	}

	@Test
	public void createNewUser() {
		log.info("Running createNewUser");
		User user = new User("foobar", "$2a$10$Gdr0sij9l1OW.aqtlrkpC.UDQ91uh9tEL4bdmWhT/ZTixsdx57NL.", "foobar@foo.bar", "USER");
		userRepository.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
	@Test
	public void deleteUser() {
		log.info("delete user test");
		User user = userRepository.findByUsername("johndoe");
		log.info("test finding johndoe");
		if(user != null) {
			userRepository.delete(user);
		}
	}	
	*/
}
