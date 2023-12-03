/*
 * class that implements homescreen
 * File: BreakoutApp.java
 * Author: Maksym Semeniuk, Myroslav Verstiuk
 */
import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GPoint;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
/**
 * creates homescreen with 2 buttons("Begin Game", "Close Window")
 */
public class BreakoutApp extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 625;

	/** Breakout levels */
	private BreakoutLevelChoose breakoutLevel;
	
	public void init() {
		this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		breakoutLevel = new BreakoutLevelChoose(this.getGCanvas());
		addLabel();
		addQuitButton();
		addNewGameButton();
		addMouseListeners();
	}

	public void mousePressed(MouseEvent e) {
		GPoint last = new GPoint(e.getPoint());
		GObject gobj = getElementAt(last);
		if (gobj != null) {
			if (e.getY() >= 2 * APPLICATION_HEIGHT / 4 - 60
					&& e.getY() <= 2.75 * APPLICATION_HEIGHT / 4 - 60) {
				removeAll();
				breakoutLevel.chooseLevel();
				this.getGCanvas().removeMouseListener(this);
			}
			if (e.getY() >= 3 * APPLICATION_HEIGHT / 4 - 60
					&& e.getY() <= 3.75 * APPLICATION_HEIGHT / 4 - 60) {
				System.exit(0);
			}
		}

	}

	/**
	 * adds button with label "Begin Game"
	 */
	private void addNewGameButton() {
		GRect quit = new GRect(APPLICATION_WIDTH / 4 - 10,
				2 * APPLICATION_HEIGHT / 4 - 60,
				1.5 * APPLICATION_WIDTH / 2 - 40, 0.75 * APPLICATION_HEIGHT / 4);
		add(quit);
		GLabel welcome = new GLabel("Begin game", APPLICATION_WIDTH / 4,
				2 * APPLICATION_HEIGHT / 4);
		welcome.setFont("Brotherley-40");
		add(welcome);

	}
	/**
	 * adds button with label "Quit"
	 */
	private void addQuitButton() {
		GRect quit = new GRect(APPLICATION_WIDTH / 4 - 10,
				3 * APPLICATION_HEIGHT / 4 - 60,
				1.5 * APPLICATION_WIDTH / 2 - 40, 0.75 * APPLICATION_HEIGHT / 4);
		quit.setFilled(true);
		quit.setFillColor(Color.RED);
		add(quit);
		GLabel welcome = new GLabel("Close window", APPLICATION_WIDTH / 4,
				3 * APPLICATION_HEIGHT / 4);
		welcome.setFont("Brotherley-40");
		add(welcome);

	}
	/**
	 * adds label "Breakout"
	 */
	private void addLabel() {
		GLabel welcome = new GLabel("Breakout", APPLICATION_WIDTH / 4,
				APPLICATION_HEIGHT / 4);
		welcome.setFont("Brotherley-50");
		add(welcome);

	}

}
