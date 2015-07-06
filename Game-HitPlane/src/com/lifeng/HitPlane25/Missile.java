package com.lifeng.HitPlane25;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.List;

public class Missile
{
	GameStart gs;
	int m1_x;
	int m1_y;
	int m2_x = 0;
	int m2_y = 0;
	int m_w = 20;
	int m_h = 10;
	boolean b_live = true;
	Image ms1_img = Toolkit.getDefaultToolkit().getImage(Missile.class.getResource("/image/missile2.PNG"));
	Image ms2_img = Toolkit.getDefaultToolkit().getImage(Missile.class.getResource("/image/MB_ultimate.png"));
	
	public Missile(GameStart gs, int m1_x, int m1_y, boolean b_live)
	{
		super();
		this.gs = gs;
		this.m1_x = m1_x;
		this.m1_y = m1_y;
		this.b_live = b_live;
	}

	//子弹画出自己的方法
	public void drawMe1(Graphics g)
	{
		if (!b_live || this.m1_x > gs.myPlane.p_x+600 || this.m1_x <0 || this.m1_x > 800)
		{
			gs.mslist1.remove(this);
			return;
		}
		m1_x += 8;
		g.drawImage(ms1_img, m1_x+12, m1_y, m_w, m_h, null);
	}
	
	public void drawMe2(Graphics g)
	{
		if (!b_live || this.m2_x > 800)
		{
			gs.mslist2.remove(this);
			return;
		}
		m2_x += 8;
		g.drawImage(ms2_img, m2_x, m2_y, 30, 600, null);
	}
	
	public Rectangle ms1Reg()
	{
		return new Rectangle(m1_x, m1_y, m_w, m_h);
	}
	
	public Rectangle ms2Reg()
	{
		return new Rectangle(m2_x, m2_y, 30, 600);
	}
	
	public boolean hit1(EmpPlane emp)
	{
		if (this.b_live && emp.e_live && this.ms1Reg().intersects(emp.empReg()))
		{
			//使子弹及飞机都死亡
			this.b_live = false;
			emp.e_live = false;
			
			return true;
		}
		return false;
	}
	
	public boolean hit2(EmpPlane emp)
	{
		if (this.b_live && emp.e_live && this.ms2Reg().intersects(emp.empReg()))
		{
			//使飞机都死亡,必杀技子弹不死亡
			emp.e_live = false;
			
			return true;
		}
		return false;
	}
	
	//打中多架飞机
	public boolean hit1Emplist(List<EmpPlane> empList)
	{
		for (int i = 0; i < empList.size(); i++)
		{
			if (this.b_live && empList.get(i).e_live && this.ms1Reg().intersects(empList.get(i).empReg()))
			{
				this.b_live = false;
				empList.get(i).e_live = false;
				
				return true;
			}
		}
		
		return false;
	}
	//打中新飞机
	public boolean hit1newlist(List<NewPlane> newList)
	{
		for (int i = 0; i < newList.size(); i++)
		{
			if (this.b_live && newList.get(i).e_live && this.ms1Reg().intersects(newList.get(i).empReg()))
			{
				this.b_live = false;
				newList.get(i).e_live = false;
				
				return true;
			}
		}
		
		return false;
	}
	
	public boolean hit2Emplist(List<EmpPlane> empList)
	{
		for (int i = 0; i < empList.size(); i++)
		{
			if (this.b_live && empList.get(i).e_live && this.ms2Reg().intersects(empList.get(i).empReg()))
			{
				empList.get(i).e_live = false;
				
				return true;
			}
		}
		return false;
	}
	
	public boolean hitBoss(Boss boss)
	{
		if (this.b_live && boss.boss_live && this.ms1Reg().intersects(boss.bossReg()))
		{
			this.b_live = false;
			boss.boss_life -= 100;
			if (boss.boss_life <= 0) {
				boss.boss_live = false;
			}
			
			return true;
		}
		
		return false;
	}
}
