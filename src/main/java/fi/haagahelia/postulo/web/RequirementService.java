package fi.haagahelia.postulo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.haagahelia.postulo.domain.Requirement;
import fi.haagahelia.postulo.domain.RequirementRepository;

/*
 * This service is created to enable the search functionality
 *  
 */

@Service
public class RequirementService  {
	@Autowired
    private RequirementRepository rrepository;
     
    public List<Requirement> listAll(String keyword) {
        if (keyword != null) {
            return rrepository.search(keyword);
        }
        return rrepository.findAll();
    }
    
    public void save(Requirement requirement) {
    	rrepository.save(requirement);
    }
    
    public Requirement get(long id) {
    	return rrepository.findById(id).get();
    }
    
    public void delete(long id) {
    	rrepository.deleteById(id);
    }

}
