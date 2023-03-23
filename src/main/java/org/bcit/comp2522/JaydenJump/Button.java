package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;

public class Button extends PApplet {

  private int x;
  private int y;
  private int width;
  private int height;
  private String label;
  private int fontSize;
  private Menu window;

  public Button(int x, int y, int width, int height, int fontSize, String label, Menu window) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.fontSize = fontSize;
    this.label = label;
    this.window = window;
  }

  public void draw() {
//    window.rectMode(CENTER);
//    window.stroke(0);
//    window.fill(0, 0, 255);
//    window.rect(x, y, width, height);
//    window.fill(0);
//    window.textSize(30);
//    window.text(label, x, y);
//    window.textAlign(CENTER, CENTER);
    window.ellipseMode(CENTER);
    window.stroke(0);
    window.fill(35, 150, 250);
    window.ellipse(x, y, width, height);
    window.fill(0);
    window.textSize(fontSize);
    window.text(label, x, y);
    window.textAlign(CENTER, CENTER);
  }

//  public boolean isClicked(int mouseX, int mouseY) {
////    System.out.println(x - width / 2);
////    System.out.println(x + width / 2);
////    System.out.println(y - height / 2);
////    System.out.println(y + height / 2);
//    return (mouseX > x - width/2 && mouseX < x + width/2 &&
//        mouseY > y - height/2 && mouseY < y + height/2);
//  }

  public boolean isClicked(int x, int y) {
    return (x >= this.x - width/2 && x <= this.x + width/2 && y >= this.y - height/2 && y <= this.y + height/2);
  }

  public void onClick() {

  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public String getLabel() {
    return label = label;
  }

  public void setLabel(String label) {
    label = label;
  }
}
