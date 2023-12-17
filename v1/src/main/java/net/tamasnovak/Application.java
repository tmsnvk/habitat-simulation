package net.tamasnovak;

import net.tamasnovak.logic.animalFactory.AnimalFactory;
import net.tamasnovak.logic.animalFactory.CarnivoreFactory;
import net.tamasnovak.logic.animalFactory.HerbivoreFactory;
import net.tamasnovak.logic.habitat.Habitat;
import net.tamasnovak.logic.habitat.savannah.SavannahConfiguration;
import net.tamasnovak.logic.routines.breedingRoutine.BreedingRoutine;
import net.tamasnovak.logic.routines.populatorRoutine.PopulatorRoutine;
import net.tamasnovak.logic.routines.huntingRoutine.HuntingRoutine;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.logic.habitat.savannah.Savannah;
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

    Matrix habitatMatrix = new Matrix();
    HuntingRoutine huntingRoutine = new HuntingRoutine(random, logger, habitatMatrix);
    BreedingRoutine breedingRoutine = new BreedingRoutine(random, logger, habitatMatrix);
    AnimalFactory animalFactory = buildAbstractFactory(random, huntingRoutine, breedingRoutine);

    SimulationController simulationController = new SimulationController(display, input, logger);

    // reorganise this build process, so only that simulation gets built that is selected by the user (need to take out the ui input into its own class or move the habitat generations into another one).
    Habitat savannah = buildSavannahSimulation(random, logger, habitatMatrix, animalFactory);

    simulationController.addHabitat(savannah);

//    simulationController.runSimulation();
    savannah.runHabitat();
  }

  private static AnimalFactory buildAbstractFactory(Random random, HuntingRoutine huntingRoutine, BreedingRoutine breedingRoutine) {
    CarnivoreFactory carnivoreFactory = new CarnivoreFactory(random, huntingRoutine, breedingRoutine);
    HerbivoreFactory herbivoreFactory = new HerbivoreFactory(random, breedingRoutine);

    return new AnimalFactory(herbivoreFactory, carnivoreFactory);
  }

  private static Habitat buildSavannahSimulation(Random random, Logger logger, Matrix savannahMatrix, AnimalFactory animalFactory) {
    SavannahConfiguration savannahConfiguration = new SavannahConfiguration();
    PopulatorRoutine savannahPopulatorRoutine = new PopulatorRoutine(random, logger, savannahMatrix, savannahConfiguration, animalFactory);

    return new Savannah(random, logger, savannahConfiguration, savannahMatrix, savannahPopulatorRoutine);
  }
}

// wishlist:
// 1. deploy on digital ocean as a terminal programme;
// 2. start/end array positions saved to a db and can be retrieved with a seed number;
// 3. multiple habitats;
// 4. multiple languages;
// 5. variables can be selected by the user (including animal types);
// 6. terminal running is colour-coded and array prints are with animal emojis;
// 7. terminal should provide messages about the animals, e.g. how long they live, etc.
// 8. full test coverage;
