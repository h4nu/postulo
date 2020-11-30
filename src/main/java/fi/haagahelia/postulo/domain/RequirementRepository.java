package fi.haagahelia.postulo.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RequirementRepository  extends CrudRepository<Requirement, Long>{

	List<Requirement> findByReqid(String reqid);
	List<Requirement> findByType(Type type);
	List<Requirement> findBySummary(String summary);
	List<Requirement> findByPriority(String priority);
	List<Requirement> findBySource(String source);
	List<Requirement> findByOwner(String owner);
	List<Requirement> findByRdate(String rdate);

}
