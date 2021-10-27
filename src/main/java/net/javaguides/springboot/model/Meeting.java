package net.javaguides.springboot.model;

import javax.persistence.*;

@Entity
@Table(name = "meetings")
public class Meeting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "department")
	private String department;

	@Column(name = "date")
	private String date;

	@Column(name = "time")
	private String time;




	public Meeting() {

	}

	public Meeting(String department, String date, String time) {
		super();
		this.department = department;
		this.date = date;
		this.time = time;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
