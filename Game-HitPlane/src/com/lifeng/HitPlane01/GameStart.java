package com.lifeng.HitPlane01;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 1. 绘制游戏窗口且设置可以关闭
 * @author LiFeng
 *
 */
public class GameStart extends Frame
{
	public void showFrame()
	{
		this.setSize(800, 600);
		this.setLocation(200, 200);
		this.setTitle("HitPlane");
		this.setResizable(false);
		this.addWindowListener(new WindowMe());
		this.setVisible(true);
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
