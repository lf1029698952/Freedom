package com.lifeng.HitPlane26;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
 * 11. �޸Ĵ��ж�ܷɻ��ķ�����ʹ������Ż����������������ֵķɻ�
 * 12. ����BOSS�࣬����BOSS�ɻ�
 * 13. ��������BOSS�ķ������޸�BOSS���ƶ�,�޸�BOSS���ַ�ʽ������N��С�ɻ�ʱ��BOSS����
 * 14. ���������ӵ�ģ�ͣ��޸Ĵ��ڵ�Ϊ��ɱ��������BOSS��Ч
 * 15. �޸�BOSS���𷽷���BOSS����ֱ�ӱ���ɱ������Ѫ���࣬����BOSSѪ��,����������ʾ
 * 16. ����BOSS�ӵ��࣬����BOSS�����ӵ�������BOSS�ӵ��ɴ����ҷ��ɻ��������ҷ��ɻ���������
 * 17. ���Ӽ����е�remove��������ʹ��ʡ�ڴ�
 * 18. ���ӱ����࣬���ñ������ƶ�
 * 19. ������Ϸ�Ŀ�ʼ�ͽ�������
 * 20. �����ҷ��ɻ�F2��Ѫ�������BOSS�Ѷ�
 * 21. ���ô��ڽ��棬ʹ�ҷ��ɻ����ܷɳ����ӵ���з��ɻ��ɳ���remove�����ӷɻ���ײ������
 * 22. ���ӱ�ը�࣬�����ӵ���ըЧ��
 * 23. ���ӵо��ӵ��࣬���ӵз�С�ɻ������ӵ�����
 * 24. ����һ�ַɻ�������ɻ����ᷢ���ӵ����Ե��˷ɻ���ɻ�Ѫ,���Ƹ����·ɻ�����ײ��ⷽ��,����Ϸ�Ѷȼ�ƽ���Խ��е���
 * 25. �����������֣�����ɱ������ʹ�ã�F1��ʾ��Ϸ������
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
		this.setBackground(Color.BLUE);
		this.setResizable(false);
		this.addWindowListener(new WindowMo());
		this.addKeyListener(new WindowKeyMo());

		mt.start();

		this.setVisible(true);
	}

	MyThread mt = new MyThread();
	int bg_x = 0;
	int bg_y = 0;
	int bg_w = 800;
	int bg_h = 600;
	int e_x = 790;
	int boss_x = 750;
	int boss_y = 300;
	int game_time = 0; // ������Ϸ��ʼ�����ı�־����
	Random random = new Random();
	int time = 1000;
	static int num = 0; // �������ͳ������о�����
	int nums = 0;
	int times = 0;
	int seconds = 0;
	Explode ex = null;
	int score = 0;
	int scores = 0;
	String help0 = "��Ϸ���� ";
	String help1 = "1��WASD�������������ҷ����ƶ���J��Ϊ�����ӵ���";
	String help2 = "2�����30����ɫС�ɻ�����BOSS���Ե���ɫС�ɻ��ɻ�Ѫ��";
	String help3 = "3��Z��Ϊ��ɱ����������100����ֵ������BOSS��Ч��";
	String help4 = "��Ȩ���У����";

	BackGround bg = new BackGround(bg_x, bg_y, 1600, this);
	Toolkit tk = Toolkit.getDefaultToolkit();
	Image img_1 = tk.getImage(GameStart.class
			.getResource("/image/gamestart.jpg"));
	Image img_2 = tk.getImage(GameStart.class
			.getResource("/image/gameover.jpg"));
	Image img_3 = tk.getImage(GameStart.class.getResource("/image/win.jpg"));
	Image img_4 = tk.getImage(GameStart.class.getResource("/image/help.jpg"));

	Image img = null; // ����˫���屳��ͼƬ

	Plane myPlane = new Plane(this, 100, 100, Direction.STOP, true);
	// �����ӵ����󼯺�
	List<Missile> mslist1 = new LinkedList<Missile>();
	List<Missile> mslist2 = new LinkedList<Missile>();
	// �����о��ɻ����󼯺�
	List<EmpPlane> emplist = new ArrayList<EmpPlane>();
	// ����BOSS����
	Boss boss = new Boss(this, boss_x, boss_y, true);
	// ����BOSS�ӵ�����
	List<BossMissile> bmlist = new ArrayList<BossMissile>();
	// �����о��ӵ�����
	List<EmpMissile> emslist = new LinkedList<EmpMissile>();
	//�����·ɻ��༯��
	List<NewPlane> newlist = new ArrayList<NewPlane>();
	// ����Ѫ������
	Blood bldBoss = new Blood(550, 60, this);
	Blood bldMe = new Blood(30, 60, this);
	
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
		switch (game_time)
		{
		case 0:
			g.drawImage(img_1, 0, 0, 800, 600, null);
			break;
		case 1:
			bg.drawMe(g);
			g.drawString("�÷֣�" + scores, 30, 90);
			g.drawString("��ǰ����ֵ��" + myPlane.p_life, 30, 105);
			g.drawString("��ǰ����ֵ��" + score, 30, 120);
			g.drawString("��Ϸʱ�䣺" + seconds + "��", 350, 80);
			myPlane.drawMe(g);
			bldMe.drawMe(g);

			// ���о��ɻ�
			for (int i = 0; i < emplist.size(); i++)
			{
				EmpPlane emp = emplist.get(i);
				emplist.get(i).fire();
				emplist.get(i).drawMe(g);
				myPlane.collide(emp);
			}
			
			//���·ɻ�
			for (int i = 0; i < newlist.size(); i++)
			{
				NewPlane newplane = newlist.get(i);
				newlist.get(i).drawMe(g);
				myPlane.newcollide(newplane);
			}
			
			for (int i = 0; i < emslist.size(); i++)
			{
				EmpMissile ems = emslist.get(i);
				ems.drawMe(g);
				if (ems.hitPlane(myPlane))
				{
					ex = new Explode(myPlane.p_x, myPlane.p_y, true, this);
					ex.draw(g);
				}
			}

			// ������30��С�ɻ�ʱ������BOSS
			if (num >= 30)
			{
				boss.drawMe(g);
				// ����BOSSѪ��
				bldBoss.drawBoss(g);
				boss.fire();
			}

			// ��BOSS��ɻ�
			for (int i = 0; i < bmlist.size(); i++)
			{
				BossMissile bm = bmlist.get(i);
				bm.drawMe(g);
				if (boss.hitPlane(myPlane))
				{
					ex = new Explode(myPlane.p_x, myPlane.p_y, true, this);
					ex.draw(g);
				}
			}
			// ���ӵ�
			if (mslist1.size() != 0)
			{
				for (int i = 0; i < mslist1.size(); i++)
				{
					mslist1.get(i).drawMe1(g);
				}
			}

			if (mslist2.size() != 0)
			{
				for (int i = 0; i < mslist2.size(); i++)
				{
					mslist2.get(i).drawMe2(g);
					mslist2.get(i).clearMissile(emslist);
					mslist2.get(i).clearPlane(newlist);
				}
			}

			// ���ô��ж�ܷɻ��ķ���
			for (int i = 0; i < mslist1.size(); i++)
			{
				Missile ms = mslist1.get(i);
				ms.drawMe1(g);

				if (ms.hit1Emplist(emplist))
				{
					num++;
					ex = new Explode(mslist1.get(i).m1_x, mslist1.get(i).m1_y,
							true, this);
					ex.draw(g);
				}
				if (ms.hit1newlist(newlist))
				{
					num++;
					ex = new Explode(mslist1.get(i).m1_x, mslist1.get(i).m1_y,
							true, this);
					ex.draw(g);
				}
				if (ms.hitBoss(boss))
				{
					nums++;
					ex = new Explode(mslist1.get(i).m1_x + 40,
							mslist1.get(i).m1_y, true, this);
					ex.draw(g);
				}
			}
			for (int i = 0; i < mslist2.size(); i++)
			{
				Missile ms = mslist2.get(i);
				ms.drawMe2(g);

				if (ms.hit2Emplist(emplist))
				{
					num++;
				}
			}
			
			score = num * 10 + nums * 5 - Plane.num * 100;
			scores = num * 40 + nums * 25;
			
			/*
			 * //˫��forѭ����ÿ���ӵ���ÿ���л�������ײ��⣬���ڴ�ܴ� for (int i = 0; i <
			 * mslist1.size(); i++) { for (int j = 0; j < emplist.size(); j++) {
			 * mslist1.get(i).hit1(emplist.get(j)); } }
			 * 
			 * for (int i = 0; i < mslist2.size(); i++) { for (int j = 0; j <
			 * emplist.size(); j++) { mslist2.get(i).hit2(emplist.get(j)); } }
			 */
			break;
		case 2:
			g.drawImage(img_2, 0, 0, 800, 600, null);
			g.drawString("��Ϸ�������밴F2����Enter�����������", 250, 350);
			break;
		case 3:
			g.drawImage(img_3, 0, 0, 800, 600, null);
			g.drawString("ʤ�������ĵ÷�Ϊ��" + scores, 120, 170);
			g.drawString("����ͨ��ʱ��Ϊ��" + seconds + "��", 120, 185);
			break;
		case 4:
			g.setColor(Color.BLACK);
			g.drawImage(img_4, 0, 0, 800, 600, null);
			g.drawString(help0, 200, 200);
			g.drawString(help1, 100, 250);
			g.drawString(help2, 100, 300);
			g.drawString(help3, 100, 350);
			g.drawString(help4, 400, 400);
		default:
			break;
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

	public void getEmp()
	{
		if (game_time == 1)
		{
			if (random.nextInt(1000) % 30 == 0)
			{
				EmpPlane emp = new EmpPlane(e_x, random.nextInt(570) + 20, true,
						Direction.L, 300, this);
				emplist.add(emp);
			}
			
			if (random.nextInt(1000) % 60 == 0)
			{
				NewPlane newplane = new NewPlane(e_x, random.nextInt(570) + 20, true, this);
				newlist.add(newplane);
			}
		}
	}

	class WindowMo extends WindowAdapter
	{
		@Override
		public void windowClosing(WindowEvent arg0)
		{
			System.exit(0);
		}
	}

	public class MyThread extends Thread
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
				getEmp(); // ���߳��е��õõ��о��ɻ��ķ���
				if (game_time == 1)
				{
					times++;
					seconds = (times*30)/1000;
				}
			}
		}
	}

	public static void main(String[] args)
	{
		GameStart gs = new GameStart();
		gs.showFrame();
	}
}
