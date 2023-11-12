package net.tamasnovak.model.animals;

import net.tamasnovak.model.matrix.Cell;

public abstract class Animal {
  private int currentAge;
  private final int maximumAge;
  private boolean isAlive;
  private Cell livingArea;

  public Animal(Cell livingArea, int[] MAXIMUM_AGE) {
    this.currentAge = 1;
    this.isAlive = true;
    this.livingArea = livingArea;
    this.maximumAge = drawMaximumAgeValue(MAXIMUM_AGE);
  }

  private static int drawMaximumAgeValue(int[] maximumAges) {
    return maximumAges[(int) (System.currentTimeMillis() % maximumAges.length)];
  }

  public Cell getLivingArea() {
    return livingArea;
  }

  public void setLivingArea(Cell livingArea) {
    this.livingArea = livingArea;
  }

  public void removeDeadAnimal() {
    this.livingArea = null;
  }

  public void age() {
    if (currentAge == maximumAge) {
      isAlive = false;
    } else {
      currentAge++;
    }
  }

  public void breed() {

  }

  public void eat() {

  }

  public void move() {

  }
}
