package com.wings2d.demo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.wings2d.framework.core.Level;
import com.wings2d.framework.core.LevelManager;

import com.wings2d.framework.svg.SVGImporter;
import com.wings2d.framework.svg.SVGShapeGroup;

public class DemoScene extends Level{
	private SVGShapeGroup g;
	private int startDragX, startDragY;
	private int appliedDragX, appliedDragY;
	private boolean move;

	public DemoScene(LevelManager manager, int thisLevel) {
		super(manager, thisLevel);
		
		g = SVGImporter.importSVG("src/data/Drawing.svg");
		move = false;
		
		JPanel p = manager.getGame().getDrawPanel();
		p.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					startDragX = e.getX();
					startDragY = e.getY();
					appliedDragX = 0;
					appliedDragY = 0;
				}
				if (SwingUtilities.isRightMouseButton(e)) {
					move = true;
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					move = false;
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {	
			}
			@Override
			public void mouseExited(MouseEvent e) {}
		});
		
		p.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					int x = e.getX() - startDragX;
					int y = e.getY() - startDragY;
					x = x - appliedDragX;
					y = y - appliedDragY;
					appliedDragX += x;
					appliedDragY += y;
					
					AffineTransform t = new AffineTransform();
					t.translate(x, y);
					g.applyTransform(t);
				}
			}
			@Override
			public void mouseMoved(MouseEvent e) {}
		});
		p.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				AffineTransform t = new AffineTransform();
				if (e.getWheelRotation() > 0) {
					t.scale(1.1, 1.1);
				}
				else {
					t.scale(0.9, 0.9);
				}
				g.applyTransform(t);
			}
		});
	}
	
	@Override
	public void render(final Graphics2D g2d) {
		g.render(g2d);
		
		String fps = String.valueOf(this.getManager().getGame().getDebugInfo().getFPS());
		g2d.setColor(Color.WHITE);
		g2d.drawString("FPS: " + fps, 10, 20);
	}
	
	@Override
	public void update(final double dt) {
		if (move) {
			double amt = 100 * dt;
			AffineTransform t = new AffineTransform();
			t.translate(amt, 0);
			g.applyTransform(t);
		}
	}

}
