package org.bcit.comp2522.JaydenJump;

public class LeaderboardsMenu extends Menu {

  /**
   * Instance variables.
   */
  private Menu window;
  Button back;

  /**
   * Initializes LeaderboardsMenu.
   *
   * @param window
   */
  public void init(Menu window) {
    this.window = window;
    draw();
  }

  /**
   * Draws to window.
   */
  public void draw() {
    window.background(25, 150, 170);
    window.textSize(30);
    window.textAlign(CENTER);
    window.text("Leaderboards", width * 2 + 50, height);
    back = new Button(50, 50, 75, 75, 20, "Back", window);
    back.draw();
  }
}
