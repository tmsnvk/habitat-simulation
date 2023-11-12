package net.tamasnovak;

import net.tamasnovak.logic.savannah.SavannahPopulator;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.logic.savannah.Savannah;
import net.tamasnovak.ui.logger.ConsoleLogger;
import net.tamasnovak.ui.logger.Logger;

import java.util.Random;

public class Application {
  public static void main(String[] args) {
    Random random = new Random();

    Matrix matrix = new Matrix();
    Logger logger = new ConsoleLogger();

    SavannahPopulator savannahPopulator = new SavannahPopulator(random, matrix, logger);
    Savannah savannah = new Savannah(matrix, savannahPopulator, logger);

    savannah.runSimulation();
  }
}

// possible extensions / further ideas:
// - terminal user input to decide on the size of the board
// - the number of animals should always be the half of the board size, e.g. a 20x20 board would have 200 animals on it (and always keep herbivore/carnivore ratio at 65%).
// - terminal user input to decide on how long the simulation should run - need to have a check method that stops the simulation if all animals are dead.
// - add a hyena/vulture class that eats dead animals.
