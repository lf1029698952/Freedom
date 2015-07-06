package com.lifeng.HitPlane12;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Boss
{
	GameStart gs;
	int boss_x;
	int boss_y;
	int boss_w = 202;
	int boss_h = 66;
	boolean boss_live;
	//BOSSµÄÉúÃüÖµ
	int boss_life = 1000;
	
	public Boss(GameStart gs, int boss_x, int boss_y, boolean boss_live)
	{
		super();
		this.gs = gs;
		this.boss_x = boss_x;
		this.boss_y = boss_y;
		this.boss_live = boss_live;
	}
	
	Image Boss_img = Toolkit.getDefaultToolkit().getImage(Boss.class.getResource("/image/boss.png"));
	
	public void drawMe(Graphics g)
	{
		move();
		g.drawImage(Boss_img, boss_x, boss_y, boss_w, boss_h, null);
	}
	
	public void move()
	{
		if (boss_x < 800)
		{
			boss_x -= 5;
		}
	}
}
