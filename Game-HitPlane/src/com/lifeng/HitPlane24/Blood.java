package com.lifeng.HitPlane24;

import java.awt.Color;
import java.awt.Graphics;

public class Blood
{
	int b_x;// Ѫ����X����
	int b_y;// Ѫ����Y����
	GameStart gs;// ����̨������
	
	public Blood(int b_x, int b_y, GameStart gs)
	{
		super();
		this.b_x = b_x;
		this.b_y = b_y;
		this.gs = gs;
	}
	
	//����Ѫ��
	public void drawBoss(Graphics g)
	{
		if (!gs.boss.boss_live)
		{
			return;
		}
		g.setColor(Color.RED);
		g.drawRect(b_x, b_y, 200, 10);
		g.fillRect(b_x, b_y, gs.boss.boss_life/50, 10);
	}
	
	public void drawMe(Graphics g)
	{
		if (!gs.myPlane.p_live)
		{
			return;
		}
		g.setColor(Color.RED);
		g.draw3DRect(b_x, b_y, 200, 10, false);
		g.fill3DRect(b_x, b_y, gs.myPlane.p_life/10, 10, true);
	}
}
