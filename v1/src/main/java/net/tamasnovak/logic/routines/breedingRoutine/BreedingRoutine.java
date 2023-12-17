package net.tamasnovak.logic.routines.breedingRoutine;

import net.tamasnovak.logic.routines.Routine;
import net.tamasnovak.model.animal.Animal;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.ui.logger.Logger;

import java.util.List;
import java.util.Random;

public final class BreedingRoutine extends Routine {
  public BreedingRoutine(Random random, Logger logger, Matrix matrix) {
    super(random, logger, matrix);
  }

  public void run() {
//    List<? extends Animal> listNeighbourSameSpecies = matrix.findNeighbourAnimalsByType(animal, animal.getClass().getSuperclass());
//    boolean canAnimalBreed = animal.canBreed();
  }
}
