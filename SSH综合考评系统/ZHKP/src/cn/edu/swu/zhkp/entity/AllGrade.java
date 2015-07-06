package cn.edu.swu.zhkp.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity()
@Table(name = "T_AllGrade")
public class AllGrade {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ID;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gather_id")
	private Gather gather;

	@OneToMany(mappedBy = "allGrade")
	private Set<Grade> grades;
	@OneToMany(mappedBy = "allGrade")
	private Set<Moral> morals;
	/*
	 * @OneToMany(mappedBy="allGrade") private Set<Awarded> awardeds;
	 */
	@OneToMany(mappedBy = "allGrade")
	private Set<Award> awards;

	public Set<Award> getAwards() {
		return awards;
	}

	public void setAwards(Set<Award> awards) {
		this.awards = awards;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Gather getGather() {
		return gather;
	}

	public void setGather(Gather gather) {
		this.gather = gather;
	}

	public Set<Grade> getGrades() {
		return grades;
	}

	public void setGrades(Set<Grade> grades) {
		this.grades = grades;
	}

	public Set<Moral> getMorals() {
		return morals;
	}

	public void setMorals(Set<Moral> morals) {
		this.morals = morals;
	}

}
