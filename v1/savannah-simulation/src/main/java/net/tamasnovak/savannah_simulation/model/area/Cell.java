package net.tamasnovak.savannah_simulation.model.area;

public class Cell {
  private final int xCoordinate;
  private final int yCoordinate;

  public Cell(int xCoordinate, int yCoordinate) {
    this.xCoordinate = xCoordinate;
    this.yCoordinate = yCoordinate;
  }

  public int getXCoordinate() {
    return xCoordinate;
  }

  public int getYCoordinate() {
    return yCoordinate;
  }

  @Override
  public String toString() {
    return String.format("%s%s %n", xCoordinate, yCoordinate);
  }
}
