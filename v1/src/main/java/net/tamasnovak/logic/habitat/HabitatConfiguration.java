package net.tamasnovak.logic.habitat;

import net.tamasnovak.model.nature.animal.AnimalSpecies;
import net.tamasnovak.model.nature.vegetation.VegetationSpecies;
import net.tamasnovak.model.nature.vegetation.VegetationType;

public abstract class HabitatConfiguration {
  public final int LENGTH_OF_SIMULATION_YEARS = 1;
  public final int NUMBER_OF_ANIMALS = 200;
  public final double CHANCE_OF_HERBIVORE = 0.65;
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
