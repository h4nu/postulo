package fi.haagahelia.postulo.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Type {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long typeid;
	
	
	// This would prevent multiple categories with same name
	@Column(name ="name", nullable = false, unique = true)
	private String name;
	
	// private String description;
	
	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
	private List<Requirement> requirements;
	
	public Type() {}
	
	public Type(String name) {
		super();
		this.name = name;
	}

	public Long getTypeid() {
		return typeid;
	}

	public void setTypeid(Long typeid) {
		this.typeid = typeid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Requirement> getRequirements() {
		return requirements;
	}
	
	public void setRequirements(List<Requirement> requirements) {
		this.requirements = requirements;
	}

	/*
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	*/
	
	@Override
	public String toString() {
		// return "RequirementType [typeid=" + typeid + ", name=" + name + ", description=" + description + "]";
		return "RequirementType [typeid=" + typeid + ", name=" + name + "]";
	}
	

}
