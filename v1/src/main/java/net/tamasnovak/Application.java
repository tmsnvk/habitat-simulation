package net.tamasnovak;

import net.tamasnovak.logic.factory.animalFactory.AnimalFactory;
import net.tamasnovak.logic.factory.animalFactory.CarnivoreFactory;
import net.tamasnovak.logic.factory.animalFactory.HerbivoreFactory;
import net.tamasnovak.logic.factory.configurationFactory.ConfigurationFactory;
import net.tamasnovak.logic.factory.habitatFactory.HabitatFactory;
import net.tamasnovak.logic.factory.vegetationFactory.GrassFactory;
import net.tamasnovak.logic.factory.vegetationFactory.VegetationFactory;
import net.tamasnovak.logic.habitat.Habitat;
import net.tamasnovak.logic.routine.animalRoutine.AgingRoutine;
import net.tamasnovak.logic.routine.animalRoutine.BreedingRoutine;
import net.tamasnovak.logic.routine.animalRoutine.MovementRoutine;
import net.tamasnovak.logic.routine.animalRoutine.HuntingRoutine;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.ui.display.Display;
import net.tamasnovak.ui.input.Input;
import net.tamasnovak.ui.logger.ConsoleLogger;
import net.tamasnovak.ui.logger.Logger;
import net.tamasnovak.ui.simulation.SimulationController;
import net.tamasnovak.ui.simulation.SimulationUserInput;

import java.util.Random;
import java.util.Scanner;

public class Application {
  public static void main(String[] args) {
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);
    Display display = new Display();
    Input input = new Input(scanner);
    Logger consoleLogger = new ConsoleLogger();

    SimulationController simulationController = new SimulationController(scanner, display, input, consoleLogger);
    SimulationUserInput simulationUserInput = simulationController.startSimulationConfiguration();

    VegetationFactory vegetationFactory = buildVegetationAbstractFactory();
    Matrix habitatMatrix = new Matrix(simulationUserInput, vegetationFactory);
    AnimalFactory animalFactory = buildAnimalAbstractFactory(random, habitatMatrix);
    habitatMatrix.setAnimalFactory(animalFactory);

    Habitat habitat = buildSelectedHabitat(random, consoleLogger, display, habitatMatrix, simulationController, simulationUserInput);
    habitat.runHabitat();
  }

  private static AnimalFactory buildAnimalAbstractFactory(Random random, Matrix habitatMatrix) {
    AgingRoutine agingRoutine = new AgingRoutine();
    HuntingRoutine huntingRoutine = new HuntingRoutine(random, habitatMatrix);
    BreedingRoutine breedingRoutine = new BreedingRoutine(random, habitatMatrix);
    MovementRoutine movementRoutine = new MovementRoutine(random, habitatMatrix);

    CarnivoreFactory carnivoreFactory = new CarnivoreFactory(random, agingRoutine, huntingRoutine, breedingRoutine, movementRoutine);
    HerbivoreFactory herbivoreFactory = new HerbivoreFactory(random, agingRoutine, breedingRoutine, movementRoutine);

    return new AnimalFactory(herbivoreFactory, carnivoreFactory);
  }

  private static VegetationFactory buildVegetationAbstractFactory() {
    GrassFactory grassFactory = new GrassFactory();

    return new VegetationFactory(grassFactory);
  }

  private static Habitat buildSelectedHabitat(Random random, Logger logger, Display display, Matrix habitatMatrix, SimulationController simulationController, SimulationUserInput simulationUserInput) {
    ConfigurationFactory configurationFactory = new ConfigurationFactory();
    HabitatFactory habitatFactory = new HabitatFactory(random, logger, display, simulationController, simulationUserInput, habitatMatrix, configurationFactory);

    return habitatFactory.createHabitat();
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


// update the annual routine with a parallel stream:
// divide the 2d array into equal parts and create two buckets: side and middle,
//  - the middle ones are independent from other parts, so they are updated without any further issues
//  - the sides are collected into an 'optimistic' bucket and resolved/merged at the end
//- MapReduce algorithm
