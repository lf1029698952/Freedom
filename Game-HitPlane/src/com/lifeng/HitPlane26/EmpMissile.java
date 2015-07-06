package com.lifeng.HitPlane26;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class EmpMissile
{
	int em_x;
	int em_y;
	int em_w = 20;
	int em_h = 6;
	boolean em_live;
	GameStart gs;
	Image ms_img = Toolkit.getDefaultToolkit().getImage(EmpPlane.class.getResource("/image/missile.PNG"));
	
	public EmpMissile(int em_x, int em_y, boolean em_live, GameStart gs)
	{
		super();
		this.em_x = em_x;
		this.em_y = em_y;
		this.em_live = em_live;
		this.gs = gs;
	}

	public void drawMe(Graphics g)
	{
		if (!this.em_live)
		{
			gs.emslist.remove(this);
			return;
		}
			
		move();
		g.drawImage(ms_img, em_x, em_y, em_w, em_h, null);
	}
	
	public void move()
	{
		em_x -= 6;
	}
	
	public Rectangle emsReg()
	{
		return new Rectangle(em_x, em_y, em_w, em_h);
	}
	
	public boolean hitPlane(Plane plane)
	{
		for (int i = 0; i < gs.emslist.size(); i++)
		{
			EmpMissile ems = gs.emslist.get(i);
			if (ems.em_live && plane.p_live && ems.emsReg().intersects(plane.planeReg()))
			{
				ems.em_live = false;
				plane.p_life -= 25;
				if (plane.p_life <= 0)
				{
					plane.p_live = false;
				}
				
				return true;
			}
			
		}
		
		return false;
	}
}
