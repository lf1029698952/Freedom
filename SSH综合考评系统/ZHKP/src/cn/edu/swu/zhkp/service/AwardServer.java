package cn.edu.swu.zhkp.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.edu.swu.zhkp.dao.impl.AwardDao;
import cn.edu.swu.zhkp.entity.AllGrade;
import cn.edu.swu.zhkp.entity.Award;
import cn.edu.swu.zhkp.entity.Checker;
import cn.edu.swu.zhkp.entity.Gather;
import cn.edu.swu.zhkp.entity.User;

public class AwardServer {
	AwardDao awardDao;

	/*
	 * 添加单个加分请求
	 */
	public String addAward(Award award) {
		try {
			awardDao.save(award);
			return "申请" + award.getId() + "提交成功";
		} catch (Exception e) {
			return "申请" + award.getId() + "提交失败";
		}
	}

	/*
	 * 修改单个加分请求
	 */
	public String updateAward(Award award) {
		try {
			this.awardDao.update(award);
			return "修改申请" + award.getId() + "成功";
		} catch (Exception e) {
			return "修改申请" + award.getId() + "失败";
		}
	}

	/*
	 * 删除单个加分请求
	 */
	public String deleteAward(Long awardid) {
		try {
			Award award = this.awardDao.queryById(awardid);
			this.awardDao.delete(award);
			return "删除申请" + awardid + "成功";
		} catch (Exception e) {
			return "删除申请" + awardid + "失败";
		}
	}

	/*
	 * 审核单个加分请求
	 */
	public String checkAward(Award award, Checker nowChecker, boolean select) {
		try {
			if (select) {
				Set<Checker> checkers = award.getCheckers();
				checkers.add(nowChecker);
				award.setCheckers(checkers);
				// 判断某个加分请求是否都已经审核
				if (checkers.size() == nowChecker.getGather().getCheckers()
						.size()) {
					award.setStatus(1);
					updateAward(award);
				}
			} else {
				award.setStatus(-1);
				updateAward(award);
			}
			return "审核人：" + nowChecker.getID() + "审核加分请求" + award.getId()
					+ "成功";
		} catch (Exception e) {
			return "审核人：" + nowChecker.getID() + "审核加分请求" + award.getId()
					+ "失败";
		}

	}

	/*
	 * 判断某个加分请求是否都已经审核暂时不用，已经在上面用其他方法解决
	 */
	public boolean isAllChecked(Gather gather, Award award) {
		if (award.getCheckers().size() == gather.getCheckers().size()) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 获取某个班级没有审核的加分请求和谁没选
	 */
	public Map<Award, List<Checker>> getUncheck(Gather gather) {
		// 返回的Map
		Map<Award, List<Checker>> unAwardAndList = null;
		// 当前班级的checkers
		Set<Checker> checkers = gather.getCheckers();
		// 当前班级的加分请求
		List<Award> awards = this.getAwardDao().getAllAwards("gather_id",
				(gather.getId() + ""));

		// 当前班级的每个加分请求
		for (Award award : awards) {
			// 判断某个加分请求有没有被驳回
			if (award.getStatus() != -1) {
				// 某个加分请求没有审核的checker的集合
				List<Checker> uncheckers = null;
				// 当前班级的每个checker
				for (Checker checker : checkers) {
					boolean bool = false;
					// 当前班级某个加分请求已经审核的每个checkered
					for (Checker checkered : award.getCheckers()) {
						// 判断当前班级的某个checker是否在某个加分请求的checkereds
						if (checker.getID() == checkered.getID())
							bool = true;
					}
					// 当前班级某个checker没有审核某个请求，添加到某个请求没有背审核的集合人里面
					if (!bool) {
						uncheckers.add(checker);
					}
				}
				// 当前某个科目的没有审核的checker是否为空
				if (uncheckers != null) {
					unAwardAndList.put(award, uncheckers);
				}
			}
		}
		return unAwardAndList;
	}

	/*
	 * 获取单个用户某班级（学年）的所有加分请求
	 */
	public List<Award> getUserAwards(User user, long gatherId) {
		return this.awardDao
				.getAllAwardsByUserAndGather(user.getId(), gatherId);
		/*
		 * for(AllGrade allGrade : user.getAllGrades() ){
		 * if(allGrade.getGather().getId().equals(gatherId)){ return
		 * allGrade.getAwards(); } } return null;
		 */
	}

	/*
	 * 获取单个用户某班级（学年）审核通过的加分请求
	 */
	public List<Award> getUserOnAwardeds(User user, long gatherId) {
		List<Award> userOnAwardeds = this.getUserAwards(user, gatherId);
		for (Award award : userOnAwardeds) {
			if (award.getStatus() != 1) {
				userOnAwardeds.remove(award);
			}
		}
		return userOnAwardeds;
	}

	/**
	 * 得到页面的list
	 * 
	 * @param pagenum
	 *            处于的页数,从1开始
	 * @param pagesize
	 *            页面的数目
	 * @return
	 */
	public List<Award> getAwardsPage(int pagenum, int pagesize) {
		return awardDao
				.getDatas(null, null, pagesize, (pagenum - 1) * pagesize);
	}

	public AwardDao getAwardDao() {
		return awardDao;
	}

	public void setAwardDao(AwardDao awardDao) {
		this.awardDao = awardDao;
	}

}
