package net.tamasnovak.logic.routine.animalRoutine;

import net.tamasnovak.model.nature.animal.Animal;
import net.tamasnovak.model.nature.animal.carnivore.Carnivore;
import net.tamasnovak.model.nature.animal.herbivore.Herbivore;
import net.tamasnovak.model.matrix.Matrix;

import java.util.List;
import java.util.Random;

public final class HuntingRoutine {
  private final Random random;
  private final Matrix matrix;

  public HuntingRoutine(Random random, Matrix matrix) {
    this.random = random;
    this.matrix = matrix;
  }

  public <T extends Animal> void run(T animal) {
    List<Herbivore> neighbourHerbivores = matrix.findNeighbourAnimalsByTypeOrSpecies(animal, Herbivore.class);
    if (animal instanceof Carnivore carnivore) {
      if (neighbourHerbivores.isEmpty()) {
        increaseHungerLevel(carnivore);
        dieIfTooHungry(carnivore);
      } else {
        killRandomNeighbourHerbivore(neighbourHerbivores);
        resetHungerLevel(carnivore);
      }
    }
  }

  private void increaseHungerLevel(Carnivore carnivore) {
    carnivore.setHungerLevel(carnivore.getHungerLevel() + 1);
  }

  private void resetHungerLevel(Carnivore carnivore) {
    carnivore.setHungerLevel(0);
  }

  private void dieIfTooHungry(Carnivore carnivore) {
    if (carnivore.getHungerLevel() == carnivore.getMaximumHungerLevel()) {
      carnivore.perish();
    }
  }

  private void killRandomNeighbourHerbivore(List<Herbivore> neighbourHerbivores) {
    int randomNumber = random.nextInt(neighbourHerbivores.size());
    Herbivore randomNeighbourHerbivore = neighbourHerbivores.get(randomNumber);

    randomNeighbourHerbivore.perish();
  }
}
