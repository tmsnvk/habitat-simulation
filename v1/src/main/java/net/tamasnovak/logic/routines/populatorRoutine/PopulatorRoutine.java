package net.tamasnovak.logic.routines.populatorRoutine;

import net.tamasnovak.logic.animalFactory.AnimalFactory;
import net.tamasnovak.logic.habitat.savannah.SavannahConfiguration;
import net.tamasnovak.logic.routines.Routine;
import net.tamasnovak.model.animal.Animal;
import net.tamasnovak.model.animal.AnimalSpecies;
import net.tamasnovak.model.animal.AnimalType;
import net.tamasnovak.model.matrix.Cell;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.ui.logger.Logger;

import java.util.Random;
import java.util.Set;

public final class PopulatorRoutine extends Routine {
  private final SavannahConfiguration habitatConfiguration;
  private final AnimalFactory animalFactory;

  public PopulatorRoutine(
    Random random,
    Logger logger,
    Matrix matrix,
    SavannahConfiguration habitatConfiguration,
    AnimalFactory animalFactory) {
    super(random, logger, matrix);
    this.habitatConfiguration = habitatConfiguration;
    this.animalFactory = animalFactory;
  }

  public void run() {
    displayPreRoutineLogs();
    populateMatrix();
    displayPostRoutineLogs();
  }

  private void populateMatrix() {
    int animalCounter = 0;

    while (animalCounter < habitatConfiguration.NUMBER_OF_ANIMALS) {
      int xCoordinate = random.nextInt(matrix.getLength());
      int yCoordinate = random.nextInt(matrix.getWidth());
      Animal animalByCoordinate = matrix.findAnimalByCoordinate(xCoordinate, yCoordinate);

      if (animalByCoordinate != null) {
        continue;
      }

      addAnimalToMatrix(xCoordinate, yCoordinate);
      animalCounter++;
    }
  }

  private void addAnimalToMatrix(int xCoordinate, int yCoordinate) {
    Cell livingArea = new Cell(xCoordinate, yCoordinate);
    Animal animal = generateAnimal(livingArea);

    matrix.placeAnimalByCoordinate(xCoordinate, yCoordinate, animal);
  }

  private Animal generateAnimal(Cell livingArea) {
    double coinFlipValue = random.nextDouble(0, 1);

    if (coinFlipValue <= habitatConfiguration.CHANCE_OF_HERBIVORE) {
      return animalFactory.createAnimal(AnimalType.HERBIVORE, habitatConfiguration.HERBIVORE, livingArea);
    } else {
      return animalFactory.createAnimal(AnimalType.CARNIVORE, habitatConfiguration.CARNIVORE, livingArea);
    }
  }

  private void displayPreRoutineLogs() {
    logger.logInfo(PopulatorRoutineMessages.START_POPULATE_MATRIX);
  }

  private void displayPostRoutineLogs() {
    logger.logInfo(PopulatorRoutineMessages.END_POPULATE_MATRIX);
    logger.logInfo(PopulatorRoutineMessages.ANIMAL_STATS_INTRO);

    Set<AnimalSpecies> animalSpeciesInMatrix = matrix.findDistinctSpecies();

    for (AnimalSpecies animalSpecies : animalSpeciesInMatrix) {
      int numberOfAnimals = matrix.countAnimalsBySpecies(animalSpecies);
      logger.logInfo(String.format(
        numberOfAnimals == 1 ? PopulatorRoutineMessages.ANIMAL_STATS_SUMMARY_SINGULAR : PopulatorRoutineMessages.ANIMAL_STATS_SUMMARY_PLURAL,
        numberOfAnimals,
        animalSpecies.name()
      ));
    }
  }
}
