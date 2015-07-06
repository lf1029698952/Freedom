package com.lifeng.HitPlane22;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class BackGround
{
	int bg_x;
	int bg_y;
	int bg_w;
	int bg_h = 600;
	GameStart gs;
	
	//得到背景图片
	Image bg_image = Toolkit.getDefaultToolkit().getImage(
			GameStart.class.getResource("/image/02.png"));
	
	public BackGround(int bg_x, int bg_y, int bg_w, GameStart gs)
	{
		super();
		this.bg_x = bg_x;
		this.bg_y = bg_y;
		this.bg_w = bg_w;
		this.gs = gs;
	}
	
	public void drawMe(Graphics g)
	{
		move();
		g.drawImage(bg_image, bg_x, bg_y, bg_w, bg_h, null);
	}
	
	//背景的移动方法
	public void move()
	{
		if (bg_x <= -800)
		{
			bg_x = 0;
		}
		bg_x--;
	}
}
