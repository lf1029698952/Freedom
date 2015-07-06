package com.lifeng.HitPlane02;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 1. 绘制游戏窗口且设置可以关闭
 * 2. 把飞机画上去
 * @author LiFeng
 *
 */
public class GameStart extends Frame
{
	public void showFrame()
	{
		this.setSize(800, 600);
		this.setLocation(250, 150);
		this.setTitle("HitPlane");
		this.setResizable(false);
		this.addWindowListener(new WindowMe());
		this.setVisible(true);
	}
	int p_x = 100;
	int p_y = 100;
	int p_w = 57;
	int p_h = 11;
	
	//得到飞机的图片
	Image myplane = Toolkit.getDefaultToolkit().getImage(GameStart.class.getResource("/image/myPlane.png"));
	
	@Override
	public void paint(Graphics g)
	{
		g.drawImage(myplane, p_x, p_y, p_w, p_h, null);
	}

	class WindowMe extends WindowAdapter
	{
		@Override
		public void windowClosing(WindowEvent arg0)
		{
			System.exit(0);
		}
	}
	
	public static void main(String[] args)
	{
		GameStart gs = new GameStart();
		gs.showFrame();
	}
}
