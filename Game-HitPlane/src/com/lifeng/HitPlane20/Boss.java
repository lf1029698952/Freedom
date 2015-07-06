package com.lifeng.HitPlane20;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

public class Boss
{
	GameStart gs;
	int boss_x;
	int boss_y;
	int boss_w = 202;
	int boss_h = 66;
	boolean boss_live;
	//BOSS的生命值
	int boss_life = 10000;
	boolean boss_move = true;
	Random random = new Random();
	
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
		if (!boss_live)
		{
			gs.game_time = 3;
			return;
		}
		move();
		g.drawImage(Boss_img, boss_x, boss_y, boss_w, boss_h, null);
	
	}
	
	//BOSS的移动
	public void move()
	{
			if (boss_move)
			{
				boss_x -= 3;
				if (boss_x == 30)
				{
					boss_y = random.nextInt(570) + 5;
					boss_move = false;
				}
			}
			
			else
			{
				boss_x += 3;
				if (boss_x == 750)
				{
					boss_y = random.nextInt(570) + 5;
					boss_move = true;
				}
			
		}
	}
	
	//得到BOSS的矩形
	public Rectangle bossReg()
	{
		return new Rectangle(boss_x, boss_y, boss_w, boss_h);
	}
	
	public void fire()
	{
		if (random.nextInt(200) % 10 == 0)
		{
			BossMissile bm = new BossMissile(boss_x - 30, boss_y + 40, true, gs);
			gs.bmlist.add(bm);
		}
	}
	
	public boolean hitPlane(Plane plane)
	{
		for (int i = 0; i < gs.bmlist.size(); i++)
		{
			BossMissile bm = gs.bmlist.get(i);
			if (this.boss_live && plane.p_live && bm.bmsReg().intersects(plane.planeReg()))
			{
				bm.b_live = false;
				plane.p_life -= 40;
				if (plane.p_life <= 0)
				{
					plane.p_live = false;
				}
			}
			
			return true;
		}
		
		return false;
	}
}
