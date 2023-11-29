/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file implements the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;

import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 625;

/** Dimensions of game board (usually the same) */
	public static final int WIDTH = APPLICATION_WIDTH;

	// application height includes the top menu, so the height of the actual screen estate is smaller
	public static final int HEIGHT = APPLICATION_HEIGHT - 25;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of lives */
	private static final int NTURNS = 3;
	/**
	 * Game canvas
	 */
	private final GCanvas canvas;
	/** Platform */
	private Platform platform;
	/**
	 * Ball
	 */
	private Ball ball;
	/**
	 * Game stopped flag.
	 */
	private boolean gameStopped = false;
	/**
	 * Brick levels
	 */
	private BrickLevels brickLevels;
	private Thread gameThread;

	/* Method: run() */
	/** Runs the Breakout program. */
	public Breakout (GCanvas canvas) {
		this.canvas = canvas;
	}

	/**
	 * Sets up the game, before it can be played.
	 */
	public void setupGame(int level) {
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
		Platform platform = new Platform(PADDLE_WIDTH, PADDLE_HEIGHT, PADDLE_Y_OFFSET);
		this.platform = platform;
		canvas.add(platform);

		ball = new Ball(BALL_RADIUS, canvas, this::onCollision);
		canvas.add(ball);

		brickLevels = new BrickLevels(WIDTH, HEIGHT, NBRICK_ROWS, NBRICKS_PER_ROW, BRICK_SEP, BRICK_Y_OFFSET, BRICK_WIDTH, BRICK_HEIGHT, canvas);
		brickLevels.initializeLevelOneBricks();

		playGame();
	}

	/**
	 * Main game loop.
	 */
	private void playGame() {
		if (gameThread == null || !gameThread.isAlive()) {
			gameThread = new Thread(() -> {
                while (!gameStopped) {
                    ball.move();
                    pause(5);
                }
            });
			gameThread.start();
		}
	}

	/**
	 * Handles ball collision with other objects.
	 * @param collider - object, with which the ball collided
	 */
	public void onCollision(GObject collider) {
		if (collider instanceof Brick) {
			brickLevels.changeBrick(collider);
		} else if (collider instanceof Platform) {

		}
	}

	public void stopGame() {
		gameStopped = true;
	}

	/**
	 * Listens for the mouse movement and updates platform's position according to mouse position.
	 * @param e - event
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		if (!gameStopped) {
			platform.setLocation(e.getX());
		}
	}
}
