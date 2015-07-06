package com.lifeng.HitPlane24;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;



public class Explode
{
	int e_x;
	int e_y;
	boolean e_live;
	GameStart gs;
	int index = 0; //Í¼Æ¬ÏÂ±ê
	int time = 0;
	
	Toolkit tk = Toolkit.getDefaultToolkit();
	Image e_images[] = {
			tk.getImage(GameStart.class.getResource("/image/huicheng4.png")),
			tk.getImage(GameStart.class.getResource("/image/huicheng3.png")),
			tk.getImage(GameStart.class.getResource("/image/huicheng2.png")),
			tk.getImage(GameStart.class.getResource("/image/huicheng1.png")), 
			};
	
	public Explode(int e_x, int e_y, boolean e_live, GameStart gs)
	{
		super();
		this.e_x = e_x;
		this.e_y = e_y;
		this.e_live = e_live;
		this.gs = gs;
	}
	
	public void draw(Graphics g)
	{
		if (!e_live)
		{
			return;
		}
		if (index == e_images.length+1)
		{
			index = 0;
		}
		
		g.drawImage(e_images[index], e_x, e_y, null);
		index++;
	}
}
