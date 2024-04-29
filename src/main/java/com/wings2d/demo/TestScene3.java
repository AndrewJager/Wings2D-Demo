package com.wings2d.demo;

import java.awt.Color;
import java.awt.geom.Point2D;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

import com.wings2d.framework.core.Level;
import com.wings2d.framework.core.LevelManager;
import com.wings2d.framework.misc.FixedGrid;

public class TestScene3 extends Level{
	public static float fontSize = 90.0f;
//	public static 

	public TestScene3(LevelManager manager, int thisLevel, final JComponent panel) {
		super(manager, thisLevel);
//		
//		FixedGrid grid = new FixedGrid(7, 6);
//		grid.setBackground(Color.RED);
//		panel.add(grid);
		
		manager.getGame().setCanvasColor(Color.LIGHT_GRAY);
		
//		panel.setLayout(null);
		
		JLabel l = new JLabel("Cats are evil!");
		l.setFont(l.getFont().deriveFont(fontSize));
		panel.add(l);
		
		MyButton b = new MyButton(50.0f);
		b.setText("Click to play!");
		panel.add(b);
	}

}
