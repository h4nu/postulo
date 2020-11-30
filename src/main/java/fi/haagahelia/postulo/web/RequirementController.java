package fi.haagahelia.postulo.web;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.postulo.domain.Requirement;
import fi.haagahelia.postulo.domain.RequirementRepository;
import fi.haagahelia.postulo.domain.TypeRepository;


@Controller
public class RequirementController {
	@Autowired
	private RequirementRepository rrepository;
	
	@Autowired
	private TypeRepository trepository;
	
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	} 
	
	@Autowired
	private RequirementService service;
	
	@RequestMapping(value="/logout")
	public String logout (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){ 
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	} 
	
	// home page
	@RequestMapping(value= {"/", "/home"}) 
	public String home() {
	// nothing required here yet
	return "home"; }
	
	
	// Show all requirements
	// will be replaced by lista and not needed
	// simplify the code and cleanup if we have time before the course deadline
	// @RequestMapping(value= {"/", "/requirementlist"})
	@RequestMapping(value= {"/requirementlist"})
	public String requirementList(Model model) {	
		model.addAttribute("requirements", rrepository.findAll());
	    return "requirementlist";
	}
	
	// List all requirements
	@RequestMapping(value= {"/lista"})
	public String viewList(Model model, @Param("keyword") String keyword) {	
		List<Requirement> listRequirements = service.listAll(keyword);
		model.addAttribute("listRequirements", listRequirements);
		model.addAttribute("keyword", keyword);
	    return "lista";
	}
	
	// RESTful service to get all requirements
	@RequestMapping(value="/requirements", method = RequestMethod.GET)
	    public @ResponseBody List<Requirement> requirementListRest() {	
	    return (List<Requirement>) rrepository.findAll();
	} 
	    
	// RESTful service to get requirement by id
	@RequestMapping(value="/requirement/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Requirement> findRequirementRest(@PathVariable("id") Long requirementId) {	
		return rrepository.findById(requirementId);
	}
	
	// Add new requirement
    // @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/add")
    public String addRequirement(Model model){
    	model.addAttribute("requirement", new Requirement());
    	model.addAttribute("types", trepository.findAll());
        return "addrequirement";
    }  

	// Save new requirement
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Requirement requirement){
        rrepository.save(requirement);
        return "redirect:requirementlist";
    }    

	// Delete requirement
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteRequirement(@PathVariable("id") Long requirementId, Model model) {
    	rrepository.deleteById(requirementId);
        return "redirect:../requirementlist";
    }

    // Edit requirement
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editRequirement(@PathVariable("id") Long requirementId, Model model) {
    	model.addAttribute("requirement", rrepository.findById(requirementId));
    	model.addAttribute("types", trepository.findAll());
        return "editrequirement";
    }    

	    

}
