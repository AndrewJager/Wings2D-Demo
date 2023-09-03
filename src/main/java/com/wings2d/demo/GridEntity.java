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
	protected double startX, startY;
	protected double xDist, yDist; // Distance from start to target location
	protected double movedAmt; // 0 - 1, with 0 being at start loc, 1 being at end loc
	
	private double speed = 10;
	
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
	
//	public void translate(final double x, final double y, final double dt) {
//		translateX(x, dt);
//		translateY(y, dt);
//	}
	public void translateX(final double xAmt) {
		this.x += xAmt;
	}
	public void translateY(final double yAmt) {
		this.y += yAmt;
	}
	
	public void setPath(List<Node> path) {
		this.path = path;
		curNode = 0;
		node = path.get(curNode);
		setTarget(node);
	}
	
	public void setTarget(final double x, final double y) {
		this.targetX = x;
		this.targetY = y;
		this.xDist = this.targetX - this.getX();
		this.yDist = this.targetY - this.getY();
		this.startX = this.getX();
		this.startY = this.getY();
		this.movedAmt = 0;
		
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
	
	public void setTarget(final Node n) {
		this.setTarget(grid.getNodeX(n), grid.getNodeY(n));
	}
	
	public void update(final double dt) {
		switch(state) {
		case IDLE -> {}
		case MOVING -> {
			this.movedAmt = this.movedAmt + (dt * speed);
			switch(xDir) {
			case POS -> {
				this.x = this.startX + (this.xDist * this.movedAmt);
				if (this.x >= this.targetX) {
					this.x = this.targetX;
					xDir = Dir.HOLD;
				}
			}
			case NEG -> {
				this.x = this.startX + (this.xDist * this.movedAmt);
				if (this.x <= this.targetX) {
					this.x = this.targetX;
					xDir = Dir.HOLD;
				}
			}
			case HOLD -> {}
			}
			
			switch(yDir) {
			case POS -> {
				this.y = this.startY + (this.yDist * this.movedAmt);
				if (this.y >= this.targetY) {
					this.y = this.targetY;
					yDir = Dir.HOLD;
				}
			}
			case NEG -> {
				this.y = this.startY + (this.yDist * this.movedAmt);
				if (this.y <= this.targetY) {
					this.y = this.targetY;
					yDir = Dir.HOLD;
				}
			}
			case HOLD -> {}
			}
			
			if ((xDir == Dir.HOLD) && (yDir == Dir.HOLD)) {
				if (curNode < path.size() - 1) {
					curNode++;
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
		double size = 20;
		double xAmt = getX() - ((size / 2) * scale);
		double yAmt = getY() - ((size / 2) * scale);
		g2d.translate(xAmt, yAmt);
		
		g2d.fillRect(0, 0, (int)(size * scale), (int)(size * scale));
		g2d.translate(-xAmt, -yAmt);
	}
	
	public Node getNode() {
		return node;
	}
}
