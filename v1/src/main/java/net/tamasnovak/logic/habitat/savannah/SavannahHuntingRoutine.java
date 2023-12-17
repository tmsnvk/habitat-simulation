package net.tamasnovak.logic.habitat.savannah;

import net.tamasnovak.model.animal.carnivore.Carnivore;
import net.tamasnovak.model.animal.herbivore.Herbivore;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.ui.logger.Logger;

import java.util.List;
import java.util.Random;

public class SavannahHuntingRoutine {
  private final Logger logger;
  private final Random random;
  private final Matrix matrix;

  public SavannahHuntingRoutine(Logger logger, Random random, Matrix matrix) {
    this.logger = logger;
    this.random = random;
    this.matrix = matrix;
  }

  Herbivore run(Carnivore carnivore) {
    List<Herbivore> neighbourHerbivores = matrix.findNeighbourAnimalsByType(carnivore, Herbivore.class);
    Herbivore killedHerbivore = null;

    if (neighbourHerbivores.isEmpty()) {
      carnivore.increaseHungerLevelAfterUnsuccessfulHunt();
    } else {
      killedHerbivore = killRandomHerbivoreNeighbour(neighbourHerbivores);
      carnivore.resetHungerLevelAfterSuccessfulHunt();
    }

    return killedHerbivore;
  }

  private Herbivore killRandomHerbivoreNeighbour(List<Herbivore> neighbourHerbivores) {
    int randomNumber = random.nextInt(neighbourHerbivores.size());
    Herbivore randomNeighbourHerbivore = neighbourHerbivores.get(randomNumber);
    randomNeighbourHerbivore.perishIfKilledByCarnivore();

    return randomNeighbourHerbivore;
  }
}
