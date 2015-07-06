package cn.edu.swu.zhkp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_moral")
public class Moral {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;// id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "allgrade_id")
	private AllGrade allGrade;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "checker_id")
	private Checker checker; // 评测人员的id

	@Column(name = "political")
	private double political;
	@Column(name = "thinking")
	private double thinking;
	@Column(name = "action")
	private double action;
	@Column(name = "legal")
	private double legal;
	@Column(name = "psychology")
	private double psychology;
	@Column(name = "total")
	private double total;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AllGrade getAllGrade() {
		return allGrade;
	}

	public void setAllGrade(AllGrade allGrade) {
		this.allGrade = allGrade;
	}

	
	public Checker getChecker() {
		return checker;
	}

	public void setChecker(Checker checker) {
		this.checker = checker;
	}

	public double getPolitical() {
		return political;
	}

	public void setPolitical(double political) {
		this.political = political;
	}

	public double getThinking() {
		return thinking;
	}

	public void setThinking(double thinking) {
		this.thinking = thinking;
	}

	public double getAction() {
		return action;
	}

	public void setAction(double action) {
		this.action = action;
	}

	public double getLegal() {
		return legal;
	}

	public void setLegal(double legal) {
		this.legal = legal;
	}

	public double getPsychology() {
		return psychology;
	}

	public void setPsychology(double psychology) {
		this.psychology = psychology;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
