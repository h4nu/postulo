package fi.haagahelia.postulo.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fi.haagahelia.postulo.domain.DeviceMetadata;

public interface DeviceMetadataRepository extends JpaRepository<DeviceMetadata, Long> {
	
	List<DeviceMetadata> findByUserId(Long userId);
}
