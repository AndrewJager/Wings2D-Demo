package com.wings2d.demo;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private int x, y;
	public List<Node> neighbors;
	private boolean passable = true;
	
	public Node(final int x, final int y) {
		this.x = x;
		this.y = y;
		neighbors = new ArrayList<Node>();
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void setPassable(final boolean p) {
		this.passable = p;
	}
	public boolean getPassable() {
		return passable;
	}
}
