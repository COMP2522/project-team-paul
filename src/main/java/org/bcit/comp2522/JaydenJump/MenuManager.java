package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;
import processing.core.PImage;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MenuManager extends Menu {

  /**
   * Instance variables.
   */
  private PImage logo;
  private PImage doodle;
  private PImage musicOn;
  private PImage musicOff;
  private MainMenu mainMenu;
  private PauseMenu pauseMenu;
  private GameSettings gameSettings;
  private DeathMenu deathMenu;
  private MusicMenu musicMenu;
  private int currentScreen = 0;
  boolean clickable = true;
  static boolean sound = true;
  private static boolean clicked = false;
  private Clip clip;

  public void settings() {
    size(480, 480);
  }

  public void setup() {
    logo = loadImage("images/logo.png");
    doodle = loadImage("images/doodle.png");
    musicOn = loadImage("images/music_on.png");
    musicOff = loadImage("images/music_off.png");
    this.init();
  }

  public void init() {
    try {
      File music = new File("music/like_a_dino.wav");
      AudioInputStream ais = AudioSystem.getAudioInputStream(music);
      clip = AudioSystem.getClip();
      clip.open(ais);
      clip.loop(Clip.LOOP_CONTINUOUSLY);
      sound = true;
    } catch (UnsupportedAudioFileException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (LineUnavailableException e) {
      throw new RuntimeException(e);
    }
    mainMenu = new MainMenu();
    pauseMenu = new PauseMenu();
    gameSettings = new GameSettings();
    deathMenu = new DeathMenu();
    musicMenu = new MusicMenu();
  }

  /**
   * Draws to window.
   */
  public void draw() {
    if (currentScreen == 0) {
      mainMenu.init(this, logo, doodle, musicOn, musicOff);
    } else if (currentScreen == 1) {
      pauseMenu.init(this);
    } else if (currentScreen == 2) {
      gameSettings.init(this);
    } else if (currentScreen == 3) {
      musicMenu.init(this);
    } else if (currentScreen == -1){
      deathMenu.init(this);
    }
  }
  public void mousePressed() {
    if (currentScreen == 0) {
      if (mainMenu.start.isClicked(mouseX, mouseY)) {
        currentScreen = 1;
      } else if (mainMenu.settings.isClicked(mouseX, mouseY)) {
        currentScreen = 2;
      } else if (mainMenu.quit.isClicked(mouseX, mouseY)) {
        exit();
      } else if (mouseX >= 30 && mouseX < 30 + mainMenu.musicOn.width && mouseY >= 70 && mouseY < 70 + mainMenu.musicOn.height) {
        if (sound) {
          clip.stop();
          clip.drain();
          clip.setFramePosition(0);
          sound = false;
        } else {
          clip.loop(Clip.LOOP_CONTINUOUSLY);
          sound = true;
        }
      } else {
        currentScreen = -1;
      }
    } else if (currentScreen == 1) {
      if (pauseMenu.resume.isClicked(mouseX, mouseY)) {
        currentScreen = 0;
      }
    } else if (currentScreen == 2) {
      if (gameSettings.back.isClicked(mouseX, mouseY)) {
        currentScreen = 0;
      } else if (gameSettings.music.isClicked(mouseX, mouseY)) {
        currentScreen = 3;
      }
    } else if (currentScreen == 3) {
      if (musicMenu.boss.isClicked(mouseX, mouseY)) {
        clip.stop();
        try {
          File music = new File("music/boss.wav");
          AudioInputStream ais = AudioSystem.getAudioInputStream(music);
          clip = AudioSystem.getClip();
          clip.open(ais);
          clip.loop(Clip.LOOP_CONTINUOUSLY);
          sound = true;
        } catch (UnsupportedAudioFileException e) {
          throw new RuntimeException(e);
        } catch (IOException e) {
          throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
          throw new RuntimeException(e);
        }
      } else if (musicMenu.dino.isClicked(mouseX, mouseY)) {
        clip.stop();
        try {
          File music = new File("music/like_a_dino.wav");
          AudioInputStream ais = AudioSystem.getAudioInputStream(music);
          clip = AudioSystem.getClip();
          clip.open(ais);
          clip.loop(Clip.LOOP_CONTINUOUSLY);
          sound = true;
        } catch (UnsupportedAudioFileException e) {
          throw new RuntimeException(e);
        } catch (IOException e) {
          throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
          throw new RuntimeException(e);
        }
      } else {
        currentScreen = 0;
      }
    } else if (currentScreen == -1) {
      if (deathMenu.playAgain.isClicked(mouseX, mouseY)) {
        currentScreen = 0;
      } else if (deathMenu.quit.isClicked(mouseX, mouseY)) {
        exit();
      }
    }
  }

  public static void main(String[] args) {
    String[] appletArgs = new String[]{"MenuManager"};
    MenuManager menuManager = new MenuManager();
    PApplet.runSketch(appletArgs, menuManager);
  }
}
