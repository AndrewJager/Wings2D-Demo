package com.wings2d.demo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.wings2d.framework.core.Scene;
import com.wings2d.framework.core.SceneManager;

import com.wings2d.framework.svg.SVGImporter;
import com.wings2d.framework.svg.SVGShapeGroup;

public class DemoScene extends Scene{
	private SVGShapeGroup g;
	private int startDragX, startDragY;
	private int appliedDragX, appliedDragY;
	private boolean move;
	private AffineTransform t;

	public DemoScene(final SceneManager manager) {
		super("Demo");
		
		g = SVGImporter.importSVG("src/data/Drawing.svg");
		this.addObject(g);
		move = false;
		t = new AffineTransform();
		
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
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							int x = e.getX() - startDragX;
							int y = e.getY() - startDragY;
							x = x - appliedDragX;
							y = y - appliedDragY;
							appliedDragX += x;
							appliedDragY += y;
							
							t.translate(x, y);
//							g.applyTransform(t);
//							g.endUpdate();
						}
					});
				}
			}
			@Override
			public void mouseMoved(MouseEvent e) {}
		});
		p.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
//						AffineTransform t = new AffineTransform();
						if (e.getWheelRotation() > 0) {
							t.scale(1.1, 1.1);
						}
						else {
							t.scale(0.9, 0.9);
						}
//						g.applyTransform(t);
					}
				});
			}
		});
	}
	
	@Override
	public void render(final Graphics2D g2d) {
		g2d.transform(t);
		g.render(g2d);
	}
	
	@Override
	public void renderUI(final Graphics2D g2d) {
		String updateFps = String.valueOf(this.getManager().getGame().getDebugInfo().getUpdateFPS());
		String renderFps = String.valueOf(this.getManager().getGame().getDebugInfo().getRenderFPS());
		g2d.setColor(Color.WHITE);
		g2d.drawString("Update FPS: " + updateFps, 10, 20);
		g2d.drawString("Render FPS: " + renderFps, 10, 40);
	}
	
	@Override
	public void update(final double dt) {
		if (move) {
			double amt = 100 * dt;
			AffineTransform t = new AffineTransform();
			t.translate(amt, 0);
			g.applyTransform(t);
		}
		
		g.endUpdate();
	}

}
