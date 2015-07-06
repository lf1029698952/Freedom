package cn.edu.swu.zhkp.entity;

import java.util.Date;

import javax.persistence.*;

/**
 * 部门实体
 * 
 * @author Medici.Yan
 */
@Entity
@Table(name = "sec_dept")
public class Dept {
	@Id
	@Column(name = "Deptid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int deptid;
	@Column(name = "Name")
	private String name;
	@Column(name = "Type")
	private String type;
	@Column(name = "Level")
	private String level;
	@Column(name = "Status")
	private int status;
	@Column(name = "Area")
	private String area;
	@Column(name = "region")
	private String region;
	@Temporal(TemporalType.DATE)
	@Column(name = "Create_time")
	private Date create_time;
	@Column(name = "Short_key")
	private String short_key;

	public int getDeptid() {
		return deptid;
	}

	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getShort_key() {
		return short_key;
	}

	public void setShort_key(String short_key) {
		this.short_key = short_key;
	}

	public Dept() {
		super();
		// TODO Auto-generated constructor stub
	}

}
