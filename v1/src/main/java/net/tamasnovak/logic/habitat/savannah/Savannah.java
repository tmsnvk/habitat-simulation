package net.tamasnovak.logic.habitat.savannah;

import net.tamasnovak.logic.habitat.Habitat;
import net.tamasnovak.logic.habitat.HabitatType;
import net.tamasnovak.logic.routine.populatorRoutine.PopulatorRoutine;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.model.nature.animal.Animal;
import net.tamasnovak.model.nature.animal.AnimalSpecies;
import net.tamasnovak.ui.logger.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public final class Savannah extends Habitat {
  private static final HabitatType HABITAT_TYPE = HabitatType.SAVANNAH;

  public Savannah(
    Random random,
    Logger logger,
    SavannahConfiguration configuration,
    Matrix matrix,
    PopulatorRoutine populatorRoutine) {
    super(random, logger, HABITAT_TYPE, configuration, matrix, populatorRoutine);
  }

  @Override
  public void runHabitat() {
    logger.logInfo(SavannahMessages.START_SIMULATION);
    populatorRoutine.run();

    int yearCounter = 0;
    while (yearCounter < habitatConfiguration.LENGTH_OF_SIMULATION_YEARS) {
      System.out.printf("YEAR - %s%n", yearCounter);
      System.out.printf("zebra - %s%n", matrix.countAnimalsBySpecies(AnimalSpecies.ZEBRA));
      System.out.printf("leopard - %s%n", matrix.countAnimalsBySpecies(AnimalSpecies.LEOPARD));
      doPreAnnualRoutine();
      doAnnualRoutine();
      doPostAnnualRoutine();

      yearCounter++;
    }
  }

  private void doPreAnnualRoutine() {

  }

  private void doAnnualRoutine() {
    List<Animal> eligibleAnimals = matrix.findAliveAnimals();
    Collections.shuffle(eligibleAnimals);

    for (Animal animal : eligibleAnimals) {
      if (!animal.isAlive()) {
        continue;
      }

      animal.doLifeCycleMethods();
    }
  }

  private void doPostAnnualRoutine() {
    matrix.replaceDeadAnimalsWithVegetation(habitatConfiguration.getVegetationType(), habitatConfiguration.getVegetationSpecies());
    matrix.setBreedingStatusToDefault();
  }
}
