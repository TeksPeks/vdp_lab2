
import acm.graphics.GCanvas;

import acm.graphics.GObject;

import acm.program.GraphicsProgram;


@SuppressWarnings("serial")
public class BrickLevels extends GraphicsProgram {
	/** Dimensions of game board */
	private double width;
	private double height;
	/** Number of rows of bricks */
	private int nbrick_rows;
	/** Number of bricks per row */
	private int nbricks_per_row;
	/** Separation between bricks */
	private int brick_sep;
	/** Width of a brick */
	private int brick_width;
	/** Height of a brick */
	private int brick_height;
	/** Offset of the top brick row from the top */
	private int brick_y_offset;
	/** game canvas*/
	private GCanvas canvas;
	/** brick variable*/
	private Brick brick;
	/**
	 * 
	 * @param width width of the game
	 * @param height height of the game
	 * @param nbrick_rows number of rows of bricks
	 * @param nbricks_per_row unmber of bricks per row
	 * @param brick_sep  separation between bricks
	 * @param brick_y_offset  offset of the top brick row from the top 
	 * @param brick_width width of a brick
	 * @param brick_height height of a brick
	 * @param canvas canvas of the game
	 */
 public BrickLevels(double width, double height, int nbrick_rows, int nbricks_per_row, int brick_sep ,int brick_y_offset, int brick_width, int brick_height, GCanvas canvas){
	 this.width= width;
	 this.height=height;
	 this.canvas=canvas;
	 this.nbrick_rows=nbrick_rows;
	 this.nbricks_per_row=nbricks_per_row;
	 this.brick_sep=brick_sep;
	 this.brick_y_offset=brick_y_offset;	
	 this.brick_width=brick_width;
	 this.brick_height=brick_height;
	 
 }
 /**
  * initializes bricks in the first level of the game
  */
 public void initializeLevelOneBricks(){
	for(int i=0;i<nbrick_rows; i++){
		 for(int j=0;j<nbricks_per_row; j++){
			 int x= j*(brick_width+brick_sep);
			 int y= brick_y_offset+i*(brick_height+brick_sep);

			 brick= new Brick(x, y, brick_width,brick_height , 1);
			 if(i==0 || i==1){

				 if(j==0){
					 brick.setNumbOfBricks(1);
				 }
			 }
			 canvas.add(brick);
			 
		 }
	 }

 }
	/**
	 * initializes bricks in the second level of the game
	 */
	public void initializeLevelTwoBricks(){
		for(int i=0;i<nbrick_rows; i++){
			for(int j=0;j<nbricks_per_row; j++){
				int x= j*(brick_width+brick_sep);
				int y= brick_y_offset+i*(brick_height+brick_sep);

				if(i==0 || i==1){
					brick= new Brick(x, y, brick_width,brick_height , 5);
					if(j==0){
						brick.setNumbOfBricks(1);
					}
				}
				if(i==2 || i==3){
					brick= new Brick(x, y, brick_width,brick_height , 4);
				}
				if(i==4 || i==5){
					brick= new Brick(x, y, brick_width,brick_height , 3);

				}
				if(i==6 || i==7){
					brick= new Brick(x, y, brick_width,brick_height , 2);
				}
				if(i==8 || i==9){
					brick= new Brick(x, y, brick_width,brick_height , 1);
				}
				canvas.add(brick);

			}
		}

	}
	/**
	 * initializes bricks in the third level of the game
	 */
	public void initializeLevelThreeBricks() {
		for (int i = 0; i < nbrick_rows; i++) {
			int startX = (int) ((width - (nbricks_per_row - i) * (brick_width + brick_sep)) / 2);

			for (int j = 0; j < nbricks_per_row - i; j++) {
				int x = startX + j * (brick_width + brick_sep);
				int y = brick_y_offset + i * (brick_height + brick_sep);

					brick= new Brick(x, y, brick_width,brick_height , 1);

				if(i==0) {

					if (j == 0) {
						brick.setNumbOfBricks(1);
					}
				}


				canvas.add(brick);
			}
		}
	}
	/**
	 * initializes bricks in the fourth level of the game
	 */
	public void initializeLevelFourBricks() {
		for (int i = 0; i < nbrick_rows; i++) {
			int startX = (int) ((width - (nbricks_per_row - i) * (brick_width + brick_sep)) / 2);

			for (int j = 0; j < nbricks_per_row - i; j++) {
				int x = startX + j * (brick_width + brick_sep);
				int y = brick_y_offset + i * (brick_height + brick_sep);

				if(i==0 || i==1){
					brick= new Brick(x, y, brick_width,brick_height , 5);
					if(j==0){
						brick.setNumbOfBricks(1);
					}
				}
				if(i==2 || i==3){
					brick= new Brick(x, y, brick_width,brick_height , 4);
				}
				if(i==4 || i==5){
					brick= new Brick(x, y, brick_width,brick_height , 3);

				}
				if(i==6 || i==7){
					brick= new Brick(x, y, brick_width,brick_height , 2);
				}
				if(i==8 || i==9){
					brick= new Brick(x, y, brick_width,brick_height , 1);
				}


				canvas.add(brick);
			}
		}
	}
 /**
  * changes color of the brick or removes it completely
  * @param gobj object from the game
  */
 public void changeBrick(GObject gobj){
	 if(gobj instanceof Brick){
		 int a=((Brick) gobj).getBrickHealth();
		 int b=((Brick) gobj).getNumbOfBricks();
		 double x=gobj.getX();
		 double y=gobj.getY();
		 brick.setNumbOfBricks(b-1);
		 canvas.remove(gobj);
		 if(a>1){
			 brick= new Brick(x, y, brick_width,brick_height , a-1);
			 canvas.add(brick);
		 }
	 }
 }
 /**
  * 
  * @return number of bricks that have left
  */
 public int getNumbOfBricks(){
	 return brick.getNumbOfBricks();
 }
  
}
