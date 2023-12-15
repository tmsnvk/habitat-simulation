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

    UiMessages uiMessages = new UiMessages();
    SimulationController simulationController = new SimulationController(display, input, logger, uiMessages);

    // reorganise this build process, so only that simulation gets built that is selected by the user.
    Habitat savannah = buildSavannahSimulation(random, logger, animalFactory);

    simulationController.addHabitat(savannah);

//    simulationController.runSimulation();
    savannah.runHabitat();
  }

  private static AbstractFactory<Animal> buildAbstractFactory(Random random) {
    HerbivoreFactory herbivoreFactory = new HerbivoreFactory();
    CarnivoreFactory carnivoreFactory = new CarnivoreFactory();

    return new AnimalFactory(random, herbivoreFactory, carnivoreFactory);
  }

  private static Habitat buildSavannahSimulation(Random random, Logger logger, AbstractFactory<Animal> animalFactory) {
    SavannahConfiguration savannahConfiguration = new SavannahConfiguration();
    Matrix savannahMatrix = new Matrix(random);
    SavannahMessages savannahMessages = new SavannahMessages();

    PopulatorRoutineMessages populatorRoutineMessages = new PopulatorRoutineMessages();
    PopulatorRoutine savannahPopulatorRoutine = new PopulatorRoutine(random, logger, savannahMatrix, savannahConfiguration, animalFactory, populatorRoutineMessages);

    SavannahAnimalSearch savannahAnimalSearch = new SavannahAnimalSearch(savannahMatrix);
    SavannahHuntingRoutine savannahHuntingRoutine = new SavannahHuntingRoutine(logger, random, savannahAnimalSearch);

    return new Savannah(random, logger, savannahConfiguration, savannahMatrix, savannahMessages, savannahPopulatorRoutine, savannahHuntingRoutine, savannahAnimalSearch);
  }
}
