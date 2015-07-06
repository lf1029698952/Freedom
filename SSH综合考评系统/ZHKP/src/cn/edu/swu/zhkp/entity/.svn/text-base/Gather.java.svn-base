package cn.edu.swu.zhkp.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_Gather")
public class Gather {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	@Column(name = "gname")
	private String gname;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "admin_id")
	private Admin admin;
		
	@OneToMany(mappedBy="gather")
	private Set<Checker> checkers;

	@ManyToMany(mappedBy="gathers")
	private Set<User> users;
	
	@ManyToMany
	@JoinTable(name = "T_GatherOfCourse", joinColumns = { @JoinColumn(name = "gather_id") }, inverseJoinColumns = { @JoinColumn(name = "course_id") })
	private Set<Course> course;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Checker> getCheckers() {
		return checkers;
	}

	public void setCheckers(Set<Checker> checkers) {
		this.checkers = checkers;
	}

	public Set<Course> getCourse() {
		return course;
	}

	public void setCourse(Set<Course> course) {
		this.course = course;
	}

}
