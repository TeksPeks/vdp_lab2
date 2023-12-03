import java.awt.Color;

import acm.graphics.GLabel;
import acm.graphics.GRect;

/*
 * class that implements buttons for choosing levels
 * File: LevelPlate.java
 * Author: Maksym Semeniuk, Myroslav Verstiuk
 */
public class LevelPlate extends GRect{
    /**number of level*/
    	private int number;

	/**
	 * creates plate with number for choosing level
 	 * @param x x plate coordinate
	 * @param y y plate coordinate
	 * @param number level number
	 */
public LevelPlate(double x, double y, int number){
	super(x,y,100,100);
	this.number=number;
	this.setFilled(true);
	this.setFillColor(Color.CYAN);
}

	/**
	 * returns level number
	 * @return number of level
	 */
	public int getLevelNumb(){
	return number;
}
}
