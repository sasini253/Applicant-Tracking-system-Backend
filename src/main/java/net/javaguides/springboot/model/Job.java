package net.javaguides.springboot.model;
import javax.persistence.*;

@Entity
@Table(name = "jobs")
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "job_Name")
	private String jobName;

	@Column(name = "job_Type")
	private String jobType;

	@Column(name = "skills")
	private String skills;

	@Column(name = "salary")
	private String salary;


	public Job() {

	}

	public Job(String jobName, String jobType, String skills, String salary) {
		super();
		this.jobName = jobName;
		this.jobType = jobType;
		this.skills = skills;
		this.salary = salary;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}
}
