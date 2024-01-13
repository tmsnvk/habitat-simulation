package net.tamasnovak.logic.routine.animalRoutine;

import net.tamasnovak.model.nature.animal.Animal;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.model.nature.vegetation.Vegetation;

import java.util.List;
import java.util.Random;

public final class BreedingRoutine {
  private final Random random;
  private final Matrix matrix;

  public BreedingRoutine(Random random, Matrix matrix) {
    this.random = random;
    this.matrix = matrix;
  }

  public <T extends Animal> void run(T animal) {
    List<? extends Animal> neighboursOfSameSpecies = matrix.findNeighbourNatureInstancesByTypeOrSpecies(animal, animal.getClass());
    List<Vegetation> neighbourVegetationPositions = matrix.findNeighbourNatureInstancesByTypeOrSpecies(animal, Vegetation.class);

    if (isAbleToBreed(neighboursOfSameSpecies, neighbourVegetationPositions, animal)) {
      addNewAnimalToHabitat(neighboursOfSameSpecies, neighbourVegetationPositions);
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
