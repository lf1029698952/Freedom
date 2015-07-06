package com.lifeng.HitPlane06;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class Plane
{
	GameStart gs;  //�õ����ͻ��˳��������
	int p_x = 100;
	int p_y = 100;
	int p_w = 57;
	int p_h = 11;
	Direction dir;
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

	// �õ��ɻ���ͼƬ
	Image myplane = Toolkit.getDefaultToolkit().getImage(
			GameStart.class.getResource("/image/myPlane.png"));
	
	//�ɻ������Լ��ķ���
	public void drawMe(Graphics g)
	{
		move();  //����move����
		g.drawImage(myplane, p_x, p_y, p_w, p_h, null);
	}
	
	//��������ʱ������Ϊtrue
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
		default:
			break;
		}
	}
	
	//�����ɿ�ʱ������Ϊfalse,ʹ��ֵ��һֱΪtrue
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
}
