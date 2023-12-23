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
import net.tamasnovak.logic.routine.habitatRoutine.populatorRoutine.PopulatorRoutine;
import net.tamasnovak.logic.routine.animalRoutine.huntingRoutine.HuntingRoutine;
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

    SimulationController simulationController = new SimulationController(display, input, logger);

    // user decision needs to be made for the habitat as the matrix has to be supplied with the vegetation type;
    // for now it is hardcoded;
    GrassFactory grassFactory = new GrassFactory();
    VegetationFactory vegetationFactory = new VegetationFactory(grassFactory);

    Matrix habitatMatrix = new Matrix(vegetationFactory);

    AgingRoutine agingRoutine = new AgingRoutine(random, logger, habitatMatrix);
    HuntingRoutine huntingRoutine = new HuntingRoutine(random, logger, habitatMatrix);
    BreedingRoutine breedingRoutine = new BreedingRoutine(random, logger, habitatMatrix);

    AnimalFactory animalFactory = buildAbstractFactory(random, agingRoutine, huntingRoutine, breedingRoutine);

    breedingRoutine.setAnimalFactory(animalFactory);


    // reorganise this build process, so only that simulation gets built that is selected by the user (need to take out the ui input into its own class or move the habitat generations into another one).
    Habitat savannah = buildSavannahSimulation(random, logger, habitatMatrix, animalFactory);

    simulationController.addHabitat(savannah);

//    simulationController.runSimulation();
    savannah.runHabitat();
  }

  private static AnimalFactory buildAbstractFactory(Random random, AgingRoutine agingRoutine, HuntingRoutine huntingRoutine, BreedingRoutine breedingRoutine) {
    CarnivoreFactory carnivoreFactory = new CarnivoreFactory(random, agingRoutine, huntingRoutine, breedingRoutine);
    HerbivoreFactory herbivoreFactory = new HerbivoreFactory(random, agingRoutine, breedingRoutine);

    return new AnimalFactory(herbivoreFactory, carnivoreFactory);
  }

  private static Habitat buildSavannahSimulation(Random random, Logger logger, Matrix savannahMatrix, AnimalFactory animalFactory) {
    SavannahConfiguration savannahConfiguration = new SavannahConfiguration();
    PopulatorRoutine savannahPopulatorRoutine = new PopulatorRoutine(random, logger, savannahMatrix, savannahConfiguration, animalFactory);

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
