package fi.haagahelia.postulo.domain;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

// public interface RequirementRepository  extends CrudRepository<Requirement, Long>{
public interface RequirementRepository  extends CrudRepository<Requirement, Long>, JpaRepository<Requirement, Long>{

	List<Requirement> findByReqid(String reqid);
	List<Requirement> findByType(Type type);
	List<Requirement> findBySummary(String summary);
	List<Requirement> findByPriority(String priority);
	List<Requirement> findBySource(String source);
	List<Requirement> findByOwner(String owner);
	List<Requirement> findByRdate(LocalDate rdate);
	
	@Query("SELECT r FROM Requirement r WHERE CONCAT(r.reqid, ' ', r.type, ' ', r.summary, ' ',  r.rationale, ' ',  r.priority, ' ',  r.source, ' ',  r.owner, ' ',  r.rdate, ' ') LIKE %?1%")
    public List<Requirement> search(String keyword);

}

	

