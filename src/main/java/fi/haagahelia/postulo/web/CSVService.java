package fi.haagahelia.postulo.web;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.haagahelia.postulo.domain.Requirement;
import fi.haagahelia.postulo.domain.RequirementRepository;
import fi.haagahelia.postulo.helper.CSVHelper;

@Service
public class CSVService {
	@Autowired
	  RequirementRepository rrepository;
	  
	  public ByteArrayInputStream load() {
	    List<Requirement> requirements = rrepository.findAll();

	    ByteArrayInputStream in = CSVHelper.requirementsToCSV(requirements);
	    return in;
	  }

}
