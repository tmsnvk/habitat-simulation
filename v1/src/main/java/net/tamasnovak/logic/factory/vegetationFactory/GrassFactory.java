package net.tamasnovak.logic.factory.vegetationFactory;

import net.tamasnovak.model.matrix.Position;
import net.tamasnovak.model.nature.vegetation.Vegetation;
import net.tamasnovak.model.nature.vegetation.VegetationSpecies;
import net.tamasnovak.model.nature.vegetation.grass.FingerGrass;

public final class GrassFactory {
  Vegetation createGrass(VegetationSpecies species, Position position) {
    Vegetation vegetation = null;

    switch (species) {
      case FINGER_GRASS -> vegetation = createFingerGrass(position);
    }

    return vegetation;
  }

  private FingerGrass createFingerGrass(Position position) {
    return new FingerGrass(position);
  }
}
