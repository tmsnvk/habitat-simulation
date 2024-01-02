package net.tamasnovak.logic.habitat.savannah;

import net.tamasnovak.logic.habitat.HabitatConfiguration;
import net.tamasnovak.model.nature.animal.AnimalSpecies;
import net.tamasnovak.model.nature.vegetation.VegetationSpecies;
import net.tamasnovak.model.nature.vegetation.VegetationType;

public final class SavannahConfiguration extends HabitatConfiguration {
  public final double CHANCE_OF_HERBIVORE = 0.65;
  private static final AnimalSpecies HERBIVORE = AnimalSpecies.ZEBRA;
  private static final AnimalSpecies CARNIVORE = AnimalSpecies.LEOPARD;
  private static final VegetationType VEGETATION_TYPE = VegetationType.GRASS;
  private static final VegetationSpecies VEGETATION_SPECIES = VegetationSpecies.FINGER_GRASS;

  public SavannahConfiguration() {
    super(HERBIVORE, CARNIVORE, VEGETATION_TYPE, VEGETATION_SPECIES);
  }
}
