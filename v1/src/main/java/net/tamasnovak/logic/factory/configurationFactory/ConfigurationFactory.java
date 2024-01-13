package net.tamasnovak.logic.factory.configurationFactory;

import net.tamasnovak.logic.habitat.HabitatConfiguration;
import net.tamasnovak.model.nature.vegetation.VegetationSpecies;
import net.tamasnovak.model.nature.vegetation.VegetationType;
import net.tamasnovak.ui.simulation.SimulationUserInput;

public final class ConfigurationFactory {
  public ConfigurationFactory() {}

  public HabitatConfiguration createHabitatConfiguration(SimulationUserInput simulationUserInput) {
    HabitatConfiguration habitatConfiguration = null;

    switch (simulationUserInput.getHabitatType()) {
      case SAVANNAH -> habitatConfiguration = createSavannahConfiguration(simulationUserInput.getVegetationType(), simulationUserInput.getVegetationSpecies());
    }

    return habitatConfiguration;
  }

  private SavannahConfiguration createSavannahConfiguration(VegetationType vegetationType, VegetationSpecies vegetationSpecies) {
    return new SavannahConfiguration(vegetationType, vegetationSpecies);
  }
}
