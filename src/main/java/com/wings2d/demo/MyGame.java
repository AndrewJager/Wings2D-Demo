package com.wings2d.demo;

import java.awt.Graphics2D;

import java.awt.Shape;
import java.util.HashMap;
import java.util.Map;

import com.wings2d.framework.core.DrawPanelJPanel;
import com.wings2d.framework.core.Game;
record MyChar(Shape shape, float x) { }



public class MyGame extends Game<Commands>{

	public MyGame() {
		super(800, 600, 240);

		getCommands().put(Commands.ONE, () -> {
			hi();
		});
		
		OnCommand n = () -> {};
	}
	
	public void hi() {
		System.out.println("HI!");
	}
	
	@Override
	public void init() {
		super.init();
		this.setTitle("Wings2D Demo");
		
		this.getManager().addLevel(new DemoScene(this.getManager()));
		
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
