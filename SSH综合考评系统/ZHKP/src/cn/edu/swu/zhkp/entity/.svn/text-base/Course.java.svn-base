package cn.edu.swu.zhkp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_Course")
public class Course {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; // 科目代码
	@Column(name = "CourseName")
	private String courseName; // 科目名称
	@Column(name = "coursepoint")
	private double coursepoint;// 科目的学分

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public double getCoursepoint() {
		return coursepoint;
	}

	public void setCoursepoint(double coursepoint) {
		this.coursepoint = coursepoint;
	}

}
