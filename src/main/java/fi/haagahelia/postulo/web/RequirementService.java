package fi.haagahelia.postulo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.haagahelia.postulo.domain.Requirement;
import fi.haagahelia.postulo.domain.RequirementRepository;

@Service
public class RequirementService  {
	@Autowired
    RequirementRepository rrepository;
     
    public List<Requirement> listAll(String keyword) {
        if (keyword != null) {
            return rrepository.search(keyword);
        }
        return rrepository.findAll();
    }

}
