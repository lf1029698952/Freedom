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

/*
 * 审核进行中
 * 审核不通过
 */

@Entity
@Table(name = "T_award")
public class Award {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id; // id
	@Column(name = "mode")
	private int mode; // 模块，1 表示文艺，2表示体育，3社会工作，4学术科技，5表示其他
	@Column(name = "type")
	private String type; // 类型，比如学术里面：比赛，论文，创新基金……
	@Column(name = "level")
	private int level; // 等级，国家级，市级，校级，院级
	@Column(name = "rate")
	private int rate; // 名次。一等奖，二等……
	@Column(name = "pre_point")
	private Double pre_point; // 预计分数
	@Column(name = "checked_point")
	private Double checked_point;// 最终分数
	@Column(name="status")
	private int status;//审核状态
	
	@ManyToMany
	@JoinTable(name = "T_AwardOfChecker", joinColumns = { @JoinColumn(name = "checker_id") }, inverseJoinColumns = { @JoinColumn(name = "award_id") })
	private Set<Checker> checkers;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "allgrade_id")
	private AllGrade allGrade; // 成绩的id
	
	
	//添加
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="gather_id")
	private Gather gather;
	//添加
		
	
	
	public Set<Checker> getCheckers() {
		return checkers;
	}

	public Gather getGather() {
		return gather;
	}

	public void setGather(Gather gather) {
		this.gather = gather;
	}

	public void setCheckers(Set<Checker> checkers) {
		this.checkers = checkers;
	}

	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public AllGrade getAllGrade() {
		return allGrade;
	}

	public void setAllGrade(AllGrade allGrade) {
		this.allGrade = allGrade;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}


	public Double getPre_point() {
		return pre_point;
	}

	public void setPre_point(Double pre_point) {
		this.pre_point = pre_point;
	}

	public Double getChecked_point() {
		return checked_point;
	}

	public void setChecked_point(Double checked_point) {
		this.checked_point = checked_point;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
