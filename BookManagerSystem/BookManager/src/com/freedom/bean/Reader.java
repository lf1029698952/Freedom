package com.freedom.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="readers")
public class Reader {
    	@Id
    	@GeneratedValue(strategy=GenerationType.AUTO)
	private int readerno;
	private String readername;
	private int sex;
	private Date birthday;
	private String tel;
	private String department;
	private String remark;

	public Reader() {
		super();
	}

	public Reader(int readerno, String readername, int sex,
			String tel, String department) {
		super();
		this.readerno = readerno;
		this.readername = readername;
		this.sex = sex;
		this.tel = tel;
		this.department = department;
	}

	public int getReaderno() {
		return readerno;
	}

	public void setReaderno(int readerno) {
		this.readerno = readerno;
	}

	public String getReadername() {
		return readername;
	}

	public void setReadername(String readername) {
		this.readername = readername;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
