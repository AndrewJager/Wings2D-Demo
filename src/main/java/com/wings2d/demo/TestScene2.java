package com.wings2d.demo;

import java.awt.Color;

import javax.swing.JComponent;

import com.wings2d.framework.core.Level;
import com.wings2d.framework.core.LevelManager;
import com.wings2d.framework.misc.FixedGrid;

public class TestScene2 extends Level{

	public TestScene2(LevelManager manager, int thisLevel, final JComponent panel) {
		super(manager, thisLevel);
		
		FixedGrid grid = new FixedGrid(7, 6);
		grid.setBackground(Color.RED);
		panel.add(grid);
		
		manager.getGame().setCanvasColor(Color.LIGHT_GRAY);
		
		
	}

}
