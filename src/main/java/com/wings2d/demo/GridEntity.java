package com.wings2d.demo;

import java.awt.Color;
import java.awt.Graphics2D;

public class GridEntity {
	public enum EntityState {
		IDLE,
		MOVING,
	}
	public enum Dir { // Increase or decrease x/y position
		POS,
		NEG,
		HOLD,
	}
	
	protected double x, y; // Unscaled, grid will handle scaling
	protected double targetX, targetY;
	protected EntityState state;
	protected Dir xDir, yDir;
	
	private double speed = 100;
	
	public GridEntity(final double x, final double y) {
		this.setX(x);
		this.setY(y);
		state = EntityState.IDLE;
		xDir = Dir.HOLD;
		yDir = Dir.HOLD;
	}
	
	public GridEntity() {
		this(0, 0);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public void translate(final double x, final double y, final double dt) {
		translateX(x, dt);
		translateY(y, dt);
	}
	public void translateX(final double x, final double dt) {
		this.x += (x * dt);
	}
	public void translateY(final double y, final double dt) {
		this.y += (y * dt);
	}
	
	public void setTarget(final double targetX, final double targetY, final double scale) {
		this.targetX = targetX / scale;
		this.targetY = targetY / scale;
		
		if (this.x > this.targetX) {
			this.xDir = Dir.NEG;
		}
		else if(this.x < this.targetX) {
			this.xDir = Dir.POS;
		}
		else {
			this.xDir = Dir.HOLD;
		}
		
		if (this.y > this.targetY) {
			this.yDir = Dir.NEG;
		}
		else if(this.y < this.targetY) {
			this.yDir = Dir.POS;
		}
		else {
			this.yDir = Dir.HOLD;
		}
		
		this.state = EntityState.MOVING;
	}
	
	public void update(final double dt) {
		switch(state) {
		case IDLE -> {}
		case MOVING -> {
			switch(xDir) {
			case POS -> {
				translateX(speed, dt);
				if (x > targetX) {
					xDir = Dir.HOLD;
				}
			}
			case NEG -> {
				translateX(-speed, dt);
				if (x < targetX) {
					xDir = Dir.HOLD;
				}
			}
			case HOLD -> {}
			}
			
			switch(yDir) {
			case POS -> {
				translateY(speed, dt);
				if (y > targetY) {
					yDir = Dir.HOLD;
				}
			}
			case NEG -> {
				translateY(-speed, dt);
				if (y < targetY) {
					yDir = Dir.HOLD;
				}
			}
			case HOLD -> {}
			}
		}
		}
	}
	
	public void render(final Graphics2D g2d, final double scale) {
		g2d.setColor(Color.BLUE);
		g2d.translate(getX() * scale, getY() * scale);
		int size = 20;
		g2d.fillRect((int)(0 - (size / 2) * scale), (int)(0 - (size / 2) * scale), (int)(size * scale), (int)(size * scale));
		g2d.translate(-getX() * scale, -getY() * scale);
	}
}
