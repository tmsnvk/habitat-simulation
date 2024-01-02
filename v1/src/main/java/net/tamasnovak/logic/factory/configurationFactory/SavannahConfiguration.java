package net.tamasnovak.logic.factory.configurationFactory;

import net.tamasnovak.logic.habitat.HabitatConfiguration;
import net.tamasnovak.model.nature.animal.AnimalSpecies;
import net.tamasnovak.model.nature.vegetation.VegetationSpecies;
import net.tamasnovak.model.nature.vegetation.VegetationType;

public final class SavannahConfiguration extends HabitatConfiguration {
  private static final AnimalSpecies HERBIVORE = AnimalSpecies.ZEBRA;
  private static final AnimalSpecies CARNIVORE = AnimalSpecies.LEOPARD;

  SavannahConfiguration(VegetationType vegetationType, VegetationSpecies vegetationSpecies) {
    super(HERBIVORE, CARNIVORE, vegetationType, vegetationSpecies);
  }
}
