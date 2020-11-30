package fi.haagahelia.postulo;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fi.haagahelia.postulo.web.RequirementController;
import fi.haagahelia.postulo.web.UserController;

@SpringBootTest
class PostuloApplicationTests {
	
	@Autowired
	private RequirementController requirementController;
	
	@Autowired
	private UserController userController;

	@Test
	
	void contextLoads() throws Exception {
		assertThat(requirementController).isNotNull();
		assertThat(userController).isNotNull();
	}

}
