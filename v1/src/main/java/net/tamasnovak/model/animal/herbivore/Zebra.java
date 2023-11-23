package net.tamasnovak.model.animal.herbivore;

import net.tamasnovak.model.animal.Animal;
import net.tamasnovak.model.animal.AnimalType;
import net.tamasnovak.model.matrix.Cell;

import java.util.Random;

public class Zebra extends Herbivore {
  private static final int[] MAXIMUM_AGE_VALUES = new int[]{ 11, 12, 13, 14 };
  private static final AnimalType animalType = AnimalType.ZEBRA;

  public Zebra(Cell livingArea, Random random) {
    super(livingArea, drawMaximumAgeValue(random), animalType);
  }

  private static int drawMaximumAgeValue(Random random) {
    int randomMaximumAge = random.nextInt(MAXIMUM_AGE_VALUES.length);

    return MAXIMUM_AGE_VALUES[randomMaximumAge];
  }

  @Override
  public boolean canBreed() {
    return this.getCurrentAge() % 2 == 0;
  }

  @Override
  public Animal makeNewAnimal() {
    return null;
  }
}
