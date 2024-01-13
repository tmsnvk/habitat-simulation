package net.tamasnovak.logic.routine.animalRoutine;

import net.tamasnovak.model.matrix.Position;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.model.nature.animal.Animal;
import net.tamasnovak.model.nature.vegetation.Vegetation;
import net.tamasnovak.model.nature.vegetation.VegetationSpecies;
import net.tamasnovak.model.nature.vegetation.VegetationType;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public final class MovementRoutine {
  private final Random random;
  private final Matrix matrix;

  public MovementRoutine(Random random, Matrix matrix) {
    this.random = random;
    this.matrix = matrix;
  }

  public <T extends Animal> void run(T animal) {
    int numberOfMoves = 0;
    Set<Position> usedPositions = new HashSet<>();

    while (numberOfMoves < animal.getMaximumCellMovement()) {
      List<Vegetation> neighbourEmptyPositions = matrix.findNeighbourVegetation(animal);

      if (!neighbourEmptyPositions.isEmpty()) {
        int xCoordinate = animal.getPosition().xCoordinate();
        int yCoordinate = animal.getPosition().yCoordinate();

        Position newPosition = handleMovement(animal, neighbourEmptyPositions, usedPositions);
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
    // hardcoded grass for now
    Vegetation newVegetation = matrix.createVegetation(VegetationType.GRASS, VegetationSpecies.FINGER_GRASS, xCoordinate, yCoordinate);
    matrix.placeNatureInstanceByPosition(xCoordinate, yCoordinate, newVegetation);
  }

  private Position handleMovement(Animal animal, List<Vegetation> emptyPositions, Set<Position> usedPositions) {
    Position randomEmptyPosition = findRandomPosition(emptyPositions);

    if (!usedPositions.contains(randomEmptyPosition)) {
      updateAnimalPosition(animal, randomEmptyPosition);
    } else {
      handleMovement(animal, emptyPositions, usedPositions);
    }

    return randomEmptyPosition;
  }

  private Position findRandomPosition(List<Vegetation> emptyPositions) {
    int randomEmptyPositionIndex = random.nextInt(emptyPositions.size());
    Vegetation randomEmptyPosition = emptyPositions.get(randomEmptyPositionIndex);

    return randomEmptyPosition.getPosition();
  }

  private void updateAnimalPosition(Animal animal, Position randomEmptyPosition) {
    animal.setPosition(randomEmptyPosition);
    matrix.placeNatureInstanceByPosition(animal.getPosition().xCoordinate(), animal.getPosition().yCoordinate(), animal);
  }
}
