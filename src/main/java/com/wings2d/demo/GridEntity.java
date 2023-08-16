package com.wings2d.demo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import com.wings2d.framework.core.LevelManager;

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
	
	protected Grid grid;
	protected double x, y; // Unscaled, grid will handle scaling
	protected double targetX, targetY;
	protected EntityState state;
	protected Dir xDir, yDir;
	protected List<Node> path;
	protected int curNode;
	protected Node node;
	
	private double speed = 100;
	
	public GridEntity(final Grid grid, final Node playerNode) {
		this.grid = grid;
		this.node = playerNode;
		this.setX(grid.getNodeX(node));
		this.setY(grid.getNodeY(node));
		state = EntityState.IDLE;
		xDir = Dir.HOLD;
		yDir = Dir.HOLD;
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
	
	public void setPath(List<Node> path) {
		this.path = path;
		curNode = 0;
		node = path.get(curNode);
		setTarget(node);
	}
	
	public void setTarget(final Node n) {
		this.targetX = grid.getNodeX(n);
		this.targetY = grid.getNodeY(n);
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
			
			if ((xDir == Dir.HOLD) && (yDir == Dir.HOLD)) {
				if (curNode < path.size() - 1) {
					curNode++;
					
					// Why does this hang instead of raising exception if I don't catch it here?
//					try {
//					System.out.println(path.get(2));
//					}
//					catch (Exception e){
//						System.out.println(e.getMessage());
//					}
					node = path.get(curNode);
					setTarget(path.get(curNode));
				}
				else {
					this.state = EntityState.IDLE;
					grid.OnEntityPathComplete(this);
				}
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
	
	public Node getNode() {
		return node;
	}
}
