// package org.bcit.comp2522.JaydenJump;

// import processing.core.PApplet;
// import processing.core.PImage;

// /**
//  * The Magnet class is a type of PowerUp that collects coins around the Player
//  * with a certain radius, without the Player having to directly touch the coin.
//  *
//  * @version 1.0
//  *
//  * @author Shawn Birring
//  *
//  * @since 2023-03-22
//  */

// public class Magnet extends PowerUp {

//   /** The amount of time the Magnet is active for. */
//   private int duration;

//   /** The radius of the magnet that can grab the coins. */
//   private final float radius;

//   /**
//    * Creates an instance of a Magnet in the game.
//    *
//    * @param xpos The x position of the Magnet
//    *
//    * @param ypos The y position of the Magnet
//    *
//    * @param vx The x velocity of the Magnet
//    *
//    * @param vy The y velocity of the Magnet
//    *
//    * @param isActive The boolean state that determines if the Magnet is active or not
//    *
//    * @param duration The amount of time the Magnet lasts for
//    *
//    * @param radius The radius that the Magnet affects
//    */
//   public Magnet(float xpos,
//                 float ypos,
//                 float vx,
//                 float vy,
//                 boolean isActive,
//                 PApplet sketch,
//                 int duration,
//                 float radius,
//                 Player player) {
//     super(xpos, ypos, vx, vy, isActive, sketch, player);
//     this.duration = duration;
//     this.radius = radius;
//   }

//   /** Checks to see if a coin is within radius of the Player.  */
//   public void checkDistance(Coin coin, Player player) {
//     if (Math.sqrt(Math.pow(coin.getXpos() - super.getPlayer().getXpos(), 2)
//             + Math.pow(coin.getYpos() - super.getPlayer().getYpos(), 2)) <= radius) {
//       collectCoin(coin);
//     }
//   }

//   /**
//    * Adds a coin to the Player if it is within radius of Player
//    * with the magnet powerup.
//    *
//    * @param coin in the game
//    */
//   public void collectCoin(Coin coin) {
//     //getPlayer().setScore(getPlayer().getScore() + coin.getValue());
//   }

//   /**
//    * Activates the Magnet.
//    */
//   @Override
//   public void activate() {
//     setActive(true);
//   }

//   /**
//    * Deactivates the Magnet.
//    */
//   @Override
//   public void deactivate() {
//     setActive(false);
//   }

// }
