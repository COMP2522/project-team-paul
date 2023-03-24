package org.bcit.comp2522.JaydenJump;

import processing.core.*;
import java.awt.*;
import java.util.ArrayList;


/**
 *
 * window class
 *
 * @author Ravdeep, Aulakh
 * @version 1.0
 */
public class Window extends PApplet {

    /**
     *
     * Instance for the player
     */
    private Player player;

    /**
     * instance for the image for the player
     */
    private PImage img;

    /**
     * platform that the player can jump on
     */
    private Platform platform;

    /**
     * the color of the platform
     */
    private Color color = Color.green;

    /**
     * array list to store the platforms that we made for the game
     */
    private ArrayList<Platform> platforms;

    private Color colorBreak = Color.red;


    /**
     *
     * set up the window's size
     */
    public void settings() {
        size(480, 800);
    }

    /**
     *
     * set up the window
     */
    public void setup() {
        frameRate(60);
        PImage img = loadImage("./Images/doodleguy.png");
        player = Player.getInstance(0, 0, 0, 0, this, img, 80, 3, 0.6f);
        platforms = new ArrayList<Platform>();
        float y = 5;
        for (int i = 0; i < 10; i++) {
            float x = random(width - Platform.getWidth());
            boolean isBreakable = random(1.0f) < 0.1; // set 10% of platforms to be breakable
            Color platformColor = isBreakable ? colorBreak : color; // set the color based on whether the platform is breakable or not
            platforms.add(new Platform(this, x, y, 100, 20, platformColor, 5, 0, 0, isBreakable));
            y += 100;
        }
    }

    /**
     *
     * nothing here right now but will contain stuff to actually set the game up
     */

    public void draw() {
        background(255);


        boolean playerOnPlatform = false;
        for (Platform platform : platforms) {
            if (player.collides(platform)) {
                if (platform.isBreakable()) {
                    platforms.remove(platform);
                }
                playerOnPlatform = true;
                player.setVy(-17);
                break;
            }
        }


        if (!playerOnPlatform) {
            player.update();
        }

        player.update();
        player.draw();


        ArrayList<Platform> platformsToRemove = new ArrayList<Platform>();
        for (Platform platform : platforms) {
            if (!platform.isOnScreen()) {
                platformsToRemove.add(platform);
            }
        }


        platforms.removeAll(platformsToRemove);


        while (platforms.size() < 7) {
            float x = random(width - Platform.getWidth());
            float y = random(50, 200);
            boolean breakable = random(1.0f) < 0.1; // 10% chance of being breakable
            boolean tooClose = true;
            while (tooClose) {
                tooClose = false;
                for (Platform platform : platforms) {
                    if (abs(x - platform.getXpos()) < 100 && abs(y - platform.getYpos()) < 100) {
                        x = random(width - Platform.getWidth());
                        y = random(50, 200);
                        tooClose = true;
                        break;
                    }
                }
            }
            if (breakable){
                platforms.add(new Platform(this, x, y, Platform.getWidth(), Platform.getHeight(), colorBreak, 5, 0, 0, breakable));
            } else {
                platforms.add(new Platform(this, x, y, Platform.getWidth(), Platform.getHeight(), color, 5, 0, 0, breakable));
            }

        }

        // Draw and move all platforms
        for (Platform platform : platforms) {
            platform.draw();
            platform.moveDown();
        }
    }

    /**
     *
     * checks if keys pressed and moves player accordingly
     */
    public void keyPressed() {
        if (keyCode == LEFT) {
            player.moveLeft();
        } else if (keyCode == RIGHT) {
            player.moveRight();
        }
        if (keyCode == 81) {
            player.shootProjectile();
        }
    }


    /**
     *
     * drives the program
     *
     * @param args unused
     */
    public static void main(String[] args) {
        PApplet.runSketch(new String[]{"Window"}, new Window());
    }
}