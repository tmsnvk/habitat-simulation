package net.tamasnovak;

import net.tamasnovak.logic.savannah.SavannahAnimalSearch;
import net.tamasnovak.logic.savannah.SavannahHuntingRoutine;
import net.tamasnovak.logic.savannah.SavannahPopulatingRoutine;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.logic.savannah.Savannah;
import net.tamasnovak.ui.logger.ConsoleLogger;
import net.tamasnovak.ui.logger.Logger;

import java.util.Random;

public class Application {
  public static void main(String[] args) {
    Random random = new Random();
    Logger logger = new ConsoleLogger();

    Matrix matrix = new Matrix();

    SavannahPopulatingRoutine savannahPopulatingRoutine = new SavannahPopulatingRoutine(random, logger, matrix);
    SavannahAnimalSearch savannahAnimalSearch = new SavannahAnimalSearch(matrix);
    SavannahHuntingRoutine savannahHuntingRoutine = new SavannahHuntingRoutine(logger, random, savannahAnimalSearch);

    Savannah savannah = new Savannah(random, logger, matrix, savannahPopulatingRoutine, savannahHuntingRoutine, savannahAnimalSearch);

    savannah.runSimulation();
  }
}

// possible extensions / further ideas:
// - terminal user input to decide on the size of the board
// - the number of animals should always be the half of the board size, e.g. a 20x20 board would have 200 animals on it (and always keep herbivore/carnivore ratio at 65%).
// - terminal user input to decide on how long the simulation should run - need to have a check method that stops the simulation if all animals are dead.
// - add a hyena/vulture class that eats dead animals.
