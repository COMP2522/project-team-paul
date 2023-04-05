package org.bcit.comp2522.JaydenJump;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public interface Audible {
  void playSound();

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