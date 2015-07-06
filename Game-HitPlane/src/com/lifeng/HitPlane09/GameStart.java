package com.lifeng.HitPlane09;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 1. 绘制游戏窗口且设置可以关闭
 * 2. 把飞机画上去
 * 3. 使飞机可以移动，调用repaint方法，未使用线程
 * 4. 画出背景图片，但会出现闪烁现象，使用线程，在线程中调用repaint方法
 * 5. 使用缓冲技术，使屏幕不再闪烁
 * 6. 对代码进行封装，添加飞机类，对方向进行枚举
 * 7. 添加子弹类，打出一颗子弹
 * 8. 使用集合，对子弹对象进行封装，使打出多颗子弹
 * 9. 创建敌机类，使用集合，画出多架敌机(修复了一个bug，使我方飞机普通子弹射程为600，而不是只能射到600的位置，超级子弹射程无限)
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

	int bg_x = 0;
	int bg_y = 0;
	int bg_w = 800;
	int bg_h = 600;
	
	Plane myPlane = new Plane(this, 100, 100, Direction.STOP, true);
	//创建子弹对象集合
	List<Missile> mslist1 = new LinkedList<Missile>(); 
	List<Missile> mslist2 = new LinkedList<Missile>();
	//创建敌军飞机对象集合
	List<EmpPlane> emplist = new ArrayList<EmpPlane>();
	
	Image bg_image = Toolkit.getDefaultToolkit().getImage(
			GameStart.class.getResource("/image/02.png"));
	Image img = null; // 创建双缓冲背景图片

	class WindowKeyMo extends KeyAdapter
	{
		@Override
		public void keyPressed(KeyEvent e)
		{
			myPlane.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e)
		{
			myPlane.keyReleased(e);
		}
	}

	@Override
	public void paint(Graphics g)
	{
		g.drawImage(bg_image, bg_x, bg_y, bg_w, bg_h, null);
		myPlane.drawMe(g);
		//画敌军飞机
		
			for (int i = 0; i < 10; i++)
			{
				EmpPlane emp = new EmpPlane(700, 100+40*i, true, Direction.L, this);
				emplist.add(emp);
				if (emplist.size() != 0)
				{
					emplist.get(i).drawMe(g);
				}
			}
		
		 //画子弹
		if (mslist1.size()!= 0)
		{
			for (int i = 0; i < mslist1.size(); i++) {
				mslist1.get(i).drawMe1(g);
			}
		}
		if (mslist2.size()!= 0)
		{
			for (int i = 0; i < mslist2.size(); i++) {
				mslist2.get(i).drawMe2(g);
			}
		}
	}

	@Override
	public void update(Graphics g)
	{
		if (null == img)
		{
			img = this.createImage(bg_w, bg_h);
		}
		Graphics gh = img.getGraphics();
		gh.setColor(Color.WHITE);
		gh.fillRect(bg_x, bg_y, bg_w, bg_y);
		paint(gh);
		
		g.drawImage(img, bg_x, bg_y, bg_w, bg_h, null);
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
		public void run()
		{
			super.run();

			while (true)
			{
				try
				{
					Thread.sleep(30);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}

				repaint(); // 在线程中进行重画。
			}
		}
	}

	public static void main(String[] args)
	{
		GameStart gs = new GameStart();
		gs.showFrame();
	}
}
