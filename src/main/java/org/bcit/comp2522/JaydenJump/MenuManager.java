package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;
import processing.core.PImage;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Starting point of game.
 *
 * @author Brian Kwon
 * @version 1.0
 */
public class MenuManager extends Menu {

  /**
   * Image of game title.
   */
  private PImage logo;

  /**
   * Image of player's avatar.
   */
  private PImage doodle;

  /**
   * Image displayed when music is turned on.
   */
  private PImage musicOn;

  /**
   * Image displayed when music is turned off.
   */
  private PImage musicOff;

  /**
   * Instance of main menu.
   */
  private MainMenu mainMenu;

  /**
   * Instance of pause menu.
   */
  private PauseMenu pauseMenu;

  /**
   * Instance of game settings menu.
   */
  private GameSettings gameSettings;

  /**
   * Instance of death menu.
   */
  private DeathMenu deathMenu;

  /**
   * Instance of music menu.
   */
  private MusicMenu musicMenu;

  /**
   * Instance of leaderboards menu.
   */
  private LeaderboardsMenu leaderboardsMenu;

  /**
   * Index of current screen being displayed.
   */
  private int currentScreen = 0;

  /**
   * Flag indicating whether sound is currently enabled.
   */
  static boolean sound = true;

  /**
   * Audio clip used for playing background music.
   */
  private Clip clip;

  /**
   * Loads images.
   */
  public void setup() {
    logo = loadImage("images/logo.png");
    doodle = loadImage("images/doodle.png");
    musicOn = loadImage("images/music_on.png");
    musicOff = loadImage("images/music_off.png");
    init();
  }

  /**
   * Initializes all Menu objects.
   */
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
    leaderboardsMenu = new LeaderboardsMenu();
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
    } else if (currentScreen == 4) {
      leaderboardsMenu.init(this);
    } else if (currentScreen == 5) {
      deathMenu.init(this);
    }
  }

  /**
   * Event listener for mouse presses.
   */
  public void mousePressed() {
    if (currentScreen == 0) {
      if (mainMenu.start.isClicked(mouseX, mouseY)) {
        currentScreen = 1;
      } else if (mainMenu.settings.isClicked(mouseX, mouseY)) {
        currentScreen = 2;
      } else if (mainMenu.leaderboards.isClicked(mouseX, mouseY)) {
        currentScreen = 4;
      } else if (mouseX >= 30 && mouseX < 30 + mainMenu.musicOn.width && mouseY >= 90 && mouseY < 90 + mainMenu.musicOn.height) {
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
        currentScreen = 5;
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
    } else if (currentScreen == 4) {
      if (leaderboardsMenu.back.isClicked(mouseX, mouseY)) {
        currentScreen = 0;
      }
    } else if (currentScreen == 5) {
      if (deathMenu.playAgain.isClicked(mouseX, mouseY)) {
        currentScreen = 0;
      } else if (deathMenu.leaderboards.isClicked(mouseX, mouseY)) {
        currentScreen = 4;
      }
    }
  }

  /**
   * Getter for current screen.
   *
   * @return current screen as an int
   */
  public int getCurrentScreen() {
    return currentScreen;
  }

  /**
   * Setter for current screen.
   *
   * @param currentScreen as an int
   */
  public void setCurrentScreen(int currentScreen) {
    this.currentScreen = currentScreen;
  }

  /**
   * Getter for sound.
   *
   * @return sound as a boolean
   */
  public boolean getSound() {
    return sound;
  }

  /**
   * Setter for sound.
   *
   * @param sound as a boolean
   */
  public void setSound(boolean sound) {
    this.sound = sound;
  }

  /**
   * Drives the program.
   *
   * @param args unused
   */
  public static void main(String[] args) {
    String[] appletArgs = new String[]{"MenuManager"};
    MenuManager menuManager = new MenuManager();
    PApplet.runSketch(appletArgs, menuManager);
  }
}
