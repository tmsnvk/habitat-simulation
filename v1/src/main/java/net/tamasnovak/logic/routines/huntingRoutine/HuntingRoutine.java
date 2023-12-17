package net.tamasnovak.logic.routines.huntingRoutine;

import net.tamasnovak.logic.routines.Routine;
import net.tamasnovak.model.animal.carnivore.Carnivore;
import net.tamasnovak.model.animal.herbivore.Herbivore;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.ui.logger.Logger;

import java.util.List;
import java.util.Random;

public class HuntingRoutine extends Routine {
  public HuntingRoutine(Random random, Logger logger, Matrix matrix) {
    super(random, logger, matrix);
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
