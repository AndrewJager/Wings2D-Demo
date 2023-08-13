package com.wings2d.demo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import com.wings2d.framework.core.Game;
import com.wings2d.framework.misc.Easings;
import com.wings2d.framework.rendering.DrawPanelJPanel;

public class MyGame extends Game{
	private Player player;
	private Grid grid;

	public MyGame() {
		super(800, 600, 120);
	}
	
	@Override
	public void init() {
		super.init();
		
		this.setTitle("Wings2D Demo");
		
		player = new Player();
//		grid = new Grid(5, 4, this.getDrawPanel().getDrawComponent());
		
		
//		System.out.println(Easings.easeInElastic(0));
//		System.out.println(Easings.easeInElastic(0.5));
//		System.out.println(Easings.easeInElastic(1));
		
		player.BuildInput(getFullPanel());
		grid = new Grid(8, 7, this.getDrawPanel(), this.getManager());
		
		JLabel test = new JLabel("Test");
		this.getDrawPanel().add(test);
	}
	
	@Override
	public void update(final double delta) {
		super.update(delta);
		
		player.update(delta);
		grid.update(delta);
	}
	
	@Override
	public void render(final Graphics2D g2d) {
		super.render(g2d);
		
		double scale = this.getManager().getScale();

		g2d.setColor(Color.WHITE);
		g2d.drawString("FPS: " + this.getDebugInfo().getFPS(), 10, 10);
		
		player.render(g2d, scale);
		
		grid.render(g2d, scale, this);
	}

	@Override
	public void onResize(final DrawPanelJPanel draw) {
		super.onResize(draw);
		grid.recalcGridSize(this);
	}
}
