package com.wings2d.demo;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ImprovedMouseListener implements MouseListener{
	private boolean clickReady;
	
	public ImprovedMouseListener() {
		clickReady = false;
	}

	/**
	 * <p>In what must be one of the worst software design decisions I've seen,
	 * the mouseClicked event doesn't fire if the mouse is moved -even by a pixel-
	 * between the press and release. Since the action of pressing the mouse button
	 * will sometimes cause the mouse to move a bit, any application using the
	 * mouseClicked event will miss some of the users clicks.</p> 
	 * 
	 * <p>This event is genuinely unusable because of this.</p>
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		/* In what must be one of the worst software design decisions I've seen,
		 * the mouseClicked event doesn't fire if the mouse is moved -even by a pixel-
		 * between the press and release. Since the action of pressing the mouse button
		 * will sometimes cause the mouse to move a bit, any application using the
		 * mouseClicked event will miss some of the users clicks. 
		 * 
		 * This event is genuinely unusable because of this.
		 */
	}

	@Override
	public void mousePressed(MouseEvent e) {
		clickReady = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (clickReady) {
			mouseClicked(e);
		}
		clickReady = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
