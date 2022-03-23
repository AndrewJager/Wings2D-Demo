package com.wings2d.demo;

import java.awt.Color;
import java.awt.Graphics2D;

import com.wings2d.framework.Game;

public class MyGame extends Game{
	private double x;

	public MyGame() {
		super(800, 600, 120);
		
		this.setTitle("Wings2D Demo");
	}
	
	@Override
	public void init() {
		super.init();
		
		x = 10;
	}
	
	@Override
	public void update(final double delta) {
		super.update(delta);
		 
		if (delta < 0.1) {
			x = x + (delta * 50);
		}
		
	}
	
	@Override
	public void render(final Graphics2D g2d) {
		super.render(g2d);
		
		double scale = this.getManager().getScale();
		
		g2d.setColor(Color.WHITE);
		g2d.drawString("FPS: " + this.getDebugInfo().getFPS(), 10, 10);
		
		g2d.setColor(Color.RED);
		g2d.translate(x, 0);
		g2d.fillRect(0, (int)(100 * scale), (int)(150 * scale), (int)(50 * scale));
	}

}
