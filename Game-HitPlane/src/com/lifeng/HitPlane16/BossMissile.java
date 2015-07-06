package com.lifeng.HitPlane16;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class BossMissile
{
	int b_x;
	int b_y;
	boolean b_live;
	GameStart gs;
	int width = 50;
	int height = 10;
	
	Image b_img = Toolkit.getDefaultToolkit().getImage(BossMissile.class.getResource("/image/missile3.png"));

	public BossMissile(int b_x, int b_y, boolean b_live, GameStart gs)
	{
		super();
		this.b_x = b_x;
		this.b_y = b_y;
		this.b_live = b_live;
		this.gs = gs;
	}
	
	public void drawMe(Graphics g)
	{
		if (!b_live)
		{
			return;
		}
		move();
		g.drawImage(b_img, b_x, b_y, width, height, null);
	}
	
	public void move()
	{
		b_x -= 8;
	}
}
