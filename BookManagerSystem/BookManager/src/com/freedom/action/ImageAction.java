package com.freedom.action;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ImageAction extends ActionSupport{
	
	public String validateCode() {
		
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		BufferedImage image = new BufferedImage(100, 20, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(new Color(200, 200, 200));
		g.fillRect(0, 0, 100, 20);
		
		String value = "";
		for (int i = 0; i < 4; i++) {
			g.setColor(new Color(getRandom(1, 100), getRandom(1, 100), getRandom(1, 100)));
			String random = String.valueOf((char)getRandom(65, 90));
			g.drawString(random, 25*i+7, 15);
			g.drawString(random, 25*i+8, 15);
			g.drawString(random, 25*i+9, 15);
			value+=random;
		}
		
		for (int i = 0; i < 40; i++) {
			g.setColor(new Color(getRandom(150, 200), getRandom(150, 200), getRandom(150, 200)));
			g.fillOval(getRandom(0, 100), getRandom(0, 100), 2, 2);
		}
		
		for (int i = 0; i < 10; i++) {
			g.setColor(new Color(getRandom(150, 200), getRandom(150, 200), getRandom(150, 200)));
			g.drawLine(getRandom(0, 100), getRandom(0, 20), getRandom(0, 100), getRandom(0, 20));
		}
		
		req.getSession().setAttribute("code", value);
		
		try {
			ImageIO.write(image, "JPEG", resp.getOutputStream());
			resp.getOutputStream().flush();
			resp.getOutputStream().close();
			resp.flushBuffer();
			resp.getWriter().flush();
			resp.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "success";
	}
	
	public int getRandom(int min, int max)
	{
		int random = (int)(Math.random()*(max-min+1)+min);
		
		return random;
	}
}
