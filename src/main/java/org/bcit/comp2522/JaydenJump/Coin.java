package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;
import processing.core.PImage;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Coin extends Sprite {

  private static Clip clip;

  private File coinSound;

  private int index = 0;

  private PImage animationFrames[];

  private final Player player;

  private static boolean play = true;

  private final static int COINSIZE = 25;

  private final static int SCORE_INCREASE = 25;

  public Coin(float xpos, float ypos, float vx, float vy, PApplet sketch, Player player, PImage[] animationFrames) {
    super(xpos, ypos, vx, vy, sketch);
    this.player = player;
    this.animationFrames = animationFrames;
    coinSound = new File("music/coin_sound.wav");
    try {
      AudioInputStream ais = AudioSystem.getAudioInputStream(coinSound);
      clip = AudioSystem.getClip();
      clip.open(ais);
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void draw() {
    super.getSketch().image(animationFrames[index % animationFrames.length], getXpos(), getYpos(), COINSIZE, COINSIZE);
    animate();
  }


  public void animate() {
    if(super.getSketch().frameCount % 6 == 0) {
      this.index++;
    }
  }

  @Override
  public void update() {
    super.setXpos(super.getXpos() + super.getVx());
    super.setYpos(super.getYpos() + super.getVy());
  }

  @Override
  public boolean collides(Object o) {
    if (o instanceof Player) {
      if (player.getXpos() + player.getPlayerSize() >= super.getXpos()
          && player.getXpos() <= super.getXpos() + COINSIZE
          && player.getYpos() + player.getPlayerSize() >= super.getYpos()
          && player.getYpos() <= super.getYpos() + COINSIZE) {
        return true;
      }
    }
    return false;
  }

  public static int getCoinSize() {
    return COINSIZE;
  }

  public void addToScore() {
    Game.increaseScore(SCORE_INCREASE);
    playSound();
  }

  public void playSound() {
    if (play) {
      clip.setFramePosition(0);
      clip.start();
    }
  }

  public static void stopSound() {
    clip.stop();
    clip.drain();
    play = false;
  }

  public static void resumeSound() {
    play = true;
  }

  public boolean isOnScreen() {
    return getXpos() >= 0 && getXpos() + COINSIZE <= super.getSketch().width
        && getYpos() >= 0 && getYpos() + COINSIZE <= super.getSketch().height;
  }
}
