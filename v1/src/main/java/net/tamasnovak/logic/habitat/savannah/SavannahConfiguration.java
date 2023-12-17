package net.tamasnovak.logic.habitat.savannah;

import net.tamasnovak.logic.habitat.HabitatConfiguration;
import net.tamasnovak.model.animal.AnimalSpecies;

public final class SavannahConfiguration extends HabitatConfiguration {
  public final double CHANCE_OF_HERBIVORE = 0.65;
  public final AnimalSpecies HERBIVORE = AnimalSpecies.ZEBRA;
  public final AnimalSpecies CARNIVORE = AnimalSpecies.LEOPARD;

  public SavannahConfiguration() {}
}
