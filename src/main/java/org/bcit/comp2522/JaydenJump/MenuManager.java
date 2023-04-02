package org.bcit.comp2522.JaydenJump;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Starting point of game.
 *
 * @author Brian Kwon
 * @version 1.3
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
   * Instance of start menu.
   */
  private StartMenu startMenu;

  /**
   * Instance of main menu.
   */
  private MainMenu mainMenu;

  /**
   * Instance of difficulty menu.
   */
  private DifficultyMenu difficultyMenu;

  /**
   * Instance of leaderboards menu.
   */
  private LeaderboardsMenu leaderboardsMenu;

  /**
   * Instance of death menu.
   */
  private DeathMenu deathMenu;

  /**
   * Instance of pause menu.
   */
  private PauseMenu pauseMenu;

  /**
   * Instance of submission menu.
   */
  private SubmitMenu submitMenu;

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

  private static int difficulty;
  Load load = new Load();
  String[] str;

  /****************************************************/

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
    logo            = loadImage("images/logo.png");
    doodle          = loadImage("images/doodle.png");
    musicOn         = loadImage("images/music_on.png");
    musicOff        = loadImage("images/music_off.png");
    playerImg       = loadImage("images/doodleguy.png");
    powerUpImg      = loadImage("images/qMarkNoBackground.png");
    enemyImg        = loadImage("images/enemy.png");
    bossImg         = loadImage("./Images/Boss.png");
    bossImg         = loadImage("./Images/Boss.png");
    backgroundImage = loadImage("images/background.png");
    coinImg         = new PImage[6];
    loadCoinImages(coinImg);
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
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
      throw new RuntimeException(e);
    }

    startMenu        = new StartMenu();
    mainMenu         = new MainMenu();
    difficultyMenu   = new DifficultyMenu();
    leaderboardsMenu = new LeaderboardsMenu();
    deathMenu        = new DeathMenu();
    pauseMenu        = new PauseMenu();
    submitMenu       = new SubmitMenu();

    enemyManager = new EnemyManager(this, 50f, enemyImg);

    player = Player.getInstance(width / 2,
                                0,
                                0,
                                0,
                                this,
                                playerImg,
                                80,
                                5,
                                0.5f);
    game = new Game(1,
                    this,
                    powerUpImg,
                    backgroundImage,
                    enemyImg,
                    playerImg,
                    coinImg);
  }

  /**
   * Plays boss soundtrack.
   */
//  public void playBossSong() {
//    clip.stop();
//    try {
//      AudioInputStream ais = AudioSystem.getAudioInputStream(boss);
//      clip = AudioSystem.getClip();
//      clip.open(ais);
//      clip.loop(Clip.LOOP_CONTINUOUSLY);
//      sound = true;
//    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
//      throw new RuntimeException(e);
//    }
//  }
//
//  /**
//   * Plays Like A Dino soundtrack.
//   */
//  public void playLikeAdinoSong() {
//    clip.stop();
//    try {
//      AudioInputStream ais = AudioSystem.getAudioInputStream(dino);
//      clip = AudioSystem.getClip();
//      clip.open(ais);
//      clip.loop(Clip.LOOP_CONTINUOUSLY);
//      sound = true;
//    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
//      throw new RuntimeException(e);
//    }
//  }

  /**
   * Draws to window.
   *
   * Screen 0 = Start Menu
   * Screen 1 = Main Menu
   * Screen 2 = Difficulty Menu
   * Screen 3 = Leaderboards
   * Screen 4 = Death Menu
   * Screen 5 = Pause Menu
   * Screen 6 = Submit Menu
   * Screen 7 = Game Window
   */
  public void draw() {
    switch (currentScreen) {
      case 0 -> startMenu.init(this);
      case 1 -> mainMenu.init(this,
                                      logo,
                                      doodle,
                                      musicOn,
                                      musicOff);
      case 2 -> difficultyMenu.init(this);
      case 3 -> {str = load.getLeaderboard(1);
                leaderboardsMenu.init(this, str);
      }
      case 4 -> deathMenu.init(this);
      case 5 -> pauseMenu.init(this);
      case 6 -> submitMenu.init(this);
      case 7 -> {game.draw();
                if (game.gameOver) {
                  currentScreen = 4;
                }
      }
    }
  }

  /**
   * Handles mouse clicks in the start menu.
   */
//  public void handleMouseClicksInStartMenu() {
//    if (currentScreen == 0 && keyPressed && key == ' ') {
//      currentScreen = 1;
//    }
//  }

  /**
   * Handles mouse clicks in the difficulty menu.
   */
  public void handleMouseClicksInDifficultyMenu() {
    if (difficultyMenu.easy.isClicked(mouseX, mouseY)) {
      difficulty = 1;
      PlatformManager.setPlatformSpeed(6);
    } else if (difficultyMenu.medium.isClicked(mouseX, mouseY)) {
      difficulty = 2;
    } else if (difficultyMenu.hard.isClicked(mouseX, mouseY)) {
      difficulty = 3;
      PlatformManager.setPlatformSpeed(10);
    }
    currentScreen = 7;
  }

  /**
   * Handles mouse clicks in the main menu.
   */
  public void handleMouseClicksInMainMenu() {
    if (mainMenu.start.isClicked(mouseX, mouseY)) {
      currentScreen = 2;
    } else if (mainMenu.leaderboards.isClicked(mouseX, mouseY)) {
      str = load.getLeaderboard(1);
      currentScreen = 3;
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
//  public void handleMouseClicksInGameSettings() {
//    if (gameSettings.back.isClicked(mouseX, mouseY)) {
//      currentScreen = 0;
//    } else if (gameSettings.music.isClicked(mouseX, mouseY)) {
//      currentScreen = 3;
//    }
//  }



  /**
   * Handles mouse clicks in the music menu.
   */
//  public void handleMouseClicksInMusicMenu() {
//    if (musicMenu.boss.isClicked(mouseX, mouseY)) {
//      playBossSong();
//    } else if (musicMenu.dino.isClicked(mouseX, mouseY)) {
//      playLikeAdinoSong();
//    } else if (musicMenu.home.isClicked(mouseX, mouseY)) {
//      currentScreen = 0;
//    }
//  }

  /**
   * Handles mouse clicks in the leaderboards menu.
   */
  public void handleMouseClicksInLeaderboards() {
    if (leaderboardsMenu.back.isClicked(mouseX, mouseY)) {
      currentScreen = 1;
    }
  }

  /**
   * Handles mouse clicks in the death menu.
   */
  public void handleMouseClicksInDeathMenu() {
    if (deathMenu.playAgain.isClicked(mouseX, mouseY)) {
      currentScreen = 7;
      game.restartGame();
      game.startGame();
      game.resetHighscore();
    } else if (deathMenu.changeDifficulty.isClicked(mouseX, mouseY)) {
      currentScreen = 2;
      game.restartGame();
      game.startGame();
      game.resetHighscore();
    } else if (deathMenu.home.isClicked(mouseX, mouseY)) {
      currentScreen = 1;
      game.restartGame();
      game.startGame();
      game.resetHighscore();
    } else if (deathMenu.submit.isClicked(mouseX, mouseY)) {
      currentScreen = 6;
    }
  }

  /**
   * Handles mouse clicks in the pause menu.
   */
  public void handleMouseClicksInPauseMenu() {
    if (pauseMenu.resume.isClicked(mouseX, mouseY)) {
      currentScreen = 7;
    } else if (pauseMenu.home.isClicked(mouseX, mouseY)) {
      currentScreen = 1;
      game.restartGame();
      game.startGame();
    }
  }

  public void handleMouseClicksInSubmitMenu() {

  }

  /**
   * Event listener for mouse presses.
   */
  public void mousePressed() {
    switch (currentScreen) {
      case 1 -> handleMouseClicksInMainMenu();
      case 2 -> handleMouseClicksInDifficultyMenu();
      case 3 -> handleMouseClicksInLeaderboards();
      case 4 -> handleMouseClicksInDeathMenu();
      case 5 -> handleMouseClicksInPauseMenu();
      case 6 -> handleMouseClicksInSubmitMenu();
    }
  }

  /**
   * Event listener for key presses.
   */
  public void keyPressed() {
    keyCode = keyEvent.getKeyCode();
    game.keyPressedListener(keyCode);

    if (currentScreen == 0 && keyPressed && key == ' ') {
      currentScreen = 1;
    } else if (currentScreen == 4 && keyPressed && key == ' ') {
      currentScreen = 7;
      game.startGame();
      game.restartGame();
    }
  }

  /**
   * Event listener for key releases.
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

  public static int getDifficulty() {
    return difficulty;
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
