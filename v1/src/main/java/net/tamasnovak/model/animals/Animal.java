package net.tamasnovak.model.animals;

import net.tamasnovak.model.savannah.Cell;

public abstract class Animal {
  private int currentAge;
  private final int maximumAge;
  private boolean isAlive;
  private Cell livingArea;

  public Animal(int[] MAXIMUM_AGE, Cell livingArea) {
    this.currentAge = 1;
    this.maximumAge = drawMaximumAgeValue(MAXIMUM_AGE);
    this.isAlive = true;
    this.livingArea = livingArea;
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
