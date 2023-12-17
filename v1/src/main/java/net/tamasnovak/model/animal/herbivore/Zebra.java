package net.tamasnovak.model.animal.herbivore;

import net.tamasnovak.model.animal.Animal;
import net.tamasnovak.model.animal.AnimalSpecies;
import net.tamasnovak.model.matrix.Cell;

import java.util.Random;

public final class Zebra extends Herbivore {
  private static int idCounter = 0;
  private final String id;
  private static final int[] MAXIMUM_AGE_VALUES = new int[]{ 11, 12, 13, 14 };
  private static final AnimalSpecies SPECIES = AnimalSpecies.ZEBRA;

  public Zebra(Cell livingArea, Random random) {
    super(livingArea, drawMaximumAgeValue(random), SPECIES);
    this.id = String.format("%s-%s", SPECIES, ++idCounter);
  }

  private static int drawMaximumAgeValue(Random random) {
    int randomMaximumAge = random.nextInt(MAXIMUM_AGE_VALUES.length);

    return MAXIMUM_AGE_VALUES[randomMaximumAge];
  }

  @Override
  protected void move() {

  }

  @Override
  public boolean canBreed() {
    return this.getCurrentAge() % 2 == 0;
  }

  @Override
  public Animal breed() {
    return null;
  }

  @Override
  public String toString() {
    return String.format("[Id]: %s | [Species]: %s | [Type]: %s | [Living area]: %s | [Maximum age]: %s | [Current Age]: %s | [Is alive?]: %s", id, SPECIES, TYPE, livingArea, maximumAge, currentAge, isAlive);
  }
}
