package com.wings2d.demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class MyButton extends JButton{
	
	private float fontSize;
	
	public MyButton(final float fontSize) {
		super();
		this.fontSize = fontSize;
		this.setBackground(Color.LIGHT_GRAY);
		InputStream is = MyButton.class.getResourceAsStream("FrancoisOne-Regular.ttf");
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, is);
			this.setFont(font);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		this.setFocusable(false);
//		this.setBorder(new CompoundBorder(new EmptyBorder(3, 3, 4, 4), new LineBorder(Color.BLACK, 7)));
//		this.setBorder(new LineBorder(Color.BLACK, 7));
//		this.setVisible(false);
	}
	
	public float getFontSize() {
		return fontSize;
	}

	@Override
    protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);

//		g.fillRect(0, 0, this.getWidth(), this.getHeight());
//		
//		g.setColor(Color.BLACK);
//		
//		int strHeight = g.getFontMetrics(this.getFont()).getHeight();
//		int strWidth = g.getFontMetrics(this.getFont()).stringWidth(this.getText());
//		int offsetY = ((this.getHeight() - strHeight) / 2) + g.getFontMetrics(getFont()).getAscent();
//		int offsetX = (this.getWidth() - strWidth) / 2;
//		g.drawString(this.getText(), offsetX, offsetY);
	}
	@Override
	public void paintBorder(Graphics g) {
		super.paintBorder(g);
	}

	@Override
	public void repaint() {
		// Do nothing here, to avoid graphical glitch
	}
	

}
