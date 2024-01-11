package net.tamasnovak.model.nature.vegetation;

import net.tamasnovak.model.matrix.Position;
import net.tamasnovak.model.nature.Nature;

public abstract class Vegetation extends Nature {
  protected final VegetationType type;
  protected final VegetationSpecies species;

  public Vegetation(String id, String icon, Position position, VegetationType type, VegetationSpecies species) {
    super(id, position, icon);
    this.type = type;
    this.species = species;
  }

  public VegetationType getType() {
    return type;
  }

  public VegetationSpecies getSpecies() {
    return species;
  }
}
