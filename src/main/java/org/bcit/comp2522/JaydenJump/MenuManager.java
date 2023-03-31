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
 * @version 1.1
 */
public class MenuManager extends PApplet {

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
   * Image used to display the player character.
   */
  private PImage playerImg;

  /**
   * Image used to display the PowerUps in the game.
   */
  private PImage powerUpImg;

  private PImage[] coinImg;

  /**
   * Audio file for background music.
   */
  File dino;

  /**
   * Audio file for background music.
   */
  File boss;

  /**
   * Audio clip used for playing background music.
   */
  private Clip clip;

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
   * Game object that manages the overall game state.
   */
  private Game game;

  /**
   * Player object that represents the user-controlled character.
   */
  private Player player;

  /**
   * Index of current screen being displayed.
   */
  private static int currentScreen = 0;

  /**
   * Manager for the enemies.
   */
  private EnemyManager enemyManager;

  /**
   * Flag indicating whether sound is currently enabled.
   */
  static boolean sound = true;

  /**
   * Image used to display the enemies in the game.
   */
  private PImage enemyImg;

  /**
   * The boss for the game.
   */
  private Boss theboss;

  /**
   * the image for the boss.
   */
  private static PImage bossImg;

  /**
   * the background image of the game.
   */
  private static PImage backgroundImage;


  /**
   * Sets up initial size of game window.
   */
  public void settings() {
    size(480, 800);
  }

  /**
   * Loads images.
   */
  public void setup() {
    logo      = loadImage("images/logo.png");
    doodle    = loadImage("images/doodle.png");
    musicOn   = loadImage("images/music_on.png");
    musicOff  = loadImage("images/music_off.png");
    playerImg = loadImage("images/doodleguy.png");
    powerUpImg = loadImage("images/qMarkNoBackground.png");
    coinImg = new PImage[6];
    loadCoinImages(coinImg);
    enemyImg = loadImage("images/enemy.png");
    bossImg = loadImage("./Images/Boss.png");
    logo            = loadImage("images/logo.png");
    doodle          = loadImage("images/doodle.png");
    musicOn         = loadImage("images/music_on.png");
    musicOff        = loadImage("images/music_off.png");
    playerImg       = loadImage("images/doodleguy.png");
    powerUpImg      = loadImage("images/qMarkNoBackground.png");
    enemyImg        = loadImage("images/enemy.png");
    bossImg         = loadImage("./Images/Boss.png");
    backgroundImage = loadImage("images/background.png");
    frameRate(60);
    init();
  }

  public PImage[] loadCoinImages(PImage[] coinImg) {
    coinImg[0] = loadImage("images/Coin1.png");
    coinImg[1] = loadImage("images/Coin2.png");
    coinImg[2] = loadImage("images/Coin3.png");
    coinImg[3] = loadImage("images/Coin4.png");
    coinImg[4] = loadImage("images/Coin5.png");
    coinImg[5] = loadImage("images/Coin6.png");
    return coinImg;
  }

  /**
   * Initializes all Menu objects.
   */
  public void init() {
    try {
      dino = new File("music/like_a_dino.wav");
      boss = new File("music/boss.wav");
      AudioInputStream ais = AudioSystem.getAudioInputStream(dino);
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

    mainMenu         = new MainMenu();
    gameSettings     = new GameSettings();
    deathMenu        = new DeathMenu();
    musicMenu        = new MusicMenu();
    leaderboardsMenu = new LeaderboardsMenu();
    pauseMenu        = new PauseMenu();

    player = Player.getInstance(width / 2,
                                0,
                                0,
                                0,
                                this,
                                playerImg,
                                80,
                                5,
                                0.5f);
    enemyManager = new EnemyManager(this, 50f, enemyImg);
    theboss = new Boss(170, 10, 5, 0, 150, 150, bossImg, this, player, 5);
    game = new Game(15,
                    2,
                    1,
                    12,
                    player,
                    4,
                    6,
                    this,
                    powerUpImg,
                    enemyManager,
                    theboss,
                    coinImg,
                    backgroundImage);
  }

  /**
   * Draws to window.
   * Screen 0 = Main menu
   * Screen 1 = Game window
   * Screen 2 = Game settings menu
   * Screen 3 = Music menu
   * Screen 4 = Leaderboards
   * Screen 5 = Death menu
   * Screen 6 = Pause menu
   */
  public void draw() {
    if (currentScreen == 0) {
      mainMenu.init(this,
                    logo,
                    doodle,
                    musicOn,
                    musicOff);
    } else if (currentScreen == 1) {
      game.draw();
      if (game.gameOver == true) {
        currentScreen = 5;
      }
    } else if (currentScreen == 2) {
      gameSettings.init(this);
    } else if (currentScreen == 3) {
      musicMenu.init(this);
    } else if (currentScreen == 4) {
      leaderboardsMenu.init(this);
    } else if (currentScreen == 5) {
      deathMenu.init(this);
    } else if (currentScreen == 6) {
      pauseMenu.init(this);
    }
  }

  /**
   * Handles mouse clicks in the main menu.
   */
  public void handleMouseClicksInMainMenu() {
    if (mainMenu.start.isClicked(mouseX, mouseY)) {
      currentScreen = 1;
      game.startGame();
    } else if (mainMenu.settings.isClicked(mouseX, mouseY)) {
      currentScreen = 2;
    } else if (mainMenu.leaderboards.isClicked(mouseX, mouseY)) {
      currentScreen = 4;
    } else if (mouseX >= 30 && mouseX < 30 + mainMenu.musicOn.width
        && mouseY >= 90 && mouseY < 90 + mainMenu.musicOn.height) {
      if (sound) {
        clip.stop();
        clip.drain();
        clip.setFramePosition(0);
        sound = false;
      } else {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        sound = true;
        Coin.resumeSound();
      }
    }
  }

  /**
   * Handles mouse clicks in the settings menu.
   */
  public void handleMouseClicksInGameSettings() {
    if (gameSettings.back.isClicked(mouseX, mouseY)) {
      currentScreen = 0;
    } else if (gameSettings.music.isClicked(mouseX, mouseY)) {
      currentScreen = 3;
    }
  }

  /**
   * Plays boss soundtrack.
   */
  public void playBossSong() {
    clip.stop();
    try {
      AudioInputStream ais = AudioSystem.getAudioInputStream(boss);
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
  }

  /**
   * Plays Like A Dino soundtrack.
   */
  public void playLikeAdinoSong() {
    clip.stop();
    try {
      AudioInputStream ais = AudioSystem.getAudioInputStream(dino);
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
  }

  /**
   * Handles mouse clicks in the music menu.
   */
  public void handleMouseClicksInMusicMenu() {
    if (musicMenu.boss.isClicked(mouseX, mouseY)) {
      playBossSong();
    } else if (musicMenu.dino.isClicked(mouseX, mouseY)) {
      playLikeAdinoSong();
    } else if (musicMenu.home.isClicked(mouseX, mouseY)) {
      currentScreen = 0;
    }
  }

  /**
   * Handles mouse clicks in the leaderboards menu.
   */
  public void handleMouseClicksInLeaderboards() {
    if (leaderboardsMenu.back.isClicked(mouseX, mouseY)) {
      currentScreen = 0;
    }
  }

  /**
   * Handles mouse clicks in the death menu.
   */
  public void handleMouseClicksInDeathMenu() {
    if (deathMenu.playAgain.isClicked(mouseX, mouseY)) {
      currentScreen = 1;
      game.startGame();
      game.restartGame();
    } else if (deathMenu.home.isClicked(mouseX, mouseY)) {
      currentScreen = 0;
      game.restartGame();
    }
  }

  /**
   * Handles mouse clicks in the pause menu.
   */
  public void handleMouseClicksInPauseMenu() {
    if (pauseMenu.resume.isClicked(mouseX, mouseY)) {
      currentScreen = 1;
    }
  }

  /**
   * Event listener for mouse presses.
   */
  public void mousePressed() {
    if (currentScreen == 0) {
      handleMouseClicksInMainMenu();
    } else if (currentScreen == 2) {
      handleMouseClicksInGameSettings();
    } else if (currentScreen == 3) {
      handleMouseClicksInMusicMenu();
    } else if (currentScreen == 4) {
      handleMouseClicksInLeaderboards();
      //leaderboardsMenu.handleMouseClick(mouseX, mouseY);
    } else if (currentScreen == 5) {
      handleMouseClicksInDeathMenu();
    } else if (currentScreen == 6) {
      handleMouseClicksInPauseMenu();
    }
  }

  /**
   * Event listener for key presses.
   */
  public void keyPressed() {
    keyCode = keyEvent.getKeyCode();
    game.keyPressedListener(keyCode);
    if (currentScreen == 5 && keyPressed && key == ' ') {
      currentScreen = 1;
      game.startGame();
      game.restartGame();
    }
  }

  /**
   * test this method better, but it should.
   * reduce how fast the player can move left and right.
   * after letting go of left/right or a/d.
   */
  public void keyReleased() {
    keyCode = keyEvent.getKeyCode();
    game.keyReleasedListener(keyCode);
  }

  /**
   * Getter for current screen.
   *
   * @return current screen as an int
   */
  public static int getCurrentScreen() {
    return currentScreen;
  }

  /**
   * Setter for current screen.
   *
   * @param currentScreen as an int
   */
  public static void setCurrentScreen(int currentScreen) {
    MenuManager.currentScreen = currentScreen;
  }

  public static PImage getBossImg() {
    return bossImg;
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
