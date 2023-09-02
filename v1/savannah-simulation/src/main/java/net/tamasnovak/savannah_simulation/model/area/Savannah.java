package net.tamasnovak.savannah_simulation.model.area;

import java.util.ArrayList;

public class Savannah {
  private final ArrayList<ArrayList<Cell>> area;

  public Savannah(ArrayList<ArrayList<Cell>> area) {
    this.area = area;
  }

  public ArrayList<ArrayList<Cell>> getArea() {
    return area;
  }

  public void performAnnualRoutine() {

  }
}
