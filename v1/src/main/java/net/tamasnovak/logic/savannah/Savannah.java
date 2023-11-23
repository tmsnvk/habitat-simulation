package net.tamasnovak.logic.savannah;

import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.model.animal.Animal;
import net.tamasnovak.ui.logger.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Savannah {
  private final Random random;
  private final Logger logger;
  private final Matrix matrix;
  private final SavannahPopulatingRoutine savannahPopulatingRoutine;
  private final SavannahHuntingRoutine savannahHuntingRoutine;
  private final SavannahAnimalSearch savannahAnimalSearch;

  public Savannah(Random random, Logger logger, Matrix matrix, SavannahPopulatingRoutine savannahPopulatingRoutine, SavannahHuntingRoutine savannahHuntingRoutine, SavannahAnimalSearch savannahAnimalSearch) {
    this.random = random;
    this.logger = logger;
    this.matrix = matrix;
    this.savannahPopulatingRoutine = savannahPopulatingRoutine;
    this.savannahHuntingRoutine = savannahHuntingRoutine;
    this.savannahAnimalSearch = savannahAnimalSearch;
  }

  public void runSimulation() {
    logger.logInfo(SavannahMessages.START_SIMULATION);
    savannahPopulatingRoutine.runPopulator();

    int yearCounter = 0;

    while (yearCounter < SavannahConfiguration.LENGTH_OF_SIMULATION_YEARS) {
      performPreAnnualRoutine();
      performAnnualAnimalRoutine();
      performPostAnnualRoutine();

      yearCounter++;
//      System.out.printf("zebra - %s%n", matrix.countAnimalType(AnimalType.ZEBRA));
//      System.out.printf("lion - %s%n", matrix.countAnimalType(AnimalType.LION));
    }
  }

  private void performPreAnnualRoutine() {
  }

  private void performAnnualAnimalRoutine() {
    List<Animal> eligibleAnimalsForTheYear = matrix.getAnimalsCurrentlyLivingOnSavannah();
    Collections.shuffle(eligibleAnimalsForTheYear);

    for (Animal animal : eligibleAnimalsForTheYear) {
      if (!animal.isAlive()) {
        continue;
      }

      animal.age();

      if (!animal.isAlive()) {
        continue;
      }

      savannahHuntingRoutine.run(animal);
      doBreedingRoutine(animal);

      animal.move();
    }
  }

  private void performPostAnnualRoutine() {
  }

  private void doBreedingRoutine(Animal animal) {
    List<? extends Animal> listNeighbourSameSpecies = savannahAnimalSearch.findNeighbouringSameSpecies(animal, animal.getClass().getSuperclass());
    System.out.printf("current animal %s | neighbours %s%n", animal, listNeighbourSameSpecies);
    boolean canAnimalBreed = animal.canBreed();

    if (canAnimalBreed) {
      System.out.println("BREED");
//      try {
////        Animal newBornAnimal = animal.getClass().getDeclaredConstructor().newInstance();
////        newBornAnimal.
//      } catch (Exception exception) {
//        logger.logError(exception.getMessage());
//      }
    }
  }

//  private void removeDeadAnimal(Animal animal) {
//    matrix.removeDeadAnimal(animal);
//  }
}
