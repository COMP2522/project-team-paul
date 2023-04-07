package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;

/**
 * Starting point of game.
 *
 * @author Brian Kwon
 * @version 1.1
 */
public class Main {

  /**
   * Drives the program.
   *
   * @param args unused
   */
  public static void main(String[] args) {
    String[] arr = {"Jayden Jump"};
    PApplet.runSketch(arr, MenuManager.getInstance());
  }
}
