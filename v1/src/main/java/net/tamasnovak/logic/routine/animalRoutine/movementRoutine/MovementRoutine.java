package net.tamasnovak.logic.routine.animalRoutine.movementRoutine;

import net.tamasnovak.logic.factory.vegetationFactory.VegetationFactory;
import net.tamasnovak.logic.routine.animalRoutine.AnimalInstanceRoutine;
import net.tamasnovak.model.matrix.Cell;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.model.nature.animal.Animal;
import net.tamasnovak.model.nature.vegetation.Vegetation;
import net.tamasnovak.model.nature.vegetation.VegetationSpecies;
import net.tamasnovak.model.nature.vegetation.VegetationType;
import net.tamasnovak.ui.logger.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public final class MovementRoutine extends AnimalInstanceRoutine {
  private final VegetationFactory vegetationFactory;

  public MovementRoutine(Random random, Logger logger, Matrix matrix, VegetationFactory vegetationFactory) {
    super(random, logger, matrix);
    this.vegetationFactory = vegetationFactory;
  }

  @Override
  public <T extends Animal> void run(T animal) {
    int numberOfMoves = 0;
    Set<Cell> usedPositions = new HashSet<>();

    while (numberOfMoves < animal.getMaximumCellMovement()) {
      List<Vegetation> neighbourEmptyPositions = matrix.findNeighbourVegetation(animal);

      if (!neighbourEmptyPositions.isEmpty()) {
        int xCoordinate = animal.getCoordinates().xCoordinate();
        int yCoordinate = animal.getCoordinates().yCoordinate();

        Cell newPosition = handleMovement(animal, neighbourEmptyPositions, usedPositions);
        usedPositions.add(newPosition);

        addVegetationToOriginalPosition(xCoordinate, yCoordinate);
      } else {
        break;
      }

      numberOfMoves++;
    }

    usedPositions.clear();
  }

  private void addVegetationToOriginalPosition(int xCoordinate, int yCoordinate) {
    Cell position = new Cell(xCoordinate, yCoordinate);

    // hardcoded grass for now
    Vegetation newVegetation = vegetationFactory.createVegetation(VegetationType.GRASS, VegetationSpecies.FINGER_GRASS, position);
    matrix.placeNatureInstanceByCoordinate(xCoordinate, yCoordinate, newVegetation);
  }

  private Cell handleMovement(Animal animal, List<Vegetation> emptyPositions, Set<Cell> usedPositions) {
    Cell randomEmptyPosition = findRandomPosition(emptyPositions);

    if (!usedPositions.contains(randomEmptyPosition)) {
      updateAnimalPosition(animal, randomEmptyPosition);
    } else {
      handleMovement(animal, emptyPositions, usedPositions);
    }

    return randomEmptyPosition;
  }

  private Cell findRandomPosition(List<Vegetation> emptyPositions) {
    int randomEmptyPositionIndex = random.nextInt(emptyPositions.size());
    Vegetation randomEmptyPosition = emptyPositions.get(randomEmptyPositionIndex);

    return randomEmptyPosition.getCoordinates();
  }

  private void updateAnimalPosition(Animal animal, Cell randomEmptyPosition) {
    animal.setCoordinates(randomEmptyPosition);
    matrix.placeNatureInstanceByCoordinate(animal.getCoordinates().xCoordinate(), animal.getCoordinates().yCoordinate(), animal);
  }
}
