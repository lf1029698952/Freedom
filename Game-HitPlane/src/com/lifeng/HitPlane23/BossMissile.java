package com.lifeng.HitPlane23;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
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
		if (!b_live || this.b_x < 0)
		{
			gs.bmlist.remove(this);
			return;
		}
		move();
		g.drawImage(b_img, b_x, b_y, width, height, null);
	}
	
	public void move()
	{
		b_x -= 8;
	}
	
	//BOSS×Óµ¯µÄ¾ØÐÎ
	public Rectangle bmsReg()
	{
		return new Rectangle(b_x, b_y, width, height);
	}
}
