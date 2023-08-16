package com.wings2d.demo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.wings2d.framework.core.LevelManager;

public class Player extends GridEntity{
	@SuppressWarnings("serial")
	public static class StartMove extends AbstractAction {
		private Player p;
		private boolean right;
		
		public StartMove(final Player p, final boolean right) {
			this.p = p;
			this.right = right;
		}
		public void actionPerformed(ActionEvent e) {
			if (right) {
				p.StartMoveRight();
			}
			else {
				p.StartMoveLeft();
			}
		}	
	}
	@SuppressWarnings("serial")
	public static class StopMove extends AbstractAction {
		private Player p;
		private boolean right;
		
		public StopMove(final Player p, final boolean right) {
			this.p = p;
			this.right = right;
		}
		public void actionPerformed(ActionEvent e) {
			if (right) {
				p.StopMoveRight();
			}
			else {
				p.StopMoveLeft();
			}
		}	
	}
	
	
	private boolean moveRight, moveLeft;
	private double width = 50;
	private double height = 50;
	
	public Player(final Grid grid, final Node node) {
		super(grid, node);
		moveRight = false;
		moveLeft = false;
	}
	
	public void BuildInput(final JPanel panel) {
		final String START_RIGHT = "startright";
		final String STOP_RIGHT = "stopright";
		final String START_LEFT = "startleft";
		final String STOP_LEFT = "stopleft";
		
		panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), START_RIGHT);
		panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released RIGHT"), STOP_RIGHT);
		panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), START_LEFT);
		panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released LEFT"), STOP_LEFT);
		
		panel.getActionMap().put(START_RIGHT, new StartMove(this, true));
		panel.getActionMap().put(STOP_RIGHT, new StopMove(this, true));
		panel.getActionMap().put(START_LEFT, new StartMove(this, false));
		panel.getActionMap().put(STOP_LEFT, new StopMove(this, false));
	}
	
	public void update(final double dt) {
		if (moveRight) {
			x = x + (100 * dt);
		}
		if (moveLeft) {
			x = x - (100 * dt);
		}
	}
	
	public void render(final Graphics2D g2d, final double scale) {
		g2d.setColor(Color.RED);
		g2d.translate(GetX() * scale, GetY() * scale);
		g2d.fillRect((int)(0 - (width / 2) * scale), (int)(0 - (height / 2) * scale), (int)(width * scale), (int)(height * scale));
		g2d.translate(-GetX() * scale, -GetY() * scale);
	}
	
	public void StartMoveRight() {
		moveRight = true;
	}
	public void StopMoveRight() {
		moveRight = false;
	}
	public void StartMoveLeft() {
		moveLeft = true;
	}
	public void StopMoveLeft() {
		moveLeft = false;
	}
	
	public double GetX() {
		return x;
	}
	public double GetY() {
		return y;
	}
}
