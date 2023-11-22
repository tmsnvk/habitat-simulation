package net.tamasnovak.model.animals.carnivores;

import net.tamasnovak.model.animals.Animal;
import net.tamasnovak.model.animals.AnimalType;
import net.tamasnovak.model.animals.herbivores.Herbivore;
import net.tamasnovak.model.matrix.Cell;

import java.util.List;
import java.util.Random;

public class Lion extends Carnivore {
  private static final int[] MAXIMUM_AGE_VALUES = new int[]{ 9, 10, 11, 12 };
  private static final AnimalType animalType = AnimalType.LION;

  public Lion(Cell livingArea, Random random) {
    super(livingArea, drawMaximumAgeValue(random), animalType);
  }

  private static int drawMaximumAgeValue(Random random) {
    int randomMaximumAge = random.nextInt(MAXIMUM_AGE_VALUES.length);

    return MAXIMUM_AGE_VALUES[randomMaximumAge];
  }

  @Override
  public void eat(List<Animal> neighbourAnimals) {
    int numberOfHerbivores = (int) neighbourAnimals.stream()
      .filter(animal -> animal instanceof Herbivore)
      .count();

    if (numberOfHerbivores == 0) {
      this.setHungerLevel(this.getHungerLevel() + 1);
    }
  }
}
