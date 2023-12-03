import acm.graphics.GCanvas;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.util.RandomGenerator;

public class Ball extends GOval {
    /**
     * Functional interface for collision handling
     */
    @FunctionalInterface
    public interface CollisionHandler {
        void onCollision(GObject collider);
    }
    /**
     * Ball's horizontal velocity
     */
    private double vx;
    /**
     * Ball's vertical velocity
     */
    private double vy;
    /**
     * Speed multiplier
     */
    private double speedMultiplier = 1.0;
    /**
     * Random number generator
     */
    private final RandomGenerator rgen = RandomGenerator.getInstance();
    /**
     * Game canvas
     */
    private static GCanvas gameCanvas = null;
    /**
     * Action to perform on collision
     */
    private final CollisionHandler collisionHandler;
    /**
     * Action to perform when ball hits the bottom of the screen
     */
    private final Runnable onFail;

    /**
     * @param radius radius of the ball
     * @param canvas canvas on which the game is played
     * @param collisionHandler function, which is called when ball collides with anything
     */
    Ball(int radius, GCanvas canvas, double speedMultiplier, CollisionHandler collisionHandler, Runnable onFail) {
        super((double) Breakout.WIDTH / 2 - radius, (double) Breakout.HEIGHT / 2 - radius, radius * 2, radius * 2);
        setFilled(true);

        generateStartingVX();
        vy = 1.0;
        this.speedMultiplier = speedMultiplier;
        this.collisionHandler = collisionHandler;
        this.onFail = onFail;
        if (gameCanvas == null) {
            gameCanvas = canvas;
        }
    }

    /**
     * Generates random starting horizontal velocity
     */
    private void generateStartingVX() {
        vx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5)) vx = -vx;
    }

    /**
     * Moves the ball by its velocity
     */
    public void move() {
        this.move(vx * speedMultiplier, vy * speedMultiplier);
        checkCollisions();
    }

    /**
     * Checks collisions with the edges of the screen and other objects
     */
    private void checkCollisions() {
        // Check collisions with edges of the screen
        if (getX() <= 0 || getX() + getWidth() >= Breakout.WIDTH) {
            collisionHandler.onCollision(null);
            vx = -vx;
            return;
        }
        if (getY() <= 0) {
            collisionHandler.onCollision(null);
            vy = -vy;
            return;
        }
        if (getY() + getHeight() >= Breakout.HEIGHT) {
            loseLife();
            return;
        }

        // Check the centers of the edges
        // Top center
        GObject collider = gameCanvas.getElementAt(getX() + getWidth() / 2, getY());
        if (colliderExists(collider)) {
            collisionHandler.onCollision(collider);
            vy = -vy;
            return;
        }
        // Bottom center
        collider = gameCanvas.getElementAt(getX() + getWidth() / 2, getY() + getHeight());
        if (colliderExists(collider)) {
            collisionHandler.onCollision(collider);
            vy = -vy;
            return;
        }
        // Left center
        collider = gameCanvas.getElementAt(getX(), getY() + getHeight() / 2);
        if (colliderExists(collider)) {
            collisionHandler.onCollision(collider);
            vx = -vx;
            return;
        }
        // Right center
        collider = gameCanvas.getElementAt(getX() + getWidth(), getY() + getHeight() / 2);
        if (colliderExists(collider)) {
            collisionHandler.onCollision(collider);
            vx = -vx;
            return;
        }
        // Check collisions with other objects
        // Top left corner
        collider = gameCanvas.getElementAt(getX(), getY());
        if (colliderExists(collider)) {
            collisionHandler.onCollision(collider);
            if (colliderExists(gameCanvas.getElementAt(getX(), getY() + getWidth() / 2))) {
                vx = -vx;
            } else {
                vy = -vy;
            }
            return;
        }
        // Top right corner
        collider = gameCanvas.getElementAt(getX() + getWidth(), getY());
        if (colliderExists(collider)) {
            collisionHandler.onCollision(collider);
            if (colliderExists(gameCanvas.getElementAt(getX() + getWidth(), getY() + getWidth() / 2))) {
                vx = -vx;
            } else {
                vy = -vy;
            }
            return;
        }
        // Bottom left corner
        collider = gameCanvas.getElementAt(getX(), getY() + getHeight());
        if (colliderExists(collider)) {
            collisionHandler.onCollision(collider);
            if (colliderExists(gameCanvas.getElementAt(getX(), getY() + getWidth() / 2))) {
                vx = -vx;
            } else {
                vy = -vy;
            }
            return;
        }
        // Bottom right corner
        collider = gameCanvas.getElementAt(getX() + getWidth(), getY() + getHeight());
        if (colliderExists(collider)) {
            collisionHandler.onCollision(collider);
            if (colliderExists(gameCanvas.getElementAt(getX() + getWidth(), getY() + getWidth() / 2))) {
                vx = -vx;
            } else {
                vy = -vy;
            }
            return;
        }
    }

    /**
     * Moves the ball to the center of the screen and generates new starting velocity
     */
    private void loseLife() {
        onFail.run();
        this.setLocation((double) Breakout.WIDTH / 2 - getWidth() / 2, (double) Breakout.HEIGHT / 2 - getHeight() / 2);
        generateStartingVX();
        pause(500);
    }

    /**
     * @param collider object to check
     * @return true if collider exists and is not the ball itself
     */
    private boolean colliderExists(GObject collider) {
        return collider != null && collider != this;
    }
}
