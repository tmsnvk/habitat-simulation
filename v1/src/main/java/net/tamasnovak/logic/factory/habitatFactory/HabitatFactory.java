package net.tamasnovak.logic.factory.habitatFactory;

import net.tamasnovak.logic.factory.configurationFactory.ConfigurationFactory;
import net.tamasnovak.logic.habitat.HabitatConfiguration;
import net.tamasnovak.logic.factory.configurationFactory.SavannahConfiguration;
import net.tamasnovak.logic.habitat.Habitat;
import net.tamasnovak.logic.habitat.savannah.Savannah;
import net.tamasnovak.logic.routine.habitatRoutine.populatorRoutine.PopulatorRoutine;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.ui.logger.Logger;
import net.tamasnovak.ui.simulation.SimulationController;
import net.tamasnovak.ui.simulation.SimulationUserInput;

import java.util.Random;

public final class HabitatFactory {
  private final Random random;
  private final Logger logger;
  private final SimulationController simulationController;
  private final SimulationUserInput simulationUserInput;
  private final Matrix habitatMatrix;
  private final ConfigurationFactory configurationFactory;

  public HabitatFactory(Random random, Logger logger, SimulationController simulationController, SimulationUserInput simulationUserInput, Matrix habitatMatrix, ConfigurationFactory configurationFactory) {
    this.random = random;
    this.logger = logger;
    this.simulationController = simulationController;
    this.simulationUserInput = simulationUserInput;
    this.habitatMatrix = habitatMatrix;
    this.configurationFactory = configurationFactory;
  }

  public Habitat createHabitat() {
    Habitat habitat = null;

    switch (simulationUserInput.getHabitatType()) {
      case SAVANNAH -> habitat = createSavannahHabitat();
    }

    return habitat;
  }

  private Savannah createSavannahHabitat() {
    SavannahConfiguration habitatConfiguration = (SavannahConfiguration) createHabitatConfiguration();
    PopulatorRoutine populatorRoutine = createPopulatorRoutine(habitatConfiguration);

    return new Savannah(random, logger, habitatConfiguration, habitatMatrix, populatorRoutine);
  }

  private HabitatConfiguration createHabitatConfiguration() {
    return configurationFactory.createHabitatConfiguration(simulationUserInput);
  }

  private PopulatorRoutine createPopulatorRoutine(HabitatConfiguration habitatConfiguration) {
    return new PopulatorRoutine(random, logger, habitatMatrix, habitatConfiguration, simulationController);
  }
}
