package com.lifeng.HitPlane07;

import java.awt.Graphics;
import java.awt.Image;
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

	//子弹画出自己的方法
	public void drawMe1(Graphics g)
	{
		if (!b_live || m_x > 600)
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
		g.drawImage(ms2_img, m_x, m_y, 63, 61, null);
	}
}
