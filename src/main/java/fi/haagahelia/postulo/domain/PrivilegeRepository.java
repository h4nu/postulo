package fi.haagahelia.postulo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import fi.haagahelia.postulo.domain.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Privilege findByName(String name);

    @Override
    void delete(Privilege privilege);

}
