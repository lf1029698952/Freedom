package com.lifeng.HitPlane04;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 1. 绘制游戏窗口且设置可以关闭
 * 2. 把飞机画上去
 * 3. 使飞机可以移动，调用repaint方法，未使用线程
 * 4. 画出背景图片，但会出现闪烁现象，使用线程，在线程中调用repaint方法
 * @author Administrator
 *
 */
public class GameStart extends Frame
{
	public void showFrame()
	{
		this.setSize(800, 600);
		this.setLocation(250, 150);
		this.setTitle("HitPlane");
		this.setBackground(Color.BLUE);
		this.setResizable(false);
		this.addWindowListener(new WindowMo());
		this.addKeyListener(new WindowKeyMo());
		
		MyThread mt = new MyThread();
		mt.start();
		
		this.setVisible(true);
	}
	
	int p_x = 100;
	int p_y = 100;
	int p_w = 57;
	int p_h = 11;
	
	int bg_x = 0;
	int bg_y = 0;
	int bg_w = 800;
	int bg_h = 600;
	
	//得到飞机的图片
	Image myplane = Toolkit.getDefaultToolkit().getImage(GameStart.class.getResource("/image/myPlane.png"));
	Image bg_image = Toolkit.getDefaultToolkit().getImage(GameStart.class.getResource("/image/02.png"));
	
	class WindowKeyMo extends KeyAdapter
	{
		@Override
		public void keyPressed(KeyEvent e)
		{
			int key = e.getKeyCode();
			
			switch (key)
			{
				case KeyEvent.VK_W:
					p_y -= 7;
					break;
				case KeyEvent.VK_S:
					p_y += 7;
					break;
				case KeyEvent.VK_A:
					p_x -= 7;
					break;
				case KeyEvent.VK_D:
					p_x += 7;
					break;
				default:
					break;
			}
		}
		
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.drawImage(bg_image, bg_x, bg_y, bg_w, bg_h, null);
		g.drawImage(myplane, p_x, p_y, p_w, p_h, null);
	}

	class WindowMo extends WindowAdapter
	{
		@Override
		public void windowClosing(WindowEvent arg0)
		{
			System.exit(0);
		}
	}
	
	private class MyThread extends Thread
	{

		@Override
		public void run() {
			super.run();
			
			while (true)
			{
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				repaint(); //在线程中进行重画。
			}
		}
		
	}
	
	public static void main(String[] args)
	{
		GameStart gs = new GameStart();
		gs.showFrame();
	}
}
