package org.bcit.comp2522.JaydenJump;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;


/**
 * Interface that gives classes the ability to play sound clips.
 *
 * @version 1.0
 *
 * @author Hyuk Park
 *
 * @since 03/26/2023
 *
 */
public interface Audible {

  /**
   * Plays the sound clip that is created in the class using the interface.
   */
  void playSound();

  /**
   * Creates a sound clip from a .wav file that is passed to the class
   * using the interface. Catches any exceptions that might occur from
   * Creating the sound clip.
   *
   * @param fileName of the wav. clip found in "./Music"
   *
   * @return Clip of .wav file that is used by the class using the interface
   *
   */
  default Clip loadSound(String fileName) {
    File fileSound = new File("./Music/" + fileName);
    Clip soundClip;
    try {
      AudioInputStream ais = AudioSystem.getAudioInputStream(fileSound);
      soundClip = AudioSystem.getClip();
      soundClip.open(ais);
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
      throw new RuntimeException(e);
    }
    return soundClip;
  }
}