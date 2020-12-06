package fi.haagahelia.postulo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import fi.haagahelia.postulo.domain.User;

public interface UserRepository extends CrudRepository<User, Long>, JpaRepository<User, Long> {
	// User findByUsername(String username);
	User findByEmail(String email);
	
	@Override
    void delete(User user);

}
