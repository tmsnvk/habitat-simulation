package net.tamasnovak.logic.factory.configurationFactory;

import net.tamasnovak.logic.habitat.HabitatConfiguration;
import net.tamasnovak.ui.simulation.SimulationUserInput;

public final class ConfigurationFactory {
  public ConfigurationFactory() {}

  public HabitatConfiguration createHabitatConfiguration(SimulationUserInput simulationUserInput) {
    HabitatConfiguration habitatConfiguration = null;

    switch (simulationUserInput.getHabitatType()) {
      case SAVANNAH -> habitatConfiguration = createSavannahConfiguration(simulationUserInput);
    }

    return habitatConfiguration;
  }

  private SavannahConfiguration createSavannahConfiguration(SimulationUserInput simulationUserInput) {
    return new SavannahConfiguration(simulationUserInput.getVegetationType(), simulationUserInput.getVegetationSpecies());
  }
}
