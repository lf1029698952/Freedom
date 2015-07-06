package cn.edu.swu.zhkp.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.edu.swu.zhkp.entity.Award;
import cn.edu.swu.zhkp.service.AwardServer;

public class AwardController {
	private Award addAward;
	private Award updateAward;
	private AwardServer awardServer;
	
	/*
	 * 转到加分的页面
	 */	
	public String addAward(){
		return "addAward";
	}

	
	/*
	 * 提交新的加分申请
	 */
	public String takeAddAward(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		if(request.getAttribute("award")!=null && !"".equals(request.getAttribute("award"))){
			String message = null;
			try{
				message = awardServer.addAward(addAward);
				session.setAttribute("message", message);
			}catch(Exception e){
				return "error";
			}
		}
		return "success";
	}
	
	/*
	 * 删除加分请求
	 */
	public String deleteAward(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		if(request.getAttribute("awardid")!= null && !"".equals(request.getAttribute("awardid"))){
			Long awardid = (Long) request.getAttribute("awardid");
			String message = null;
			try{
				message = awardServer.deleteAward(awardid);
			}catch(Exception e){
				session.setAttribute("message", message);
				return "error";
			}
		}
		return "success";
	}
	
	/*
	 * 修改加分请求
	 */
	public String updateAward(){
		HttpServletRequest request  = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		if(request.getAttribute("updateAward") != null){
			String message = null;
			try{
				message = awardServer.updateAward(updateAward);
			}catch(Exception e){
				session.setAttribute("message", message);
				return "error";
			}
		}
		return "success";
	}
	
	/*
	 * 查询当前checker未审核完的加分请求
	 */
	public String queryMyUncheck(){
		return "success";
	}
	
	
	
	
	/************************************** set,get *****************************************/
	

	public Award getAddAward() {
		return addAward;
	}
	public void setAddAward(Award addAward) {
		this.addAward = addAward;
	}
	public void setAwardServer(AwardServer awardServer) {
		this.awardServer = awardServer;
	}
	public Award getUpdateAward() {
		return updateAward;
	}
	public void setUpdateAward(Award updateAward) {
		this.updateAward = updateAward;
	}
	
}
