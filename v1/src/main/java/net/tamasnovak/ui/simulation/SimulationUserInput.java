package net.tamasnovak.ui.simulation;

import net.tamasnovak.logic.habitat.HabitatType;
import net.tamasnovak.model.nature.vegetation.VegetationSpecies;
import net.tamasnovak.model.nature.vegetation.VegetationType;

public final class SimulationUserInput {
  private HabitatType habitatType;
  private VegetationType vegetationType;
  private VegetationSpecies vegetationSpecies;

  SimulationUserInput() {
    this.habitatType = null;
    this.vegetationType = null;
    this.vegetationSpecies = null;
  }

  public HabitatType getHabitatType() {
    return habitatType;
  }

  void setHabitatType(HabitatType habitatType) {
    this.habitatType = habitatType;
  }

  public VegetationType getVegetationType() {
    return vegetationType;
  }

  void setVegetationType(VegetationType vegetationType) {
    this.vegetationType = vegetationType;
  }

  public VegetationSpecies getVegetationSpecies() {
    return vegetationSpecies;
  }

  void setVegetationSpecies(VegetationSpecies vegetationSpecies) {
    this.vegetationSpecies = vegetationSpecies;
  }
}
