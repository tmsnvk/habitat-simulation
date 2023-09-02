package net.tamasnovak.savannah_simulation.model.animals;

import net.tamasnovak.savannah_simulation.model.area.Cell;

public class Animal {
  private int age = 1;
  private int hungerLevel = 0;
  private boolean isAlive = true;
  private Cell currentLivingCoordinate;

  public Animal(Cell currentLivingCoordinate) {
    this.currentLivingCoordinate = currentLivingCoordinate;
  }

  public void updateAge() {
    age++;
  }

  public void updateHungerLevel() {
    hungerLevel++;
  }

  public void breed() {

  }

  public void feed() {

  }

  public void move() {

  }
}
