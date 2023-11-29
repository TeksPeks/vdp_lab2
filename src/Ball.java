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
     * @param radius - radius of the ball
     * @param canvas - canvas on which the game is played
     * @param collisionHandler - function, which is called when ball collides with anything
     */
    Ball(int radius, GCanvas canvas, CollisionHandler collisionHandler) {
        super((double) Breakout.WIDTH / 2 - radius, (double) Breakout.HEIGHT / 2 - radius, radius * 2, radius * 2);
        setFilled(true);

        generateStartingVX();
        vy = 1.0;
        this.collisionHandler = collisionHandler;
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
        this.move(vx, vy);
        checkCollisions();
    }

    /**
     * Checks collisions with the edges of the screen and other objects
     */
    private void checkCollisions() {
        // Check collisions with edges of the screen
        if (getX() <= 0 || getX() + getWidth() >= Breakout.WIDTH) {
            vx = -vx;
        }
        if (getY() <= 0 || getY() + getHeight() >= Breakout.HEIGHT) {
            vy = -vy;
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
     * @param collider - object to check
     * @return true if collider exists and is not the ball itself
     */
    private boolean colliderExists(GObject collider) {
        return collider != null && collider != this;
    }
}
