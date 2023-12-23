package net.tamasnovak.logic.routine.animalRoutine.huntingRoutine;

import net.tamasnovak.logic.routine.animalRoutine.AnimalInstanceRoutine;
import net.tamasnovak.model.nature.animal.Animal;
import net.tamasnovak.model.nature.animal.carnivore.Carnivore;
import net.tamasnovak.model.nature.animal.herbivore.Herbivore;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.ui.logger.Logger;

import java.util.List;
import java.util.Random;

public final class HuntingRoutine extends AnimalInstanceRoutine {
  public HuntingRoutine(Random random, Logger logger, Matrix matrix) {
    super(random, logger, matrix);
  }

  @Override
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
      carnivore.die();
    }
  }

  private void killRandomNeighbourHerbivore(List<Herbivore> neighbourHerbivores) {
    int randomNumber = random.nextInt(neighbourHerbivores.size());
    Herbivore randomNeighbourHerbivore = neighbourHerbivores.get(randomNumber);

    randomNeighbourHerbivore.dieByBeingHunted();
  }
}
