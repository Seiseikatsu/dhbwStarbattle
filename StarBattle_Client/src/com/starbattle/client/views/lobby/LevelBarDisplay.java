package com.starbattle.client.views.lobby;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;

import com.starbattle.client.layout.CustomPaintPanelInterface;
import com.starbattle.client.layout.ViewModel;
import com.starbattle.client.resource.GUIDesign;

public class LevelBarDisplay extends ViewModel {

	private Dimension size = new Dimension(300, 30);
	private int min, max, value;
	private double barPercentFilled;
	private Font experienceFont = GUIDesign.labelFont.deriveFont(Font.ITALIC, 14f);
	private Font levelFont = GUIDesign.labelFont.deriveFont(Font.BOLD, 24f);
	private int level;

	public LevelBarDisplay() {
		initLayout();
		setExperienceMinValue(0);
		setExperienceMaxValue(100);
		setExperienceValue(0);
		setLevel(1);
	}

	public void setExperienceMaxValue(int max) {
		this.max = max;
		upateBar();
	}

	public void setExperienceMinValue(int min) {
		this.min = min;
		upateBar();
	}

	public void setExperienceValue(int value) {
		this.value = value;
		upateBar();
	}

	public void setLevel(int level) {
		this.level = level;
	}

	private void initLayout() {
		view.setPreferredSize(size);
		view.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
		setCustomPaintPanel(new BarPainter());
	}

	private void upateBar() {
		int width = max - min;
		int filled = value - min;
		barPercentFilled = (double) filled / (double) width;
		if (barPercentFilled < 0) {
			filled = 0;
		} else if (barPercentFilled > 1) {
			barPercentFilled = 1;
		}
		view.repaint();
	}

	private String getExperienceString()
	{
		return  value + " / " + max+" EXP";
	}
	
	
	private class BarPainter implements CustomPaintPanelInterface {

		@Override
		public void paint(Graphics g, Dimension size) {

			int w = size.width;
			int h = size.height;

			Graphics2D g2d = (Graphics2D) g;
			// draw bar background
			//g2d.setPaint(new GradientPaint(0, 0, new Color(140, 140, 140), 0, h, new Color(50,50,50)));
			//g2d.fillRect(0, 0, w, h);
			g.setColor(new Color(90,90,90));
			g.fillRect(0, 0, w, h);

			int fw = (int) (w * barPercentFilled);
			// draw bar
			g2d.setPaint(new GradientPaint(0, 0, new Color(20, 120, 220), w, 0, new Color(110, 190, 255)));
			g2d.fillRect(0, 0, fw, h);

			
			//draw level string
			g.setColor(new Color(200,200,200,200));
			g.setFont(experienceFont.deriveFont(Font.PLAIN,10));
			g.drawString("Level",4,h-4);
			
			// draw level
			g2d.setFont(levelFont);
			g2d.setColor(Color.BLACK);
			g2d.drawString(Integer.toString(level), 36, h-3);
			g2d.setPaint(new GradientPaint(0, 0, new Color(255,200,15),0, h,new Color(220,80,0)));
			g2d.drawString(Integer.toString(level), 36, h-4);
		
			
			// draw experience
			g.setColor(new Color(255, 255, 255, 150));
			g.setFont(experienceFont);
			FontMetrics fm = g.getFontMetrics();
			String title = getExperienceString();
			int x = w - fm.stringWidth(title) - 5;
			int y = h - 4;
			g.drawString(title, x, y);

		}

	}

}
