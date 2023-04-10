package org.bcit.comp2522.JaydenJump.interfaces;

/**
 * Interface for objects that can collide with other objects.
 *
 * @author Shawn Birring
 */
public interface Collidable {
  /**
   * Determines if this sprite collides with the specified object.
   *
   * @param o The object to check for collision
   *
   * @return True if the sprite collides with the object, false otherwise
   */
  boolean collides(Object o);
}
