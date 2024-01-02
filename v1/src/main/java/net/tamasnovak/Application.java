package net.tamasnovak;

import net.tamasnovak.logic.factory.animalFactory.AnimalFactory;
import net.tamasnovak.logic.factory.animalFactory.CarnivoreFactory;
import net.tamasnovak.logic.factory.animalFactory.HerbivoreFactory;
import net.tamasnovak.logic.factory.vegetationFactory.GrassFactory;
import net.tamasnovak.logic.factory.vegetationFactory.VegetationFactory;
import net.tamasnovak.logic.habitat.Habitat;
import net.tamasnovak.logic.habitat.savannah.SavannahConfiguration;
import net.tamasnovak.logic.routine.animalRoutine.agingRoutine.AgingRoutine;
import net.tamasnovak.logic.routine.animalRoutine.breedingRoutine.BreedingRoutine;
import net.tamasnovak.logic.routine.animalRoutine.movementRoutine.MovementRoutine;
import net.tamasnovak.logic.routine.habitatRoutine.populatorRoutine.PopulatorRoutine;
import net.tamasnovak.logic.routine.animalRoutine.huntingRoutine.HuntingRoutine;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.logic.habitat.savannah.Savannah;
import net.tamasnovak.ui.display.Display;
import net.tamasnovak.ui.input.Input;
import net.tamasnovak.ui.logger.ConsoleLogger;
import net.tamasnovak.ui.logger.Logger;
import net.tamasnovak.ui.simulation.SimulationController;
import net.tamasnovak.ui.simulation.SimulationUserInputData;

import java.util.Random;
import java.util.Scanner;

public class Application {
  public static void main(String[] args) {
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);
    Display display = new Display();
    Input input = new Input();
    Logger consoleLogger = new ConsoleLogger();

    SimulationController simulationController = new SimulationController(scanner, display, input, consoleLogger);
    SimulationUserInputData simulationUserInputData =  simulationController.startSimulation();

    // user decision needs to be made for the habitat as the matrix has to be supplied with the vegetation type;
    // for now it is hardcoded in the matrix's createHabitat() method;
    VegetationFactory vegetationFactory = buildVegetationAbstractFactory();

    Matrix habitatMatrix = new Matrix(vegetationFactory);

    AnimalFactory animalFactory = buildAnimalAbstractFactory(random, consoleLogger, habitatMatrix);
    habitatMatrix.setAnimalFactory(animalFactory);
    // reorganise this build process, so only that simulation gets built that is selected by the user (need to take out the ui input into its own class or move the habitat generations into another one).
    Habitat savannah = buildSavannahSimulation(random, consoleLogger, habitatMatrix, simulationController);

//    savannah.runHabitat();
  }

  private static AnimalFactory buildAnimalAbstractFactory(Random random, Logger consoleLogger, Matrix habitatMatrix) {
    AgingRoutine agingRoutine = new AgingRoutine(random, consoleLogger, habitatMatrix);
    HuntingRoutine huntingRoutine = new HuntingRoutine(random, consoleLogger, habitatMatrix);
    BreedingRoutine breedingRoutine = new BreedingRoutine(random, consoleLogger, habitatMatrix);
    MovementRoutine movementRoutine = new MovementRoutine(random, consoleLogger, habitatMatrix);

    CarnivoreFactory carnivoreFactory = new CarnivoreFactory(random, agingRoutine, huntingRoutine, breedingRoutine, movementRoutine);
    HerbivoreFactory herbivoreFactory = new HerbivoreFactory(random, agingRoutine, breedingRoutine, movementRoutine);

    return new AnimalFactory(herbivoreFactory, carnivoreFactory);
  }

  private static VegetationFactory buildVegetationAbstractFactory() {
    GrassFactory grassFactory = new GrassFactory();

    return new VegetationFactory(grassFactory);
  }

  private static Habitat buildSavannahSimulation(Random random, Logger logger, Matrix savannahMatrix, SimulationController simulationController) {
    SavannahConfiguration savannahConfiguration = new SavannahConfiguration();
    PopulatorRoutine savannahPopulatorRoutine = new PopulatorRoutine(random, logger, savannahMatrix, savannahConfiguration, simulationController);

    return new Savannah(random, logger, savannahConfiguration, savannahMatrix, savannahPopulatorRoutine);
  }
}

// wishlist:
// features:
// while building the habitat, it should not be filled with blank fields but actual soil, e.g. grass or trees. some terrain, like water, would not be passable.
// multiple habitats;
// multiple languages;
// variables can be selected by the user (including animal types);
// terminal running is colour-coded and array prints are with animal emojis;
// terminal should provide messages about the animals, e.g. how long they live, etc.

// deployment, etc.:
// deploy on digital ocean as a terminal programme;
// start/end array positions saved to a db and can be retrieved with a seed number;
// full test coverage;
