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
@Table(name = "T_USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	@Column(name = "schoolnum")
	private String schoolnum;
	@Column(name = "uname")
	private String uname;
	@Column(name = "password")
	private String password;
	@Column(name = "academy")
	private String academy; // 学院
	@Column(name = "enrollment")
	private String enrollment; // 入学年
	@Column(name = "major")
	private String major; // 专业
	@Column(name = "academy")


	//27日修改
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "T_UserOfGather", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "gather_id") })
	private Set<Gather> gathers;
	
	@	OneToMany(mappedBy="user")
	private Set<AllGrade> allGrades;
	//
	
	

	public long getId() {
		return id;
	}

	public Set<Gather> getGathers() {
		return gathers;
	}

	public void setGathers(Set<Gather> gathers) {
		this.gathers = gathers;
	}

	public Set<AllGrade> getAllGrades() {
		return allGrades;
	}

	public void setAllGrades(Set<AllGrade> allGrades) {
		this.allGrades = allGrades;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSchoolnum() {
		return schoolnum;
	}

	public void setSchoolnum(String schoolnum) {
		this.schoolnum = schoolnum;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAcademy() {
		return academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	public String getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(String enrollment) {
		this.enrollment = enrollment;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

}
