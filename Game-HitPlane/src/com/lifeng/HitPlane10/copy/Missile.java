package com.lifeng.HitPlane10.copy;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

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

	//�ӵ������Լ��ķ���
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
			//ʹ�ӵ����ɻ�������
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
			//ʹ�ӵ����ɻ�������
			this.b_live = false;
			emp.e_live = false;
			
			return true;
		}
		return false;
	}
}
