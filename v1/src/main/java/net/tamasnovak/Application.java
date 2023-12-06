package net.tamasnovak;

import net.tamasnovak.logic.animalFactory.AbstractFactory;
import net.tamasnovak.logic.animalFactory.AnimalFactory;
import net.tamasnovak.logic.habitat.Habitat;
import net.tamasnovak.logic.habitat.HabitatConfiguration;
import net.tamasnovak.logic.habitat.savannah.SavannahConfiguration;
import net.tamasnovak.logic.routines.populatorRoutine.PopulatorRoutine;
import net.tamasnovak.logic.routines.populatorRoutine.PopulatorRoutineMessages;
import net.tamasnovak.logic.habitat.savannah.SavannahAnimalSearch;
import net.tamasnovak.logic.habitat.savannah.SavannahHuntingRoutine;
import net.tamasnovak.model.animal.Animal;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.logic.habitat.savannah.Savannah;
import net.tamasnovak.ui.logger.ConsoleLogger;
import net.tamasnovak.ui.logger.Logger;

import java.util.Random;

public class Application {
  public static void main(String[] args) {
    Random random = new Random();
    Logger logger = new ConsoleLogger();
    AbstractFactory<Animal> animalFactory = new AnimalFactory(random);

    Habitat savannah = buildSavannahSimulation(random, logger, animalFactory);

    savannah.runSimulation();
  }

  private static Habitat buildSavannahSimulation(Random random, Logger logger, AbstractFactory<Animal> animalFactory) {
    Matrix savannahMatrix = new Matrix();
    HabitatConfiguration savannahConfiguration = new SavannahConfiguration();

    PopulatorRoutineMessages populatorRoutineMessages = new PopulatorRoutineMessages();
    PopulatorRoutine savannahPopulatorRoutine = new PopulatorRoutine(random, logger, savannahMatrix, savannahConfiguration, animalFactory, populatorRoutineMessages);

    SavannahAnimalSearch savannahAnimalSearch = new SavannahAnimalSearch(savannahMatrix);
    SavannahHuntingRoutine savannahHuntingRoutine = new SavannahHuntingRoutine(logger, random, savannahAnimalSearch);

    return new Savannah(random, logger, savannahConfiguration, savannahMatrix, savannahPopulatorRoutine, savannahHuntingRoutine, savannahAnimalSearch);
  }
}
