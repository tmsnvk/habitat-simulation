package net.tamasnovak.logic.factory.vegetationFactory;

import net.tamasnovak.model.matrix.Cell;
import net.tamasnovak.model.nature.vegetation.Vegetation;
import net.tamasnovak.model.nature.vegetation.VegetationSpecies;
import net.tamasnovak.model.nature.vegetation.grass.FingerGrass;

public class GrassFactory {
  Vegetation createGrass(VegetationSpecies species, Cell coordinates) {
    Vegetation vegetation = null;

    switch (species) {
      case FINGER_GRASS -> vegetation = createFingerGrass(coordinates);
    }

    return vegetation;
  }

  private FingerGrass createFingerGrass(Cell livingArea) {
    return new FingerGrass(livingArea);
  }
}
