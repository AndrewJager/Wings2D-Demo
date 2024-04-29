package com.wings2d.demo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import com.wings2d.framework.core.Game;
import com.wings2d.framework.core.Grid;
import com.wings2d.framework.core.LevelManager;
import com.wings2d.framework.misc.Easings;
import com.wings2d.framework.rendering.DrawPanelJPanel;

record MyChar(Shape shape, float x) { }

public class MyGame extends Game{
//	private Player player;
	private double testScale = 1.0;
	private Font f;
	private TextLayout l;

	public MyGame() {
		super(800, 600, 120);
	}
	
	@Override
	public void init() {
		super.init();
		
		this.setTitle("Wings2D Demo");
		
//		new TestScene(this.getManager(), 0, this.getDrawPanel());
//		new TestScene2(this.getManager(), 0, this.getDrawPanel());
		new TestScene3(this.getManager(), 0, this.getDrawPanel());
		JLabel la = new JLabel("T");
		f = la.getFont();
		f = f.deriveFont(100.0f);
		
//		grid = new Grid(5, 4, this.getDrawPanel().getDrawComponent());
		
		
//		System.out.println(Easings.easeInElastic(0));
//		System.out.println(Easings.easeInElastic(0.5));
//		System.out.println(Easings.easeInElastic(1));
		
		
		
//		player = new Player(grid, grid.getNodes()[1][1]);
//		player.BuildInput(getFullPanel());
//		

		
//		new LevelManager.Coord(this.getManager());
	}
	
	@Override
	public void update(final double delta) {
		super.update(delta);
		
		testScale = testScale + (delta * 1);
//		f = f.deriveFont((float)testScale);
//		System.out.println(testScale);
	}
	
	@Override
	public void render(final Graphics2D g2d) {
		super.render(g2d);
		
//		AffineTransform t = new AffineTransform();
//		t.setToScale(testScale, testScale);
//		g2d.setColor(Color.GREEN);
//		g2d.setTransform(t);
//		g2d.drawString("Cats are evil?", 10, 10);
//		t.setToScale(1.0, 1.0);
//		g2d.setTransform(t);
		
		g2d.setColor(Color.GREEN);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		
		AffineTransform t = new AffineTransform();
		t.setToScale(testScale, testScale);
		g2d.setTransform(t);
		g2d.drawString("Cats bad", 10, 10);
		g2d.setTransform(new AffineTransform());
		
		String str = "Cat bad";
		List<MyChar> chars = new ArrayList<MyChar>();
		float totalX = 0f;
		float spacing = 1f;
//		for (int i = 0; i < str.length(); i++){
//		    char c = str.charAt(i);    
//		    Shape shape = f.createGlyphVector(g2d.getFontRenderContext(), String.valueOf(c)).getOutline();
//		    MyChar newChar = new MyChar(shape, totalX);
//		    chars.add(newChar);
//		    totalX += (totalX + shape.getBounds2D().getWidth() + spacing); 
//		}
//		for (int i = 0; i < chars.size(); i++) {
//			AffineTransform t = new AffineTransform();
//			t.setToScale(testScale, testScale);
//			t.translate(chars.get(i).x(), 100);
//			g2d.setTransform(t);
//			g2d.draw(chars.get(i).shape());
//			g2d.setTransform(new AffineTransform());
//		}
	}

	@Override
	public void onResize(final DrawPanelJPanel draw) {
		super.onResize(draw);

		for (int i = 0; i < getDrawPanel().getComponents().length; i++) {
			
			Component c = getDrawPanel().getComponents()[i];
			if (c instanceof MyButton) {
				MyButton m = (MyButton)c;
				System.out.println("cat");
				m.setFont(m.getFont().deriveFont((float)(m.getFontSize() * getManager().getScale())));
			}
			else if (c instanceof JComponent) {
				JComponent j = (JComponent)c;
				
				j.setFont(j.getFont().deriveFont((float)(TestScene3.fontSize * getManager().getScale())));
			}
		}
	}
}
