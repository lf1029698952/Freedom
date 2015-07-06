package com.lifeng.HitPlane13;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class EmpPlane 
{
	int e_x = 700;
	int e_y = 100;
	int e_w = 50;
	int e_h = 19;
	boolean e_live = true;
	Direction dir;
	GameStart gs;
	Image e_img = Toolkit.getDefaultToolkit().getImage(EmpPlane.class.getResource("/image/r1.png"));
	
	public EmpPlane(int e_x, int e_y, boolean e_live, Direction dir,
			GameStart gs)
	{
		super();
		this.e_x = e_x;
		this.e_y = e_y;
		this.e_live = e_live;
		this.dir = dir;
		this.gs = gs;
	}

	public void drawMe(Graphics g)
	{
		if (!e_live)
		{
			return;
		}
		//µÐ¾üµÄÒÆ¶¯
		e_x -= 3;
		g.drawImage(e_img, e_x, e_y, e_w, e_h, null);
	}
	
	public Rectangle empReg()
	{
		return new Rectangle(e_x, e_y, e_w, e_h);
	}
	
}
