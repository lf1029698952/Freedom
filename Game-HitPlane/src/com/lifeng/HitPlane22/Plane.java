package com.lifeng.HitPlane22;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class Plane
{
	GameStart gs;  //拿到主客户端程序的引用
	int p_x = 100;
	int p_y = 100;
	int p_w = 57;
	int p_h = 11;
	Direction dir;
	int p_life = 2000;
	boolean p_live = true;
	boolean bU, bD, bL, bR;
	
	public Plane(GameStart gs, int p_x, int p_y, Direction dir, boolean p_live)
	{
		super();
		this.gs = gs;
		this.p_x = p_x;
		this.p_y = p_y;
		this.dir = dir;
		this.p_live = p_live;
	}

	// 得到飞机的图片
	Image myplane = Toolkit.getDefaultToolkit().getImage(
			GameStart.class.getResource("/image/myPlane.png"));
	
	//飞机画出自己的方法
	public void drawMe(Graphics g)
	{
		if (p_x < 0)
		{
			p_x = 0;
		}
		if (p_x > 743)
		{
			p_x = 743;
		}
		if (p_y < 40)
		{
			p_y = 40;
		}
		if (p_y > 585)
		{
			p_y = 585;
		}
		
		if (!p_live)
		{
			gs.game_time = 2;
			return;
		}
		move();  //调用move方法
		g.drawImage(myplane, p_x, p_y, p_w, p_h, null);
	}
	
	//当键按下时，设置为true
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();

		switch (key)
		{
		case KeyEvent.VK_W:
			bU = true;
			break;
		case KeyEvent.VK_S:
			bD = true;
			break;
		case KeyEvent.VK_A:
			bL = true;
			break;
		case KeyEvent.VK_D:
			bR = true;
			break;
		case KeyEvent.VK_F2:
			gs.myPlane.p_life = 2000;
			gs.myPlane.p_live = true;
			break;
		case KeyEvent.VK_ENTER:
			gs.game_time = 1;
			break;
		default:
			break;
		}
	}
	
	//当键松开时，设置为false,使其值不一直为true
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		switch (key)
		{
		case KeyEvent.VK_W:
			bU = false;
			break;
		case KeyEvent.VK_S:
			bD = false;
			break;
		case KeyEvent.VK_A:
			bL = false;
			break;
		case KeyEvent.VK_D:
			bR = false;
			break;
		case KeyEvent.VK_J:
			fire();
			break;
		case KeyEvent.VK_Z:
			bigfire();
			break;
		default:
			break;
		}
	}
	
	public void setDir()
	{
		if (bU && !bD && !bL && !bR) {
			dir = Direction.U;
		}
		if (!bU && bD && !bL && !bR) {
			dir = Direction.D;
		}
		if (!bU && !bD && bL && !bR) {
			dir = Direction.L;
		}
		if (!bU && !bD && !bL && bR) {
			dir = Direction.R;
		}
		if (!bU && !bD && !bL && !bR) {
			dir = Direction.STOP;
		}
	}
	
	public void move()
	{
		setDir();
		
		switch (dir)
		{
		case U:
			p_y -= 7;
			break;
		case D:
			p_y += 7;
			break;
		case L:
			p_x -= 7;
			break;
		case R:
			p_x += 7;
		default:
			break;
		}
	}
	
	public void fire()
	{
		//创建子弹类的对象
		Missile ms1 = new Missile(gs, p_x+20, p_y+5, true);
		//添加到游戏启动类里
		gs.mslist1.add(ms1);
	}
	public void bigfire()
	{
		//创建子弹类的对象
		Missile ms2 = new Missile(gs, p_x+20, p_y-10, true);
		//添加到游戏启动类里
		gs.mslist2.add(ms2);
	}
	
	//得到飞机的矩形
	public Rectangle planeReg()
	{
		return new Rectangle(p_x, p_y, p_w, p_h);
	}
	
	public boolean collide(EmpPlane emp)
	{
		for (int i = 0; i < gs.emplist.size(); i++)
		{
			if (this.p_live && emp.e_live && this.planeReg().intersects(emp.empReg()))
			{
				this.p_life -= 80;
				if (this.p_life <= 0)
				{
					this.p_live = false;
				}
				emp.e_live = false;
			}
			
			return true;
		}
		
		return false;
	}
}