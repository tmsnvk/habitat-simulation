package net.tamasnovak.logic.routine.animalRoutine.breedingRoutine;

import net.tamasnovak.logic.routine.animalRoutine.AnimalInstanceRoutine;
import net.tamasnovak.model.nature.animal.Animal;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.model.nature.vegetation.Vegetation;
import net.tamasnovak.ui.logger.Logger;

import java.util.List;
import java.util.Random;

public final class BreedingRoutine extends AnimalInstanceRoutine {
  public BreedingRoutine(Random random, Logger logger, Matrix matrix) {
    super(random, logger, matrix);
  }

  @Override
  public <T extends Animal> void run(T animal) {
    List<? extends Animal> neighboursOfSameSpecies = matrix.findNeighbourAnimalsByTypeOrSpecies(animal, animal.getClass());
    List<Vegetation> neighbourEmptyPositions = matrix.findNeighbourVegetation(animal);

    if (isAbleToBreed(neighboursOfSameSpecies, neighbourEmptyPositions, animal)) {
      addNewAnimalToHabitat(neighboursOfSameSpecies, neighbourEmptyPositions);
      changeBreedingStatus(animal);
    } else {
//      logger.logInfo(String.format("This %s was not able to breed this year.", animal.getSpecies()));
    }
  }

  private <T extends Animal> boolean isAbleToBreed(List<T> neighboursOfSameSpecies, List<Vegetation> neighbourEmptyPositions, Animal animal) {
    return !neighboursOfSameSpecies.isEmpty() && !neighbourEmptyPositions.isEmpty() && animal.isAbleToBreed() && !animal.didAlreadyBreedInGivenYear();
  }

  private <T extends Animal> void addNewAnimalToHabitat(List<T> neighboursOfSameSpecies, List<Vegetation> neighbourEmptyPositions) {
    int randomNeighbourIndex = random.nextInt(neighboursOfSameSpecies.size());
    Animal breedingMate = neighboursOfSameSpecies.get(randomNeighbourIndex);

    int randomEmptyPositionIndex = random.nextInt(neighbourEmptyPositions.size());
    Vegetation randomEmptyPosition = neighbourEmptyPositions.get(randomEmptyPositionIndex);

    int xCoordinate = randomEmptyPosition.getPosition().xCoordinate();
    int yCoordinate = randomEmptyPosition.getPosition().yCoordinate();

    Animal newAnimal = matrix.createAnimal(breedingMate.getType(), breedingMate.getSpecies(), xCoordinate, yCoordinate);
    matrix.placeNatureInstanceByPosition(xCoordinate, yCoordinate, newAnimal);

    changeBreedingStatus(breedingMate);
  }

  private void changeBreedingStatus(Animal animal) {
    animal.setDidAlreadyBreedInRunningYear(true);
  }
}
