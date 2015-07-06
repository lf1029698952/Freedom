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
 * 1. 绘制游戏窗口且设置可以关闭
 * 2. 把飞机画上去
 * 3. 使飞机可以移动，调用repaint方法，未使用线程
 * 4. 画出背景图片，但会出现闪烁现象，使用线程，在线程中调用repaint方法
 * 5. 使用缓冲技术，使屏幕不再闪烁
 * 6. 对代码进行封装，添加飞机类，对方向进行枚举
 * 7. 添加子弹类，打出一颗子弹
 * 8. 使用集合，对子弹对象进行封装，使打出多颗子弹
 * 9. 创建敌机类，使用集合，画出多架敌机(修复了一个bug，使我方飞机普通子弹射程为600，而不是只能射到600的位置，超级子弹射程无限)
 * 10. 设置碰撞检测，使子弹打中飞机时子弹飞机同时消失
 * 10.copy. (1)将发射普通子弹的方法放在keyReleased方法中,而不是在keyPressed方法中，这样可使飞机边移动边发射子弹，也使我方子弹不再那么变态。
 * 			(2)增强了我方大炮弹的威力，覆盖范围更广
 * 11. 修改打中多架飞机的方法，使代码更优化，画出多架随机出现的飞机
 * 12. 增加BOSS类，画出BOSS飞机
 * 13. 增加消灭BOSS的方法，修改BOSS的移动,修改BOSS出现方式，消灭N个小飞机时，BOSS出现
 * 14. 重新制作子弹模型，修改大炮弹为必杀技，但对BOSS无效
 * 15. 修改BOSS消灭方法，BOSS不能直接被秒杀，增加血条类，增加BOSS血条,增加文字显示
 * 16. 增加BOSS子弹类，增加BOSS发射子弹方法，BOSS子弹可打中我方飞机，增加我方飞机死亡方法
 * 17. 增加集合中的remove方法，以使节省内存
 * 18. 增加背景类，设置背景的移动
 * 19. 设置游戏的开始和结束界面
 * 20. 设置我方飞机F2满血复活，增大BOSS难度
 * 21. 设置窗口界面，使我方飞机不能飞出，子弹或敌方飞机飞出后remove，增加飞机相撞处理方法
 * 22. 增加爆炸类，增加子弹爆炸效果
 * 23. 增加敌军子弹类，增加敌方小飞机发射子弹方法
 * 24. 新增一种飞机，此类飞机不会发射子弹，吃掉此飞机后可回血,完善该类新飞机的碰撞检测方法,对游戏难度及平衡性进行调整
 * 25. 增加能量积分，供必杀技发射使用，F1显示游戏帮助。
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
	int game_time = 0; // 设置游戏开始结束的标志变量
	Random random = new Random();
	int time = 1000;
	static int num = 0; // 定义变量统计消灭敌军数量
	int nums = 0;
	int times = 0;
	int seconds = 0;
	Explode ex = null;
	int score = 0;
	int scores = 0;
	String help0 = "游戏帮助 ";
	String help1 = "1、WASD键控制上下左右方向移动，J键为发射子弹。";
	String help2 = "2、打掉30个白色小飞机出现BOSS，吃掉红色小飞机可回血。";
	String help3 = "3、Z键为必杀技，需消耗100能量值，但对BOSS无效。";
	String help4 = "版权所有：李峰";

	BackGround bg = new BackGround(bg_x, bg_y, 1600, this);
	Toolkit tk = Toolkit.getDefaultToolkit();
	Image img_1 = tk.getImage(GameStart.class
			.getResource("/image/gamestart.jpg"));
	Image img_2 = tk.getImage(GameStart.class
			.getResource("/image/gameover.jpg"));
	Image img_3 = tk.getImage(GameStart.class.getResource("/image/win.jpg"));
	Image img_4 = tk.getImage(GameStart.class.getResource("/image/help.jpg"));

	Image img = null; // 创建双缓冲背景图片

	Plane myPlane = new Plane(this, 100, 100, Direction.STOP, true);
	// 创建子弹对象集合
	List<Missile> mslist1 = new LinkedList<Missile>();
	List<Missile> mslist2 = new LinkedList<Missile>();
	// 创建敌军飞机对象集合
	List<EmpPlane> emplist = new ArrayList<EmpPlane>();
	// 创建BOSS对象
	Boss boss = new Boss(this, boss_x, boss_y, true);
	// 创建BOSS子弹集合
	List<BossMissile> bmlist = new ArrayList<BossMissile>();
	// 创建敌军子弹集合
	List<EmpMissile> emslist = new LinkedList<EmpMissile>();
	//创建新飞机类集合
	List<NewPlane> newlist = new ArrayList<NewPlane>();
	// 创建血条对象
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
			g.drawString("得分：" + scores, 30, 90);
			g.drawString("当前生命值：" + myPlane.p_life, 30, 105);
			g.drawString("当前能量值：" + score, 30, 120);
			g.drawString("游戏时间：" + seconds + "秒", 350, 80);
			myPlane.drawMe(g);
			bldMe.drawMe(g);

			// 画敌军飞机
			for (int i = 0; i < emplist.size(); i++)
			{
				EmpPlane emp = emplist.get(i);
				emplist.get(i).fire();
				emplist.get(i).drawMe(g);
				myPlane.collide(emp);
			}
			
			//画新飞机
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

			// 当消灭30个小飞机时，画出BOSS
			if (num >= 30)
			{
				boss.drawMe(g);
				// 画出BOSS血条
				bldBoss.drawBoss(g);
				boss.fire();
			}

			// 画BOSS打飞机
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
			// 画子弹
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

			// 调用打中多架飞机的方法
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
			 * //双重for循环对每颗子弹对每个敌机进行碰撞检测，耗内存很大 for (int i = 0; i <
			 * mslist1.size(); i++) { for (int j = 0; j < emplist.size(); j++) {
			 * mslist1.get(i).hit1(emplist.get(j)); } }
			 * 
			 * for (int i = 0; i < mslist2.size(); i++) { for (int j = 0; j <
			 * emplist.size(); j++) { mslist2.get(i).hit2(emplist.get(j)); } }
			 */
			break;
		case 2:
			g.drawImage(img_2, 0, 0, 800, 600, null);
			g.drawString("游戏结束，请按F2键及Enter键复活继续。", 250, 350);
			break;
		case 3:
			g.drawImage(img_3, 0, 0, 800, 600, null);
			g.drawString("胜利！您的得分为：" + scores, 120, 170);
			g.drawString("您的通关时间为：" + seconds + "秒", 120, 185);
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

				repaint(); // 在线程中进行重画。
				getEmp(); // 在线程中调用得到敌军飞机的方法
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
