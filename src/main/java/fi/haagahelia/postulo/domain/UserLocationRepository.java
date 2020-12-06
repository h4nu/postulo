package fi.haagahelia.postulo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {
	UserLocation findByCountryAndUser(String country, User user);
}
