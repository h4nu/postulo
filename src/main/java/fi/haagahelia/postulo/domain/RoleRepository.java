package fi.haagahelia.postulo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import fi.haagahelia.postulo.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);

    @Override
    void delete(Role role);
}
