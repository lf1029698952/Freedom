package com.lifeng.HitPlane13;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.List;

public class Missile
{
	GameStart gs;
	int m_x;
	int m_y;
	int m_w = 20;
	int m_h = 5;
	boolean b_live = true;
	Image ms1_img = Toolkit.getDefaultToolkit().getImage(Missile.class.getResource("/image/Missile.PNG"));
	Image ms2_img = Toolkit.getDefaultToolkit().getImage(Missile.class.getResource("/image/MB_ultimate.png"));
	
	public Missile(GameStart gs, int m_x, int m_y, boolean b_live)
	{
		super();
		this.gs = gs;
		this.m_x = m_x;
		this.m_y = m_y;
		this.b_live = b_live;
	}

	//子弹画出自己的方法
	public void drawMe1(Graphics g)
	{
		if (!b_live || m_x > gs.myPlane.p_x+600)
		{
			return;
		}
		m_x += 10;
		g.drawImage(ms1_img, m_x, m_y, m_w, m_h, null);
	}
	
	public void drawMe2(Graphics g)
	{
		if (!b_live)
		{
			return;
		}
		m_x += 10;
		g.drawImage(ms2_img, m_x, m_y, 63, 300, null);
	}
	
	public Rectangle ms1Reg()
	{
		return new Rectangle(m_x, m_y, m_w, m_h);
	}
	
	public Rectangle ms2Reg()
	{
		return new Rectangle(m_x, m_y, 63, 300);
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
			//使子弹及飞机都死亡
			this.b_live = false;
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
	
	public boolean hit2Emplist(List<EmpPlane> empList)
	{
		for (int i = 0; i < empList.size(); i++)
		{
			if (this.b_live && empList.get(i).e_live && this.ms2Reg().intersects(empList.get(i).empReg()))
			{
				this.b_live = false;
				empList.get(i).e_live = false;
				
				return true;
			}
		}
		return false;
	}
	
	public boolean hit1Boss(Boss boss)
	{
		if (this.b_live && boss.boss_live && this.ms1Reg().intersects(boss.bossReg()))
		{
			this.b_live = false;
			boss.boss_live = false;
			
			return true;
		}
		
		return false;
	}
	
	public boolean hit2Boss(Boss boss)
	{
		if (this.b_live && boss.boss_live && this.ms1Reg().intersects(boss.bossReg()))
		{
			this.b_live = false;
			boss.boss_live = false;
			
			return true;
		}
		
		return false;
	}
}
