package net.tamasnovak.savannah_simulation.model;

public class Cell {
  private final int xCoordinate;
  private final int yCoordinate;
  private char content;

  public Cell(int xCoordinate, int yCoordinate, char content) {
    this.xCoordinate = xCoordinate;
    this.yCoordinate = yCoordinate;
    this.content = content;
  }

  public int getxCoordinate() {
    return xCoordinate;
  }

  public int getyCoordinate() {
    return yCoordinate;
  }

  public char getContent() {
    return content;
  }

  @Override
  public String toString() {
    return String.format("%c", content);
  }
}
