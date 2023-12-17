package net.tamasnovak.logic.habitat.savannah;

import net.tamasnovak.logic.habitat.Habitat;
import net.tamasnovak.logic.habitat.HabitatConfiguration;
import net.tamasnovak.logic.habitat.HabitatType;
import net.tamasnovak.logic.routines.populatorRoutine.PopulatorRoutine;
import net.tamasnovak.model.animal.carnivore.Carnivore;
import net.tamasnovak.model.animal.herbivore.Herbivore;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.model.animal.Animal;
import net.tamasnovak.ui.logger.Logger;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public final class Savannah extends Habitat {
  private static final HabitatType HABITAT_TYPE = HabitatType.SAVANNAH;
  private final SavannahHuntingRoutine savannahHuntingRoutine;

  public Savannah(
    Random random,
    Logger logger,
    HabitatConfiguration configuration,
    Matrix matrix,
    PopulatorRoutine populatorRoutine,
    SavannahHuntingRoutine savannahHuntingRoutine) {
    super(random, logger, HABITAT_TYPE, configuration, matrix, populatorRoutine);
    this.savannahHuntingRoutine = savannahHuntingRoutine;
  }

  @Override
  public void runHabitat() {
    logger.logInfo(SavannahMessages.START_SIMULATION);
    populatorRoutine.run();

    int yearCounter = 0;
    while (yearCounter < habitatConfiguration.LENGTH_OF_SIMULATION_YEARS) {
//      System.out.printf("zebra - %s%n", matrix.countAnimalsBySpecies(AnimalSpecies.ZEBRA));
//      System.out.printf("leopard - %s%n", matrix.countAnimalsBySpecies(AnimalSpecies.LEOPARD));
      doPreAnnualRoutine();
      doAnnualRoutine();
      doPostAnnualRoutine();

      yearCounter++;
    }
  }

  private void doPreAnnualRoutine() {
  }

  private void doAnnualRoutine() {
    List<Animal> eligibleAnimalsForTheYear = matrix.findAliveAnimals();
    Set<Animal> animalsDiedDuringTheYear = new HashSet<>();

    Collections.shuffle(eligibleAnimalsForTheYear);

    for (Animal animal : eligibleAnimalsForTheYear) {
      if (animalsDiedDuringTheYear.contains(animal)) {
        continue;
      }

      animal.doLifeCycleMethods();

      if (!animal.isAlive()) {
        animalsDiedDuringTheYear.add(animal);
      }


//      if (animal instanceof Carnivore carnivore) {
//        doHuntingRoutine(carnivore, animalsDiedDuringTheYear);
//      }
//      // animal lifecycle method that includes all lower level methods, so only one method would have to be called here
//
//      doBreedingRoutine(animal);
    }
  }

  private void doPostAnnualRoutine() {
    matrix.removeDeadAnimals();
  }

  private void doHuntingRoutine(Carnivore carnivore, Set<Animal> deadAnimals) {
    Herbivore killedHerbivore = savannahHuntingRoutine.run(carnivore);

    if (killedHerbivore != null) {
      deadAnimals.add(killedHerbivore);
    }
  }

  private void doBreedingRoutine(Animal animal) {
//    List<? extends Animal> listNeighbourSameSpecies = savannahAnimalSearch.findNeighbourAnimalTypeInstances(animal, animal.getClass().getSuperclass());
//    boolean canAnimalBreed = animal.canBreed();
  }
}
