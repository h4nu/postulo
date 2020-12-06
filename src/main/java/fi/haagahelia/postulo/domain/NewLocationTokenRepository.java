package fi.haagahelia.postulo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import fi.haagahelia.postulo.domain.UserLocation;
import fi.haagahelia.postulo.domain.NewLocationToken;

public interface NewLocationTokenRepository extends JpaRepository<NewLocationToken, Long> {

	NewLocationToken findByToken(String token);

    NewLocationToken findByUserLocation(UserLocation userLocation);
}
