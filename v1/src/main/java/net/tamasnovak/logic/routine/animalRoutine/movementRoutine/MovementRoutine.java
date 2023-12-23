package net.tamasnovak.logic.routine.animalRoutine.movementRoutine;

import net.tamasnovak.logic.routine.animalRoutine.AnimalInstanceRoutine;
import net.tamasnovak.model.matrix.Cell;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.model.nature.animal.Animal;
import net.tamasnovak.model.nature.animal.carnivore.Carnivore;
import net.tamasnovak.model.nature.animal.herbivore.Herbivore;
import net.tamasnovak.model.nature.vegetation.Vegetation;
import net.tamasnovak.ui.logger.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public final class MovementRoutine extends AnimalInstanceRoutine {
  public MovementRoutine(Random random, Logger logger, Matrix matrix) {
    super(random, logger, matrix);
  }

  @Override
  public <T extends Animal> void run(T animal) {
    System.out.println(animal.getClass());
    int numberOfMoves = 0;
    Set<Cell> usedPositions = new HashSet<>();

    while (numberOfMoves < animal.getMaximumCellMovement()) {
      List<Vegetation> neighbourEmptyPositions = matrix.findNeighbourVegetation(animal);

      if (!neighbourEmptyPositions.isEmpty()) {
        if (animal instanceof Carnivore) {
          Cell newPosition =  handleCarnivoreMovement(animal, neighbourEmptyPositions, usedPositions);
          usedPositions.add(newPosition);
          System.out.printf("animal: %s | position: %s %n", animal.getId(), newPosition);
        }

        if (animal instanceof Herbivore) {
          Cell newPosition = handleHerbivoreMovement(animal, neighbourEmptyPositions);
          usedPositions.add(newPosition);
        }
      }

      numberOfMoves++;
    }

    usedPositions.clear();
  }

  private Cell handleCarnivoreMovement(Animal animal, List<Vegetation> emptyPositions, Set<Cell> usedPositions) {
    Cell randomEmptyPosition = findRandomPosition(emptyPositions);

    if (!usedPositions.contains(randomEmptyPosition)) {
      updateAnimalPosition(animal, randomEmptyPosition);
    } else {
      handleCarnivoreMovement(animal, emptyPositions, usedPositions);
    }

    return randomEmptyPosition;
  }

  private Cell handleHerbivoreMovement(Animal animal, List<Vegetation> emptyPositions) {
    Cell randomEmptyPosition = findRandomPosition(emptyPositions);

    updateAnimalPosition(animal, randomEmptyPosition);

    return randomEmptyPosition;
  }

  private Cell findRandomPosition(List<Vegetation> emptyPositions) {
    int randomEmptyPositionIndex = random.nextInt(emptyPositions.size());
    Vegetation randomEmptyPosition = emptyPositions.get(randomEmptyPositionIndex);

    return randomEmptyPosition.getCoordinates();
  }

  private void updateAnimalPosition(Animal animal, Cell randomEmptyPosition) {
    animal.setCoordinates(randomEmptyPosition);
  }
}
