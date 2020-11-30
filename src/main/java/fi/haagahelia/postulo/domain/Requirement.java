package fi.haagahelia.postulo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Requirement {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	// unique requirement id to track the requirement. this can be left empty while storing multiple requirements
	// and defined later i.e infra-iaas-001, vahti-T-01, pcidss-0102, gdpr-xx-yyy
	@Column(name="reqid", nullable = true, unique = true)
	private String reqid;
	
	@ManyToOne
	@JoinColumn(name= "typeid")
	//@JsonIgnore
	@JsonManagedReference
	// defines the requirement type. initally stored Functional, Non-functional, Feature, Epic, Theme, and User story
	private Type type;
	
	// Represents the short description/title/summary of a given Requirement.
	private String summary;
	
	// Describes the rationale of the requirement
	private String rationale;
	
	// Define the priority of the requirement
	private String priority;
	
	// Describes the source of the requirement (specific customer, standard...)
	private String source;
	
	// Describes, who will be owner of the requirement
	private String owner;
	
	// Stores the date when the requirement has been initially stored
	private String rdate;
	
	public Requirement() {}
	
	public Requirement(String reqid, Type type, String summary, String rationale, String priority, String source, String owner, String rdate) {
		super();
		this.reqid = reqid;
		this.type = type;
		this.summary = summary;
		this.rationale = rationale;
		this.priority = priority;
		this.source = source;
		this.owner = owner;
		this.rdate = rdate;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReqid() {
		return reqid;
	}

	public void setReqid(String reqid) {
		this.reqid = reqid;
	}

	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getRationale() {
		return rationale;
	}

	public void setRationale(String rationale) {
		this.rationale = rationale;
	}
	
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		this.rdate = rdate;
	}


	
	@Override
	public String toString() {
		if (this.type != null)
			return "Requirement [id=" + id + ", reqid=" + reqid + ", type =" + this.getType() + ", summary=" + summary + ", rationale=" + rationale + ", source=" + source + ", owner=" + owner + ", rdate=" + rdate + "]";
			else

		return "Requirement [id=" + id + ", reqid=" + reqid + ", summary=" + summary + ", rationale=" + rationale + ", priority=" + priority + ",source=" + source + ", owner=" + owner + ", rdate=" + rdate + "]";


	}
	
	

}
