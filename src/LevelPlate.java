import java.awt.Color;

import acm.graphics.GLabel;
import acm.graphics.GRect;


public class LevelPlate extends GRect{

	private int number;
public LevelPlate(double x, double y, int number){
	super(x,y,100,100);
	this.number=number;
	this.setFilled(true);
	this.setFillColor(Color.CYAN);
}
public int getLevelNumb(){
	return number;
}
}
