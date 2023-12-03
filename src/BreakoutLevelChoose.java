import java.awt.event.MouseEvent;

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;


public class BreakoutLevelChoose extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 625;

	/**Breakout game */
	private final Breakout breakout;
	private final GCanvas canvas;
	public BreakoutLevelChoose(GCanvas canvas){
		this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		this.canvas=canvas;
		breakout = new Breakout(canvas, this::chooseLevel);
	}

	/**
	 * Adds level plates to the canvas.
	 */
	public void chooseLevel(){
		canvas.addMouseListener(this);

		/** Plate with number of level*/
		LevelPlate plate = new LevelPlate(APPLICATION_WIDTH / 2 - 125, APPLICATION_HEIGHT / 2 - 200, 1);
		canvas.add(plate);
		GLabel l1= new GLabel("1", plate.getX()+0.5* plate.getWidth()-15, plate.getY()+ plate.getHeight()*0.5+10);
		l1.setFont("Brotherley-50");
		canvas.add(l1);
		plate = new LevelPlate(APPLICATION_WIDTH/2+25,APPLICATION_HEIGHT/2-200, 2);
		canvas.add(plate);
		l1= new GLabel("2", plate.getX()+0.5* plate.getWidth()-15, plate.getY()+ plate.getHeight()*0.5+10);
		l1.setFont("Brotherley-40");
		canvas.add(l1);
		plate = new LevelPlate(APPLICATION_WIDTH/2-125,APPLICATION_HEIGHT/2, 3);
		canvas.add(plate);
		l1= new GLabel("3", plate.getX()+0.5* plate.getWidth()-15, plate.getY()+ plate.getHeight()*0.5+10);
		l1.setFont("Brotherley-40");
		canvas.add(l1);
		plate = new LevelPlate(APPLICATION_WIDTH/2+25,APPLICATION_HEIGHT/2, 4);
		canvas.add(plate);
		l1= new GLabel("4", plate.getX()+0.5* plate.getWidth()-15, plate.getY()+ plate.getHeight()*0.5+10);
		l1.setFont("Brotherley-40");
		canvas.add(l1);
				
	}

	/**
	 * Starts game when the level is selected.
	 * @param e mouse event
	 */
	public void mousePressed(MouseEvent e){
		GPoint last = new GPoint(e.getPoint());
		GObject gobj = canvas.getElementAt(last);

		if(gobj instanceof LevelPlate) {
			canvas.removeMouseListener(this);
			int level = ((LevelPlate) gobj).getLevelNumb();
			canvas.removeAll();
			breakout.setupGame(level);
		}
	}
}
