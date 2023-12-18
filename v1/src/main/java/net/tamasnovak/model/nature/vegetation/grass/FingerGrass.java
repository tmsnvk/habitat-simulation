package net.tamasnovak.model.nature.vegetation.grass;

import net.tamasnovak.model.matrix.Cell;
import net.tamasnovak.model.nature.vegetation.Vegetation;
import net.tamasnovak.model.nature.vegetation.VegetationSpecies;
import net.tamasnovak.model.nature.vegetation.VegetationType;

public final class FingerGrass extends Vegetation {
  private static final String ICON = "\uD83C\uDF3E";
  private static int idCounter = 0;
  private static final VegetationSpecies SPECIES = VegetationSpecies.FINGER_GRASS;
  private static final VegetationType TYPE = VegetationType.GRASS;

  public FingerGrass(Cell habitatCoordinates) {
    super(createId(), ICON, habitatCoordinates, TYPE, SPECIES);
  }

  private static String createId() {
    return String.format("%s-%s", SPECIES, ++idCounter);
  }

  @Override
  public String toString() {
    return String.format("[Id]: %s | [Species]: %s | [Type]: %s | [Coordinates]: %s", id, species, type, coordinates);
  }
}
