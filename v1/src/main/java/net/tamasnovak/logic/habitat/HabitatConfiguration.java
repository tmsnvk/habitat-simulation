package net.tamasnovak.logic.habitat;

import net.tamasnovak.model.nature.animal.AnimalSpecies;
import net.tamasnovak.model.nature.vegetation.VegetationSpecies;
import net.tamasnovak.model.nature.vegetation.VegetationType;

public abstract class HabitatConfiguration {
  public final int LENGTH_OF_SIMULATION_YEARS = 100;
  public final int NUMBER_OF_ANIMALS = 200;
  private final AnimalSpecies herbivore;
  private final AnimalSpecies carnivore;
  private final VegetationType vegetationType;
  private final VegetationSpecies vegetationSpecies;

  public HabitatConfiguration(AnimalSpecies herbivore, AnimalSpecies carnivore, VegetationType vegetationType, VegetationSpecies vegetationSpecies) {
    this.herbivore = herbivore;
    this.carnivore = carnivore;
    this.vegetationType = vegetationType;
    this.vegetationSpecies = vegetationSpecies;
  }

  public AnimalSpecies getHerbivore() {
    return herbivore;
  }

  public AnimalSpecies getCarnivore() {
    return carnivore;
  }

  public VegetationType getVegetationType() {
    return vegetationType;
  }

  public VegetationSpecies getVegetationSpecies() {
    return vegetationSpecies;
  }
}
