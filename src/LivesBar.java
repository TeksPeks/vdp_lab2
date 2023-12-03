import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GLabel;

import java.awt.*;
/*
 * class that implements bar of lives
 * File: LivesBar.java
 * Author: Maksym Semeniuk, Myroslav Verstiuk
 */
public class LivesBar extends GCompound {
    /**
     * Separation between lives.
     */
    private static final int LIVES_SEP = 4;
    /**
     * Radius of a life.
     */
    private static final int LIFE_RADIUS = 10;
    /**
     * Diameter of a life.
     */
    private static final int LIFE_DIAMETER = LIFE_RADIUS * 2;

    /**
     * Number of lives left.
     */
    private int livesLeft;
    /**
     * Label with the number of lives left.
     */
    private final GLabel livesLeftLabel;

    /**
     * Creates a new lives bar with the given number of lives.
     * @param LIVES number of lives
     */
    public LivesBar(int LIVES) {
        livesLeft = LIVES;
        GImage life = new GImage(System.getProperty("user.dir") + "/assets/heart.png");
        life.setSize(LIFE_DIAMETER, LIFE_DIAMETER);
        add(life);
        livesLeftLabel = new GLabel("x" + livesLeft);
        livesLeftLabel.setFont("SansSerif-20");
        livesLeftLabel.setLocation(LIFE_DIAMETER + LIVES_SEP, LIFE_DIAMETER / 2 + livesLeftLabel.getAscent() / 2 - 2);
        livesLeftLabel.setColor(Color.RED);
        add(livesLeftLabel);
    }

    /**
     * Returns the number of lives left.
     */
    public void loseLife() {
        if (livesLeft > 0) {
            livesLeft--;
        }
        livesLeftLabel.setLabel("x" + livesLeft);
    }

    /**
     * Checks if the game is over.
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        return livesLeft == 0;
    }
}
