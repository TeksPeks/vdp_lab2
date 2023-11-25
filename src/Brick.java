import java.awt.Color;

import acm.graphics.GRect;
public class Brick extends GRect {
	/**instance variables*/
	/**brick health*/
	private int brickHealth; 
	/** static variables*/
	/** number of all bricks*/
	private static int numbOfBricks=0;
	/**
	 * 
	 * @param x x brick coordinate;
	 * @param y y brick coordinate;
	 * @param brick_width brick width;
	 * @param brick_height brick height;
	 * @param brickHealth brick health(1-5);
	 */
 public Brick(double x, double y, int brick_width, int brick_height, int brickHealth ){
	 super(x,y,brick_width,brick_height);
	 this.setFilled(true);
	 this.brickHealth=brickHealth;
	 numbOfBricks++;
	 switch(brickHealth){
	 case 5:
		 this.setFillColor(Color.RED);
		 break;
	 case 4:
		 this.setFillColor(Color.ORANGE);
		 break;
	 case 3:
		 this.setFillColor(Color.YELLOW);
		 break;
	 case 2:
		 this.setFillColor(Color.GREEN);
		 break;
	 default:
		 this.setFillColor(Color.CYAN);

	 }
 }
 /**
  * returns brick health
  * @return brick health
  */
 public int getBrickHealth(){
	 return brickHealth;
 }
 /**
  * returns number of bricks
  * @return number of bricks
  */
 public int getNumbOfBricks(){
	 return numbOfBricks;
 }
 /**
  * sets number of bricks
  * @param a number of bricks  
  */
 public void setNumbOfBricks(int a){
	 Brick.numbOfBricks= a;
 }
}
