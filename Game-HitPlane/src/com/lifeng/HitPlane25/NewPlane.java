package com.lifeng.HitPlane25;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class NewPlane
{
	int new_x = 700;
	int new_y = 100;
	int new_w = 47;
	int new_h = 15;
	boolean e_live = true;
	GameStart gs;
	Image new_img = Toolkit.getDefaultToolkit().getImage(EmpPlane.class.getResource("/image/r11.png"));
	
	public NewPlane(int e_x, int e_y, boolean e_live, GameStart gs)
	{
		super();
		this.new_x = e_x;
		this.new_y = e_y;
		this.e_live = e_live;
		this.gs = gs;
	}

	public void drawMe(Graphics g)
	{
		if (!e_live || new_x < -50)
		{
			gs.emplist.remove(this);
			return;
		}
		//新飞军的移动
		new_x -= 3;
		g.drawImage(new_img, new_x, new_y, new_w, new_h, null);
	}
	
	public Rectangle empReg()
	{
		return new Rectangle(new_x, new_y, new_w, new_h);
	}
	
	
}
