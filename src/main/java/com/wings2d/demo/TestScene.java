package com.wings2d.demo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JLabel;

import com.wings2d.framework.core.Grid;
import com.wings2d.framework.core.Level;
import com.wings2d.framework.core.LevelManager;
import com.wings2d.framework.rendering.DrawPanelJPanel;

public class TestScene extends Level{
	private Grid grid;
	private JComponent panel;

	public TestScene(LevelManager manager, int thisLevel, final JComponent panel) {
		super(manager, thisLevel);
		this.panel = panel;
		
		grid = new Grid(7, 6, panel, this.getManager());
		
		JLabel test = new JLabel("Test");
		panel.add(test);
	}

	public void update(double dt) {
		super.update(dt);
		
		grid.update(dt);
	}
	
	public void render(Graphics2D g2d, boolean debug) {
		super.render(g2d, debug);
		
		double scale = this.getManager().getScale();

		g2d.setColor(Color.WHITE);
//		g2d.drawString("FPS: " + this.getDebugInfo().getFPS(), 10, 10);
		
//		player.render(g2d, scale);
		
		grid.render(g2d, scale);
	}
	
	public void rescale() {
		grid.recalcGridSize(panel);

	}
	
	@Override
	public void afterRescale() {
	}
}
