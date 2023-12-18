package net.tamasnovak.logic.routine.breedingRoutine;

import net.tamasnovak.logic.routine.Routine;
import net.tamasnovak.model.nature.animal.Animal;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.model.nature.vegetation.Vegetation;
import net.tamasnovak.ui.logger.Logger;

import java.util.List;
import java.util.Random;

public final class BreedingRoutine extends Routine {
  public BreedingRoutine(Random random, Logger logger, Matrix matrix) {
    super(random, logger, matrix);
  }

  public void run(Animal animal) {
    List<? extends Animal> neighboursOfSameSpecies = matrix.findNeighbourAnimalsByTypeOrSpecies(animal, animal.getClass());
    List<Vegetation> vegetationPositions = matrix.findNeighbourVegetation(animal);

    if (!neighboursOfSameSpecies.isEmpty() && !vegetationPositions.isEmpty()) {
//      addNewAnimalToHabitat(neighboursOfSameSpecies, vegetationPositions);
    } else {
//      logger.logInfo(String.format("This %s was not able to breed this year.", animal.getAnimalSpecies()));
    }
  }

  private void addNewAnimalToHabitat(List<? extends Animal> neighboursOfSameSpecies, List<Animal> emptyPositions) {
    int neighbourNumber = random.nextInt(neighboursOfSameSpecies.size());
    Animal randomNeighbourOfSameSpecies = neighboursOfSameSpecies.get(neighbourNumber);

    int emptyPositionNumber = random.nextInt(emptyPositions.size());
    Animal randomEmptyPosition = emptyPositions.get(emptyPositionNumber);
//    System.out.println(randomEmptyPosition.getLivingArea());
//    matrix.placeAnimalByCoordinate();
  }
}
