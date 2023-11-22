package net.tamasnovak.model.animals.carnivores;

import net.tamasnovak.model.animals.AnimalType;
import net.tamasnovak.model.matrix.Cell;

import java.util.Random;

public class Lion extends Carnivore {
  private static final int[] MAXIMUM_AGE_VALUES = new int[]{ 9, 10, 11, 12 };
  private static final int MAXIMUM_HUNGER_LEVEL = 2;
  private static final AnimalType animalType = AnimalType.LION;

  public Lion(Cell livingArea, Random random) {
    super(livingArea, drawMaximumAgeValue(random), animalType);
  }

  private static int drawMaximumAgeValue(Random random) {
    int randomMaximumAge = random.nextInt(MAXIMUM_AGE_VALUES.length);

    return MAXIMUM_AGE_VALUES[randomMaximumAge];
  }

  @Override
  public void perishIfTooHungry() {
    if (this.getHungerLevel() == MAXIMUM_HUNGER_LEVEL) {
      this.setAlive(false);
    }
  }
}
