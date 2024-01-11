package net.tamasnovak.logic.factory.vegetationFactory;

import net.tamasnovak.model.matrix.Position;
import net.tamasnovak.model.nature.vegetation.Vegetation;
import net.tamasnovak.model.nature.vegetation.VegetationSpecies;
import net.tamasnovak.model.nature.vegetation.VegetationType;

public final class VegetationFactory {
  private final GrassFactory grassFactory;

  public VegetationFactory(GrassFactory grassFactory) {
    this.grassFactory = grassFactory;
  }

  public Vegetation createVegetation(VegetationType type, VegetationSpecies species, Position position) {
    Vegetation vegetation = null;

    switch (type) {
      case GRASS -> vegetation = grassFactory.createGrass(species, position);
    }

    return vegetation;
  }
}
