package net.tamasnovak.logic.routine.animalRoutine.agingRoutine;

import net.tamasnovak.logic.routine.animalRoutine.AnimalInstanceRoutine;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.model.nature.animal.Animal;
import net.tamasnovak.ui.logger.Logger;

import java.util.Random;

public final class AgingRoutine extends AnimalInstanceRoutine {
  public AgingRoutine(Random random, Logger logger, Matrix matrix) {
    super(random, logger, matrix);
  }

  @Override
  public <T extends Animal> void run(T animal) {
    if (animal.getCurrentAge() == animal.getMaximumAge()) {
      animal.die();
    } else {
      animal.setCurrentAge(animal.getCurrentAge() + 1);
    }
  }
}
