package com.lifeng.HitPlane10.copy;

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
 * 1. ������Ϸ���������ÿ��Թر�
 * 2. �ѷɻ�����ȥ
 * 3. ʹ�ɻ������ƶ�������repaint������δʹ���߳�
 * 4. ��������ͼƬ�����������˸����ʹ���̣߳����߳��е���repaint����
 * 5. ʹ�û��弼����ʹ��Ļ������˸
 * 6. �Դ�����з�װ����ӷɻ��࣬�Է������ö��
 * 7. ����ӵ��࣬���һ���ӵ�
 * 8. ʹ�ü��ϣ����ӵ�������з�װ��ʹ�������ӵ�
 * 9. �����л��࣬ʹ�ü��ϣ�������ܵл�(�޸���һ��bug��ʹ�ҷ��ɻ���ͨ�ӵ����Ϊ600��������ֻ���䵽600��λ�ã������ӵ��������)
 * 10. ������ײ��⣬ʹ�ӵ����зɻ�ʱ�ӵ��ɻ�ͬʱ��ʧ
 * 10.copy. (1)��������ͨ�ӵ��ķ�������keyReleased������,��������keyPressed�����У�������ʹ�ɻ����ƶ��߷����ӵ���Ҳʹ�ҷ��ӵ�������ô��̬��
 * 			(2)��ǿ���ҷ����ڵ������������Ƿ�Χ����
 * 11. ���ӵо��ӵ��࣬���ӵо��ɻ������ӵ�����
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
	//�����ӵ����󼯺�
	List<Missile> mslist1 = new LinkedList<Missile>(); 
	List<Missile> mslist2 = new LinkedList<Missile>();
	//�����о��ɻ����󼯺�
	List<EmpPlane> emplist = new ArrayList<EmpPlane>();
	
	Image bg_image = Toolkit.getDefaultToolkit().getImage(
			GameStart.class.getResource("/image/02.png"));
	Image img = null; // ����˫���屳��ͼƬ

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
		//���о��ɻ�
		
			for (int i = 0; i < 10; i++)
			{
				EmpPlane emp = new EmpPlane(700, 100+40*i, true, Direction.L, this);
				emplist.add(emp);
				if (emplist.size() != 0)
				{
					emplist.get(i).drawMe(g);
				}
			}
		
		 //���ӵ�
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
		
		//˫��forѭ����ÿ���ӵ���ÿ���л�������ײ���
		for (int i = 0; i < mslist1.size(); i++)
		{
			for (int j = 0; j < emplist.size(); j++)
			{
				mslist1.get(i).hit1(emplist.get(j));
			}
		}
		
		for (int i = 0; i < mslist2.size(); i++)
		{
			for (int j = 0; j < emplist.size(); j++)
			{
				mslist2.get(i).hit2(emplist.get(j));
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

				repaint(); // ���߳��н����ػ���
			}
		}
	}

	public static void main(String[] args)
	{
		GameStart gs = new GameStart();
		gs.showFrame();
	}
}
