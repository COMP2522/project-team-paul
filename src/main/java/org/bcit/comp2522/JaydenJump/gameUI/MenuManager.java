package org.bcit.comp2522.JaydenJump.gameUI;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.bcit.comp2522.JaydenJump.*;
import org.bcit.comp2522.JaydenJump.sprites.Coin;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Menu manager.
 *
 * @author Brian Kwon
 * @version 1.4
 */
public class MenuManager extends PApplet {

  /**
   * Window width.
   */
  private static final int WIDTH = 480;

  /**
   * Window height.
   */
  private static final int HEIGHT = 800;

  /**
   * Frames per second.
   */
  private static final int FPS = 60;

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
  private PImage[] playerImg;

  /**
   * Image used to display the power ups.
   */
  private PImage[] powerUpImg;

  /**
   * Image used to display coins.
   */
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
   * Index of current screen being displayed.
   */
  private static int currentScreen = 0;

  /**
   * Start menu.
   */
  private static final int START_MENU = 0;

  /**
   * Main menu.
   */
  private static final int MAIN_MENU = 1;

  /**
   * Difficulty menu.
   */
  private static final int DIFFICULTY_MENU = 2;

  /**
   * Leaderboards menu.
   */
  private static final int LEADERBOARDS_MENU = 3;

  /**
   * Death menu.
   */
  private static final int DEATH_MENU = 4;

  /**
   * Pause menu.
   */
  private static final int PAUSE_MENU = 5;

  /**
   * Submit Menu.
   */
  private static final int SUBMIT_MENU = 6;

  /**
   * Game window.
   */
  private static final int GAME = 7;

  /**
   * Flag indicating whether sound is currently enabled.
   */
  public static boolean sound = true;

  /**
   * Image used to display the enemies in the game.
   */
  private PImage enemyImg;

  /**
   * Image used to display the boss.
   */
  private static PImage bossImg;

  /**
   * Image used to display the background.
   */
  private static PImage backgroundImage;

  /**
   * Difficulty setting.
   */
  private static int difficulty = 1;

  /**
   * Load object for grabbing player stats from database.
   */
  Load load;

  /**
   * String used to store player stats.
   */
  String[] str;

  /**
   * Constants.
   */
  private static int X = 30;
  private static int Y = 90;

  /**
   * Static instance of MenuManager class.
   */
  private static MenuManager instance = null;

  /****************************************************/

  /**
   * Sets up initial size of game window.
   */
  public void settings() {
    size(WIDTH, HEIGHT);
  }

  /**
   * Loads images.
   */
  public void setup() {
    logo            = loadImage("./Images/logo.png");
    doodle          = loadImage("./Images/JaydenFrontPage.png");
    musicOn         = loadImage("./Images/music_on.png");
    musicOff        = loadImage("./Images/music_off.png");
    enemyImg        = loadImage("./Images/enemy.png");
    bossImg         = loadImage("./Images/Boss.png");
    bossImg         = loadImage("./Images/Boss.png");
    backgroundImage = loadImage("./Images/background.png");
    playerImg       = loadPlayerImages();
    coinImg         = loadCoinImages();
    powerUpImg      = loadPowerUpImages();
    load            = new Load();
    frameRate(FPS);
    init();
  }

  /**
   * Private constructor to prevent creating multiple instances.
   */
  private MenuManager() {}

  /**
   * Returns static instance of MenuManager class.
   *
   * @return MenuManger object
   */
  public static MenuManager getInstance() {
    if (instance == null) {
      instance = new MenuManager();
    }
    return instance;
  }

  /**
   * Loads PImage array with images used for PowerUps.
   * @return PImage array for PowerUps
   */
  private PImage[] loadPowerUpImages() {
    final int n = 3;
    PImage[] powerUpImages = new PImage[n];
    for (int i = 0; i < powerUpImages.length; i++) {
      switch (i) {
        case 0 -> powerUpImages[i] = loadImage("./Images/heart.png");
        case 1 -> powerUpImages[i] = loadImage("./Images/jetpack.png");
        case 2 -> powerUpImages[i] = loadImage("./Images/spring.png");
      }
    }
    return powerUpImages;
  }

  /**
   * Loads PImage array with images used for Coin.
   * @return PImage array for Coin
   */
  private PImage[] loadCoinImages() {
    final int n = 6;
    PImage[] coinImages = new PImage[n];
    for (int i = 0; i < coinImages.length; i++) {
      coinImages[i] = loadImage("./Images/Coin" + (i + 1) + ".png");
    }
    return coinImages;
  }

  /**
   * Loads PImage array with images used for Player.
   * @return PImage array for Player
   */
  private PImage[] loadPlayerImages() {
    PImage[] playerImages = new PImage[2];
    for (int i = 0; i < playerImages.length; i++) {
      switch (i) {
        case 0 -> playerImages[i] = loadImage("./Images/Player.png");
        case 1 -> playerImages[i] = loadImage("./Images/PlayerShoot.png");
      }
    }
    return playerImages;
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

    game = new Game(difficulty,
        powerUpImg,
        backgroundImage,
        enemyImg,
        playerImg,
        coinImg);
  }

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
      case START_MENU -> startMenu.init();
      case MAIN_MENU -> mainMenu.init(logo,
                                      doodle,
                                      musicOn,
                                      musicOff);
      case DIFFICULTY_MENU -> difficultyMenu.init();
      case LEADERBOARDS_MENU -> {str = load.getLeaderboard();
        leaderboardsMenu.init(str);
      }
      case DEATH_MENU -> deathMenu.init();
      case PAUSE_MENU -> pauseMenu.init();
      case SUBMIT_MENU -> submitMenu.init();
      case GAME -> {game.draw();
        if (game.isGameOver()) {
          currentScreen = DEATH_MENU;
        }
      }
    }
  }

  /**
   * Handles mouse clicks in the difficulty menu.
   */
  public void handleMouseClicksInDifficultyMenu() {
    if (difficultyMenu.easy.isClicked(mouseX, mouseY)) {
      difficulty = 1;
    } else if (difficultyMenu.medium.isClicked(mouseX, mouseY)) {
      difficulty = 2;
    } else if (difficultyMenu.hard.isClicked(mouseX, mouseY)) {
      difficulty = 3;
    }

    game = new Game(difficulty,
        powerUpImg,
        backgroundImage,
        enemyImg,
        playerImg,
        coinImg);

    currentScreen = GAME;
  }

  /**
   * Handles mouse clicks in the main menu.
   */
  public void handleMouseClicksInMainMenu() {
    if (mainMenu.start.isClicked(mouseX, mouseY)) {
      currentScreen = DIFFICULTY_MENU;
    } else if (mainMenu.leaderboards.isClicked(mouseX, mouseY)) {
      str = load.getLeaderboard();
      currentScreen = LEADERBOARDS_MENU;
    } else if (mouseX >= X && mouseX < X + mainMenu.musicOn.width
        && mouseY >= Y && mouseY < Y + mainMenu.musicOn.height) {
      if (sound) {
        clip.stop();
        clip.drain();
        clip.setFramePosition(0);
        sound = false;
      } else {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        sound = true;
      }
    }
  }

  /**
   * Handles mouse clicks in leaderboards menu.
   */
  public void handleMouseClicksInLeaderboards() {
    if (leaderboardsMenu.back.isClicked(mouseX, mouseY)) {
      currentScreen = MAIN_MENU;
    }
  }

  /**
   * Handles mouse clicks in the death menu.
   */
  public void handleMouseClicksInDeathMenu() {
    if (deathMenu.playAgain.isClicked(mouseX, mouseY)) {
      currentScreen = GAME;
      game.restartGame();
      game.startGame();
      game.resetHighscore();
    } else if (deathMenu.changeDifficulty.isClicked(mouseX, mouseY)) {
      currentScreen = DIFFICULTY_MENU;
      game.restartGame();
      game.startGame();
      game.resetHighscore();
    } else if (deathMenu.home.isClicked(mouseX, mouseY)) {
      currentScreen = MAIN_MENU;
      game.restartGame();
      game.startGame();
      game.resetHighscore();
    } else if (deathMenu.submit.isClicked(mouseX, mouseY)) {
      currentScreen = SUBMIT_MENU;
    }
  }

  /**
   * Handles mouse clicks in the pause menu.
   */
  public void handleMouseClicksInPauseMenu() {
    if (pauseMenu.resume.isClicked(mouseX, mouseY)) {
      currentScreen = GAME;
    } else if (pauseMenu.home.isClicked(mouseX, mouseY)) {
      currentScreen = MAIN_MENU;
      game.restartGame();
      game.startGame();
    }
  }

  /**
   * Event listener for mouse presses.
   */
  public void mousePressed() {
    switch (currentScreen) {
      case MAIN_MENU -> handleMouseClicksInMainMenu();
      case DIFFICULTY_MENU -> handleMouseClicksInDifficultyMenu();
      case LEADERBOARDS_MENU -> handleMouseClicksInLeaderboards();
      case DEATH_MENU -> handleMouseClicksInDeathMenu();
      case PAUSE_MENU -> handleMouseClicksInPauseMenu();
    }
  }

  /**
   * Event listener for key presses.
   */
  public void keyPressed() {
    keyCode = keyEvent.getKeyCode();
    game.keyPressedListener(keyCode);

    if (currentScreen == START_MENU && keyPressed && key == ' ') {
      currentScreen = MAIN_MENU;
    } else if (currentScreen == DEATH_MENU && keyPressed && key == ' ') {
      currentScreen = GAME;
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

  /**
   *  Getter for boss image.
   *
   * @return boss image as a PImage object
   */
  public static PImage getBossImg() {
    return bossImg;
  }

  /**
   * Getter for difficulty.
   *
   * @return difficulty as an int
   */
  public static int getDifficulty() {
    return difficulty;
  }

  /**
   * Getter for game.
   */
  public Game getGame() {
    return game;
  }
}
