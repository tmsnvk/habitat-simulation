package net.tamasnovak.logic.routine.breedingRoutine;

import net.tamasnovak.logic.factory.animalFactory.AnimalFactory;
import net.tamasnovak.logic.routine.Routine;
import net.tamasnovak.model.matrix.Cell;
import net.tamasnovak.model.nature.animal.Animal;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.model.nature.vegetation.Vegetation;
import net.tamasnovak.ui.logger.Logger;

import java.util.List;
import java.util.Random;

public final class BreedingRoutine extends Routine {
  private AnimalFactory animalFactory;

  public BreedingRoutine(Random random, Logger logger, Matrix matrix) {
    super(random, logger, matrix);
    this.animalFactory = null;
  }

  public void setAnimalFactory(AnimalFactory animalFactory) {
    this.animalFactory = animalFactory;
  }

  public void run(Animal animal) {
    List<? extends Animal> neighboursOfSameSpecies = matrix.findNeighbourAnimalsByTypeOrSpecies(animal, animal.getClass());
    List<Vegetation> vegetationPositions = matrix.findNeighbourVegetation(animal);

    if (!neighboursOfSameSpecies.isEmpty() && !vegetationPositions.isEmpty()) {
      addNewAnimalToHabitat(neighboursOfSameSpecies, vegetationPositions);
      changeBreedingStatus(animal);
    } else {
//      logger.logInfo(String.format("This %s was not able to breed this year.", animal.getSpecies()));
    }
  }

  private void addNewAnimalToHabitat(List<? extends Animal> neighboursOfSameSpecies, List<Vegetation> vegetationPositions) {
    int randomNeighbourIndex = random.nextInt(neighboursOfSameSpecies.size());
    Animal randomNeighbourOfSameSpecies = neighboursOfSameSpecies.get(randomNeighbourIndex);

    int randomEmptyPositionIndex = random.nextInt(vegetationPositions.size());
    Vegetation randomEmptyPosition = vegetationPositions.get(randomEmptyPositionIndex);

    int xCoordinate = randomEmptyPosition.getCoordinates().xCoordinate();
    int yCoordinate = randomEmptyPosition.getCoordinates().yCoordinate();

    Cell coordinates = new Cell(xCoordinate, yCoordinate);
    Animal newAnimal = animalFactory.createAnimal(randomNeighbourOfSameSpecies.getType(), randomNeighbourOfSameSpecies.getSpecies(), coordinates);
    matrix.placeAnimalByCoordinate(xCoordinate, yCoordinate, newAnimal);

    changeBreedingStatus(randomNeighbourOfSameSpecies);
  }

  private void changeBreedingStatus(Animal animal) {
    animal.setDidAlreadyBreedInGivenYear(true);
  }
}
