package net.tamasnovak.logic.routines.huntingRoutine;

import net.tamasnovak.model.animal.carnivore.Carnivore;
import net.tamasnovak.model.animal.herbivore.Herbivore;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.ui.logger.Logger;

import java.util.List;
import java.util.Random;

public class HuntingRoutine {
  private final Logger logger;
  private final Random random;
  private final Matrix matrix;

  public HuntingRoutine(Logger logger, Random random, Matrix matrix) {
    this.logger = logger;
    this.random = random;
    this.matrix = matrix;
  }

  public void run(Carnivore carnivore) {
    List<Herbivore> neighbourHerbivores = matrix.findNeighbourAnimalsByType(carnivore, Herbivore.class);

    if (neighbourHerbivores.isEmpty()) {
      carnivore.increaseHungerLevel();
    } else {
      killRandomNeighbourHerbivore(neighbourHerbivores);
      carnivore.resetHungerLevel();
    }
  }

  private void killRandomNeighbourHerbivore(List<Herbivore> neighbourHerbivores) {
    int randomNumber = random.nextInt(neighbourHerbivores.size());
    Herbivore randomNeighbourHerbivore = neighbourHerbivores.get(randomNumber);

    randomNeighbourHerbivore.dieByBeingHunted();
  }
}
