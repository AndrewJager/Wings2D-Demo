package com.wings2d.demo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;

import java.awt.Shape;


import com.wings2d.framework.core.DrawPanelJPanel;
import com.wings2d.framework.core.Game;
import com.wings2d.framework.core.Grid;
import com.wings2d.framework.core.LevelManager;
import com.wings2d.framework.misc.Easings;

record MyChar(Shape shape, float x) { }

public class MyGame extends Game{


	public MyGame() {
		super(800, 600, 120);
	}
	
	@Override
	public void init() {
		super.init();
		this.setTitle("Wings2D Demo");
		
		new DemoScene(this.getManager(), 0);
	}
	
	@Override
	public void update(final double delta) {
		super.update(delta);
		

	}
	
	@Override
	public void render(final Graphics2D g2d) {
		super.render(g2d);

	}

	@Override
	public void onResize(final DrawPanelJPanel draw) {
		super.onResize(draw);

	}
}
