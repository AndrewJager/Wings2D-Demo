package com.wings2d.demo;

import java.awt.Graphics2D;

import com.wings2d.framework.core.Level;
import com.wings2d.framework.core.LevelManager;

import com.wings2d.framework.svg.SVGImporter;
import com.wings2d.framework.svg.SVGShapeGroup;

public class DemoScene extends Level{
	private SVGShapeGroup g;

	public DemoScene(LevelManager manager, int thisLevel) {
		super(manager, thisLevel);
		
		g = SVGImporter.importSVG("src/data/Drawing.svg");
		g.printData("");
	}
	
	public void render(Graphics2D g2d, boolean debug) {
		g.render(g2d);
	}

}
