package net.tamasnovak;

import net.tamasnovak.logic.habitat.Habitat;
import net.tamasnovak.logic.habitat.HabitatConfiguration;
import net.tamasnovak.logic.habitat.savannah.SavannahConfiguration;
import net.tamasnovak.logic.routines.populatorRoutine.PopulatorRoutine;
import net.tamasnovak.logic.routines.populatorRoutine.PopulatorRoutineMessages;
import net.tamasnovak.logic.habitat.savannah.SavannahAnimalSearch;
import net.tamasnovak.logic.habitat.savannah.SavannahHuntingRoutine;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.logic.habitat.savannah.Savannah;
import net.tamasnovak.ui.logger.ConsoleLogger;
import net.tamasnovak.ui.logger.Logger;

import java.util.Random;

public class Application {
  public static void main(String[] args) {
    Random random = new Random();
    Logger logger = new ConsoleLogger();

    Matrix savannahMatrix = new Matrix();

    HabitatConfiguration savannahConfiguration = new SavannahConfiguration();

    PopulatorRoutineMessages populatorRoutineMessages = new PopulatorRoutineMessages();
    PopulatorRoutine savannahPopulatorRoutine = new PopulatorRoutine(random, logger, savannahMatrix, savannahConfiguration, populatorRoutineMessages);

    SavannahAnimalSearch savannahAnimalSearch = new SavannahAnimalSearch(savannahMatrix);
    SavannahHuntingRoutine savannahHuntingRoutine = new SavannahHuntingRoutine(logger, random, savannahAnimalSearch);

    Habitat savannah = new Savannah(random, logger, savannahMatrix, savannahPopulatorRoutine, savannahHuntingRoutine, savannahAnimalSearch);

    savannah.runSimulation();
  }
}

// check if a CarnivoreCreator / HerbivoreCreator would be better, to avoid passing the random to each instance + class instance type needs to be passed to creator as argument.

// possible extensions / further ideas:
// - terminal user input to decide on the size of the board
// - the number of animals should always be the half of the board size, e.g. a 20x20 board would have 200 animals on it (and always keep herbivore/carnivore ratio at 65%).
// - terminal user input to decide on how long the simulation should run - need to have a check method that stops the simulation if all animals are dead.
// - add a hyena/vulture class that eats dead animals.
