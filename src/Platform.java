import acm.graphics.GRect;
/*
 * class that implements platform
 * File: Platform.java
 * Author: Maksym Semeniuk, Myroslav Verstiuk
 */
public class Platform extends GRect {
    /**
     * absolute Y position of the platform on the screen
     */
    private final int y;
    /**
     * width of the platform
     */
    private final int width;

    /**
     * @param width - width of the platform
     * @param height - height of the platform
     * @param offsetY - offset from the bottom of the screen to the bottom of the platform
     */
    Platform(int width, int height, int offsetY) {
        super((double) (Breakout.WIDTH - width) / 2, Breakout.HEIGHT - offsetY - height, width, height);
        this.setFilled(true);

        this.y = Breakout.HEIGHT - offsetY - height;
        this.width = width;
    }

    /**
     * Moves the center of the platform to the mouse position,
     * if the platform would be out of bounds, sets its position to the edge of the application.
     * @param x - new X position of the platform
     */
    public void setLocation(double x) {
        if (x + (double) width / 2 > Breakout.WIDTH) {
            this.setLocation(Breakout.WIDTH - width, y);
        } else if (x - (double) width / 2 < 0) {
            this.setLocation(0, y);
        } else {
            double center_x = x - (double) width / 2;
            this.setLocation(center_x, y);
        }
    }
}