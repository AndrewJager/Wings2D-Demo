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
import com.wings2d.framework.core.Grid;
import com.wings2d.framework.core.LevelManager;
import com.wings2d.framework.misc.Easings;
import com.wings2d.framework.rendering.DrawPanelJPanel;

public class MyGame extends Game{
//	private Player player;
	

	public MyGame() {
		super(800, 600, 120);
	}
	
	@Override
	public void init() {
		super.init();
		
		this.setTitle("Wings2D Demo");
		
		new TestScene(this.getManager(), 0, this.getDrawPanel());
		
		
		
//		grid = new Grid(5, 4, this.getDrawPanel().getDrawComponent());
		
		
//		System.out.println(Easings.easeInElastic(0));
//		System.out.println(Easings.easeInElastic(0.5));
//		System.out.println(Easings.easeInElastic(1));
		
		
		
//		player = new Player(grid, grid.getNodes()[1][1]);
//		player.BuildInput(getFullPanel());
//		

		
//		new LevelManager.Coord(this.getManager());
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
