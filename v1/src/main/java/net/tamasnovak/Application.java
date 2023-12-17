package net.tamasnovak;

import net.tamasnovak.logic.animalFactory.AbstractFactory;
import net.tamasnovak.logic.animalFactory.AnimalFactory;
import net.tamasnovak.logic.animalFactory.carnivoreFactory.CarnivoreFactory;
import net.tamasnovak.logic.animalFactory.herbivoreFactory.HerbivoreFactory;
import net.tamasnovak.logic.habitat.Habitat;
import net.tamasnovak.logic.habitat.savannah.SavannahConfiguration;
import net.tamasnovak.logic.habitat.savannah.SavannahMessages;
import net.tamasnovak.logic.routines.populatorRoutine.PopulatorRoutine;
import net.tamasnovak.logic.routines.populatorRoutine.PopulatorRoutineMessages;
import net.tamasnovak.logic.habitat.savannah.SavannahAnimalSearch;
import net.tamasnovak.logic.habitat.savannah.SavannahHuntingRoutine;
import net.tamasnovak.model.animal.Animal;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.logic.habitat.savannah.Savannah;
import net.tamasnovak.ui.UiMessages;
import net.tamasnovak.ui.display.Display;
import net.tamasnovak.ui.input.Input;
import net.tamasnovak.ui.logger.ConsoleLogger;
import net.tamasnovak.ui.logger.Logger;
import net.tamasnovak.ui.simulation.SimulationController;

import java.util.Random;

public class Application {
  public static void main(String[] args) {
    Random random = new Random();
    Display display = new Display();
    Input input = new Input();
    Logger logger = new ConsoleLogger();

    AbstractFactory<Animal> animalFactory = buildAbstractFactory(random);

    SimulationController simulationController = new SimulationController(display, input, logger);

    // reorganise this build process, so only that simulation gets built that is selected by the user (need to take out the ui input into its own class or move the habitat generations into another one).
    Habitat savannah = buildSavannahSimulation(random, logger, animalFactory);

    simulationController.addHabitat(savannah);

//    simulationController.runSimulation();
    savannah.runHabitat();
  }

  private static AbstractFactory<Animal> buildAbstractFactory(Random random) {
    HerbivoreFactory herbivoreFactory = new HerbivoreFactory(random);
    CarnivoreFactory carnivoreFactory = new CarnivoreFactory(random);

    return new AnimalFactory(herbivoreFactory, carnivoreFactory);
  }

  private static Habitat buildSavannahSimulation(Random random, Logger logger, AbstractFactory<Animal> animalFactory) {
    SavannahConfiguration savannahConfiguration = new SavannahConfiguration();
    Matrix savannahMatrix = new Matrix(random);

    PopulatorRoutine savannahPopulatorRoutine = new PopulatorRoutine(random, logger, savannahMatrix, savannahConfiguration, animalFactory);

    SavannahAnimalSearch savannahAnimalSearch = new SavannahAnimalSearch(savannahMatrix);
    SavannahHuntingRoutine savannahHuntingRoutine = new SavannahHuntingRoutine(logger, random, savannahAnimalSearch);

    return new Savannah(random, logger, savannahConfiguration, savannahMatrix, savannahPopulatorRoutine, savannahHuntingRoutine, savannahAnimalSearch);
  }
}


// wishlist:
// 1. deploy on digital ocean as a terminal programme;
// 2. start/end array positions saved to a db and can be retrieved with a seed number;
// 3. multiple habitats;
// 4. multiple languages;
// 5. variables can be selected by the user;
// 6. terminal running is colour-coded and array prints are with animal emojis;
// 7. full test coverage;
