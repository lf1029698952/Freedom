package com.lifeng.HitPlane14;

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
	//BOSS������ֵ
	int boss_life = 1000;
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
			return;
		}
		move();
		g.drawImage(Boss_img, boss_x, boss_y, boss_w, boss_h, null);
	}
	
	//BOSS���ƶ�
	public void move()
	{
		if (boss_move)
		{
			if (boss_move)
			{
				boss_x -= 3;
				if (boss_x == 30)
				{
					boss_y = random.nextInt(570) + 5;
					boss_move = false;
				}
			} else
			{
				boss_x += 3;
				if (boss_x == 750)
				{
					boss_y = random.nextInt(570) + 5;
					boss_move = true;
				}
			}
		}
	}
	
	//�õ�BOSS�ľ���
	public Rectangle bossReg()
	{
		return new Rectangle(boss_x, boss_y, boss_w, boss_h);
	}
	
}
